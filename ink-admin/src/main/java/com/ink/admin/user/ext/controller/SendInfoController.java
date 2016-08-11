
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.ext.controller;

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.ext.api.constant.UserLogConstant;
import com.ink.user.ext.api.constant.UserMessageConstant;
import com.ink.user.ext.core.po.SendInfo;
import com.ink.user.ext.core.query.SendInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("userext/SendInfo")
@Controller
public class SendInfoController extends BaseController {
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " update_time desc ";

	// forward paths
	protected static final String LIST_JSP = "/userext/SendInfo/list";
	protected static final String CREATE_JSP = "/userext/SendInfo/create";
	protected static final String EDIT_JSP = "/userext/SendInfo/edit";
	protected static final String SHOW_JSP = "/userext/SendInfo/show";
	// redirect paths,startWith: !

	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private DubboBaseService userextDubboBaseService;

	private static YinkerLogger logger = YinkerLogger.getLogger(SendInfoController.class);

	@RequestMapping(value = "/list")
	public ModelAndView list() throws Exception {

		SendInfoQuery query = newQuery(SendInfoQuery.class, DEFAULT_SORT_COLUMNS);
		Page page = userextDubboBaseService.findPage("sendInfoManager",query);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);

		return modelAndView;
	}

	/** 进入新增页面 */
	@RequestMapping(value = "/create")
	public ModelAndView create(HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(CREATE_JSP);
		modelAndView.addObject("userName", userName);
		return modelAndView;
	}

	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(@RequestParam(value="ffile", required=false) MultipartFile file,HttpServletRequest request, SendInfo sendInfo, String type) throws Exception {
		String fileId = idCodeGenerator.getId();
		// 保存sendinfo
		String fileName = null;
		if(file!=null && !file.isEmpty()){
			fileName = uploadFile(file, request, fileId);
			sendInfo.setFileId(Long.valueOf(fileId));
			sendInfo.setFilePath(fileName);
			sendInfo.setOriginalName(file.getOriginalFilename());
		}
		sendInfo.setId(Long.valueOf(idCodeGenerator.getId()));
		sendInfo.setStatus(0);
		Date date = new Date();
		sendInfo.setCreateTime(date);
		sendInfo.setUpdateTime(date);
		//及时发送设置发送时间为当前时间
		if(sendInfo.getSendType().equals(UserMessageConstant.SendType_Now)){
			sendInfo.setSendTime(date);
		}
		
		userextDubboBaseService.save("sendInfoManager",sendInfo);
		logger.info(UserLogConstant.ModuleCode, UserLogConstant.SendMassMsg_BusCode, "保存发送信息成功!"+sendInfo.toString());
		if (fileName != null) {
			// 保存并发送
			if ("send".equals(type)) {
//				userMsgJobTriggerService.sendMessage(sendInfo.getFilePath(), Long.valueOf(fileId), sendInfo.getSendTime(),
//						Integer.valueOf(sendInfo.getSendType()), String.valueOf(sendInfo.getMchId()));
				userextDubboBaseService.executeDynamicMethod("userMsgJobTriggerService","sendMessage",sendInfo.getFilePath(), Long.valueOf(fileId), sendInfo.getSendTime(),
						Integer.valueOf(sendInfo.getSendType()), String.valueOf(sendInfo.getMchId()));
				sendInfo.setStatus(1);
				userextDubboBaseService.update("sendInfoManager",sendInfo);
			}
			return writeAjaxResponse("2");
		}
		return writeAjaxResponse("1");
	}
	
	/**
	 * @Description: 发送
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/send")
	@ResponseBody
	public String send(String id) throws Exception {
		if(StringUtils.isNotBlank("id")){
			SendInfo sendInfo = (SendInfo) userextDubboBaseService.getById("sendInfoManager", Long.valueOf(id));
			if( sendInfo !=null && sendInfo.getFileId() != null){
				File file = new File(sendInfo.getFilePath());
				if(file.exists()){
					//发送
					logger.info(UserLogConstant.ModuleCode, UserLogConstant.SendMassMsg_BusCode, "调用短信群发成功!"+sendInfo.toString());
					userextDubboBaseService.executeDynamicMethod("userMsgJobTriggerService","sendMessage",sendInfo.getFilePath(), Long.valueOf(sendInfo.getFileId()), sendInfo.getSendTime(), Integer.valueOf(sendInfo.getSendType()), String.valueOf(sendInfo.getMchId()));
					sendInfo.setStatus(1);
					userextDubboBaseService.update("sendInfoManager",sendInfo);
				}
			}
		}

		return writeAjaxResponse("1");
	}
	
	/** 查看对象 */
	@RequestMapping(value = "/show")
	public ModelAndView show(SendInfo sendInfo) throws Exception {
		SendInfo model = (SendInfo) userextDubboBaseService.getById("sendInfoManager",sendInfo.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}

	/** 进入更新页面 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(SendInfo sendInfo) throws Exception {
		SendInfo model = (SendInfo) userextDubboBaseService.getById("sendInfoManager", sendInfo.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}

	/** 保存更新对象 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(@RequestParam(value="ffile", required=false) MultipartFile file,HttpServletRequest request, SendInfo sendInfo) throws Exception {
		String fileId = idCodeGenerator.getId();
		// 保存sendinfo
		if(file!=null && !file.isEmpty()){
			String fileName = uploadFile(file, request, fileId);
			sendInfo.setFilePath(fileName);
		}
		userextDubboBaseService.update("sendInfoManager",sendInfo);
		return writeAjaxResponse("1");
	}

	/** 删除对象 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(String items) throws Exception {
		if (items != null) {
			String[] idArray = items.split(",");
			for (String id : idArray) {
				if(StringUtils.isNotBlank(id)){
					SendInfo info = (SendInfo) userextDubboBaseService.getById("sendInfoManager",Long.valueOf(id));
					info.setStatus(UserMessageConstant.SENDINFO_STATUS_DELETE);
					userextDubboBaseService.update("sendInfoManager",info);
				}

			}
		}

		return writeAjaxResponse("1");
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @param fileId
	 * @return
	 */
	private String uploadFile(MultipartFile file, HttpServletRequest request, String fileId){
		FileOutputStream outputStream = null;
		InputStream stream = null;
		String fileName = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("");
			File ofile = new File(path);
			File filePath = new File(ofile.getParent()+"/userMessageSendFile");
			//如果文件夹不存在则创建    
			if  (!filePath.exists()  && !filePath.isDirectory())      
			{       
			    System.out.println("//不存在");  
			    filePath.mkdir();    
			}
			String oriName = file.getOriginalFilename();
			String prefix = oriName.substring(oriName.lastIndexOf("."));
			fileName = filePath + "/" + fileId + prefix;
			System.out.println(fileName);
			outputStream = new FileOutputStream(fileName);
			int byteCount = 0;
			byte[] bytes = new byte[1024];
			stream = file.getInputStream();
			while ((byteCount = stream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, byteCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
			}
		}
		return fileName;
	}
}
