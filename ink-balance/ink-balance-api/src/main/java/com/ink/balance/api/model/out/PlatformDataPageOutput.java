package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.util.List;

/**
 * @author bo.chen
 * @Description TODO
 * @date 2016年4月18日 上午9:41:30
 */
public class PlatformDataPageOutput<PlatformDataOutput> implements Serializable {

    private static final long serialVersionUID = 2596804961601985301L;

    private int total;//总数

    private List<PlatformDataOutput> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PlatformDataOutput> getList() {
        return list;
    }

    public void setList(List<PlatformDataOutput> list) {
        this.list = list;
    }

    public PlatformDataPageOutput() {
        super();
    }

    public PlatformDataPageOutput(int total, List<PlatformDataOutput> list) {
        super();
        this.total = total;
        this.list = list;
    }

    @Override
    public String toString() {
        return "PlatformDataPageOutput [total=" + total + ", list=" + list + "]";
    }


}
