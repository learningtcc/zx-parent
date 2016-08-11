package com.ink.balance.core.batch.mapper;

import java.math.BigDecimal;
import java.util.Date;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.api.constants.SysParamConst;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BooDzItemJobMapper implements FieldSetMapper<ChannelData> {

    /**
     * 读取宝付代付对账文件
     */
    @Override
    public ChannelData mapFieldSet(FieldSet fieldSet) throws BindException {
        ChannelData bean = new ChannelData();

        try {
            fieldSet.readBigDecimal(9);
        } catch (Exception e) {
            bean.setPlatformOrderNo("CB1008610010");
            return bean;
        }
        if (!fieldSet.readString(3).equals("00")) {
            bean.setPlatformOrderNo("CB1008610010");
            return bean;
        }
        try {
            /** 平台订单号*/
            bean.setPlatformOrderNo(fieldSet.readString(5));
            /** 订单金额 */
            bean.setAmt(fieldSet.readBigDecimal(9));
            /** 交易金额 */
            bean.setReceivedAmt(fieldSet.readBigDecimal(9));
            /** 渠道编号 */
            bean.setChannelNo(SysParamConst.BOOFOO_CHANNEL_NO);
            /** 商户编号 */
            bean.setMerchantNo(fieldSet.readString(0));
            /** 交易子类型*/
             /* bean.setPayMethod(fieldSet.readString(3));*/
            /** 批次号*/
            bean.setBatchId(fieldSet.readString(6));
            /** 交易时间 */
            bean.setTransTime(fieldSet.readDate(7, "yyyy-MM-dd"));
            /** 订单状态 */
            switch (fieldSet.readInt(8)) {
                case 1:
                    bean.setTransStatus(2);
                    break;
                default:
                    bean.setTransStatus(1);
                    break;
            }
            /** 交易号*/
            bean.setTransNo(fieldSet.readString(13));
            /** 对账状态*/
            bean.setCheckStatus(0);
            bean.setDelFlag(0);
            bean.setResideFlag(0);
            bean.setCreateTime(new Date());
            bean.setUpdateTime(new Date());
        } catch (Exception e) {
            bean.setRemark("ERROR");
            return bean;
        }
        return bean;
    }

}
