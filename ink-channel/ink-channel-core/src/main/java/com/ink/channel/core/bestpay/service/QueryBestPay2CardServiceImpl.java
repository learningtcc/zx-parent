package com.ink.channel.core.bestpay.service;

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
 * 翼支付查询代付
 * @author Lenovo
 *
 */
@Service("queryBestPay2CardServiceImpl")
public class QueryBestPay2CardServiceImpl implements AsilePay2AccountQueryService{
	 private YinkerLogger LOGGER = YinkerLogger.getLogger(QueryBestPay2CardServiceImpl.class);
	 
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
		try {
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_CARD_CODE,"进入翼支付代付查询接口");
			EPayTransDetailQueryRequestBean req = new EPayTransDetailQueryRequestBean();
			if(StringUtils.isNotBlank(input.getOrderNo())){
				req.setQueryCondition("extOrderSeq");// extOrderSeq(外部订单号),reqSeq(请求流水号),trsSeq(交易流水号)
				req.setValue(input.getOrderNo());
			}
			req.setTransPlatCode(RsaUtil.props.getString("platCode"));
			EPayTransDetailQueryResponseBean res = this.transDetailQuery(req);
			AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
			if(res==null){
				return null;
			}
			String stat = res.getStat();
			if(StringUtils.isNotBlank(stat)){
				if(stat.equals("001")||stat.equals("002")){
					out.setOrderStatus("01");//处理中
				}else if(stat.equals("004")){
					out.setOrderStatus("02");//失败
				}else{
					out.setOrderStatus("00");//成功
				}
			}
			out.setResCode(res.getCode());
			out.setResMsg(res.getMsg());
			System.out.println("返回码："+res.getCode()+",返回内容："+res.getMsg()+"，返回内容："+ObjectJsonUtil.objToJson(res));
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_CARD_CODE,"结束翼支付交易明细查询接口");
			return out;
		} catch (Exception e) {
			LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_QUERY_PAY_CARD_CODE,"翼支付交易明细查询返回结果异常",e,"");
			e.printStackTrace();
			return null;
		}
	}
	
	public EPayTransDetailQueryResponseBean transDetailQuery(EPayTransDetailQueryRequestBean req){
        String url = RsaUtil.props.getString("fas")+"/service/transDetailQuery";
        
        return EPayHttpUtil.postJson(url, req, EPayTransDetailQueryResponseBean.class);
	}
}
