package com.ink;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.core.manager.IBalanceDataManager;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.core.manager.IChannelParamManager;
import com.ink.balance.core.manager.ICheckChannelManager;
import com.ink.balance.core.manager.IPlatformDataManager;
import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.balance.core.util.VeDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:yinker-balance-core.xml")
public class BalanceTest {

	@Autowired
	private IChannelDataManager channelDataManager;

	@Autowired
	private IPlatformDataManager platformDataManager;

	@Autowired
	private IBalanceDataManager balanceDataManager;

	@Autowired
	private IChannelParamManager channelParamManager;
	
	@Autowired
	private ICheckChannelManager checkChannelManager;
	
	
	/** 支付金额 */
	private BigDecimal amt;

	/** 到账金额 */
	private BigDecimal arrivedAmt;

	/** 支付渠道（1：CMBC） */
	private String channelNo;

	/** 平台订单号 */
	private String platformOrderNo;

	/** 交易流水号 */
	private String transNo;

	/** 支付生成时间 */
	private Date payTime;

	/** 到账时间 */
	private Date arrivedTime;

	@Test
	public void channelDataTest() {
		ChannelDataQuery query = new ChannelDataQuery();
		query.setChannelNo("chno00001");
		List<ChannelData> channelDataList = channelDataManager
				.getChannelDataList(query);
		for (ChannelData channelData : channelDataList) {
			System.out.println(channelData.toString());
		}
	}
	@Test
	public void checkChannelTest() {
		CheckChannel checkChannel=new CheckChannel();
		checkChannel.setChannelNo("5455555");
		checkChannel.setTradeDate(new Date());
		checkChannelManager.insertCheckChannel(checkChannel);
		System.out.println(checkChannel.getId());
	}
	/*@Test
	public void platformDataTest() {
		PlatformDataQuery query = new PlatformDataQuery();
		query.setChannelNo("10002");
		List<PlatformData> platformDataList = platformDataManager
				.getPlatformDataList(query);
		for (PlatformData platformData : platformDataList) {
			System.out.println(platformData.toString());
		}
	}*/
	

	@Test
	public void platformDataTest() {
	PlatformData pd = new PlatformData();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Map<String, Object> params =new HashMap<String, Object>();
		
		
		params.put("amt", "10.00");
		params.put("arrivedAmt", "10.00");
		params.put("channelNo", "1111");
		params.put("platformOrderNo", "1111");
		params.put("transNo", "1111");
		params.put("payTime", "2016-03-03 12:00:00");
		params.put("arrivedTime", "2016-03-03 12:00:00");
		if (params != null) {
			amt = new BigDecimal(params.get("amt").toString());
			arrivedAmt = new BigDecimal(params.get("arrivedAmt").toString());
			channelNo = (String) params.get("channelNo");
			platformOrderNo = (String) params.get("platformOrderNo");
			transNo = (String) params.get("transNo");
			try {
				payTime = sdf.parse(params.get("payTime")+"");
				arrivedTime = sdf.parse(params.get("arrivedTime")+"");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pd.setArrivedAmt(arrivedAmt);
			pd.setAmt(amt);
			pd.setChannelNo(channelNo);
			pd.setPlatformOrderNo(platformOrderNo);
			pd.setTransNo(transNo);
			pd.setPayTime(payTime);
			pd.setArrivedTime(arrivedTime);
		}
		int insNum = platformDataManager.savePlatformData(pd);
	}
	/**
	 * 实际的对账算法测试
	 */
	@Test
	public void balanceDataTest() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		String defaultStartDate = sdf.format(dBefore); // 格式化前一天
		String defaultEndDate = sdf.format(dNow);
		// 交易日期
		Date tradeDate = VeDate.strToDate(defaultStartDate);
		// 对账日期
		Date checkDate = VeDate.strToDate(defaultEndDate);

		String channelNo = SysParamConst.CMBC_CHANNEL_NO;

		ChannelParamQuery cpq = new ChannelParamQuery();
		cpq.setNo(channelNo);

		ChannelParam cp = channelParamManager.getChannelParam(cpq);

		CheckChannel cc = new CheckChannel();
		cc.setChannelNo(channelNo);
		cc.setTradeDate(tradeDate);
		cc.setCheckDate(checkDate);
		cc.setChannelMerchantNo(cp.getChannelMerchantNo());
		balanceDataManager.executeBalanceData(cc);
	}
}