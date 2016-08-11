package com.ink.balance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.ChannelDataQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelDataOutput;
import com.ink.balance.api.model.out.ChannelDataPageOutput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelDataService;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.utils.ftp.FTPConfigCenter;
import com.ink.balance.utils.ftp.FTPUtils;
import com.ink.balance.utils.ftp.FtpDef;
import com.ink.balance.vo.input.ResidentParamVo;
import com.ink.base.BaseController;
import com.ink.base.page.Page;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.log.support.ServletControllerContext;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.web.util.WebUtils;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("ChannelData")
@Controller
public class ChannelDataController extends BaseController {
	
	YinkerLogger log = YinkerLogger.getLogger(ChannelDataController.class);

	@Autowired
	@Qualifier("channelParamService")
	private IChannelParamService channelParamService;//渠道信息服务
	
	@Autowired(required=false)
	private IChannelDataService channelDataService;

	@Autowired
	FTPConfigCenter ftpConfigCenter;
	
	@RequestMapping("view")
	public String view(){
		return "channel/view";
	}


	
	/**
	 * 
	 * @Description 分页查询渠道数据
	 * @author xuguoqi
	 * @date 2016年5月17日 下午4:36:28
	 * @param paramVo
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="list")
	public ModelAndView list(@ModelAttribute ResidentParamVo paramVo,Integer pageSize, Integer pageNumber){
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询开始:param="+paramVo);
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
		Page<ChannelDataOutput> page = new Page<>(pageNumber, pageSize);
		PageParamInput paramInput = new PageParamInput(pageNumber, pageSize);
		CommonOutput<Object> ret = null;
		
		//业务调用
		try {
			ChannelDataQueryParamInput channelParam = BeanCopyConverter.converterClass(paramVo, ChannelDataQueryParamInput.class);
			ret = channelDataService.pageQuery(channelParam, paramInput);
		} catch (Exception e) {
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询dubbo服务异常"+e.getMessage(), e, null);
			modelAndView.addObject("page", page);
			return modelAndView;
		}
		//结果返回
		if(BalanceCodeUtils.SUCCESS==ret.getCode()){
			ChannelDataPageOutput output = (ChannelDataPageOutput)ret.getBusinessObj();
			List<ChannelDataOutput> ChannelList = output.getList();
			page.setTotalCount(output.getTotal());
			if(CollectionUtils.isNotEmpty(ChannelList)){
				page.setResult(ChannelList);
			}else{
				page.setResult(new ArrayList<ChannelDataOutput>());
			}
			modelAndView.addObject("page", page);
		}else{
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询失败:"+ret.getMessage(), null, null);
		}
		PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        modelAndView.addObject("channelList", list);
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道交易数据分页查询结束共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description 获取渠道信息详情
	 * @author bo.chen
	 * @date 2016年4月19日 下午5:32:24
	 * @param id
	 * @return
	 */
	@RequestMapping(value="show")
	@ResponseBody
	public ModelAndView show(Long id){
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单个渠道交易信息开始 id="+id);
		long startTime = System.currentTimeMillis();
		//数据封装
		ModelAndView modelAndView = new ModelAndView();
		CommonOutput<ChannelDataOutput> ret = null;
		//功能调用
		try {
			ret =channelDataService.getDetails(id);
		} catch (Exception e) {
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单渠道交易调用dubbo服务异常:"+e.getMessage(), e, null);
			modelAndView.addObject("model", new ChannelDataOutput());
			return modelAndView;
		}
		if(BalanceCodeUtils.SUCCESS==ret.getCode()){
			modelAndView.addObject("model", ret.getBusinessObj());
			return modelAndView;
		}else{
			log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取渠道交易信息异常:"+ret.getMessage(), null, null);
			modelAndView.addObject("model", new ChannelDataOutput());
		}
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单个渠道交易信息结束共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
		return modelAndView;
	}	
	
	/**
	 * 
	 * @Description 打开信息页面
	 * @author xuguoqi
	 * @date 2016年5月17日 下午4:53:35
	 * @return
	 */
	@RequestMapping(value="/export")
	public ModelAndView export() {
		return new ModelAndView();
	}
	

	/**
	 *
	 * @Description 导入渠道交易信息文件
	 * @author xuguoqi
	 * @date 2016年5月17日 下午4:53:10
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/exportFile")
	@ResponseBody
	public String exportFile(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "导入渠道交易信息开始");
		Map<String,String> ftpConfig = ftpConfigCenter.getFtpConfig();
		long startTime = System.currentTimeMillis();
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					//取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					String prefix=myFileName.substring(myFileName.lastIndexOf(".")+1);
					//if(!"txt".equals(prefix) && !"xlsx".equals(prefix) && !"xls".equals(prefix)){
					//	return "文件类型不支持";
					//}
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if(StringUtils.isNotBlank(myFileName)){
						try {
							FTPUtils.uploadFile(file.getInputStream(), file.getOriginalFilename(), ftpConfig.get(FtpDef.FTP_HSOTNAME), ftpConfig.get(FtpDef.FTP_USERNAME), ftpConfig.get(FtpDef.FTP_PASSWORD), Integer.parseInt(ftpConfig.get(FtpDef.FTP_PORT)));
						} catch (Exception e) {
							log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "调用ftp服务上传文件失败:"+e.getMessage(), e, null);
							writeAjaxResponse(e.getMessage());
						}
					}
				}
				//记录上传该文件后的时间
				log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "获取单个渠道交易信息结束共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
				return writeAjaxResponse("上传成功");
			}

		}
		return writeAjaxResponse("请选择上传文件");
	}


	
	
	
	
	
	
	
	
	
//	@Autowired
//	private IChannelDataService channelDataService; 
//	
//	@RequestMapping(value="/list")
//	public ModelAndView list() {
//		
////		ChannelDataQuery query = newQuery(ChannelDataQuery.class,DEFAULT_SORT_COLUMNS);
////		Page page = channelDataManager.findPage(query);
//		channelDataService.pageQuery(paramInput);
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName(LIST_JSP);
//		modelAndView.addObject("page", page);
//		
//		return modelAndView;
//	}
//	

//	
//	/** 保存新增对象 */
//	@RequestMapping(value="/save")
//	@ResponseBody
//	public String save(ChannelData channelData) {
//		channelDataManager.save(channelData);
//		return writeAjaxResponse("1");
//	}
//	
//	/** 查看对象*/
//	@RequestMapping(value="/show")
//	public ModelAndView show(ChannelData channelData) {
//		ChannelData model = channelDataManager.getById(channelData.getId());
//		ModelAndView mav = new ModelAndView(SHOW_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**进入更新页面*/
//	@RequestMapping(value="/edit")
//	public ModelAndView edit(ChannelData channelData) {
//		ChannelData model = channelDataManager.getById(channelData.getId());
//		ModelAndView mav = new ModelAndView(EDIT_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**保存更新对象*/
//	@RequestMapping(value="/update")
//	@ResponseBody
//	public String update(ChannelData channelData) {
//		channelDataManager.update(channelData);
//		return writeAjaxResponse("1");
//	}
//	
//	
//	/**删除对象*/
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	public String delete(String items) {
//		if(items!=null){
//			String[] idArray = items.split(",");
//			for(String id: idArray){
//				channelDataManager.removeById(Integer.valueOf(id));
//			}
//		}
//		
//		return writeAjaxResponse("1");
//	}
	//默认多列排序,example: username desc,createTime asc
//		protected static final String DEFAULT_SORT_COLUMNS = null; 
//		
//		//forward paths
//		protected static final String LIST_JSP= "WEB-INF/views/ChannelData/list";
//		protected static final String CREATE_JSP = "WEB-INF/views/ChannelData/create";
//		protected static final String EDIT_JSP = "WEB-INF/views/ChannelData/edit";
//		protected static final String SHOW_JSP = "WEB-INF/views/ChannelData/show";
		//redirect paths,startWith: !
}
