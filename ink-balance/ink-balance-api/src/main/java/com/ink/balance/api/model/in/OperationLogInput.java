package com.ink.balance.api.model.in;

import java.util.Date;

/**
 * @author bo.chen
 * @Description 操作日志数据
 * @date 2016年4月11日 上午11:03:07
 */
public class OperationLogInput  implements java.io.Serializable{
	private static final long serialVersionUID = 6528047151205004276L;

	/**  */
    private Long id;

    /** 操作类型（1：新增、2：修改、3：删除） */
    private Integer type;

    /** 操作表名称 */
    private String tableName;

    /** 操作表主键 */
    private Long tableId;

    /** 操作表字段 */
    private String tableColumn;

    /** 原始内容 */
    private String oldContent;

    /** 更新内容 */
    private String newContent;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 操作人 */
    private String operator;

    /** 删除标志 */
    private Integer delFlag;

    /** 版本号 */
    private Integer version;

    /** 备注 */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn == null ? null : tableColumn.trim();
    }

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent == null ? null : oldContent.trim();
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent == null ? null : newContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "OperationLog [id=" + id + ", type=" + type + ", tableName="
				+ tableName + ", tableId=" + tableId + ", tableColumn="
				+ tableColumn + ", oldContent=" + oldContent + ", newContent="
				+ newContent + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", operator=" + operator + ", delFlag="
				+ delFlag + ", version=" + version + ", remark=" + remark + "]";
	}
    
    
}