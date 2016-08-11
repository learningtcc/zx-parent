package com.ink.balance.core.batch.mapper.yinker;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.api.constants.SysParamConst;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.ink.base.utils.dateUtil.DateUtil;

public class TransactionItemJobMapper implements FieldSetMapper<ChannelData> {

    /**
     * 读取银客交易对账文件
     */
    @Override
    public ChannelData mapFieldSet(FieldSet fieldSet) throws BindException {
    	 ChannelData bean = new ChannelData();
         
         if (fieldSet.readString(0).equals("发生时间")) {//随便设置值，在后续处理中为了忽略掉此行
             bean.setPlatformOrderNo("CB1008610010");
             return bean;
         }
         /*实收金额*/
         bean.setReceivedAmt(fieldSet.readBigDecimal(5));
         /** 渠道编号 */
         bean.setChannelNo(SysParamConst.YKLM_CHANNEL_NO);
         /** 商户编号 */
         bean.setMerchantNo(SysParamConst.YK_TRANSACTION_MERCHANT_NO);
         /** 平台订单号*/
         String type=fieldSet.readString(4);
         if(type.equals("03")||type.equals("04")||type.equals("05")){
        	 bean.setPlatformOrderNo(fieldSet.readString(2)+"_"+fieldSet.readString(16));
         }else{
        	 bean.setPlatformOrderNo(fieldSet.readString(2));
         }
         
        
         /** 交易时间 */
         try {
 			bean.setTransTime(DateUtil.formatYYYYMMDDMMHHSSToStr(fieldSet.readString(0)));
 		} catch (ParseException e) {
 			e.printStackTrace();
 		}
         /** 订单状态 */
         bean.setTransStatus(2);
         /** 对账状态*/
         bean.setCheckStatus(0);
         
         bean.setDelFlag(0);
         bean.setCreateTime(new Date());
         bean.setUpdateTime(new Date());
         bean.setResideFlag(0);
         return bean;
    }

}
