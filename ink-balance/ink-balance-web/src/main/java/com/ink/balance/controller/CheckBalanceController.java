package com.ink.balance.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.CheckBalanceInput;
import com.ink.balance.api.model.in.CheckBalanceQueryParamInput;
import com.ink.balance.api.model.in.CheckDifferenceInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.in.PlatformDataQueryParamInput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CheckBalanceOutput;
import com.ink.balance.api.model.out.CheckBalancePageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.model.out.PlatformDataOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.api.service.ICheckBalanceService;
import com.ink.balance.api.service.ICheckDifferenceService;
import com.ink.balance.utils.ExcelUtils;
import com.ink.balance.vo.input.CheckBalanceParamVo;
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

@RequestMapping("CheckBalance")
@Controller
public class CheckBalanceController extends BaseController {

	YinkerLogger log = YinkerLogger.getLogger(CheckBalanceController.class);

    private static final String CREATE_JSP = "CheckBalance/create";

    @Autowired
    @Qualifier("checkBalanceService")
    private ICheckBalanceService checkBalanceService;//调账表信息服务

    @Autowired
    @Qualifier("checkDifferenceService")
    private ICheckDifferenceService checkDifferenceService;//差异信息服务
    
    @Autowired
	@Qualifier("channelParamService")
	private IChannelParamService channelParamService;//渠道信息服务

    /**
     * @return
     * @Description 页面跳转
     * @author xuguoqi
     * @date 2016年5月17日 下午4:04:59
     */
    @RequestMapping("check_balance_view")
    public String checkBalanceView() {
        return "checkBalance/check_balance_view";
    }


    /**
     * @param paramVo
     * @param paramVo
     * @param pageSize
     * @return
     * @Description 调账信息分页查询
     * @author bo.chen
     * @date 2016年4月28日 下午5:30:21
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "list")
    public ModelAndView pageQuery(@ModelAttribute CheckBalanceParamVo paramVo, Integer pageSize, Integer pageNumber) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据分页查询开始:param=" + paramVo);
        //参数回写到页面
        HttpServletRequest request = ServletControllerContext.getRequest();
        Map params = WebUtils.getParametersStartingWith(request, "");
        WebUtils.exposeRequestAttributes(request, params);

        long startTime = System.currentTimeMillis();
        if (null == pageSize || pageNumber == null) {//分页参数初始化
            pageSize = 10;
            pageNumber = 1;
        }
        //数据转化和封装
        ModelAndView modelAndView = new ModelAndView();
        Page<CheckBalanceOutput> page = new Page<>(pageNumber, pageSize);
        PageParamInput pageParam = new PageParamInput(pageNumber, pageSize);
        pageParam.setPageNum(pageNumber);
        pageParam.setNumPerPage(pageSize);
        CommonOutput<Object> ret;
        CheckBalanceQueryParamInput checkBalanceParam = BeanCopyConverter.converterClass(paramVo, CheckBalanceQueryParamInput.class);
        try {
            ret = checkBalanceService.pageQuery(checkBalanceParam, pageParam);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据分页查询dubbo服务异常:" + e.getMessage(), e, null);
            return modelAndView;
        }
        if (BalanceCodeUtils.SUCCESS == ret.getCode()) {
            CheckBalancePageOutput output = (CheckBalancePageOutput) ret.getBusinessObj();
            List<CheckBalanceOutput> checkBalanceList = output.getList();
            if (CollectionUtils.isNotEmpty(checkBalanceList)) {
                page.setResult(output.getList());
            } else {
                page.setResult(new ArrayList<CheckBalanceOutput>());
            }
            page.setTotalCount(output.getTotal());
            modelAndView.addObject("page", page);
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据分页查询失败:" + ret.getMessage(), null, null);
        }
        PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        modelAndView.addObject("channelList", list);
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据分页查询结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return modelAndView;

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
    public String exportExcel(@ModelAttribute CheckBalanceParamVo paramVo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出调账信息开始参数为: param=" + paramVo);
        long startTime = System.currentTimeMillis();

        String fileName = "调账信息文件";
        List<CheckBalanceOutput> list=new ArrayList<CheckBalanceOutput>();

        try {
	    	  CheckBalanceQueryParamInput checkBalanceParam = BeanCopyConverter.converterClass(paramVo, CheckBalanceQueryParamInput.class);    
	          list = checkBalanceService.findList(checkBalanceParam);
	    } catch (Exception e) {
	          log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "条件查询调账信息数据调用dubbo服务异常:" + e.getMessage(), e, null);
	    }
        
        String columnNames[] = {"调账对象", "调账方向","调账金额","渠道","平台订单号","调账状态","创建时间","更新时间","调账说明"};// 列名
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            //调用poi服务生成excel
            ExcelUtils.createCheckBalanceWorkBook(list, columnNames).write(os);
        } catch (IOException e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印调账信息excel服务失败原因为:" + e.getMessage(), e, null);
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
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "调用poi打印调账信息excel服务失败原因为:" + e.getMessage(), e, null);
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "导出调账信息结束共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        return null;

    }
    /**
     * @param id 调账表主键
     * @return
     * @Description 调账明细查询
     * @author xuguoqi
     * @date 2016年4月18日 下午4:14:36
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView show(Long id) {
        ModelAndView mav = new ModelAndView();
        CommonOutput<CheckBalanceOutput> ret;
        try {
            ret = checkBalanceService.getDetails(id);
        } catch (Exception e) {
            return mav;
        }
        CheckBalanceOutput output = ret.getBusinessObj();
        mav.addObject("model", output);
        PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        mav.addObject("channelList", list);
        return mav;
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
        String name;
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
                name = "";
                break;
        }
        return name;
    }


    /**
     * 进入新增页面
     */
    @RequestMapping(value = "/create")
    public ModelAndView create() {
    	ModelAndView modelAndView = new ModelAndView();
    	PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        modelAndView.addObject("channelList", list);
        return modelAndView;
    }
//	

    /**
     * 保存新增对象
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(CheckBalanceInput checkBalance) {
        //数据完整性校验
        /**
         * 根据1、渠道编号 2、平台订单号 3、处理状态（待处理）从差异表获取集合list
         */
        //获取调账表单数据
        int balanceSource = checkBalance.getBalanceSource();//调账对象（1：渠道、2：平台）
        int balanceDirection = checkBalance.getBalanceDirection();//调账方向（1：正、2：负）
        BigDecimal balanceAmt = checkBalance.getBalanceAmt();

        //获取差异表数据
        CheckDifferenceInput checkDifferenceApi = new CheckDifferenceInput();
        checkDifferenceApi.setChannelNo(checkBalance.getChannelNo());
        checkDifferenceApi.setPlatformOrderNo(checkBalance.getPlatformOrderNo());
        checkDifferenceApi.setHandleStatus(0);
        List<CheckDifferenceInput> checkDifferenceList = checkDifferenceService.getDifferenceListByParams(checkDifferenceApi);
        if (checkDifferenceList == null || checkDifferenceList.size() == 0) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "根据调账数据未找到可调整的差异数据记录 checkBalance：" + checkBalance.toString(), null, null);
            return writeAjaxResponse("DATA_IS_EMPTY");
        }
        int listSize = checkDifferenceList.size();
        if (listSize == 1) {
            /**
             * 单边情况：
             * （1）一方单边且支付状态为成功，则可以增加本方负记录或增加对方正记录
             * （2）一方单边且支付状态为失败，直接做挂账处理
             */
            CheckDifferenceInput cdApi = checkDifferenceList.get(0);
            int payStatus = cdApi.getStatus();//交易状态 1、待支付 2、支付成功 3、支付失败'
            BigDecimal amount = cdApi.getAmount();
            if (payStatus != 2) {
                log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "单边调账操作订单状态不正确 payStatus：" + payStatus, null, null);
                return writeAjaxResponse("PAY_STATUS_ERROR");
            }
            int differenceSource = cdApi.getDifferenceSource();//差错来源（1：渠道  2：平台）
            if (differenceSource == 1) {//渠道
                if (balanceSource == 1) {//渠道方调账，金额必为负，且数额相等
                    if (balanceDirection == 1 || balanceAmt.compareTo(amount) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误1，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                } else {//平台方调账，金额必为正，且数额相等
                    if (balanceDirection == 2 || balanceAmt.compareTo(amount) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误2，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                }
            } else {//平台
                if (balanceSource == 1) {//渠道方调账，金额必为正，且数额相等
                    if (balanceDirection == 2 || balanceAmt.compareTo(amount) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误3，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                } else {//平台方调账，金额必为负，且数额相等
                    if (balanceDirection == 1 || balanceAmt.compareTo(amount) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误4，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                }
            }
        } else if (listSize == 2) {//差异情况
            CheckDifferenceInput cdApi1 = checkDifferenceList.get(0);
            CheckDifferenceInput cdApi2 = checkDifferenceList.get(1);
            int differenceSource1 = cdApi1.getDifferenceSource();//差错来源（1：渠道  2：平台）
            int differenceSource2 = cdApi2.getDifferenceSource();
            int status1 = cdApi1.getStatus();//交易状态 1、待支付 2、支付成功 3、支付失败'
            int status2 = cdApi2.getStatus();
            if (status1 == 3 && status2 == 3) {
                log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "差异调账操作差异状态都失败，直接挂账 checkBalance：" + checkBalance.toString(), null, null);
                return writeAjaxResponse("PAY_STATUS_ERROR");
            }
            BigDecimal amount1;
            if (status1 == 3) {
                amount1 = new BigDecimal(0);
            } else {
                amount1 = cdApi1.getAmount();
            }
            BigDecimal amount2;
            if (status2 == 3) {
                amount2 = new BigDecimal(0);
            } else {
                amount2 = cdApi2.getAmount();
            }
            if (differenceSource1 == 1 && differenceSource2 == 2) {//对象1为渠道，对象2为平台
                if (balanceSource == 1) {//向渠道上调账
                    BigDecimal newAmt;
                    if (balanceDirection == 1) {//调账方向 1：正
                        newAmt = amount1.add(balanceAmt);
                    } else {//2：负
                        newAmt = amount1.subtract(balanceAmt);
                    }
                    if (newAmt.compareTo(amount2) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误5，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                } else {//向平台上调账
                    BigDecimal newAmt;
                    if (balanceDirection == 1) {//调账方向 1：正
                        newAmt = amount2.add(balanceAmt);
                    } else {//2：负
                        newAmt = amount2.subtract(balanceAmt);
                    }
                    if (newAmt.compareTo(amount1) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误6，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                }
            } else if (differenceSource1 == 2 && differenceSource2 == 1) {//对象2为渠道，对象1为平台
                if (balanceSource == 1) {//向渠道上调账
                    BigDecimal newAmt;
                    if (balanceDirection == 1) {//调账方向 1：正
                        newAmt = amount2.add(balanceAmt);
                    } else {//2：负
                        newAmt = amount2.subtract(balanceAmt);
                    }
                    if (newAmt.compareTo(amount1) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误7，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                } else {//向平台上调账
                    BigDecimal newAmt;
                    if (balanceDirection == 1) {//调账方向 1：正
                        newAmt = amount1.add(balanceAmt);
                    } else {//2：负
                        newAmt = amount1.subtract(balanceAmt);
                    }
                    if (newAmt.compareTo(amount2) != 0) {
                        log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "调账数据有误8，无法调平 checkBalance ：" + checkBalance.toString(), null, null);
                        return writeAjaxResponse("DATA_BALANCE_ERROR");
                    }
                }
            } else {
                log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "差错双方数据获取异常，差错来源有误checkBalance ：" + checkBalance.toString(), null, null);
                return writeAjaxResponse("DIFFERENCE_SOURCE_ERROR");
            }
        } else {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.BALANCE_QUERY_BUS, "所选数据异常" + checkDifferenceApi.toString(), null, null);
            return writeAjaxResponse("DATA_IS_EXCEPTION");
        }

        /**
         * 调账数据符合调账要求，依次做以下操作（一个事务）；
         * 1、插入调账记录
         * 2、修改差异记录
         * 3、修改原始数据
         * 4、修改对账总表信息
         */

        //向调账表插入记录
        int effNum = checkBalanceService.saveBalanceBatch(checkBalance);
        if (effNum == 1) {
            return writeAjaxResponse("BALANCE_SUCCESS");
        } else {
            return writeAjaxResponse("DATA_IS_EXCEPTION");
        }
    }
    
}
