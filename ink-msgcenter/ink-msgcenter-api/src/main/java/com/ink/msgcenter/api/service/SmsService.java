package com.ink.msgcenter.api.service;

import com.ink.msgcenter.api.model.input.SmsExtInput;
import com.ink.msgcenter.api.model.input.SmsInput;
import com.ink.msgcenter.api.model.input.SmsMassInput;
import com.ink.msgcenter.api.model.output.MsgOutput;

/**
 * 短信发送接口
 * Created by aiyungui on 2016/5/18.
 */
public interface SmsService {

    /**
     * 发送短信
     * @param smsInput
     * @return
     */
    public MsgOutput sendSms(SmsInput smsInput);

    /**
     * 发送短信(含扩展接口)
     * @param smsExtInput
     * @return
     */
    public MsgOutput sendSmsWithExt(SmsExtInput smsExtInput);

    /**
     * 短信群发接口
     * @param smsMassInput
     * @return
     */
    public MsgOutput sendMassSms(SmsMassInput smsMassInput);
}
