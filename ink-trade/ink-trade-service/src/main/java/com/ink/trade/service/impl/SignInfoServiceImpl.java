package com.ink.trade.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.model.in.AuthenBindCardInput;
import com.ink.channel.api.model.out.AuthenBindCardOutput;
import com.ink.channel.api.service.AuthenBindCardService;
import com.ink.trade.api.constants.TradeConstants;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.ISignInfoService;
import com.ink.trade.service.dto.QuerySignInfoDto;

/**
 * 用户签约信息服务实现类
 * Created by huohb on 2016/5/6.
 */
@Service("signInfoService")
public class SignInfoServiceImpl implements ISignInfoService {

    private static final YinkerLogger logger = YinkerLogger.getLogger(SignInfoServiceImpl.class);
    @Autowired
    private IAuthManager authManager;// 绑卡关系Manager
    @Autowired
    private IAsileSignManager asileSignManager;// 签约关系Manager
    @Autowired
    private IAsileResCodeManager asileResCodeManager;// 平台返回码与渠道返回码转换Manager
    @Autowired
    private AuthenBindCardService authenBindCardService;// 签约服务(dubbo)
    @Autowired
    private IdCodeGenerator idCodeGenerator;//发号器
    @Override
    public String getSignIdByUserIdAndCardNo(QuerySignInfoDto dto) {
        // 先查询绑卡列表
        AuthQuery authParam = new AuthQuery();
        authParam.setMchId(dto.getMerNo());
        authParam.setUserId(dto.getUserId());
        authParam.setCardNo(dto.getCardNo());
        authParam.setPayType(PayType.ALL.getValue());
        authParam.setMasterMark(true);// 查主库
        List<Auth> authList = authManager.find(authParam);
        if (CollectionUtils.isEmpty(authList)) {
            logger.error("用户" + dto.getUserId() + "未绑卡");
            throw new RuntimeException("user " + dto.getUserId() + " not yet bound card");
        }
        Auth auth=authList.get(0);
        // 根据绑卡列表主键查询签约信息表
        AsileSign param = new AsileSign();
        param.setCid(auth.getId());// 绑卡表主键
        param.setChanelNo(dto.getChannelId());// 渠道号
        param.setPayType(dto.getPayType());
        String signId = null;
        AsileSign asileSign = asileSignManager.selectSignIdByChannel(param);
        if (asileSign == null) {
            logger.debug("用户" + dto.getUserId() + "未签约渠道" + dto.getChannelId() + ",进行签约...");
            // 签约
            signId = sign(dto);
            // 保存签约信息
            saveSignInfo(dto, signId,auth.getId());
        } else {
            signId = asileSign.getSignId();
        }
        return signId;
    }

    /**
     * 签约
     * @param dto
     * @return
     */
    private String sign(QuerySignInfoDto dto) {
        AuthenBindCardInput input = new AuthenBindCardInput();
        input.setAccountNo(dto.getCardNo());
        input.setBankShort(dto.getBankShort());
        input.setCertNo(dto.getIdNo());
        input.setAccountName(dto.getRealName());
        input.setCertType(dto.getIdType());
        input.setChannelId(dto.getChannelId());
        input.setOrderNo(idCodeGenerator.getId());
        input.setPhoneNo(dto.getPhoneNo());
        input.setMerchantNo(dto.getMerNo());
        AuthenBindCardOutput output = authenBindCardService.bindCard(input);

        // 将渠道返回码转换为平台转换码
        AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(dto.getChannelId(), output.getResCode());

        if (asileResCode == null) {
            logger.error("用户"+dto.getUserId()+"进行签约，渠道返回码未找到与平台的关联关系，转换为默认的错误码");
            asileResCode = new AsileResCode();
            asileResCode.setResCode(TradeConstants.PLAT_RES_ERR_CODE);
            asileResCode.setResMsg("签约失败");
        }
        if (!TradeConstants.PLAT_RES_SUCCESS_CODE.equals(asileResCode.getResCode())) {
            if (TradeConstants.PLAT_RES_UNKOWN_CODE.equals(asileResCode.getResCode())) {
                // 超时，暂时先不处理
            }
            throw new RuntimeException("sign failed");
        }
        logger.info("用户" + dto.getUserId() + "签约渠道" + dto.getChannelId() + "返回的签约号为" + (output == null ? "" : output.getIdentityid()));

        return output.getIdentityid();

    }

    /**
     * 保存签约信息
     *
     * @param dto
     * @param signId
     * @param cid
     */
    private void saveSignInfo(QuerySignInfoDto dto, String signId,Long cid) {
        try {
            AsileSign asileSign = new AsileSign();
            asileSign.setCid(cid);
            asileSign.setChanelNo(dto.getChannelId());
            asileSign.setSignId(signId);
            asileSign.setStatus(1);
            asileSign.setVersion(1);
            asileSign.setIsDelete(0);
            asileSign.setRemark(null);
            asileSign.setPayType(dto.getPayType());
            Date sysdate = new Date();
            asileSign.setCreateTime(sysdate);
            asileSign.setLastupdateTime(sysdate);
            asileSign.setCardNo(dto.getCardNo());
            asileSign.setUserName(dto.getRealName());
            asileSign.setIdNo(dto.getIdNo());
            asileSign.setIdType(dto.getIdType());
            asileSign.setPhone(dto.getPhoneNo());
            asileSign.setBankShort(dto.getBankShort());
            asileSign.setUserId(dto.getUserId());
            asileSign.setMchId(dto.getMerNo());
            asileSign.setCardType(dto.getCardType());
            asileSignManager.save(asileSign);
        } catch (Exception e) {
            logger.error("用户" + dto.getUserId() + "保存签约信息失败",e);
        }
    }
}
