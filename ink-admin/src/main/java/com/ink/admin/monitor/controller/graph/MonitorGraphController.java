package com.ink.admin.monitor.controller.graph;

import com.ink.base.BaseController;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.monitor.core.po.MonitorServiceRecord;
import com.ink.monitor.core.po.SystermSource;
import com.ink.monitor.core.query.SystermSourceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.*;

@RequestMapping("monitor/graph")
@Controller("monitorGraphCOntroller")
public class MonitorGraphController extends BaseController{

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	/**
	 * 服务监控图表
	 * @return
	 */
	@RequestMapping("/monitorGraph")
	public ModelAndView tradeGraph(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/monitor/graphs/monitorGraph");
		return modelAndView;		
	}

	/**
	 * 服务监控图表数据
	 * @return
	 */
	@RequestMapping("/getSysMsg")
	@ResponseBody
	public Map<Object, Object> getSysMsg(){
		SystermSourceQuery query = newQuery(SystermSourceQuery.class,null);
		//根据平台编号查询，总错误次数
		List<MonitorServiceRecord>  soveleErro = new ArrayList<>();
		List<MonitorServiceRecord>  unSoveled = new ArrayList<>();
		List<SystermSource> sys = null;
		try {
			sys = monitorDubboBaseService.find("systermSourceManager",query);
			for(SystermSource source :sys){
				String code =source.getCode();
				List<MonitorServiceRecord> errs = (List<MonitorServiceRecord>) monitorDubboBaseService.
						executeDynamicMethod("monitorServiceRecordManager","getErrCountByStatus",code);
				if(errs.size()>0){
					for(MonitorServiceRecord msr :errs){
						if(msr.getStatus().equals("1")){
							//已解决
							soveleErro.add(msr);
						}else if (msr.getStatus() .equals("0")){
							//未解决
							unSoveled.add(msr);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap<Object, Object> map = new HashMap<>();
		map.put("soveleErro", soveleErro);
		map.put("unSoveled", unSoveled);
		return map;
	}

	/**
	 * 系统服务数据:当天的数据
	 * @param sysCode
	 * @return
	 */
	@RequestMapping("/getSysSource")
	@ResponseBody
	public List<MonitorServiceRecord> getSysSources(String sysCode){

		List<MonitorServiceRecord> list = null;
		try {
			list = (List<MonitorServiceRecord>) monitorDubboBaseService.executeDynamicMethod("monitorServiceRecordManager","getMonitorService",sysCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list ;
	}

	@RequestMapping("/monitorSysGraph")
	public ModelAndView monitorSysGraph(String sysCode){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/monitor/graphs/monitorSysGraph");
		modelAndView.addObject("sysCode",sysCode);

		return modelAndView;
	}

	/**
	 * 系统波动图
	 * @return
	 */
	@RequestMapping("/sysWaveGrap")
	@ResponseBody
	public int sysWaveGrap(String sysCode){

		int count = 0;
		try {
			String minuteStr = DateUtil.getDateMinute(new Date());
			minuteStr = minuteStr + ":00";
			Date startDate = DateUtil.formatToyyyyMMddHHmmss(minuteStr);
			Date endDate = DateUtil.getBeforeMinute(startDate,-1);
			count = (int) monitorDubboBaseService.executeDynamicMethod("mongoLogChartServiceManager","countSysErrorLog",sysCode,startDate,endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	/**
	 *获取前5分钟系统错误数
	 * @param sysCode
	 * @return
     */
	@RequestMapping("/getMinuteCount")
	@ResponseBody
	public int getMinuteCount(String sysCode){

		int count = 0;
		try{
			String minuteStr = DateUtil.getDateMinute(new Date());
			minuteStr = minuteStr + ":00";
			Date endDate = DateUtil.formatToyyyyMMddHHmmss(minuteStr);
			Date startDate = DateUtil.getBeforeMinute(endDate,5);
			count = (int) monitorDubboBaseService.executeDynamicMethod("mongoLogChartServiceManager","countSysErrorLog",sysCode,startDate,endDate);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	/**
	 *获取前1个小时系统错误数
	 * @param sysCode
	 * @return
	 */
	@RequestMapping("/getHourCount")
	@ResponseBody
	public int getHourCount(String sysCode){

		int count = 0;
		try{
			String minuteStr = DateUtil.getDateMinute(new Date());
			minuteStr = minuteStr + ":00";
			Date endDate = DateUtil.formatToyyyyMMddHHmmss(minuteStr);
			Date startDate = DateUtil.getBeforeHour(endDate,1);
			count = (int) monitorDubboBaseService.executeDynamicMethod("mongoLogChartServiceManager","countSysErrorLog",sysCode,startDate,endDate);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	/**
	 *获取前一天系统错误数
	 * @param sysCode
	 * @return
	 */
	@RequestMapping("/getDayCount")
	@ResponseBody
	public int getDayCount(String sysCode){

		int count = 0;
		try{
			String minuteStr = DateUtil.getDateMinute(new Date());
			minuteStr = minuteStr + ":00";
			Date endDate = DateUtil.formatToyyyyMMddHHmmss(minuteStr);
			Date startDate = DateUtil.getBeforeDay(endDate,1);
			count = (int) monitorDubboBaseService.executeDynamicMethod("mongoLogChartServiceManager","countSysErrorLog",sysCode,startDate,endDate);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}
}

