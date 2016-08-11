package com.ink.trade.controller.callbacks;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.ldyspay.LdysNotifyOperateUtil;
import com.ink.trade.service.PreCollectionCallBackService;
import com.ink.trade.service.dto.PreCollectionCallBackDto;

/**
 * 联动优势回调处理控制器
 * Created by huohb on 2016/7/6.
 */
@Controller
@RequestMapping("ldys/callback")
public class LdysNotifyOperateController {
    private YinkerLogger logger = YinkerLogger.getLogger(LdysNotifyOperateController.class);
    // 充值回调
    @Autowired
    private PreCollectionCallBackService preCollectionCallBackService;

    /**
     * 联动优势快捷回调
     *
     * @return
     */
    @RequestMapping("/quickpay")
    public void qucickPayNotify(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入联动优势支付回调接口");
        try {
            Map<String,String[]> paraMap = request.getParameterMap();
            Map<String, String> ht = new HashMap<String, String>();
            // 解析参数Map
            Iterator<Map.Entry<String, String[]>> iterator = paraMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String[]> entry = iterator.next();
                ht.put(entry.getKey(),entry.getValue()[0]);
            }

            // 解密+验签
            Map<String, String> returnMap = LdysNotifyOperateUtil.decrpt(ht, ht.get("mer_id"));
            String exceptionCode = returnMap.get("exceptionCode");
            if (LdysConstant.decodeCode.equals(exceptionCode)) {
                logger.error("联动优势支付回调解密验签失败");
                responseErrorMsg(response);
                return;
            } else {
                String status = returnMap.get("trade_state");// 订单状态
                String orderId = returnMap.get("order_id");// 订单号
                PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
                logger.info("联动优势回调参数:orderNo[" + orderId + "],status[" + status + "]");
                dto.setOrderNo(orderId);
                if ("TRADE_SUCCESS".equals(status)) {
                    logger.info("联动优势回调订单[" + orderId + "]订单状态为成功，开始记账");
                    // 成功
                    dto.setOrderStatus("01");
                    preCollectionCallBackService.callBack(dto);
                } else if ("TRADE_FAIL".equals(status) || "TRADE_CLOSED".equals(status)) {
                    logger.info("联动优势回调订单[" + orderId + "]订单状态为失败，开始记账");
                    // 失败
                    dto.setOrderStatus("03");
                    preCollectionCallBackService.callBack(dto);
                } else {
                    //do nothing
                }
                //响应通道
                responseChannel(returnMap, response);
            }
        } catch (Exception e) {
            logger.error("联动优势回调异常", e);
        }

    }

    /**
     * 响应渠道
     *
     * @param reqData
     * @param response
     */
    private void responseChannel(Map<String, String> reqData, HttpServletResponse response) {
        try {
            Map<String, String> resData = new HashMap<String, String>();
            resData.put("ret_code", "0000");
            resData.put("mer_id", reqData.get("mer_id"));
            resData.put("sign_type", reqData.get("sign_type"));
            resData.put("version", reqData.get("version"));
            resData.put("ret_msg", "success");

            // 加密
            String responseData = LdysNotifyOperateUtil.encrpt(resData);
            logger.info("证书中心加密返回渠道信息："+responseData);
            PrintWriter out = response.getWriter();
            out.write(responseData);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("联动优势回调，响应渠道异常", e);
        }
    }

    private void responseErrorMsg(HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            out.write("<html><head>联动优势回调</head><body><h1>请求参数异常</h1></body></html>");
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("联动优势回调获取输出流异常", e);
        }
    }
}