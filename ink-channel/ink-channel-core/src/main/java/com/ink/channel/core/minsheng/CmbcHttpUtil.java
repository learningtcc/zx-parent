package com.ink.channel.core.minsheng;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.minsheng.request.CmbcBodyBean;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.utils.Dom4jXmlUtil;
import com.ink.channel.core.utils.HttpClientUtils;

/**
 * 民生电商http请求工具类
 * 
 * @author huohb
 *
 */
public class CmbcHttpUtil {

    private static YinkerLogger logger = YinkerLogger.getLogger(CmbcHttpUtil.class);

    /**
     * 民生电商发送XML方法，返回Bean
     * 
     * @param url
     * @param req
     * @param resClazz
     * @return
     */
    public static CmbcRootBean postXml(String url, CmbcRootBean rootBean, Class<? extends CmbcBodyBean> resClazz,
                    CmbcMacPloy ploy) {
    	Long startTime=0l;
    	Object postResultObj=null;
        String xml = Dom4jXmlUtil.getInstance().convertVoToXml(rootBean);
        // StringBuffer xmlStr = new StringBuffer(xml);
        CmbcRootBean resObj = new CmbcRootBean();
        try {
            // xmlStr.append(ploy.getKey());
            String mac = ploy.getMac(xml.toString());
            if (StringUtils.isEmpty(mac)) {
            	CmbcHeadBean head = new CmbcHeadBean();
            	head.setRespCode("ESC0000007");
            	head.setRespMsg("摘要为空！");
            	resObj.setHead(head);
                return resObj;
            }
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[平台]待签名串[" + xml.toString() + "],MAC[" + mac + "]");
            // 封装POST请求参数
            Map<String, Object> postParam = new HashMap<String, Object>();
            postParam.put("xml", xml);
            postParam.put("mac", mac);
            // 请求民生实名认证接口
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[民生]请求参数信息:[" + postParam + "]");
            startTime = System.currentTimeMillis();
            postResultObj = HttpClientUtils.execute(url, postParam);
        } catch (ConnectTimeoutException e) {
            CmbcHeadBean head = new CmbcHeadBean();
            head.setRespMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
            head.setRespCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.REQUEST_EXCEPTION_MSG, e);
            resObj.setHead(head);
            return resObj;
        } catch (SocketTimeoutException e) {
            CmbcHeadBean head = new CmbcHeadBean();
            head.setRespMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
            head.setRespCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.RESPONSE_EXCEPTION_MSG, e);
            resObj.setHead(head);
            return resObj;
        } catch (Exception e) {
            CmbcHeadBean head = new CmbcHeadBean();
            head.setRespMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
            head.setRespCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "民生渠道错误：" + e.getMessage(), e);
            resObj.setHead(head);
            return resObj;
        }
        try{
            logger.info(ChannelConstants.LOGGER_MODULE_NAME,
                            "[民生]响应信息:[" + postResultObj + "]耗时" + (System.currentTimeMillis() - startTime));
            // 解析民生返回报文&入库数据&将对应的MSG返回给客户端
            String postResult = postResultObj.toString();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[平台]开始验签");
            String[] xmlArray = StringUtils.substringsBetween(postResult, "xml=", "&mac=");
            String respXml = xmlArray[0];
            String respChkRemark = ploy.getMac(respXml.toString().trim());
            String xmlMac = StringUtils.substringAfter(postResult, "&mac=");
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[民生]响应摘要[" + xmlMac + "]");
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[平台]平台验证摘要[" + respChkRemark + "]");
            Document doc = DocumentHelper.parseText(respXml);
            Dom4jXmlUtil.getInstance().convertElementToBean(doc.getRootElement(), resObj);
            CmbcBodyBean t = resClazz.newInstance();
            if (!respChkRemark.equals(xmlMac)) {
                CmbcHeadBean head = new CmbcHeadBean();
            	head.setRespCode(LdysConstant.decodeCode);
            	head.setRespMsg("民生响应信息解码失败");
            	resObj.setHead(head);
                return resObj;
            }
            // 只有返回码正常才解析body
            if ("C000000000".equals(resObj.getHead().getRespCode())) {
                // 解析出BodyElement，单独生成实例，因为rootBean中定义的body节点为CmbcBodyBean，不知道具体实现子类
                Element bodyElement = doc.getRootElement().element("body");
                Dom4jXmlUtil.getInstance().convertElementToBean(bodyElement, t);
            }
            resObj.setBody(t);
            return resObj;
        }catch (Exception e) {
        	CmbcHeadBean head = new CmbcHeadBean();
        	head.setRespCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
        	head.setRespMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
        	resObj.setHead(head);
            return resObj;
        }
    }

}
