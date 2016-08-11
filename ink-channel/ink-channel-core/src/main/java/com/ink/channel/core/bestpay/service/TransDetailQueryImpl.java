package com.ink.channel.core.bestpay.service;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.asile.epro.EPayHttpUtil;
import com.ink.channel.core.bestpay.ObjectJsonUtil;
import com.ink.channel.core.bestpay.RsaUtil;
import com.ink.channel.core.bestpay.request.EPayTransDetailQueryRequestBean;
import com.ink.channel.core.bestpay.response.EPayTransDetailQueryResponseBean;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;

/**
 * 交易列表查询
 *<pre>
 *<b>类描述:</b>(交易明细查询)
 *<b>作者:</b>YKDZ075
 *<b>创建日期:</b>2016年3月14日 下午2:37:32
 *</pre>
 */
@Service("transDetailQuery")
public class TransDetailQueryImpl implements AsilePay2AccountQueryService{
	 private YinkerLogger LOGGER = YinkerLogger.getLogger(TransDetailQueryImpl.class);
	 
	 /**
	 * 交易明细查询
	 * @param queryIn
	 * @return
	 */
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn queryIn) {
		try {
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_ACCOUNT_CODE,"进入翼支付交易明细查询接口");
			EPayTransDetailQueryRequestBean req = new EPayTransDetailQueryRequestBean();
			req.setQueryCondition("extOrderSeq");// extOrderSeq(外部订单号),reqSeq(请求流水号),trsSeq(交易流水号)
			req.setValue(queryIn.getOrderNo());
			req.setTransPlatCode(RsaUtil.props.getString("platCode"));
			EPayTransDetailQueryResponseBean res = this.transDetailQuery(req);
			AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
			if(res==null){
				LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_ACCOUNT_CODE,"翼支付交易明细查询返回结果为null");
				return null;
			}
			//out.setActualAmount(res.getActualAmount());
			//out.setChannelCode(res.getChannelCode());
			//out.setPlatCode(res.getPlatCode());
			//out.setCurrencyCode(res.getCurrencyCode());
			//out.setOrderNo(res.getExtOrderSeq());
			//out.setOrgTranFlow(res.getTrsSeq());
			//out.setFee(res.getFee());
			//out.setPayType(res.getPayType());
			//out.setReqTime(res.getReqTime());
			String stat = res.getStat();
			//处理返回状态码
			if(StringUtils.isNotBlank(stat)){
				if(stat.equals("001")||stat.equals("002")){
					out.setOrderStatus("01");//处理中
				}else if(stat.equals("004")){
					out.setOrderStatus("02");//失败
				}else{
					out.setOrderStatus("00");//成功
				}
			}
			//out.setTradeTime(res.getTradeTime());
			out.setResCode(res.getCode());
			out.setResMsg(res.getMsg());
			LOGGER.info("返回码："+res.getCode()+",返回内容："+res.getMsg()+"，返回内容："+ObjectJsonUtil.objToJson(res));
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_ACCOUNT_CODE,"结束翼支付交易明细查询接口");
			return out;
		} catch (IOException e) {
			LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_ACCOUNT_CODE,"翼支付交易明细查询返回结果异常",e,"");
			e.printStackTrace();
			return null;
		}
	}
	
	public EPayTransDetailQueryResponseBean transDetailQuery(EPayTransDetailQueryRequestBean req){
        String url = RsaUtil.props.getString("fas")+"/service/transDetailQuery";
        return EPayHttpUtil.postJson(url, req, EPayTransDetailQueryResponseBean.class);
	}

}
