package com.ink.balance.core.manager.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ink.balance.core.batch.job.FileSystemResource;
import com.ink.balance.core.batch.job.yinker.YinkerBatchJob;
import com.ink.balance.core.util.oss.OSSUtil;
import com.ink.base.log.util.YinkerLogger;
import com.ink.tfs.client.TFSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.ink.balance.core.batch.job.BooFooDataBatchJob;
import com.ink.balance.core.batch.job.ChannelDataBatchJob;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.balance.core.dao.IChannelDataDao;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.core.util.rarUtil;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:36:38
 * @description 描述：渠道数据manager实现
 */
@Service("channelDataManager")
public class ChannelDataManagerImpl extends BaseManager<ChannelData, Long>
        implements IChannelDataManager {

    YinkerLogger loger = YinkerLogger.getLogger(ChannelDataManagerImpl.class);

    private final String resource = SysParamConst.CHANNEL_ADDRESS_URL;

    @Autowired
    private IChannelDataDao channelDataDao;
    @Autowired
    private YinkerBatchJob yinkerBatchJob;
    @Autowired
    private ChannelDataBatchJob channelDataBatchJob;
    @Autowired
    private BooFooDataBatchJob booFooDataBatchJob;
    @Autowired
    private TFSOperator tfsOperator;

    @Autowired
    private OSSUtil oSSUtil;

    @Autowired
    private FileSystemResource res;


    @Override
    protected EntityDao<ChannelData, Long> getEntityDao() {
        return this.channelDataDao;
    }

    public void setChannelDataDao(IChannelDataDao channelDataDao) {
        this.channelDataDao = channelDataDao;
    }

    @Override
    public List<ChannelData> find(PageRequest arg0) {
        return null;
    }

    @Override
    public Page<ChannelData> findPage(PageRequest arg0) {
        return super.findPage(arg0);
    }

    @Override
    public ChannelData getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ChannelData getById(Long arg0, boolean arg1)
            throws DataAccessException {
        return null;
    }

    @Override
    public int removeById(Long arg0) {
        return 0;
    }

    @Override
    public int save(ChannelData arg0) {
        return 0;
    }

    @Override
    public int saveBatch(List<ChannelData> arg0) {
        return 0;
    }

    @Override
    public int update(ChannelData arg0) {
        return 0;
    }

    /**
     * 根据主键获取ChannelData对象
     */
    @Override
    public ChannelData selectByPrimaryKey(Long id) {
        return channelDataDao.selectByPrimaryKey(id);
    }

    /**
     * 获取渠道数据List
     */
    @Override
    public List<ChannelData> getChannelDataList(ChannelDataQuery query) {
        return channelDataDao.getChannelDataList(query);
    }

    /**
     * 根据平台订单号更改渠道数据
     */
    @Override
    public int updateChannelByPlatformOrderNo(ChannelDataQuery query) {
        query.setUpdateTime(new Date());
        return channelDataDao.updateChannelByPlatformOrderNo(query);
    }

    /**
     * 获取渠道数据
     */
    @Override
    public ChannelData getChannelData(ChannelDataQuery query) {
        List<ChannelData> list = getChannelDataList(query);
        ChannelData channelData = null;
        if (list != null && list.size() > 0) {
            channelData = list.get(0);
        }
        return channelData;
    }

    /**
     * 获取渠道本次对账数据List（包含前期驻留数据）
     */
    @Override
    public List<ChannelData> getChannelBalanceList(ChannelDataQuery query) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "获取渠道数据开始，入参:" + query.toString(), null);
        try {
            return channelDataDao.getChannelBalanceList(query);
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "获取渠道数据异常", e, null);
            return null;
        }
    }

    /**
     * 获取渠道对账总笔数和总金额
     */
    @Override
    public CheckChannel getChannelSumAmtAndCount(ChannelDataQuery query) {
        return channelDataDao.getChannelSumAmtAndCount(query);
    }


    /**
     * spring batch读取渠道文件入库
     */
    @Override
    public void readBatchChannelData(CheckChannelParamInput checkChannel) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                "执行readBatchChannelData()方法开始，获取渠道数据", null);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 设置时间格式
        String defaultStartDate = sdf.format(checkChannel.getCheckDate()); 
        String channel=checkChannel.getChannelNo()==null?SysParamConst.CMBC_CHANNEL_NO:checkChannel.getChannelNo();
        String channelMerchantNo=checkChannel.getChannelMerchantNo()==null?SysParamConst.CMBC_JLC_MERCHANT_NO:checkChannel.getChannelMerchantNo();
        try {
            res.setPath(SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + "00" + defaultStartDate+".txt");
           /* loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                    "TFS下载" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] + "；对账日期："+defaultStartDate+"到本地服务器成功并开始进行入库操作。。。。", null);*/
            //从oss上下载民生对账文件开始
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                    "民生OSS下载" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；对账日期："+defaultStartDate+"到本地服务器开始下载。。。。", null);
            byte[] fileBytes = oSSUtil.getFile("usr/local/file/cmbc",channel + channelMerchantNo + "00" + defaultStartDate+".txt");
            File file = new File("/server/yinker_balance/channel_data/" + channel + channelMerchantNo + "00" + defaultStartDate+".txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileBytes);
            fos.close();
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                    "民生OSS下载" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；对账日期："+defaultStartDate+"到本地服务器下载完成。。。。", null);
            //从oss上下载民生对账文件结束
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                    "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型：00；对账日期："+defaultStartDate+"开始进行入库操作。。。。", null);
            String resourcePath = resource + channel + channelMerchantNo + "00" +  defaultStartDate + ".txt";
            channelDataBatchJob.performJob(resourcePath);
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                    "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型：00；对账日期："+defaultStartDate+"数据入库成功", null);
            
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS, e.getMessage()
                    , null);
        }

    }
    /**
     * 
    * @Title: readBatchBooChannelData 
    * @Description: 读取宝付渠道数据
    * @param @param checkChannel
    * @return void 
    * @author zhaojie
    * @date 2016年7月14日 下午5:45:20
    * @throws
     */
    @Override
    public void readBatchBooChannelData(CheckChannelParamInput checkChannel) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                "执行readBatchBooChannelData()方法开始，获取渠道数据", null);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 设置时间格式
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        String defaultStartDate = sdf.format(checkChannel.getCheckDate()); 
        String defaultDate = sdfs.format(checkChannel.getCheckDate()); 
        String channel=checkChannel.getChannelNo()==null?SysParamConst.BOOFOO_CHANNEL_NO:checkChannel.getChannelNo();
        String channelMerchantNo=checkChannel.getChannelMerchantNo()==null?SysParamConst.BOOFOO_MERCHANT_NO:checkChannel.getChannelMerchantNo();
        String[] type= new String[2]; 
        String[] typeTwo= new String[2];
		type[0]="01";
		type[1]="02";
		typeTwo[0]="fi";
		typeTwo[1]="fo";
        
        try {
        	for(int i=0;i<type.length;i++){
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "宝付OSS下载" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] + "；对账日期："+defaultStartDate+"到本地服务器开始下载。。。。", null);
                byte[] fileBytes = oSSUtil.getFile("usr/local/file/boofoo",channel + channelMerchantNo + type[i] + defaultStartDate+".zip");
                File file = new File("/server/yinker_balance/channel_data/" + channel + channelMerchantNo + type[i] + defaultStartDate+".zip");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileBytes);
                fos.close();
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "宝付OSS下载" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] + "；对账日期："+defaultStartDate+"到本地服务器下载完成。。。。", null);
        		loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                "开始解压" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] + "；对账日期："+defaultStartDate+"文件。。。", null);
        		
        		rarUtil.unZipFiles(SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + type[i] + defaultStartDate+".zip", SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + type[i] + defaultStartDate);
        		
        		loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "解压" + "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] +"；对账日期："+defaultStartDate+"文件完成", null);
    			
                res.setPath(SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + type[i] + defaultStartDate+"/"+typeTwo[i] +"_dz_" + channelMerchantNo + "_"+defaultDate+".txt");
                
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] + "；对账日期："+defaultStartDate+"开始进行入库操作。。。。", null);
                String resourcePath = resource + channel + channelMerchantNo + type[i] + defaultStartDate+"/"+typeTwo[i] +"_dz_" + channelMerchantNo + "_"+defaultDate+".txt";
            	if(type[i].equals("01")){
            		booFooDataBatchJob.performJob(resourcePath,"boofooDataItemJob");
            	}else if(type[i].equals("02")){
            		booFooDataBatchJob.performJob(resourcePath,"boofooDzDataItemJob");
            	}
            	
               
            	loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "渠道编号："+channel +"；商户号："+ channelMerchantNo+"；支付类型："+ type[i] + "；对账日期："+defaultStartDate+"数据入库成功", null);
                
                /* File file = new File("/tmp/" + channel + channelMerchantNo + defaultStartDate + ".txt");
                res.setPath("D:/"+ channel +channelMerchantNo + defaultStartDate + ".txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileBytes);
                fos.close();*/
        	}
            
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS, e.getMessage()
                    , null);
        }

    }
    /**
     * 
    * @Title: readBatchYinkerData 
    * @Description: 读取银客对账数据
    * @param @param checkChannel
    * @return void 
    * @author zhaojie
    * @date 2016年7月12日 上午11:03:55
    * @throws
     */
    @Override
    public void readBatchYinkerData(CheckChannelParamInput checkChannel) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                "执行readBatchYinkerData()方法开始，获取渠道数据", null);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 设置时间格式
        String defaultStartDate = sdf.format(checkChannel.getCheckDate()); 
        String channel=checkChannel.getChannelNo()==null?SysParamConst.CMBC_CHANNEL_NO:checkChannel.getChannelNo();
        String channelMerchantNo=checkChannel.getChannelMerchantNo()==null?SysParamConst.CMBC_JLC_MERCHANT_NO:checkChannel.getChannelMerchantNo();
        String[] type=new String[4]; 
    	type[0]="RECHARGE";
    	type[1]="WITHDRAW";
    	type[2]="TRANSACTION";
    	type[3]="COMMISSION";
        
        /**
         *tfs下载对账文件
         */
        try {
        	/*try {
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "开始从TFS下载渠道文件.....", null);
                byte[] fileBytes = getTfsFile(channel +channelMerchantNo + "00" + defaultStartDate + ".rar", "1001", "10002");
                File file = new File("/tmp/" + channel + channelMerchantNo + "00" + defaultStartDate + ".rar");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileBytes);
                fos.close();
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "从TFS下载渠道文件成功", null);
            } catch (IOException e) {
                loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS, e.getMessage()
                        , null);
            }*/
        	rarUtil.unZipFiles(SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + "00" + defaultStartDate + ".rar", SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + "00" + defaultStartDate );
        	for(int i=0;i<type.length;i++){
        	/*	loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "开始执行TFS下载银客懒猫"+ type[i] + defaultStartDate+"文件到本地服务器。。。", null);*/
                res.setPath( SysParamConst.CHANNEL_ADDRESS_URL + channel + channelMerchantNo + "00" + defaultStartDate+"/" + defaultStartDate + "_3000000022_"+type[i]+".txt");
               /* loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "TFS下载银客懒猫" +  type[i] + defaultStartDate+"到本地服务器成功并开始进行入库操作。。。。", null);*/
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        "银客懒猫；支付类型："+ type[i]+"；对账日期："+defaultStartDate+"开始进行入库操作。。。。", null);
                String resourcePath = resource + channel + channelMerchantNo + type[i] +  defaultStartDate + ".txt";
                if(type[i].equals("RECHARGE")){
                	yinkerBatchJob.performJob(resourcePath,"yinkerRechargeItemJob");
            	}else if(type[i].equals("WITHDRAW")){
            		yinkerBatchJob.performJob(resourcePath,"yinkerWithdrawItemJob");
            	}else if(type[i].equals("TRANSACTION")){
            		yinkerBatchJob.performJob(resourcePath,"yinkerTransactionItemJob");
            	}else if(type[i].equals("COMMISSION")){
            		yinkerBatchJob.performJob(resourcePath,"yinkerCommissionItemJob");
            	}
                loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                        type[i] + defaultStartDate+"数据入库成功", null);
                
                /* File file = new File("/tmp/" + channel + channelMerchantNo + defaultStartDate + ".txt");
                res.setPath("D:/"+ channel +channelMerchantNo + defaultStartDate + ".txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileBytes);
                fos.close();*/
        	}
            
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS, e.getMessage()
                    , null);
        }

    }
    /**
     * @param fileName
     * @param sourceCode
     * @param moduleCode
     * @return 获取对账文件字节
     * @throws IOException
     */
    public byte[] getTfsFile(String fileName, String sourceCode, String moduleCode) throws IOException {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS,
                "TFS下载.调用tfsOperator的getTfsByte()方法开始：【fileName=" + fileName + ";sourceCode:" + sourceCode + ";moduleCode:" + moduleCode + "】", null);
        byte[] content = tfsOperator.getTfsByte(fileName, sourceCode, moduleCode);
        if (content == null || content.length <= 0) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.TFS_DATA_BUS, "TFS下载文件失败！【fileName=" + fileName + ";sourceCode:" + sourceCode + ";moduleCode:" + moduleCode + "】"
                    , null);
        }
        return content;
    }

}
