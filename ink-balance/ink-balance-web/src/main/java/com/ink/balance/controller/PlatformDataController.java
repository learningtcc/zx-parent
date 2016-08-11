
/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.balance.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.*;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CheckChannelOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.model.out.PlatformDataOutput;
import com.ink.balance.api.model.out.PlatformDataPageOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.api.service.IPlatformDataService;
import com.ink.balance.utils.ExcelUtils;
import com.ink.balance.vo.input.CheckParamVo;
import com.ink.balance.vo.input.ResidentParamVo;
import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.log.support.ServletControllerContext;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.web.util.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("PlatformData")
@Controller
public class PlatformDataController extends BaseController {


    YinkerLogger log = YinkerLogger.getLogger(PlatformDataController.class);

    private static final String CREATE_JSP = "PlatformData/insertMq";

    private static final String UPDATE_JSP = "PlatformData/updateMq";


    @Autowired(required = false)
    private IPlatformDataService platformDataService;
    @Autowired
	@Qualifier("channelParamService")
	private IChannelParamService channelParamService;//渠道信息服务
    /**
     *
     * @Description 页面跳转
     * @author xuguoqi
     * @date 2016年5月17日 下午5:00:59
     * @return
     */
    @RequestMapping("view")
    public String view() {
        return "platform/view";
    }

    /**
     * 进入新增mq页面
     */
    @RequestMapping(value = "/create")
    public ModelAndView create() {
        return new ModelAndView(CREATE_JSP);
    }

    /**
     * 进入更新mq页面
     */
    @RequestMapping(value = "/update")
    public ModelAndView update() {
        return new ModelAndView(UPDATE_JSP);
    }

    /**
     *
     * @Description 分页查询平台交易数据
     * @author xuguoqi
     * @date 2016年5月17日 下午5:01:14
     * @param paramVo
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "list")
    public ModelAndView list(@ModelAttribute ResidentParamVo paramVo, Integer pageSize, Integer pageNumber) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "分页条件查询平台交易数据开始参数为: param=" + paramVo);
        //参数回写到页面
        HttpServletRequest request = ServletControllerContext.getRequest();
        Map params = WebUtils.getParametersStartingWith(request, "");
        WebUtils.exposeRequestAttributes(request, params);

        long startTime = System.currentTimeMillis();
        //初始化分页参数
        if (null == pageSize || null == pageNumber ) {
            pageSize = 10;
            pageNumber = 1;
        }
        //数据封装转化
        ModelAndView modelAndView = new ModelAndView();
        Page<PlatformDataOutput> page = new Page<>(pageNumber, pageSize);
        PageParamInput paramInput = new PageParamInput(pageNumber, pageSize);
        CommonOutput<Object> ret = null;
        //功能调用
        try {
            PlatformDataQueryParamInput platformParam = BeanCopyConverter.converterClass(paramVo, PlatformDataQueryParamInput.class);
            ret = platformDataService.pageQuery(platformParam, paramInput);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "分页条件查询平台交易数据调用dubbo服务异常:" + e.getMessage(), e, null);
            modelAndView.addObject("page", page);
        }
        //分会返回信息转化
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            PlatformDataPageOutput pageOutput = (PlatformDataPageOutput) ret.getBusinessObj();
            page.setTotalCount(pageOutput.getTotal());
            List<PlatformDataOutput> platList = pageOutput.getList();
            if (CollectionUtils.isNotEmpty(platList)) {
                page.setResult(platList);
            } else {
                page.setResult(new ArrayList<PlatformDataOutput>());
            }
            modelAndView.addObject("page", page);
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "分页条件查询平台交易数据失败:" + ret.getMessage(), null, null);
        }
        PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        modelAndView.addObject("channelList", list);
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "分页条件查询台交易数据结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return modelAndView;
    }

    /**
     *
     * @Description 获取平台信息详情
     * @author bo.chen
     * @date 2016年4月19日 下午5:32:24
     * @param id
     * @return
     */
    @RequestMapping(value = "show")
    @ResponseBody
    public ModelAndView show(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "查询单个平台交易数据开始参数为: id=" + id);
        long startTime = System.currentTimeMillis();

        ModelAndView model = new ModelAndView();
        CommonOutput<PlatformDataOutput> ret = null;
        //功能调用
        try {
            ret = platformDataService.getDetails(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "查询单个平台交易数据调用dubbo服务异常:" + e.getMessage(), e, null);
            model.addObject("model", new PlatformDataOutput());
            return model;
        }
        //返回信息转化
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            model.addObject("model", ret.getBusinessObj());
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "查询单个平台交易数据调用失败:" + ret.getMessage(), null, null);
            model.addObject("model", new PlatformDataOutput());
        }
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "查询单个平台交易数据结束共"
                + "耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return model;
    }
    /**
     * 数据导出
     * @param paramVo
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "exprot_excel")
    public String exportExcel(@ModelAttribute ResidentParamVo paramVo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出平台交易信息开始参数为: param=" + paramVo);
        long startTime = System.currentTimeMillis();

        String fileName = "平台交易信息文件";
        List<PlatformDataOutput> list=new ArrayList<PlatformDataOutput>();
        
        try {
        	  PlatformDataQueryParamInput platformParam = BeanCopyConverter.converterClass(paramVo, PlatformDataQueryParamInput.class);
              list = platformDataService.findList(platformParam);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "条件查询平台交易数据调用dubbo服务异常:" + e.getMessage(), e, null);
        }
        
        String columnNames[] = {"支付渠道","支付渠道商户","平台订单号","支付金额", "到账金额","支付生成时间","到账时间","交易状态","对账状态","驻留标识","驻留截止日","创建时间","更新时间"};// 列名
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            //调用poi服务生成excel
            ExcelUtils.createPlatformDataWorkBook(list, columnNames).write(os);
        } catch (IOException e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印平台交易数据excel服务失败原因为:" + e.getMessage(), e, null);
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes("gb2312"), "ISO8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印平台交易数据excel服务失败原因为:" + e.getMessage(), e, null);
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出平台交易数据结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return null;

    }

    /**
     *
     * @Description 插入mq消息
     * @author bo.chen
     * @date 2016年7月20日 下午12:53:10
     * @param param
     */
    @RequestMapping(value="insertMq")
    public String insertMq(@ModelAttribute PlatformDataMqParamInput param) throws IllegalStateException, IOException {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "插入mq消息param："+param.toString());
        String ret=platformDataService.insertMq(param);
        if (ret.equals("SUCCESS")) {
            return writeAjaxResponse("SUCCESS");
        }else{
            return writeAjaxResponse("ERROR");
        }
    }

    /**
     *
     * @Description 更新mq消息
     * @author bo.chen
     * @date 2016年7月20日 下午12:53:10
     * @param param
     */
    @RequestMapping(value="updateMq")
    public String updateMq(@ModelAttribute PlatformDataMqParamInput param) throws IllegalStateException, IOException {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "更新mq消息param："+param.toString());
        String ret=platformDataService.updateMq(param);
        if (ret.equals("SUCCESS")) {
            return writeAjaxResponse("SUCCESS");
        }else{
            return writeAjaxResponse("ERROR");
        }
    }
}
