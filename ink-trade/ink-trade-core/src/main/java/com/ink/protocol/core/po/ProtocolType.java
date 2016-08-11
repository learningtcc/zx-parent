package com.ink.protocol.core.po;

import java.util.Date;

public class ProtocolType {
    /**  */
    private Long id;

    /**  */
    private String protocolTypeNum;

    /**  */
    private String protocolTypeName;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Long createrId;

    /**  */
    private String createrName;

    /**  */
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolTypeNum() {
        return protocolTypeNum;
    }

    public void setProtocolTypeNum(String protocolTypeNum) {
        this.protocolTypeNum = protocolTypeNum == null ? null : protocolTypeNum.trim();
    }

    public String getProtocolTypeName() {
        return protocolTypeName;
    }

    public void setProtocolTypeName(String protocolTypeName) {
        this.protocolTypeName = protocolTypeName == null ? null : protocolTypeName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}