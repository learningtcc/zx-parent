package com.ink.balance.api.model.in;

import java.io.Serializable;
/**
 * @author bo.chen
 * @Description 分页查询页面参数
 * @date 2016年4月11日 上午11:03:07
 */
public class PageParamInput implements Serializable {

    private static final long serialVersionUID = 6297178964005032338L;
    private int pageNum; // 当前页数
    private int numPerPage; // 每页记录数
    private int offset; //起始位置

    public PageParamInput() {
        super();
    }

    public PageParamInput(int pageNum, int numPerPage) {
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getOffset() {
        //计算起始位置
        if (pageNum == 0) {
            pageNum = 1;
        }
        return (pageNum - 1) * numPerPage;
    }

    @Override
    public String toString() {
        return "PageParamInput [pageNum=" + pageNum + ", numPerPage=" + numPerPage + ", offset=" + offset + "]";
    }


}
