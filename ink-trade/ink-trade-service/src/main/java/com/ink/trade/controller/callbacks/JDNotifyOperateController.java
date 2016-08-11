package com.ink.trade.controller.callbacks;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.jdPay.util.CallBackUtil;
import com.ink.channel.core.jdPay.util.JDConstant;
import com.ink.channel.core.jdPay.util.JDHttpClientUtils;
import com.ink.channel.core.jdPay.util.XmlMsg;
import com.ink.channel.core.utils.Constants;
import com.ink.trade.service.PreCollectionCallBackService;
import com.ink.trade.service.dto.PreCollectionCallBackDto;

/**
 * Created by huohb on 2016/7/5.
 */
@Controller
@RequestMapping("jd/callback")
public class JDNotifyOperateController {

    private final YinkerLogger logger = YinkerLogger.getLogger(JDNotifyOperateController.class);

    @Autowired
    private IdCodeGenerator certificateUtil;
    @Autowired
    private PreCollectionCallBackService preCollectionCallBackService;

    @ResponseBody
    @RequestMapping("/quickpay")
    public Map<String,Object> quickPayNotify(@RequestParam("resp") String resp) {

        logger.info("京东开始异步回调，回调内容[{}]", resp);
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            // 快捷支付数据先base64解码
            String decodeStr = CallBackUtil.base64Decode(resp);
            logger.info("京东异步回调，Base64解密后内容[{}]", decodeStr);
            // 解析xml中的数据
            Map<String, String> respParams = XmlMsg.parse(decodeStr);
            logger.info("JD快捷支付处理交易结果Base64解码开始，" + "版本号|商户号|终端号|交易数据|数据签名"
                    + respParams.get("chinabank.version") + "|" + respParams.get("chinabank.merchant") + "|"
                    + respParams.get("chinabank.terminal") + "|" + respParams.get("chinabank.data") + "|"
                    + respParams.get("chinabank.sign"));
            // 渠道商户号
            String channelMerNo = respParams.get("chinabank.merchant");
            // 根据渠道商户号获取平台商户号，规则：渠道商户号+渠道号+渠道商户号后缀
            String platMerNo = certificateUtil.getIpMapsConfig().get(channelMerNo + JDConstant.Asile_NO + Constants.CHANNEL_MERCHANT_SUFFIX);

            Map<String, Object> operate = JDHttpClientUtils.operate("resp="+resp, platMerNo);

            String code = (String) operate.get("code");

            String orderId = (String) operate.get("id");
            logger.info("京东异步回调返回码为0000，订单号{}", orderId);
            String stat = (String) operate.get("status");
            PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
            dto.setOrderNo(orderId);
            if (StringUtils.isNotBlank(stat)) {
                if ("6".equals(stat)) {
                    //处理中
                    logger.error("京东异步回调订单" + orderId + "返回状态为处理中，不进行处理");
                } else if ("7".equals(stat)) {
                    callBack(dto, false);
                } else {
                    callBack(dto, true);
                }
                logger.info("京东异步回调订单" + orderId + "回调完成");
            }
            result.put("result","SUCCESS");
        } catch (Exception e) {
            logger.error("解析京东快捷支付回调接口失败", e);
            result.put("result","FAILED");
        }

        return result;
    }

    private void callBack(PreCollectionCallBackDto dto, boolean isSuccess) {
        logger.info("京东异步回调订单状态为" + (isSuccess ? "成功" : "失败") + "，开始回调");
        dto.setOrderStatus(isSuccess ? "01" : "03");
        preCollectionCallBackService.callBack(dto);
    }
}