package com.ink.balance.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ink.balance.core.manager.impl.ChannelDataManagerImpl;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.base.log.util.YinkerLogger;

/**
 * 
 * <p>Title:rarUtil</p>
 * <p>Description:解压zip、rar工具类</p>
 * <p>Company:</p>
 * 
 * @author zhaojie
 * @date 2016年6月29日 上午11:01:42
 */
public class rarUtil {
	
	/**
	 * 解压文件到指定目录
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	public static void unZipFiles(String oldDir,String newDir)throws IOException{ 
		YinkerLogger loger = YinkerLogger.getLogger(ChannelDataManagerImpl.class);
		
		long startTime=System.currentTimeMillis();  
		
	    try {  
	    	loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
	                "开始解压渠道数据文件，路径:"+oldDir, null);
	        ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
	        		oldDir));//输入源zip路径  
	        BufferedInputStream Bin=new BufferedInputStream(Zin);  
	        String Parent=newDir; //输出路径（文件夹目录）  
	        File Fout=null;  
	        ZipEntry entry;  
	        try {  
	            while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
	                Fout=new File(Parent,entry.getName());  
	                if(!Fout.exists()){  
	                    (new File(Fout.getParent())).mkdirs();  
	                }  
	                FileOutputStream out=new FileOutputStream(Fout);  
	                BufferedOutputStream Bout=new BufferedOutputStream(out);  
	                int b;  
	                while((b=Bin.read())!=-1){  
	                    Bout.write(b);  
	                }  
	                Bout.close();  
	                out.close();  
	            }  
	            Bin.close();  
	            Zin.close();  
	        } catch (IOException e) {  
	        	loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS, e.getMessage()
	                    , null);
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    } catch (FileNotFoundException e) {  
	        // TODO Auto-generated catch block  
	    	loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS, e.getMessage()
                    , null);
	    }  
	    long endTime=System.currentTimeMillis();  
	    loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                "解压"+oldDir+"文件成功,耗费时间："+(endTime-startTime), null);
	}  

}
