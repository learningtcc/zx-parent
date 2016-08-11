
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
import com.ink.balance.api.model.in.CheckQueryParamInput;
import com.ink.balance.api.model.in.DifferenceParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.in.PlatformDataQueryParamInput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CheckChannelOutput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CheckDifferenceOutput;
import com.ink.balance.api.model.out.CheckPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.api.service.ICheckMainService;
import com.ink.balance.utils.ExcelUtils;
import com.ink.balance.vo.input.CheckChannelParamVo;
import com.ink.balance.vo.input.CheckParamVo;
import com.ink.balance.vo.input.DifferenceParamVo;
import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.log.support.ServletControllerContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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

@RequestMapping("/CheckChannel")
@Controller
public class CheckChannelController extends BaseController {

    YinkerLogger log = YinkerLogger.getLogger(CheckChannelController.class);

    @Autowired
    @Qualifier("checkMainService")
    private ICheckMainService checkMainService;//对账信息服务
    @Autowired
    @Qualifier("channelParamService")
    private IChannelParamService channelParamService;//渠道信息服务
    /**
     * @return
     * @Description 页面跳转
     * @author xuguoqi
     * @date 2016年4月14日 下午3:06:55
     */
    @RequestMapping("main_status")
    public String checkQuery() {
        return "check/main_status";
    }


    /**
     * @param paramVo
     * @param pageSize
     * @param pageNumber
     * @return
     * @Description 对账主状态月分页查询
     * @author xuguoqi
     * @date 2016年4月14日 下午2:56:52
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/list")
    public ModelAndView list(@ModelAttribute CheckChannelParamVo paramVo, Integer pageSize, Integer pageNumber) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "分页条件查询对账信息开始参数为: param=" + paramVo);
        //参数回写到页面
        HttpServletRequest request = ServletControllerContext.getRequest();
        Map params = WebUtils.getParametersStartingWith(request, "");
        WebUtils.exposeRequestAttributes(request, params);
        long startTime = System.currentTimeMillis();
        if (null == pageSize || null == pageNumber) {//初始化分页参数
            pageSize = 10;
            pageNumber = 1;
        }
        //参数分装
        ModelAndView modelAndView = new ModelAndView();
        PageParamInput pageParam = new PageParamInput(pageNumber, pageSize);
        Page<CheckChannelOutput> retPage = new Page<>(pageNumber, pageSize);
        CommonOutput<Object> ret;
        CheckQueryParamInput checkParam = BeanCopyConverter.converterClass(paramVo, CheckQueryParamInput.class);
        //调用服务
        try {
            ret = checkMainService.pageQuery(checkParam, pageParam);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "分页条件查询对账信息调用dubbo服务异常:" + e.getMessage(), e, null);
            retPage.setResult(new ArrayList<CheckChannelOutput>());
            modelAndView.addObject("page", retPage);
            return modelAndView;
        }
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            CheckPageOutput pageRet = (CheckPageOutput) ret.getBusinessObj();
            List<CheckChannelOutput> checkChannelList = pageRet.getList();

            retPage.setTotalCount(pageRet.getTotal());
            if (CollectionUtils.isNotEmpty(checkChannelList)) {
                retPage.setResult(checkChannelList);
            } else {
                retPage.setResult(new ArrayList<CheckChannelOutput>());
            }
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "分页条件查询对账信息失败:" + ret.getMessage(), null, null);
        }
        //modelAndView.setViewName();
        PageParamInput paramInput=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInput);
        modelAndView.addObject("page", retPage);
        modelAndView.addObject("channelList", list);
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "分页条件查询对账信息结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return modelAndView;
    }

    /**
     * @param id 总队长明细
     * @return
     * @Description 对账明细查询
     * @author xuguoqi
     * @date 2016年4月18日 下午4:14:36
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView show(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "获取单个对账信息开始 id=" + id);
        long startTime = System.currentTimeMillis();
        ModelAndView mav = new ModelAndView();
        CommonOutput<CheckChannelOutput> ret = null;
        try {
            ret = checkMainService.getCheckDetails(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "获取单个对账信息调用dubbo服务异常:" + e.getMessage(), e, null);
            return mav;
        }
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            CheckChannelOutput output = ret.getBusinessObj();
            mav.addObject("model", output);
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "获取单个对账信息失败:" + ret.getMessage(), null, null);
            mav.addObject("model", new CheckChannelOutput());
        }
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "获取单个对账信息结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return mav;
    }

    /**
     * @return
     * @Description TODO
     * @author xuguoqi
     * @date 2016年5月17日 下午4:03:18
     */
    @RequestMapping(value = "get_channel_list")
    public List<String> getChannelList() {
        List<String> nameList = new ArrayList<>();
        return nameList;
    }

    /**
     * 更改对账状态[批量]
     *
     * @param ids
     * @param remark
     * @return
     */
    @RequestMapping(value = "change_check_status")
    @ResponseBody
    public int changeChenkStatus(String ids, String remark) {
        if (StringUtils.isNotBlank(ids)) {
            int index = ids.lastIndexOf(",");
            String sub = ids.substring(0, index);
            String[] split = sub.split(",");
            List<Long> list = new ArrayList<>();
            for (String id : split) {
                list.add(Long.parseLong(id));
            }
            CommonOutput<Object> ret = null;
            try {
                ret = checkMainService.updateBatchStatus(list, remark);
            } catch (Exception e) {
                return list.size();
            }
            if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
                return list.size();
            }
        }

        return 0;
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
    public String exportExcel(@ModelAttribute CheckParamVo paramVo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出对账状态信息开始参数为: param=" + paramVo);
        long startTime = System.currentTimeMillis();

        String fileName = "对账状态文件";
        List<CheckChannelOutput> list = new ArrayList<CheckChannelOutput>();

        try {
        	  CheckQueryParamInput checkParam = BeanCopyConverter.converterClass(paramVo, CheckQueryParamInput.class);
              list = checkMainService.findList(checkParam);
        } catch (Exception e) {
          log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "条件查询对账状态信息调用dubbo服务异常:" + e.getMessage(), e, null);
        }
        
        String columnNames[] = {"批次号", "对账日期","交易日期","渠道","支付渠道商户","渠道总金额","渠道总笔数","平台总金额","平台总笔数","平台单边笔数","渠道单边笔数","对账结果","差异总笔数","处理状态","未处理差异笔数","创建时间","更新时间"};// 列名
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            //调用poi服务生成excel
            ExcelUtils.createCheckChanneWorkBook(list, columnNames).write(os);
        } catch (IOException e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印对账状态信息excel服务失败原因为:" + e.getMessage(), e, null);
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
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印对账状态信息excel服务失败原因为:" + e.getMessage(), e, null);
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出对账状态信息结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return null;

    }
}
