package com.ink.balance.core.batch.mapper;

import java.math.BigDecimal;
import java.util.Date;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.api.constants.SysParamConst;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class InterestItemJobMapper implements FieldSetMapper<ChannelData> {

    /**
     * @param fieldSet
     * @return ChannelData
     * @Description 【读取民生对账文件】读取每一行数据
     * @author bo.chen
     * @date 2016年4月11日 上午11:10:58
     */
    @Override
    public ChannelData mapFieldSet(FieldSet fieldSet) throws BindException {
        ChannelData bean = new ChannelData();
        try {
			
		
	        /** 渠道编号 */
	        bean.setChannelNo(SysParamConst.CMBC_CHANNEL_NO);
	        /** 商户编号 */
	        String merchantNo = fieldSet.readString(0);
	        
	        if (merchantNo.equals("商户号")) {//随便设置值，在后续处理中为了忽略掉此行
	            bean.setPlatformOrderNo("CB1008610010");
	            return bean;
	        }
	        bean.setMerchantNo(merchantNo);
	        
	        /** 平台订单号 */
	        bean.setPlatformOrderNo(fieldSet.readString(3));

			/** 交易状态 1、待支付 2、支付成功 3、支付失败 */
			int realStatus;
			String transStatus = fieldSet.readString(13);
			if ("交易成功".equals(transStatus)) {
				realStatus = 2;
			} else if("交易失败".equals(transStatus)){
				realStatus = 3;
			} else{
				bean.setRemark("TRANSACTION_PROCESSING");
				return bean;//交易处理中
			}
	
	        /** 订单金额 */
	        bean.setAmt(fieldSet.readBigDecimal(7));
	        
	        /** 实收金额 */
	        bean.setReceivedAmt(fieldSet.readBigDecimal(7));
	        
	        /** 交易时间 */
	        bean.setTransTime(fieldSet.readDate(12, "yyyy-MM-dd HH:mm:ss"));
	        

	        bean.setResideFlag(0);
	        bean.setTransStatus(realStatus);
	        bean.setCreateTime(new Date());
	        bean.setUpdateTime(new Date());
	        bean.setCheckStatus(0);
    	} catch (Exception e) {
    		 bean.setRemark("ERROR");
    		 return bean;
		}
        return bean;
    }

}
