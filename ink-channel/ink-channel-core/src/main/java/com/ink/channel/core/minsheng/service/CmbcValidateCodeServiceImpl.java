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
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.request.CmbcValidateCodeReqBean;
import com.ink.channel.core.minsheng.response.CmbcValidateCodeResBean;
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.service.AsileValidateCodeService;
import com.ink.channel.core.utils.Constants;

/**
 * 民生发送短信验证
 * 
 * @author ZYC7-DZ-057
 *
 */
@Service("cmbcValidateCodeService")
public class CmbcValidateCodeServiceImpl implements AsileValidateCodeService {
    private YinkerLogger logger = YinkerLogger.getLogger(CmbcValidateCodeServiceImpl.class);
    @Autowired
    private IdCodeGenerator idCodeGenerator;
    @Autowired
    private DataSecurityClient dataSecurityClient;
    @Autowired
    private IdCodeGenerator certificateUtil;

    /*
     * private static String[][] SUPPORT_BANK_LIST = {{ "中国民生银行", "CMBC" }, { "广东发展银行", "GDB" },{ "中国工商银行", "ICBC" }, {
     * "中国农业银行", "ABC" }, { "中国银行", "BOC" }, { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" }, { "招商银行", "CMB" },{ "中国光大银行",
     * "CEB" }, { "兴业银行", "CIB" }, { "平安银行", "PAB" }};
     */

    @Override
    public AsileValidCodeOutput getValidateCode(AsileValidCodeInput codeInput) {
        final String merchantId=codeInput.getMerchantNo();
        String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode=certificateUtil.getIpMapsConfig().get(codeInput.getMerchantNo()+ASILE_NO+CmbcConstants.validateCodeCert);
        final String collectionUrl=certificateUtil.getIpMapsConfig().get(codeInput.getMerchantNo()+ASILE_NO+CmbcConstants.validateCodeURL);
        final String merchantNo=certificateUtil.getIpMapsConfig().get(codeInput.getMerchantNo()+ASILE_NO+CmbcConstants.merchantNoRechargeAndCash);
        AsileValidCodeOutput out = new AsileValidCodeOutput();
        try {
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_VALIDATE_CODE, "开始调用民生鉴权发送短信");
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            DateFormat dft = new SimpleDateFormat("HHmmss");
            CmbcRootBean rootBean = new CmbcRootBean();
            CmbcHeadBean headBean = new CmbcHeadBean();
            headBean.setVersion(CmbcConstants.VERSION);
            headBean.setMsgtype(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);
            headBean.setChannelno(CmbcConstants.CHANNEL_NO);
            headBean.setMerchantno(merchantNo);
            Date date = new Date();
            headBean.setTrandate(df.format(date));
            headBean.setTrantime(dft.format(date));
            /** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
            String tranFlow = merchantNo + idCodeGenerator.getId();
            headBean.setBussflowno(tranFlow);
            headBean.setTrancode("CP0028");
            CmbcValidateCodeReqBean bodyBean = new CmbcValidateCodeReqBean();
            String bankName = null;
            for (int i = 0; i < Constants.CMBC_BANK_NAME_LIST.length; i++) {
                if (Constants.CMBC_BANK_NAME_LIST[i][1].equals(codeInput.getBankShort())) {
                    bankName = Constants.CMBC_BANK_NAME_LIST[i][0];
                }
            }
            bodyBean.setBankName(bankName);
            bodyBean.setAccountNo(codeInput.getCardNo());
            bodyBean.setAccountName(codeInput.getUserName());
            bodyBean.setTranAmt("1");
            bodyBean.setCertType(codeInput.getIdType());
            bodyBean.setCertNo(codeInput.getIdNo());
            bodyBean.setMobileNo(codeInput.getPhoneNo());
            rootBean.setHead(headBean);
            rootBean.setBody(bodyBean);
            CmbcRootBean resBean = CmbcHttpUtil.postXml(collectionUrl, rootBean,
                            CmbcValidateCodeResBean.class, new CmbcMacPloy() {

                                @Override
                                public String getMac(String xml) {
                                    String mac = "";
                                    MsgOutput out = dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                                    if (ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                                        mac = out.getMessage().toString();
                                    return mac;
                                }

                            });
            CmbcValidateCodeResBean authBodyBean = (CmbcValidateCodeResBean) resBean.getBody();

            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_VALIDATE_CODE, "****银行名称****:"
                            + authBodyBean.getCustId());
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_VALIDATE_CODE, "****是否确认提交****:"
                            + authBodyBean.getMerOrderId());
            out.setResCode(resBean.getHead().getRespCode());
            out.setResMsg(resBean.getHead().getRespMsg());
            out.setReqId(authBodyBean.getMerOrderId());
            out.setToken(authBodyBean.getPhoneToken());
            out.setIdentityId(authBodyBean.getCustId());
            /*
             * try { baseRedis.setCache(codeInput.getReqId()+"Token", authBodyBean.getPhoneToken(), 300);
             * baseRedis.setCache(codeInput.getReqId()+"OrderId", authBodyBean.getMerOrderId(), 300);
             * baseRedis.setCache(codeInput.getReqId()+"CustId", authBodyBean.getCustId(), 300); } catch (Exception e) {
             * e.printStackTrace();
             * logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_PAY_VALIDATE_CODE
             * ,"validCode put cache into Redis error,requestId "+authBodyBean.getPhoneToken(),""); throw new
             * RuntimeException("put cache from Redis error"); }
             */
            return out;
        } catch (Exception ex) {
        	out.setResCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_VALIDATE_CODE, ex.getMessage(),ex, null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_VALIDATE_CODE, "结束调用民生鉴权发送短信");
        return out;
    }

}
