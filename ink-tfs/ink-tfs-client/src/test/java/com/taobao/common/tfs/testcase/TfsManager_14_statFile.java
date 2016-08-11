/**
 * 
 */
package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Test;

import com.taobao.common.tfs.TfsBaseCase;
import com.taobao.common.tfs.packet.FileInfo;

/**
 * @author Administrator
 *
 */
public class TfsManager_14_statFile extends TfsBaseCase {
	public String suffix = ".jpg";
	public String TfsName_temp = null;
	public String TfsName_with_suffix = null;
	public String TfsName_without_suffix = null;
	public boolean bRet = false;
	public int iRet = -1;
	long localCrc = 0;
	
	private int saveFileToTFS(String localFile, String tfsName, String suffix)
	{
		String tfsFile = "";
		
		/* Save file */
		tfsFile = tfsManager.saveTfsFile(localFile, tfsName, suffix);
		
		/* Check file name */
		iRet = checkTfsName(tfsFile, BOTH);
		if (iRet != INFOSUCCESS) return iRet;
		
		TfsName_with_suffix = tfsFile;
		
		/* Check CRC */
		localCrc = getCrc(localFile).getValue();
		
		return INFOSUCCESS;
	}
	
	private int statFileToTFS(String statFile, String statSuffix){
		FileInfo info = new FileInfo();

		info = tfsManager.statFile(statFile, statSuffix);
		if (info == null)
		{
			log.error("File info is null!!!");
			return ERRINFONULL;
		}
		
//		if (info.getCrc() != localCrc)
//		{
//			log.error("Local crc(" + localCrc + ") is different from tfs crc(" + info.getCrc() + ")");
//			return ERRTFSCRC;
//		}
		
		if (info.getFlag() != 0)
		{
			log.error("Tfs file's status flag(" + info.getFlag() + ") is not corrent!!!");
			return ERRTFSSTAT;
		}
		
		/* Hide */
		bRet = tfsManager.hideFile(statFile, suffix, 1);
		if (bRet == false)
		{
			log.error("File(" + statFile + ") is failed to hide!!!");
			return ERRHIDE;
		}
		
//		if (info.getCrc() != localCrc)
//		{
//			log.error("Local crc(" + localCrc + ") is different from tfs crc(" + info.getCrc() + ") after hiding!!!");
//			return ERRTFSCRC;
//		}
		
		info = tfsManager.statFile(statFile, statSuffix);
		if (info == null)
		{
			log.error("File info is null!!!");
			return ERRINFONULL;
		}
		
		if (info.getFlag() != 4)
		{
			log.error("Tfs file's status flag(" + info.getFlag() + ") is not corrent after hiding!!!");
			return ERRTFSSTAT;
		}
		
		/* Rehide */
		bRet = tfsManager.hideFile(statFile, suffix, 0);
		if (bRet == false)
		{
			log.error("File(" + statFile + ") is failed to rehide!!!");
			return ERRREHIDE;
		}
		
//		if (info.getCrc() != localCrc)
//		{
//			log.error("Local crc(" + localCrc + ") is different from tfs crc(" + info.getCrc() + ") after rehiding!!!");
//			return ERRTFSCRC;
//		}
		
		info = tfsManager.statFile(statFile, statSuffix);
		if (info == null)
		{
			log.error("File info is null!!!");
			return ERRINFONULL;
		}
		
		if (info.getFlag() != 0)
		{
			log.error("Tfs file's status flag(" + info.getFlag() + ") is not corrent after rehiding!!!");
			return ERRTFSSTAT;
		}
		
		/* Delete */
		bRet = tfsManager.unlinkFile(statFile, suffix);
		if (bRet == false)
		{
			log.error("File(" + statFile + ") is failed to unlink!!!");
			return ERRDEL;
		}
		
//		if (info.getCrc() != localCrc)
//		{
//			log.error("Local crc(" + localCrc + ") is different from tfs crc(" + info.getCrc() + ") after unlinking!!!");
//			return ERRTFSCRC;
//		}
		
		info = tfsManager.statFile(statFile, statSuffix);
		if (info == null)
		{
			log.error("File info is null!!!");
			return ERRINFONULL;
		}
		
		if (info.getFlag() != 1)
		{
			log.error("Tfs file's status flag(" + info.getFlag() + ") is not corrent after unlinking!!!");
			return ERRTFSSTAT;
		}
		
		return INFOSUCCESS;
	}
	
	@Test
	public void test_01_saveTfsFile_with_suffix_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		iRet = statFileToTFS(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_02_saveTfsFile_with_empty_suffix_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		suffix = "";
		iRet = statFileToTFS(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_03_saveTfsFile_with_null_suffix_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		suffix = null;
		iRet = statFileToTFS(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_04_saveTfsFile_with_wrong_tfsName_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		//suffix = null;
		iRet = statFileToTFS("T12345678901234567", suffix);
		Assert.assertTrue(iRet == ERRINFONULL);
	}
	
	@Test
	public void test_05_saveTfsFile_with_empty_tfsName_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		//suffix = null;
		iRet = statFileToTFS("", suffix);
		Assert.assertTrue(iRet == ERRINFONULL);
	}
	
	@Test
	public void test_06_saveTfsFile_with_null_tfsName_small_size(){
		TfsName_without_suffix = tfsManager.newTfsFileName(".jpg");
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE-1.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		//suffix = null;
		iRet = statFileToTFS(null, suffix);
		Assert.assertTrue(iRet == ERRINFONULL);
	}
	
	/* Large file */
	@Test
	public void test_07_saveTfsFile_with_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		iRet = statFileToTFS(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_08_saveTfsFile_with_empty_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		suffix = "";
		iRet = statFileToTFS(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_09_saveTfsFile_with_null_suffix_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		suffix = null;
		iRet = statFileToTFS(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
	}
	
	@Test
	public void test_10_saveTfsFile_with_wrong_tfsName_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		//suffix = null;
		iRet = statFileToTFS("T12345678901234567", suffix);
		Assert.assertTrue(iRet == ERRINFONULL);
	}
	
	@Test
	public void test_11_saveTfsFile_with_empty_tfsName_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		//suffix = null;
		iRet = statFileToTFS("", suffix);
		Assert.assertTrue(iRet == ERRINFONULL);
	}
	
	@Test
	public void test_12_saveTfsFile_with_null_tfsName_max_small_size(){
		TfsName_without_suffix = "";
		suffix = ".jpg";
		iRet = saveFileToTFS(RESPATH + "MAX_SMALL_FILE_SIZE.jpg", TfsName_without_suffix, suffix);
		Assert.assertTrue(iRet == INFOSUCCESS);
		//suffix = null;
		iRet = statFileToTFS(null, suffix);
		Assert.assertTrue(iRet == ERRINFONULL);
	}
	
}
