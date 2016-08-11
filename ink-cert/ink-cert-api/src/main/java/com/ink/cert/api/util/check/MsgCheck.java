package com.ink.cert.api.util.check;

import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import org.apache.commons.lang3.StringUtils;

/**
 * 参数验证
 * Created by aiyungui on 2016/6/22.
 */
public class MsgCheck {

    /**
     * 验证参数是否为null或空
     * @param merchantCode 商户编号
     * @param certCode 证书编号
     * @return 
     */
    public static MsgOutput paramCheck(String merchantCode,String certCode){
        MsgOutput msgOutput = new MsgOutput();
        if (StringUtils.isBlank(merchantCode)){
            msgOutput.setResultCode(ResultConstant.VALIDATE_PARAM_FAILURE);
            msgOutput.setResultMsg("商户编号为null或空");

            return msgOutput;
        }

        if (StringUtils.isBlank(certCode)){
            msgOutput.setResultCode(ResultConstant.VALIDATE_PARAM_FAILURE);
            msgOutput.setResultMsg("证书编号为null或空");

            return msgOutput;
        }

        return msgOutput;
    }
}
