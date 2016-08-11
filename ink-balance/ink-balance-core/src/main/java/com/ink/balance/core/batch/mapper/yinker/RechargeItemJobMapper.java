package com.ink.balance.core.batch.mapper.yinker;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.api.constants.SysParamConst;
import com.ink.base.utils.dateUtil.DateUtil;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public class RechargeItemJobMapper implements FieldSetMapper<ChannelData> {

    /**
     * @param fieldSet
     * @return ChannelData
     * @Description 读取每一行数据
     * @author bo.chen
     * @date 2016年07月11日 上午11:10:58
     */
    @Override
    public ChannelData mapFieldSet(FieldSet fieldSet) throws BindException {
        ChannelData bean = new ChannelData();
        /** 渠道编号 */
        String channelNo = SysParamConst.YKLM_CHANNEL_NO;

        /** 商户编号 */
        String merchantNo = SysParamConst.YK_RECHARGE_MERCHANT_NO;

        /** 交易时间 */
        String transTime = fieldSet.readString(0);

        if (transTime.equals("充值时间")) {//随便设置值，在后续处理中为了忽略掉此行
            bean.setPlatformOrderNo("CB1008610010");
            return bean;
        }

        /** 平台订单号 */
        String platformOrderNo = fieldSet.readString(3);

        /** 订单金额 */
        BigDecimal amt = fieldSet.readBigDecimal(6);

        /** 实收金额 */
        BigDecimal receivedAmt = fieldSet.readBigDecimal(13);

        /** 交易状态 1、待支付 2、支付成功 3、支付失败 */
        int realStatus = 2;
        bean.setMerchantNo(merchantNo);
        bean.setChannelNo(channelNo);
        bean.setPlatformOrderNo(platformOrderNo);
        bean.setAmt(amt);
        bean.setReceivedAmt(receivedAmt);
        bean.setResideFlag(0);
        try {
			bean.setTransTime(DateUtil.formatYYYYMMDDMMHHSSToStr(transTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        bean.setTransStatus(realStatus);
        
        bean.setDelFlag(0);
        bean.setCheckStatus(0);
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        return bean;
    }

}
