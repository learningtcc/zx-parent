
package com.ink.balance.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.DifferenceParamInput;
import com.ink.balance.api.model.in.OperationLogInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CheckBalanceOutput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CheckDifferenceOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.api.service.ICheckBalanceService;
import com.ink.balance.api.service.ICheckDifferenceService;
import com.ink.balance.api.service.IOperationLogService;
import com.ink.balance.base.ObjectJson;
import com.ink.balance.utils.ExcelUtils;
import com.ink.balance.vo.input.DifferenceParamVo;
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

/**
 * @author bo.chen
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("CheckDifference")
@Controller
public class CheckDifferenceController extends BaseController {

    YinkerLogger log = YinkerLogger.getLogger(CheckDifferenceController.class);

    @Autowired
    @Qualifier("checkDifferenceService")
    private ICheckDifferenceService checkDifferenceService;//差异信息服务

    @Autowired
    @Qualifier("operationLogService")
    private IOperationLogService operationLogService;//差异信息服务

    @Autowired
    @Qualifier("checkBalanceService")
    private ICheckBalanceService checkBalanceService;//调账表信息服务
    
    @Autowired
    @Qualifier("channelParamService")
    private IChannelParamService channelParamService;//渠道信息服务

    protected static final String LIST_JSP = "/CheckBalance/show";

    @RequestMapping("difference_view")
    public String differenceView() {
        return "difference/difference_view";
    }

    /**
     * @param paramVo
     * @param pageSize
     * @param pageNumber
     * @return
     * @Description 差异信息分页查询
     * @author xuguoqi
     * @date 2016年4月18日 下午5:30:21
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "list")
    public ModelAndView pageQuery(@ModelAttribute DifferenceParamVo paramVo, Integer pageSize, Integer pageNumber) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异分页查询结束开始参数为: param=" + paramVo);
        //参数回写到页面
        HttpServletRequest request = ServletControllerContext.getRequest();
        Map params = WebUtils.getParametersStartingWith(request, "");
        WebUtils.exposeRequestAttributes(request, params);

        long startTime = System.currentTimeMillis();
        PageParamInput pageParam = new PageParamInput();
        if (null == pageSize || null == pageNumber) {//页面初始化
            pageSize = 10;
            pageNumber = 1;
        }
        //参数转化封装
        pageParam.setPageNum(pageNumber);
        pageParam.setNumPerPage(pageSize);
        CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>> ret;
        DifferenceParamInput diffParam = BeanCopyConverter.converterClass(paramVo, DifferenceParamInput.class);
        ModelAndView modelAndView = new ModelAndView();
        Page<CheckDifferenceOutput> page = new Page<>(pageNumber, pageSize);
        //调用服务
        try {
            ret = checkDifferenceService.pageQuery(diffParam, pageParam);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异分页查询dubbo服务异常:" + e.getMessage(), e, null);
            page.setResult(new ArrayList<CheckDifferenceOutput>());
            modelAndView.addObject("page", page);
            return modelAndView;
        }
        CheckCommonPageOutput<CheckDifferenceOutput> retPage = ret.getBusinessObj();
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            page.setTotalCount(retPage.getTotail());
            if (CollectionUtils.isNotEmpty(retPage.getList())) {
                page.setResult(retPage.getList());
            } else {
                page.setResult(new ArrayList<CheckDifferenceOutput>());
            }
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异分页查询失败:" + ret.getMessage(), null, null);
        }
        PageParamInput paramInput=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInput);
        
        modelAndView.addObject("page", page);
        modelAndView.addObject("channelList", list);
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异分页查询结束  耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return modelAndView;
    }

    /**
     * @param id
     * @return
     * @Description 获取差异信息明细
     * @author xuguoqi
     * @date 2016年4月18日 下午5:32:24
     */
    @RequestMapping(value = "show")
    @ResponseBody
    public ModelAndView show(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异信息获取开始参数为: id=" + id);
        ModelAndView modelAndView = new ModelAndView();
        CommonOutput<CheckDifferenceOutput> ret;
        //调用dubbo服务
        try {
            ret = checkDifferenceService.getDetails(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异信息获取dubbo服务异常:" + e.getMessage(), e, null);
            modelAndView.addObject("model", new CheckDifferenceOutput());
            return modelAndView;
        }
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            modelAndView.addObject("model", ret.getBusinessObj());
            return modelAndView;
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异信息获取失败:" + ret.getMessage(), null, null);
        }
        modelAndView.addObject("model", new CheckDifferenceOutput());
        return modelAndView;
    }

    /**
     * @param platformOrderNo
     * @return
     * @Description 获取调账详情
     * @author xuguoqi
     * @date 2016年5月26日 下午5:32:24
     */
    @RequestMapping(value = "showBalance")
    @ResponseBody
    public ModelAndView showBalance(String platformOrderNo) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异信息对应调账详情开始参数为: platformOrderNo=" + platformOrderNo);
        ModelAndView modelAndView = new ModelAndView(LIST_JSP);
        CommonOutput<CheckBalanceOutput> ret;
        try {
            ret = checkBalanceService.getBalanceDetails(platformOrderNo);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异信息对应调账详情获取dubbo服务异常:" + e.getMessage(), e, null);
            modelAndView.addObject("model", new CheckBalanceOutput());
            return modelAndView;
        }
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            modelAndView.addObject("model", ret.getBusinessObj());
            return modelAndView;
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "对账差异信息对应调账详情失败:" + ret.getMessage(), null, null);
        }
        modelAndView.addObject("model", new CheckBalanceOutput());
        return modelAndView;
    }

    /**
     * @param paramVo
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @Description 导出excel文件
     * @author xuguoqi
     * @date 2016年4月20日 下午4:24:13
     */
    @RequestMapping(value = "exprot_excel")
    public String exportExcel(@ModelAttribute DifferenceParamVo paramVo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出对账差异信息开始参数为: param=" + paramVo);
        long startTime = System.currentTimeMillis();

        String fileName = "差异信息文件";
        // 调用差异信息填充数据
        List<CheckDifferenceOutput> list = createData(paramVo);

        String columnNames[] = {"渠道编号", "支付渠道商户号", "差异来源", "差异类型", "平台订单号", "金额", "处理 状态", "创建时间"};// 列名
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            //调用poi服务生成excel
            ExcelUtils.createWorkBook(list, columnNames).write(os);
        } catch (IOException e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印excel服务失败原因为:" + e.getMessage(), e, null);
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
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印excel服务失败原因为:" + e.getMessage(), e, null);
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出对账差异信息结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return null;

    }

    /**
     * @param paramVo
     * @return
     * @Description 查询单条并获取总数
     * @author xuguoqi
     * @date 2016年4月20日 下午6:50:42
     */
    @RequestMapping(value = "select_one", method = RequestMethod.POST)
    @ResponseBody
    public ObjectJson selectOne(@ModelAttribute DifferenceParamVo paramVo) {
        //定义初始数据
        int rows = 1;
        int page = 1;
        PageParamInput pageParam = new PageParamInput();
        pageParam.setPageNum(page);
        pageParam.setNumPerPage(rows);
        CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>> ret = null;
        DifferenceParamInput diffParam = BeanCopyConverter.converterClass(paramVo, DifferenceParamInput.class);
        ObjectJson json = new ObjectJson();
        try {
            ret = checkDifferenceService.pageQuery(diffParam, pageParam);
        } catch (Exception e) {
            return json;
        }
        CheckCommonPageOutput<CheckDifferenceOutput> retPage = ret.getBusinessObj();
        if (0 == ret.getCode()) {
            json.setTotal(retPage.getTotail());
            if (CollectionUtils.isNotEmpty(retPage.getList())) {
                json.setResult(retPage.getList());
            } else {
                json.setResult(new ArrayList<>());
            }
        }
        return json;
    }

    /**
     * @param paramVo
     * @return
     * @Description 获取差异信息数据库数据
     * @author xuguoqi
     * @date 2016年4月20日 下午6:50:15
     */
    private List<CheckDifferenceOutput> createData(DifferenceParamVo paramVo) {
        PageParamInput pageParam = new PageParamInput(1, Integer.MAX_VALUE);
        CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>> ret = null;
        DifferenceParamInput diffParam = BeanCopyConverter.converterClass(paramVo, DifferenceParamInput.class);
        try {
            ret = checkDifferenceService.pageQuery(diffParam, pageParam);
        } catch (Exception e) {
        	return new ArrayList<CheckDifferenceOutput>();
        }
        
        CheckCommonPageOutput<CheckDifferenceOutput> businessObj =  ret.getBusinessObj();
        List<CheckDifferenceOutput> list = businessObj.getList();
        return list;
    }

    /**
     * @param type
     * @return
     * @Description 数据字典转换(1:渠道单边，2平台单边 3、金额差错 4、状态差错)
     * @author xuguoqi
     * @date 2016年4月18日 下午5:30:46
     */
    @RequestMapping("get_diffSource")
    @ResponseBody
    public String getDiffSource(int type) {
        String name = "";
        switch (type) {
            case 1:
                name = "渠道单边";
                break;
            case 2:
                name = "平台单边";
                break;
            case 3:
                name = "金额差错";
                break;
            case 4:
                name = "状态差错";
                break;
            default:
                name = "状态差错";
                break;
        }
        return name;
    }

    /**
     * @param ids
     * @return String
     * @Description 处理双单边勾兑
     * @author bo.chen
     * @date 2016年5月18日 下午5:32:24
     */
    @RequestMapping(value = "handle2side")
    @ResponseBody
    public String handle2side(String ids) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "处理双单边情况: param=" + ids);
        Long pid;
        Long cid;
        //记录操作日志
        OperationLogInput oli = new OperationLogInput();
        oli.setTableName("check_difference");
        oli.setNewContent("处理双单边情况失败: param=" + ids);
        oli.setOperator("0");
        oli.setCreateTime(new Date());
        try {
            String[] idsArr = ids.split(",");
            Long ids1 = Long.parseLong(idsArr[0]);
            Long ids2 = Long.parseLong(idsArr[1]);
            CommonOutput<CheckDifferenceOutput> ccd1 = checkDifferenceService.getDetails(ids1);
            CheckDifferenceOutput cdo1 = ccd1.getBusinessObj();
            int source1 = cdo1.getDifferenceSource();
            CommonOutput<CheckDifferenceOutput> ccd2 = checkDifferenceService.getDetails(ids2);
            CheckDifferenceOutput cdo2 = ccd2.getBusinessObj();
            int source2 = cdo2.getDifferenceSource();
            if (source1 == 1 && source2 == 2) {
                pid = ids2;
                cid = ids1;
            } else if (source1 == 2 && source2 == 1) {
                pid = ids1;
                cid = ids2;
            } else {
                operationLogService.save(oli);
                return writeAjaxResponse("ERROR_ONE_SIDE");
            }
            int ret = checkDifferenceService.handle2oneSideToMatch(pid, cid);
            if (ret <= 0) {
                operationLogService.save(oli);
                return writeAjaxResponse("ERROR");
            }
        } catch (Exception e) {
            operationLogService.save(oli);
            return writeAjaxResponse("ERROR");
        }
        oli.setNewContent("处理双单边情况成功: param=" + ids);
        operationLogService.save(oli);
        return writeAjaxResponse("SUCCESS");
    }

    /**
     * @param id
     * @return String
     * @Description 挂起差异
     * @author bo.chen
     * @date 2016年5月30日 下午5:32:24
     */
    @RequestMapping(value = "hangup")
    @ResponseBody
    public String hangup(String id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "处理单边挂起情况: param=" + id);
        Long cdId = Long.parseLong(id);
        CommonOutput<CheckDifferenceOutput> ccd = checkDifferenceService.getDetails(cdId);
        CheckDifferenceOutput cdo = ccd.getBusinessObj();
        int type = cdo.getDifferenceType();//差异类型
        int status = cdo.getStatus();//交易状态
        int handleStatus = cdo.getHandleStatus();//处理状态
        if (type != 1 && type != 2) {//记录差异类型不符，无法挂起
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "处理单边挂起情况，记录差异类型不符，无法挂起", null, null);
            return writeAjaxResponse("ERROR_TYPE");
        }
        if (status != 3) {//交易状态非失败 无法挂起
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "处理单边挂起情况，交易状态非失败 无法挂起", null, null);
            return writeAjaxResponse("ERROR_STATUS");
        }
        if (handleStatus != 0) {//非待处理的数据，无法挂起
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "处理单边挂起情况，非待处理的数据，无法挂起", null, null);
            return writeAjaxResponse("ERROR_HANDLESTATUS");
        }
        int ret = checkDifferenceService.handle2hangUp(cdId);
        if (ret != 1) {
            return writeAjaxResponse("ERROR");
        }
        return writeAjaxResponse("SUCCESS");
    }
}
