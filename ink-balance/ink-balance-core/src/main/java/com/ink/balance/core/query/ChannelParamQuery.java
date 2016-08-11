package com.ink.balance.core.query;

import java.io.Serializable;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月31日 下午2:06:38
 * @description 描述：渠道参数数据查询参数
 */
public class ChannelParamQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -3331370691259262534L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 渠道编号
     */
    private String no;
    /**
     * 渠道名称
     */
    private String name;
    /**
     * 支付渠道商户号
     */
    private String channelMerchantNo;

    /**
     * 渠道类型
     */
    private Integer type;

    /**
     * 日切开始时间
     */
    private Date cutDayStart;

    /**
     * 日切结束时间
     */
    private Date cutDayEnd;

    /**
     * 驻留天数(对于未匹配的记录，继续参与对账的天数)
     */
    private Integer resideDays;

    /**
     * 操作员
     */
    private Long operatorId;

    /**
     * 删除标志
     */
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCutDayStart() {
        return cutDayStart;
    }

    public void setCutDayStart(Date cutDayStart) {
        this.cutDayStart = cutDayStart;
    }

    public Date getCutDayEnd() {
        return cutDayEnd;
    }

    public void setCutDayEnd(Date cutDayEnd) {
        this.cutDayEnd = cutDayEnd;
    }

    public Integer getResideDays() {
        return resideDays;
    }

    public void setResideDays(Integer resideDays) {
        this.resideDays = resideDays;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "ChannelParamQuery [id=" + id + ", no=" + no
        		+ ", name=" + name
                + ", channelMerchantNo=" + channelMerchantNo
                + ", type=" + type
                + ", cutDayStart=" + cutDayStart + ", cutDayEnd=" + cutDayEnd
                + ", resideDays=" + resideDays + ", operatorId=" + operatorId
                + ", delFlag=" + delFlag + "]";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
