
/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.balance.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.ChannelParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelDataPageOutput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.vo.input.CheckParamVo;
import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.log.support.ServletControllerContext;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("ChannelParam")
@Controller
public class ChannelParamController extends BaseController {
	
    YinkerLogger log = YinkerLogger.getLogger(ChannelDataController.class);
    
    @Autowired
	@Qualifier("channelParamService")
	private IChannelParamService channelParamService;//渠道信息服务
    /**
     * 
    * @Title: list 
    * @Description: 分页查询渠道信息
    * @param @param paramVo
    * @param @param pageSize
    * @param @param pageNumber
    * @param @return
    * @return ModelAndView 
    * @author zhaojie
    * @date 2016年7月22日 上午11:52:39
    * @throws
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(@ModelAttribute CheckParamVo paramVo,Integer pageSize, Integer pageNumber){
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息数据分页查询开始:param="+paramVo);
		//参数回写到页面
		HttpServletRequest request= ServletControllerContext.getRequest();
		Map params = WebUtils.getParametersStartingWith(request, "");
		WebUtils.exposeRequestAttributes(request, params);

		long startTime = System.currentTimeMillis();
		//分页参数初始化
		if(null==pageSize || null==pageNumber){
			pageSize=10;
			pageNumber=1;
		}
		ModelAndView modelAndView = new ModelAndView();
		Page<ChannelParamOutput> page = new Page<>(pageNumber, pageSize);
		PageParamInput paramInput = new PageParamInput(pageNumber, pageSize);
		CommonOutput<Object> ret = null;
		
		//业务调用
		try {
			ChannelParamInput channelParam = BeanCopyConverter.converterClass(paramVo, ChannelParamInput.class);
			ret = channelParamService.pageChannel(channelParam, paramInput);
		} catch (Exception e) {
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询dubbo服务异常"+e.getMessage(), e, null);
			modelAndView.addObject("page", page);
			return modelAndView;
		}
		//结果返回
		if(BalanceCodeUtils.SUCCESS==ret.getCode()){
			ChannelDataPageOutput output = (ChannelDataPageOutput)ret.getBusinessObj();
			List<ChannelParamOutput> ChannelList = output.getList();
			page.setTotalCount(output.getTotal());
			if(CollectionUtils.isNotEmpty(ChannelList)){
				page.setResult(ChannelList);
			}else{
				page.setResult(new ArrayList<ChannelParamOutput>());
			}
			modelAndView.addObject("page", page);
		}else{
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询失败:"+ret.getMessage(), null, null);
		}
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询结束共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
		return modelAndView;
	}
    /**
     * 
    * @Title: show 
    * @Description: 获取渠道详细信息
    * @param @param id
    * @param @return
    * @return ModelAndView 
    * @author zhaojie
    * @date 2016年7月25日 上午10:32:13
    * @throws
     */
    @RequestMapping(value="show")
	@ResponseBody
	public ModelAndView show(Long id){
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单个渠道信息开始 id="+id);
		long startTime = System.currentTimeMillis();
		//数据封装
		ModelAndView modelAndView = new ModelAndView();
		CommonOutput<ChannelParamOutput> ret = null;
		//功能调用
		try {
			ret =channelParamService.getDetails(id);
		} catch (Exception e) {
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单渠道调用dubbo服务异常:"+e.getMessage(), e, null);
			modelAndView.addObject("model", new ChannelParamOutput());
			return modelAndView;
		}
		if(BalanceCodeUtils.SUCCESS==ret.getCode()){
			modelAndView.addObject("model", ret.getBusinessObj());
			return modelAndView;
		}else{
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取渠道信息异常:"+ret.getMessage(), null, null);
			modelAndView.addObject("model", new ChannelParamOutput());
		}
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取渠道信息结束共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
		return modelAndView;
	}	
    /**
     * 进入新增页面
     */
    @RequestMapping(value = "/create")
    public ModelAndView create() {
        return new ModelAndView("ChannelParam/create");
    }
    /**
     * 
    * @Title: save 
    * @Description: 保存渠道
    * @param @param channelParam
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年7月25日 下午2:46:43
    * @throws
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(ChannelParamInput channelParam) {
    	 log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "保存渠道数据："+channelParam.toString());
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    	 String ret=null;
    	 try {
			channelParam.setCutDayStart(sdf.parse(channelParam.getCutDayStartString()));
			channelParam.setCutDayEnd(sdf.parse(channelParam.getCutDayEndString()));
			ret=channelParamService.insert(channelParam);
		} catch (ParseException e) {
			 log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "添加渠道失败" +e, null);
		}
    	 
    	 
         if (ret.equals("SUCCESS")) {
             return writeAjaxResponse("SUCCESS");
         }else{
             return writeAjaxResponse("ERROR");
         }
    }
    /**
     * 进入修改页面
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(Long id) {
    	log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单个渠道信息开始 id="+id);
		long startTime = System.currentTimeMillis();
		//数据封装
		ModelAndView modelAndView = new ModelAndView();
		CommonOutput<ChannelParamOutput> ret = null;
		//功能调用
		try {
			ret =channelParamService.getDetails(id);
		} catch (Exception e) {
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单渠道调用dubbo服务异常:"+e.getMessage(), e, null);
			modelAndView.addObject("model", new ChannelParamOutput());
			return modelAndView;
		}
		if(BalanceCodeUtils.SUCCESS==ret.getCode()){
			modelAndView.addObject("model", ret.getBusinessObj());
			return modelAndView;
		}else{
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取渠道信息异常:"+ret.getMessage(), null, null);
			modelAndView.addObject("model", new ChannelParamOutput());
		}
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取渠道信息结束共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
		return modelAndView;
    }
    /**
     * 
    * @Title: update 
    * @Description: 修改渠道
    * @param @param channelParam
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年7月25日 下午7:18:05
    * @throws
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(ChannelParamInput channelParam) {
    	 log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "修改渠道数据："+channelParam.toString());
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    	 String ret=null;
    	 try {
			channelParam.setCutDayStart(sdf.parse(channelParam.getCutDayStartString()));
			channelParam.setCutDayEnd(sdf.parse(channelParam.getCutDayEndString()));
			ret=channelParamService.update(channelParam);
		} catch (ParseException e) {
			 log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "修改渠道失败" +e, null);
		}
    	 
         if (ret.equals("SUCCESS")) {
             return writeAjaxResponse("SUCCESS");
         }else{
             return writeAjaxResponse("ERROR");
         }
    }
}
