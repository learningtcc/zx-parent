package com.ink.channel.core.boofoopay.request;

import java.io.Serializable;

/**
 * 宝付请求基类
 * Created by huohb on 2016/6/28.
 */
public class BaseRequest implements Serializable{

    private static final long serialVersionUID = -3633338510201399758L;

    private String terminal_id;//终端号
    private String member_id;//商户号

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
}
