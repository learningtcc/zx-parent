/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年4月26日 下午4:42:59
 */
package com.ink.trade.service.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.core.cnst.TradeLogConstant;

/**
 * @Description 签约信息同步给账户系统
 * @author xuguoqi
 * @date 2016年4月26日 下午4:42:59
 */
@Component("asileSignToAccProducer")
public class AsileSignToAccProducer {
	
	/**
	 * 日志
	 */
	YinkerLogger log = YinkerLogger.getLogger(AsileSignToAccProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;//mq模版
	
	/**
	 * 
	 * @Description 发送消息
	 * @author xuguoqi
	 * @date 2016年4月26日 下午5:22:31
	 * @param list
	 */
	public void sendMessage(String queenName,Object obj){
		try {
			rabbitTemplate.convertAndSend(queenName, JSON.toJSONString(obj));//发送消息
		} catch (Exception e) {
			log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "绑卡确认发送mq消息异常", e, null);
		}
	}
	
	/**
	 * 
	 * @Description 签约信息封装并发送消息
	 * @author xuguoqi
	 * @date 2016年4月28日 下午1:54:36
	 * @param asileSignList
	 */
//	public void signMsgToAcc(List<AsileSign> asileSignList){
//		//签约信息集合转化为账户签约请求参数集合
//		List<ACC38070Input> inputList = this.getSignInputList(asileSignList);
//		if(CollectionUtils.isNotEmpty(inputList)){
//			for (ACC38070Input input : inputList) {
//				//输入参数转换为map参数
////				Map<String, String> param = this.getMap(input);
//				this.sendMessage(QueenNameConstant.CHECK_SIGN_TO_ACC_QUEEN, JSON.toJSONString(input));
//			}
//		}
//	}
	
	/**
	 * 
	 * @Description 签约信息集合转化为账户签约请求参数集合
	 * @author xuguoqi
	 * @date 2016年4月26日 下午5:41:13
	 * @param asileSignList
	 * @return
	 */
//	private List<ACC38070Input> getSignInputList(List<AsileSign> asileSignList){
//		List<ACC38070Input> inputList = new ArrayList<ACC38070Input>();
//		if(CollectionUtils.isNotEmpty(asileSignList)){
//			for (AsileSign asileSign : asileSignList) {
//				//签约信息转账户绑卡请求参数
//				ACC38070Input input = this.asileSignToAccInput(asileSign);
//				inputList.add(input);
//			}
//		}
//		return inputList;
//	}
	
	/**
	 * 
	 * @Description 签约信息转账户绑卡请求参数
	 * @author xuguoqi
	 * @date 2016年4月26日 下午5:56:52
	 * @param asileSign
	 * @return
	 */
//	private ACC38070Input asileSignToAccInput(AsileSign asileSign){
//		ACC38070Input input = new ACC38070Input();
//		input.setMchId(asileSign.getMchId());
//		input.setCustId(asileSign.getUserId());
//		input.setOrdId(asileSign.getAuthOrderId());
//		input.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSS(asileSign.getCreateTime()));
//		input.setCustName(asileSign.getUserName());
//		input.setIdType(asileSign.getIdType());
//		input.setIdNo(asileSign.getIdNo());
//		input.setCardNo(asileSign.getCardNo());
//		input.setCardType(asileSign.getCardType());
//		return input;
//	}
	
	/**
	 * 
	 * @Description 输入参数转换为map参数
	 * @author xuguoqi
	 * @date 2016年4月26日 下午5:24:46
	 * @param input
	 * @return
	 */
//	private Map<String,String> getMap(ACC38070Input input){
//		Map<String,String> param = new HashMap<String,String>();
//		param.put("txnCode", "ACC38070");
//		param.put("mchId", input.getMchId());
//		param.put("custId", input.getCustId());
//		param.put("ordId", input.getOrdId());
//		param.put("uid", input.getUid()==null?"":input.getUid());
//		param.put("tradeDate", input.getTradeDate());//yyyy-MM-dd HH:mm:ss
//		param.put("custName", input.getCustName());
//		param.put("idType", input.getIdType());
//		param.put("idNo", input.getIdNo());
//		param.put("cardNo", input.getCardNo());
//		param.put("cardType", input.getCardType());
//		//可为空
//		param.put("bankMblNo", input.getBankMblNo()==null?"":input.getBankMblNo());
//		param.put("expDate", input.getExpDate()==null?"":input.getExpDate());
//		param.put("cvn2", input.getCvn2()==null?"":input.getCvn2());
//		param.put("depositType", "APT0000001");
//		return param;
//	}

}
