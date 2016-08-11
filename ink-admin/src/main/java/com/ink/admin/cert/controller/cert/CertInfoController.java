
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.cert.controller.cert;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.cert.api.constant.MQConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.query.CertInfoQuery;
import com.ink.tfs.client.TFSOperator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 证书管理控制类
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("cert/certInfo")
@Controller
public class CertInfoController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/cert/certInfo/list";
	protected static final String CREATE_JSP = "/cert/certInfo/create";
	protected static final String EDIT_JSP = "/cert/certInfo/edit";
	protected static final String SHOW_JSP = "/cert/certInfo/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService certDubboBaseService;
	@Autowired
	private TFSOperator tfsOperator;
	@Autowired
	private AmqpTemplate amqpTemplate;

	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		CertInfoQuery query = newQuery(CertInfoQuery.class,DEFAULT_SORT_COLUMNS);
		if(StringUtils.isBlank(query.getStatus())){
			query.setStatus(null);
		}
		Page page = null;
		try {
			page = certDubboBaseService.findPage("certInfoManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(CertInfo certInfo, MultipartFile certFile) {
		String result = "0";
		try{
			String userName = (String) getRequest().getSession().getAttribute("SESSION_USERNAME");
			certInfo.setCreateTime(new Date());
			certInfo.setCreateUser(userName);
			certInfo.setStatus("0");

			//根据商户号、证书号判断不能重复
			CertInfoQuery certInfoQuery = new CertInfoQuery();
			certInfoQuery.setMerchatCode(certInfo.getMerchatCode());
			certInfoQuery.setCertCode(certInfo.getCertCode());
			List<CertInfo> certInfoList = certDubboBaseService.find("certInfoManager",certInfoQuery);
			if (null == certInfoList || certInfoList.isEmpty()){
				//取得上传文件
				if(certFile != null && "0".equalsIgnoreCase(certInfo.getEndecryType())){
					try{
							//取得当前上传文件的文件名称
							String uploadFileName = certFile.getOriginalFilename();
							//如果名称不为“”,说明该文件存在，否则说明该文件不存在
							if(StringUtils.isNotBlank(uploadFileName)){
								//发送byte到tfs服务端
								String certId = tfsOperator.saveByteToTfsId(uploadFileName, certFile.getBytes(),
										SysConstant.SYSTEM_CODE, SysConstant.MODULE_CERT_MAINTAIN_CODE,SysConstant.INFO_CERT_MAINTAIN_CODE);
								certInfo.setCertId(certId);
								certInfo.setFileName(uploadFileName);
								//发送MQ信息，使在分布式情况下其它server快速本地下载证书
								amqpTemplate.convertAndSend(MQConstant.SERVER_DOWNLOAD_CERT_EXCHANGE
										, MQConstant.SERVER_DOWNLOAD_CERT_KEY, certInfo);
							}
					}catch (Exception e1){
						e1.printStackTrace();
					}finally {
						certFile.getInputStream().close();
					}
				}

				certDubboBaseService.save("certInfoManager",certInfo);
				result= "1";
			}else{
				result= "2";
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return writeAjaxResponse(result);
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(CertInfo certInfo) {
		CertInfo model = null;
		try {
			model = (CertInfo) certDubboBaseService.getById("certInfoManager",certInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(CertInfo certInfo) {
		CertInfo model = null;
		try {
			model = (CertInfo) certDubboBaseService.getById("certInfoManager",certInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(CertInfo certInfo) {
		String userName = (String) getRequest().getSession().getAttribute("SESSION_USERNAME");
		certInfo.setUpdateTime(new Date());
		certInfo.setUpdateUser(userName);

		try {
			certDubboBaseService.update("certInfoManager",certInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}

	/**更新状态*/
	@RequestMapping(value="/updateStatus")
	@ResponseBody
	public String updateStatus(String items,String status) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try{
					CertInfo certInfo = new CertInfo();
					certInfo.setStatus(status);
					certInfo.setId(Integer.valueOf(id));
					certDubboBaseService.executeDynamicMethod("certInfoManager","updateStatus",certInfo);

					CertInfo certInfo1 = (CertInfo) certDubboBaseService.getById("certInfoManager",Integer.valueOf(id));
					if ("2".equals(status)){//删除tfs文件
						tfsOperator.delete(certInfo1.getFileName(),SysConstant.SYSTEM_CODE,SysConstant.MODULE_CERT_MAINTAIN_CODE);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}

		return writeAjaxResponse("1");
	}
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try {
					certDubboBaseService.removeById("certInfoManager",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
					return writeAjaxResponse("0");
				}
			}
		}
		
		return writeAjaxResponse("1");
	}

	/**
	 * 证书下载
	 * @param id
	 */
	@RequestMapping(value="/certFileDownLoad")
	@ResponseBody
	public void certFileDownLoad(String id){

		OutputStream out = null;
		try{

			CertInfo certInfo = (CertInfo) certDubboBaseService.getById("certInfoManager",Integer.valueOf(id));
			HttpServletResponse response = getResponse();
			out = response.getOutputStream();
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain");
			response.setHeader("content-disposition", "attachment;filename=" + certInfo.getFileName());
			byte[] content =  tfsOperator.getTfsByteById(certInfo.getCertId());
			out.write(content);
			out.flush();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 证书下载
	 * @param ids
	 */
	@RequestMapping(value="/propertiesFileDownLoad")
	@ResponseBody
	public void propertiesFileDownLoad(String ids){

		ZipOutputStream zipOutputStream = null;
		OutputStream out = null;
		try{
			HttpServletResponse response = getResponse();
			out = response.getOutputStream();
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/zip");
			response.setHeader("content-disposition", "attachment;filename=cert_server_"+ DateUtil.formatToYYYYMMDDMMHHSSStr(new Date())+".zip");
			zipOutputStream = new ZipOutputStream(out);
			if (StringUtils.isNotBlank(ids)){
				String[] idArray = ids.split(",");
				for(String id: idArray){
					CertInfo certInfo = (CertInfo) certDubboBaseService.getById("certInfoManager",Integer.valueOf(id));

					List<String> lines = Lists.newArrayList();
					lines.add("#商户编号");
					lines.add("merchatCode="+certInfo.getMerchatCode());
					lines.add("#证书编号");
					lines.add("certCode="+certInfo.getCertCode());
					lines.add("#加解密方式 1证书 2密钥");
					lines.add("endecryType="+certInfo.getEndecryType());
					lines.add("#证书类型");
					lines.add("certType="+certInfo.getCertType());
					lines.add("#密钥");
					lines.add("secretKey="+certInfo.getSecretKey());
					lines.add("#算法类型");
					lines.add("arithmeticType="+certInfo.getArithmeticType());
					lines.add("#文件名称");
					lines.add("fileName="+certInfo.getFileName());
					lines.add("#证书名称");
					lines.add("certName="+certInfo.getCertName());
					lines.add("#证书ID");
					lines.add("certId="+certInfo.getCertId());
					ZipEntry zipEntry = new ZipEntry("properties" + File.separator +certInfo.getMerchatCode()+"_"+certInfo.getCertCode()+".properties");
					zipOutputStream.putNextEntry(zipEntry);
					IOUtils.writeLines(lines, "\r\n", zipOutputStream, Charsets.UTF_8.displayName());
					zipOutputStream.closeEntry();

					if ("0".equals(certInfo.getEndecryType())){//证书
						byte[] content =  tfsOperator.getTfsByteById(certInfo.getCertId());
						ZipEntry certZipEntry = new ZipEntry("cert" + File.separator  + certInfo.getMerchatCode()+"_"+certInfo.getCertCode()+"_" + certInfo.getFileName());
						zipOutputStream.putNextEntry(certZipEntry);
						IOUtils.write(content, zipOutputStream);
						zipOutputStream.closeEntry();
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (zipOutputStream != null){
					zipOutputStream.close();
				}
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
