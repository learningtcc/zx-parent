package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.util.List;

/**
 * @author bo.chen
 * @Description TODO
 * @date 2016年4月15日 上午9:41:30
 */
public class ChannelDataPageOutput<ChannelDataOutput> implements Serializable {

    private static final long serialVersionUID = 2596804961601985301L;

    private int total;//总数

    private List<ChannelDataOutput> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ChannelDataOutput> getList() {
        return list;
    }

    public void setList(List<ChannelDataOutput> list) {
        this.list = list;
    }

    public ChannelDataPageOutput() {
        super();
    }

    public ChannelDataPageOutput(int total, List<ChannelDataOutput> list) {
        super();
        this.total = total;
        this.list = list;
    }

    @Override
    public String toString() {
        return "ChannelDataPageOutput [total=" + total + ", list=" + list + "]";
    }


}
