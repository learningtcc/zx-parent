package com.ink.trade.controller.cert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.module.MsgOutput;

@Controller
@RequestMapping("dataCert")
public class DataSecurityClientController {
	@Autowired
	private DataSecurityClient dataSecurityClient;
	@RequestMapping("encrypt")
	@ResponseBody
	public MsgOutput  dataEncrypt(String merchantCode,String certCode,String message){
		MsgOutput output= dataSecurityClient.dataEncrypt(merchantCode, certCode, message);
		return output;
	}
}
