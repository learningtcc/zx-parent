package com.ink.msgcenter.api.model.input;

import java.util.List;

/**
 * 短信群发接口bean
 * Created by aiyungui on 2016/5/24.
 */
public class SmsMassInput extends SmsModel{

    /*手机号*/
    private List<String> mobileList;


    public List<String> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<String> mobileList) {
        this.mobileList = mobileList;
    }

}
