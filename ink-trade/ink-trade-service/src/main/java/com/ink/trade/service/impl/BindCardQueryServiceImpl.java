package com.ink.trade.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.dateUtil.DateFormat;
import com.ink.trade.api.model.BindCard;
import com.ink.trade.api.model.in.BindCardQueryInput;
import com.ink.trade.api.model.out.BindCardQueryOutput;
import com.ink.trade.api.service.IBindCardQueryService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.IsDelete;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.query.AuthQuery;
/**
 * 
 *<pre>
 *<b>类描述:</b>(绑卡列表查询)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月6日 下午2:47:03
 *</pre>
 */
@Service("bindCardQueryService")
public class BindCardQueryServiceImpl implements IBindCardQueryService {
    private YinkerLogger LOGGER = YinkerLogger.getLogger(BindCardQueryServiceImpl.class);
    @Autowired
    private IAuthManager authManager;

    @Override
    public BindCardQueryOutput bindCardQuery(BindCardQueryInput bindCardInput) {
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUERY, TradeLogConstant.LOGGER_BIZ_BINDCARDQUERY, "开始绑卡列表查询");
        BindCardQueryOutput output = new BindCardQueryOutput();
        AuthQuery query = new AuthQuery();
        query.setUserId(bindCardInput.getUserId());
        query.setMchId(bindCardInput.getMerchantId());
        query.setPayType(bindCardInput.getPayType());
        query.setIsDelete(IsDelete.NO.getValue());
        query.setMasterMark(true);// 查主库
        List<Auth> list = authManager.find(query);
        if (CollectionUtils.isEmpty(list)) {
            LOGGER.info("用户号："+bindCardInput.getUserId()+"商户号："+bindCardInput.getMerchantId()+" "+"该用户还未绑卡！");
            output.setReponseCode(TradeRespConstant.NO_BIND_LIST);
            output.setReponseMsg(TradeRespConstant.NO_BIND_LIST_MSG);
            return output;
        }
        //根据最后更新时间排序
        Collections.sort(list,Collections.reverseOrder());
        List<BindCard> bindCardList = new ArrayList<BindCard>();
        for (Auth auth : list) {
            BindCard bindCard = new BindCard();
            bindCard.setPhone(auth.getPhoneNo());
            bindCard.setBankCode(auth.getBankShort());
            bindCard.setBankName(auth.getBankName());
            bindCard.setCardNo(auth.getCardNo());
            bindCardList.add(bindCard);
        }
        LOGGER.debug("绑卡列表"+bindCardList.toString());
        output.setMerchantId(bindCardInput.getMerchantId());
        output.setTradeDate(DateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
        output.setUserId(bindCardInput.getUserId());
        output.setVersion("1.0");
        output.setBindCardList(bindCardList);
        output.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
        output.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUERY, TradeLogConstant.LOGGER_BIZ_BINDCARDQUERY,"查询绑卡列表完成！"+output.toString());
        return output;
    }

}
