/**
 * 
 */
package com.taobao.common.tfs.testcase;

import org.junit.Test;

import junit.framework.Assert;

import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author Administrator
 *
 */
public class TfsManager_12_saveTfsFile extends TfsBaseCase {
	
	public String suffix = ".jpg";
	public String TfsName_temp = null;
	public String TfsName_with_suffix = null;
	public String TfsName_without_suffix = null;
	public boolean bRet = false;
	public int iRet = -1;
	
	private int saveFileToTFS(String localFile, String tfsName, String suffix, int iFlag){
		String tfsFile = "";
		long localCrc = 0;
		long tfsCrc = 0;
		/* Save file */
		tfsFile = tfsManager.saveTfsFile(localFile, tfsName, suffix);
		
		/* Check file name */
		iRet = checkTfsName(tfsFile, iFlag);
		if (iRet != INFOSUCCESS) return iRet;
		
		/* Check CRC */
		localCrc = getCrc(localFile).getValue();
		tfsCrc = getTfsCrc(tfsFile, suffix, iFlag).getValue();
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
	public void test_01_saveTfsFile_with_suffix_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_02_saveTfsFile_with_suffix_equal_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_03_saveTfsFile_with_suffix_large_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "1g.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_05_saveTfsFile_without_suffix_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = "";
		iRet = saveFileToTFS(RESPATH + "1m.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_06_saveTfsFile_without_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = "";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_07_saveTfsFile_with_null_suffix_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "1m.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_08_saveTfsFile_with_null_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_09_saveTfsFile_with_empty_tfsName_null_suffix_small_size(){
		TfsName_without_suffix = "";
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_10_saveTfsFile_with_null_tfsName_null_suffix_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_11_saveTfsFile_with_null_localName_null_tfsName_null_suffix_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS(null, TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_12_saveTfsFile_with_wrong_localName_null_tfsName_null_suffix_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS("wrong", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_13_saveTfsFile_with_null_suffix_max_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_14_saveTfsFile_with_null_tfsName_null_suffix_max_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_15_saveTfsFile_with_empty_localName_null_tfsName_null_suffix_max_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS("", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_16_saveTfsFile_with_null_localName_null_tfsName_null_suffix_max_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS(null, TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_17_saveTfsFile_with_wrong_localName_null_tfsName_null_suffix_max_small_size(){
		TfsName_without_suffix = null;
		suffix = null;
		iRet = saveFileToTFS("wrong", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == ERRTFSNAMENULL);
	}
	
	@Test
	public void test_18_saveTfsFile_with_long_suffix_max_small_size(){
		TfsName_without_suffix = null;
		suffix = "kfjalskdjfalskdfj";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix, LARGE);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_19_saveTfsFile_with_long_suffix_small_size(){
		TfsName_without_suffix = null;
		suffix = "kfjalskdjfalskdfj";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_20_saveTfsFile_with_little_size(){
		TfsName_without_suffix = null;
		suffix = "kfjalskdjfalskdfj";
		iRet = saveFileToTFS(RESPATH + "1.jpg", TfsName_without_suffix, suffix, SMALL);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	

}
