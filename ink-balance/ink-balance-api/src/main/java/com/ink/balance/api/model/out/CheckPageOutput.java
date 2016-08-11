package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuguoqi
 * @Description TODO
 * @date 2016年4月7日 下午6:41:30
 */
public class CheckPageOutput implements Serializable {

    private static final long serialVersionUID = 2596804961601985301L;

    private int total;//总数

    private List<CheckChannelOutput> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CheckChannelOutput> getList() {
        return list;
    }

    public void setList(List<CheckChannelOutput> list) {
        this.list = list;
    }

    public CheckPageOutput() {
        super();
    }

    public CheckPageOutput(int total, List<CheckChannelOutput> list) {
        super();
        this.total = total;
        this.list = list;
    }

    @Override
    public String toString() {
        return "CheckPageOutput [total=" + total + ", list=" + list + "]";
    }


}
