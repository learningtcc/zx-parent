package com.ink.balance.controller;

import java.util.Date;
import java.util.List;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.api.service.ICheckMainService;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.vo.input.CheckChannelParamVo;
import com.ink.base.BaseController;

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

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("ManualReconciliation")
@Controller
public class ManualReconciliationController extends BaseController {

    YinkerLogger log = YinkerLogger.getLogger(ManualReconciliationController.class);

    @Autowired
    @Qualifier("checkMainService")
    private ICheckMainService checkMainService;//对账信息服务

    @Autowired
    private IChannelDataManager channelDataManager;
    @Autowired
	@Qualifier("channelParamService")
	private IChannelParamService channelParamService;//渠道信息服务
    /**
     * 
    * @Title: show 
    * @Description:界面跳转
    * @param @return
    * @return ModelAndView 
    * @author zhaojie
    * @date 2016年6月17日 下午2:32:24
    * @throws
     */
    @RequestMapping("show")
    public ModelAndView show() {
    	ModelAndView modelAndView = new ModelAndView();
    	PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        modelAndView.addObject("channelList", list);
    	return modelAndView;
    }
    /**
     * 
    * @Title: toDown 
    * @Description: 跳转对账入库
    * @param @return
    * @return ModelAndView 
    * @author zhaojie
    * @date 2016年6月21日 下午2:07:08
    * @throws
     */
    @RequestMapping("toDown")
    public ModelAndView toDown() {
    	ModelAndView modelAndView = new ModelAndView();
    	PageParamInput paramInputs=new PageParamInput();
        List<ChannelParamOutput> list=channelParamService.pageQuery(paramInputs);
        modelAndView.addObject("channelList", list);
    	return modelAndView;
    }

    /**
     * 
    * @Title: check 
    * @Description: 手工对账
    * @param @param paramVo
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年6月17日 下午2:33:00
    * @throws
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public String check(@ModelAttribute CheckChannelParamVo paramVo) {
    	log.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "进入web的check。。。", null);
    	String ret = null;
        try {
        	CheckChannelParamInput checkParam = BeanCopyConverter.converterClass(paramVo, CheckChannelParamInput.class);
        	checkParam.setCheckDate(new Date());
        	ret = checkMainService.balanceData(checkParam);
        } catch (Exception e) {
        	log.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "web系统check异常" + e.getMessage(), e, null);
        }
        return ret;
    }
    /**
     * 
    * @Title: readData 
    * @Description: 读取渠道数据
    * @param @param paramVo
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年6月21日 下午4:33:51
    * @throws
     */
    @RequestMapping(value = "readData", method = RequestMethod.POST)
    @ResponseBody
    public String readData(@ModelAttribute CheckChannelParamVo paramVo) {
    	log.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "进入web的手工对账。。。", null);
        String ret = null;
        try {
        	CheckChannelParamInput checkParam = BeanCopyConverter.converterClass(paramVo, CheckChannelParamInput.class);
        	//ret = checkMainService.readChannelData(checkParam);
                if(checkParam.getChannelNo().equals(SysParamConst.YKLM_CHANNEL_NO)){
                    channelDataManager.readBatchYinkerData(checkParam);
                }else if(checkParam.getChannelNo().equals(SysParamConst.CMBC_CHANNEL_NO)){
                    channelDataManager.readBatchChannelData(checkParam);
                }else if(checkParam.getChannelNo().equals(SysParamConst.BOOFOO_CHANNEL_NO)){
                    channelDataManager.readBatchBooChannelData(checkParam);
                }
        } catch (Exception e) {
        	log.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "web系统手工对账异常" + e.getMessage(), e, null);
            return "ERROR";
        }
        return "SUCCESS";
    }
}
