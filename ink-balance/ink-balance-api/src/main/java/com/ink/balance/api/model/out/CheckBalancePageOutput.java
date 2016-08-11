package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.util.List;

/**
 * @author bo.chen
 * @Description TODO
 * @date 2016年4月28日 上午9:41:30
 */
public class CheckBalancePageOutput<CheckBalanceOutput> implements Serializable {

    private static final long serialVersionUID = 2596804961602985301L;

    private int total;//总数

    private List<CheckBalanceOutput> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CheckBalanceOutput> getList() {
        return list;
    }

    public void setList(List<CheckBalanceOutput> list) {
        this.list = list;
    }

    public CheckBalancePageOutput() {
        super();
    }

    public CheckBalancePageOutput(int total, List<CheckBalanceOutput> list) {
        super();
        this.total = total;
        this.list = list;
    }

    @Override
    public String toString() {
        return "CheckBalancePageOutput [total=" + total + ", list=" + list + "]";
    }


}
