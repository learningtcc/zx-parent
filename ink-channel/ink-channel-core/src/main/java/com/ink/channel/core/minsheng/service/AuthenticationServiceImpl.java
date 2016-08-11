package com.ink.channel.core.minsheng.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.channel.core.minsheng.CmbcHttpUtil;
import com.ink.channel.core.minsheng.CmbcMacPloy;
import com.ink.channel.core.minsheng.request.CmbcAuthReqBean;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcAuthResBean;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;
import com.ink.channel.core.service.AsileAuthorityService;
import com.ink.channel.core.utils.Constants;

/**
 * 民生鉴权接口
 * 
 * @author Lenovo
 *
 */
@Service("cmbcAuthenticationService")
public class AuthenticationServiceImpl implements AsileAuthorityService {

    private YinkerLogger logger = YinkerLogger.getLogger(AuthenticationServiceImpl.class);
    @Autowired
    private IdCodeGenerator idCodeGenerator;
    @Autowired
    private DataSecurityClient dataSecurityClient;
    @Autowired
    private IdCodeGenerator certificateUtil;

    /**
     * 代扣支持的银行列表
     */
    /*
     * private static String[][] SUPPORT_BANK_LIST = {{ "中国民生银行", "CMBC" }, { "广东发展银行", "GDB" },{ "中国工商银行", "ICBC" }, {
     * "中国农业银行", "ABC" }, { "中国银行", "BOC" }, { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" }, { "招商银行", "CMB" },{ "中国光大银行",
     * "CEB" }, { "兴业银行", "CIB" }, { "平安银行", "PAB" }};
     */

    @Override
    public AsileAuthorityOutput authorize(AsileAuthorityInput authority) {
        final String merchantId=authority.getMerchantNo();
        String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode=certificateUtil.getIpMapsConfig().get(authority.getMerchantNo()+ASILE_NO+CmbcConstants.authCert);
        final String collectionUrl=certificateUtil.getIpMapsConfig().get(authority.getMerchantNo()+ASILE_NO+CmbcConstants.authURL);
        final String merchantNo=certificateUtil.getIpMapsConfig().get(authority.getMerchantNo()+ASILE_NO+CmbcConstants.merchantNoRechargeAndCash);
        AsileAuthorityOutput authorityOut = new AsileAuthorityOutput();
        try {
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_FAS_SING_CODE,
                            "开始调用民生安保银行卡实名认证接口");
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            DateFormat dft = new SimpleDateFormat("HHmmss");
            CmbcRootBean rootBean = new CmbcRootBean();
            CmbcHeadBean headBean = new CmbcHeadBean();
            CmbcAuthReqBean bodyBean = new CmbcAuthReqBean();
            headBean.setVersion(CmbcConstants.VERSION);
            headBean.setMsgtype(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);
            headBean.setChannelno(CmbcConstants.CHANNEL_NO);
            headBean.setMerchantno(merchantNo);
            Date date = new Date();
            headBean.setTrandate(df.format(date));
            headBean.setTrantime(dft.format(date));
            /** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
            // String tranFlow = CmbcPayInfo.merchantNoRechargeAndCash + authority.getReqId();
            // int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
            String tranFlow = merchantNo + idCodeGenerator.getId();
            headBean.setBussflowno(tranFlow);
            headBean.setTrancode("CP0029");
            String code = "";
            for (int i = 0; i < Constants.CMBC_BANK_NAME_LIST.length; i++) {
                if (authority.getBankShort().toString().trim().contains(Constants.CMBC_BANK_NAME_LIST[i][1])) {
                    code = Constants.CMBC_BANK_NAME_LIST[i][0];
                }
            }
            bodyBean.setBankName(code);// 银行码
            bodyBean.setAccountNo(authority.getCardNo());// 银行卡号
            bodyBean.setAccountName(authority.getUserName());// 账户名称
            bodyBean.setTranAmt("1");// 交易金额
            bodyBean.setCertType(authority.getIdType());// 证件类型
            bodyBean.setCertNo(authority.getIdNo());// 身份证号
            bodyBean.setMobileNo(authority.getPhoneNo());// 手机号码
            // 在缓存中取出 订单号 token 和用户号
            String token = authority.getToken();
            String OrderId = authority.getReqId();
            String custmerId = authority.getIdentityId();
            bodyBean.setCustId(custmerId);
            bodyBean.setMerOrderId(OrderId);
            bodyBean.setPhoneVerCode(authority.getValidCode());// 验证码
            bodyBean.setPhoneToken(token);
            rootBean.setHead(headBean);
            rootBean.setBody(bodyBean);
            CmbcRootBean resBean = CmbcHttpUtil.postXml(collectionUrl, rootBean, CmbcAuthResBean.class,
                            new CmbcMacPloy() {
                                @Override
                                public String getMac(String xml) {
                                    String mac="";
                                    MsgOutput out= dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                                    if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                                    mac=out.getMessage().toString();
                                    return mac;
                                }

                            });
            // CmbcAuthResBean authBodyBean = (CmbcAuthResBean)resBean.getBody();
            authorityOut.setResCode(resBean.getHead().getRespCode());
            authorityOut.setResMsg(resBean.getHead().getRespMsg());
            authorityOut.setIdentityId(custmerId);
            return authorityOut;
        } catch (Exception ex) {
        	authorityOut.setResCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
        	authorityOut.setResMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_FAS_SING_CODE, ex.getMessage(),ex, null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_FAS_SING_CODE, "结束调用民生安保银行卡实名认证接口");
        return authorityOut;
    }

}