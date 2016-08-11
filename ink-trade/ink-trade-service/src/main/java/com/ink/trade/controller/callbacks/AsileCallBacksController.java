package com.ink.trade.controller.callbacks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ink.trade.service.callbacks.AsileCallBacksServiceImpl;

@Controller
@RequestMapping("callBacks")
public class AsileCallBacksController {
	@Autowired
	private AsileCallBacksServiceImpl asileCallBacksService;
	/**
	 * 代收回调
	 * @param data
	 * @param encryptkey
	 * @return
	 */
	@RequestMapping("pay2AccountCallBack")
	public String pay2AccountCallBack(@RequestParam("data") String data,@RequestParam("encryptkey")String encryptkey){
		return asileCallBacksService.payCallBack(data, encryptkey,"account");
	}
	/**
	 * 代付回调
	 * @param data
	 * @param encryptkey
	 * @return
	 */
	@RequestMapping("pay2CardCallBack")
	public String pay2CardCallBack(@RequestParam("data") String data,@RequestParam("encryptkey")String encryptkey){
		return asileCallBacksService.payCallBack(data, encryptkey,"card");
	}
}
