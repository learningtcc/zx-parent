package com.ink.base.nosql.convert.service;

import java.io.Serializable;
import java.util.Date;

/**
 * User: kingstar
 * Date: 16-3-15
 * Time: 下午8:11
 */
public class MongoPo  implements Serializable {

    private Integer id;

    private Date createTime;

    private   String opUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }
}
