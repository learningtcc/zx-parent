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
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.channel.core.minsheng.CmbcHttpUtil;
import com.ink.channel.core.minsheng.CmbcMacPloy;
import com.ink.channel.core.minsheng.request.CmbcCollectionReqBean;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcCollectionResBean;
import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;
import com.ink.channel.core.service.AsilePay2CardService;
import com.ink.channel.core.utils.Constants;

/**
 * 民生代付服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("cmbcPay2CardServiceImpl")
public class CmbcPay2CardServiceImpl implements AsilePay2CardService {
    @Autowired
    private IdCodeGenerator certificateUtil;
    @Autowired
    private DataSecurityClient dataSecurityClient;
    private static YinkerLogger logger = YinkerLogger.getLogger(CmbcPay2CardServiceImpl.class);

    /**
     * 代付
     */
    @Override
    public AsilePay2CardOutput pay(AsilePay2CardInput input) {
        final String merchantId = input.getMerchantNo();
        String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode = certificateUtil.getIpMapsConfig().get(
                        input.getMerchantNo() + ASILE_NO + CmbcConstants.paymentCert);
        final String paymentUrl = certificateUtil.getIpMapsConfig().get(
                        input.getMerchantNo() + ASILE_NO + CmbcConstants.paymentURL);
        final String merchantNo = certificateUtil.getIpMapsConfig().get(
                        input.getMerchantNo() + ASILE_NO + CmbcConstants.merchantNoRechargeAndCash);
        AsilePay2CardOutput out = new AsilePay2CardOutput();
        try {
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,
                            "进入民生代付服务，卡号：" + input.getCardNo());
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
            CmbcRootBean rootBean = new CmbcRootBean();
            CmbcHeadBean headBean = new CmbcHeadBean();
            CmbcCollectionReqBean bodyBean = new CmbcCollectionReqBean();
            headBean.setVersion(CmbcConstants.VERSION);// 信息格式版本号
            headBean.setMsgtype(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);// 报文类型
            headBean.setChannelno(CmbcConstants.CHANNEL_NO);// 渠道代号
            headBean.setMerchantno(merchantNo);// 商户号
            headBean.setTrandate(df.format(new Date()));// 交易日期
            headBean.setTrantime(sdf.format(new Date()));// 交易时间
            // headBean.setClientDate(df.format(new Date()));
            /** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
            // int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
            // SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String tranFlow = input.getOrderNo();
            headBean.setBussflowno(tranFlow);// 交易流水号
            headBean.setTrancode("CP0003");// 交易代码
            bodyBean.setMerPlatAcctAlias("");
            bodyBean.setProtocolNo("");
            String bankName = null;
            for (int i = 0; i < Constants.CMBC_BANK_NAME_LIST.length; i++) {
                if (Constants.CMBC_BANK_NAME_LIST[i][1].equals(input.getBankShort())) {
                    bankName = Constants.CMBC_BANK_NAME_LIST[i][0];
                }
            }
            bodyBean.setBankName(bankName);// 银行名称
            bodyBean.setAccountNo(input.getCardNo());// 银行账号
            bodyBean.setAccountName(input.getUserName());// 银行账户名称
            bodyBean.setAccountType("00");// 账户类型
            bodyBean.setOpenProvince("");// 开户行所在省
            bodyBean.setOpenCity("");// 开户行所在市
            bodyBean.setOpenName("");// 开户行名称
            bodyBean.setTranAmt(input.getAmount().toString());// 交易金额
            bodyBean.setCurType(CmbcConstants.CUE_TYPE);// 币种
            bodyBean.setBsnType("09400");// 业务类型
            bodyBean.setCertType(input.getIdType());// 开户证件类型
            bodyBean.setCertNo(input.getIdNo());// 证件号
            bodyBean.setMobileNo("");// 手机号码
            bodyBean.setProdInfo("");// 商品信息
            bodyBean.setMsgExt("");// 附加信息
            rootBean.setHead(headBean);
            rootBean.setBody(bodyBean);
            long startMillis = System.currentTimeMillis();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,
                            "开始调用代付CmbcHttpUtil.postXml系统时间:" + startMillis);
            CmbcRootBean resBean = CmbcHttpUtil.postXml(paymentUrl, rootBean, CmbcCollectionResBean.class,
                            new CmbcMacPloy() {

                                @Override
                                public String getMac(String xml) {
                                    String mac = "";
                                    MsgOutput out = dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                                    if (ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                                        mac = out.getMessage().toString();
                                    return mac;
                                }

                            });
           /* long endMillis = System.currentTimeMillis();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,
                            "结束调用代付CmbcHttpUtil.postXml系统时间:" + endMillis);
            long diffMillis = endMillis - startMillis;
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,
                            "调用代付CmbcHttpUtil.postXml时间差:" + diffMillis);*/
            out.setResCode(resBean.getHead().getRespCode());
            out.setResMsg(resBean.getHead().getRespMsg());
            out.setOrgTranFlow(tranFlow);
            out.setOrderNo(input.getOrderNo());
            out.setAmount(input.getAmount().toString());
            if("C000000000".equals(resBean.getHead().getRespCode())){
		        CmbcCollectionResBean authBodyBean = (CmbcCollectionResBean)resBean.getBody();
	        	if("01".equals(authBodyBean.getTranState())){
	        		out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
	        	}else if("03".equals(authBodyBean.getTranState())){
	        		out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
	        	}else{
	        		out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        	}
	        }else if(ChannelConstants.RESPONSE_EXCEPTION_CODE.equals(resBean.getHead().getRespCode())){
	        	out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }else if(LdysConstant.decodeCode.equals(resBean.getHead().getRespCode())){
	        	out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }else{
	        	out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
	        }
            return out;
        } catch (Exception ex) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE, ex.getMessage(), ex,null);
            out.setResCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
            out.setResMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
            out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//失败 
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,
                        "民生代付服务执行完毕，卡号：" + input.getCardNo());
        return out;
    }
    /*
     * private static String[][] SUPPORT_BANK_LIST = {{ "中国民生银行", "CMBC" }, { "广东发展银行", "GDB" },{ "中国工商银行", "ICBC" }, {
     * "中国农业银行", "ABC" }, { "中国银行", "BOC" }, { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" }, { "招商银行", "CMB" },{ "中国光大银行",
     * "CEB" }, { "兴业银行", "CIB" }, { "平安银行", "PAB" }};
     */

}
