/**
 * 
 */
package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Test;

import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author Administrator
 *
 */
public class TfsManager_13_saveLargeFile extends TfsBaseCase {
	public String suffix = ".jpg";
	public String TfsName_temp = null;
	public String TfsName_with_suffix = null;
	public String TfsName_without_suffix = null;
	public boolean bRet = false;
	public int iRet = -1;
	
	private int saveFileToTFS(String localFile, String tfsName, String suffix){
		String tfsFile = "";
		long localCrc = 0;
		long tfsCrc = 0;
		/* Save file */
		tfsFile = tfsManager.saveLargeFile(localFile, tfsName, suffix);
		
		/* Check file name */
		iRet = checkTfsName(tfsFile, LARGE);
		if (iRet != INFOSUCCESS) return iRet;
		
		/* Check CRC */
		localCrc = getCrc(localFile).getValue();
		tfsCrc = getTfsCrc(tfsFile, suffix, LARGE).getValue();
		if (localCrc != tfsCrc)
		{
			log.error("Local file's crc(" + localCrc + ") is different from tfs file's crc(" + tfsCrc + ")!!!");
			return ERRTFSCRC;
		} else {
			log.debug("Local file's crc(" + localCrc + ") is same as tfs file's crc(" + tfsCrc + ").");
		}
		return INFOSUCCESS;
	}
	
	@Test
	public void test_01_saveLargeFile_with_suffix_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_02_saveLargeFile_with_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_04_saveLargeFile_with_suffix_large_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "1g.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_05_saveLargeFile_with_null_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_06_saveLargeFile_with_tfsName_max_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_07_saveLargeFile_with_null_tfsName_max_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_08_saveLargeFile_with_empty_localFile_null_tfsName(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS("", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_09_saveLargeFile_with_null_localFile_null_tfsName(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS(null, TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_10_saveLargeFile_with_wrong_localFile_null_tfsName(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS("wrong", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
}
