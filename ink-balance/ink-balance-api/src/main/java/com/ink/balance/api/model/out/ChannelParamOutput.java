package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuguoqi
 * @Description 渠道配置返回类
 * @date 2016年4月19日 上午10:15:11
 */
public class ChannelParamOutput implements Serializable {

    private static final long serialVersionUID = 5368227556992165193L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 渠道编号
     */
    private String no;

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
     * 文件定位
     */
    private String fileAddress;

    /**
     * 文件获取方式
     */
    private Integer fileGetModel;

    /**
     * 驻留天数(对于未匹配的记录，继续参与对账的天数)
     */
    private Integer resideDays;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 操作员
     */
    private Long operatorId;

    /**
     * 删除标志
     */
    private Integer delFlag;

    /**
     * 版本号
     */
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
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

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress == null ? null : fileAddress.trim();
    }

    public Integer getFileGetModel() {
        return fileGetModel;
    }

    public void setFileGetModel(Integer fileGetModel) {
        this.fileGetModel = fileGetModel;
    }

    public Integer getResideDays() {
        return resideDays;
    }

    public void setResideDays(Integer resideDays) {
        this.resideDays = resideDays;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    @Override
    public String toString() {
        return "ChannelParam [id=" + id + ", name=" + name + ", no=" + no
                + ", type=" + type + ", cutDayStart=" + cutDayStart
                + ", cutDayEnd=" + cutDayEnd + ", fileAddress=" + fileAddress
                + ", fileGetModel=" + fileGetModel + ", resideDays="
                + resideDays + ", remark=" + remark + ", createDate="
                + createDate + ", updateDate=" + updateDate + ", operatorId="
                + operatorId + ", delFlag=" + delFlag + ", version=" + version
                + "]";
    }

}
