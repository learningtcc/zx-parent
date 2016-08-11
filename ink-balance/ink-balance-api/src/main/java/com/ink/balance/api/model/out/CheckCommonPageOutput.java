package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuguoqi
 * @Description TODO
 * @date 2016年4月11日 上午10:13:58
 */
public class CheckCommonPageOutput<T> implements Serializable {

    private static final long serialVersionUID = 2755814608440969819L;

    public List<T> list;

    public int totail;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotail() {
        return totail;
    }

    public void setTotail(int totail) {
        this.totail = totail;
    }

    public CheckCommonPageOutput() {
        super();
    }

    public CheckCommonPageOutput(List<T> list, int totail) {
        super();
        this.list = list;
        this.totail = totail;
    }

    @Override
    public String toString() {
        return "CheckCommonPageOutput [list=" + list + ", totail=" + totail + "]";
    }


}
