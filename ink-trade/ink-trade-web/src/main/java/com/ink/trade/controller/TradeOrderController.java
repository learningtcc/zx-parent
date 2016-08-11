package com.ink.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.basic.model.base.BasicBankEntity;
import com.ink.trade.api.platform.basic.service.IBasicBankService;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.PayOrderEntity;
import com.ink.trade.api.platform.trade.model.base.TradeOrderEntity;
import com.ink.trade.api.platform.trade.model.in.TradeOrderQueryInput;
import com.ink.trade.api.platform.trade.service.IPayOrderService;
import com.ink.trade.api.platform.trade.service.ITradeOrderService;
@Controller
@RequestMapping("TradeOrder")
public class TradeOrderController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
		protected static final String DEFAULT_SORT_COLUMNS = null; 
		
		//forward paths
		protected static final String LIST_JSP= "/TradeOrder/list";
		protected static final String SHOW_JSP= "/TradeOrder/show";
		//redirect paths,startWith: !
		
		@Autowired
		private ITradeOrderService tradeOrderService;
		@Autowired
		private IAsileInfoService asileInfoService;
		@Autowired
		private IBasicBankService basicBankService;
		@Autowired
		private IPayOrderService payOrderService;

	
		@SuppressWarnings("unchecked")
		@RequestMapping(value="/list")
		public ModelAndView list() {
			List<AsileInfoEntity> asileList=asileInfoService.getAll();
			List<BasicBankEntity> basicBankList=basicBankService.getAll();
			TagDataInfo payType=new TagDataInfo();
			payType.setJsonArray(PayType.getPayType());
			TagDataInfo asileInfos=new TagDataInfo();
			asileInfos.setJsonArray(asileList);
			TagDataInfo basicBanks=new TagDataInfo();
			basicBanks.setJsonArray(basicBankList);
			TagDataInfo tradeOrderStauts=new TagDataInfo();
			tradeOrderStauts.setJsonArray(OrderStatus.getStatus());
			TradeOrderQueryInput query = newQuery(TradeOrderQueryInput.class,DEFAULT_SORT_COLUMNS);
			CommonResult<Page<TradeOrderEntity>> ret = tradeOrderService.list(query);
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(LIST_JSP);
			modelAndView.addObject("page", ret.getBussinessObj());
			modelAndView.addObject("asileInfos", asileInfos);
			modelAndView.addObject("basicBanks",basicBanks);
			modelAndView.addObject("payType",payType);
			modelAndView.addObject("tradeOrderStatus",tradeOrderStauts);
			return modelAndView;
		}
		
		@RequestMapping(value="/show")
		public ModelAndView show(PayOrderEntity payOrder) {
			//PayOrderQueryInput query = newQuery(PayOrderQueryInput.class,DEFAULT_SORT_COLUMNS);
			CommonResult<Page<PayOrderEntity>> ret = payOrderService.list(payOrder);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(SHOW_JSP);
			modelAndView.addObject("page", ret.getBussinessObj());
			
			List<AsileInfoEntity> asileList=asileInfoService.getAll();
			TagDataInfo asileInfos=new TagDataInfo();
			asileInfos.setJsonArray(asileList);
			modelAndView.addObject("asileInfos", asileInfos);
			
			TagDataInfo tradeOrderStauts=new TagDataInfo();
			tradeOrderStauts.setJsonArray(OrderStatus.getStatus());
			modelAndView.addObject("tradeOrderStatus",tradeOrderStauts);
			
			TagDataInfo payType=new TagDataInfo();
			payType.setJsonArray(PayType.getPayType());
			modelAndView.addObject("payType",payType);
			return modelAndView;
		}
}
