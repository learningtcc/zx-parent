package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author zongluo
 * @modified by mingyan.zc: some one come here to modify them
 *
 */
public class TfsManager_06_saveUniqueFile extends TfsBaseCase {

	public String suffix = ".jpg";
	public String TfsName_temp = null;
	public String TfsName_with_suffix = null;
	public String TfsName_without_suffix = null;
	public boolean bRet = false;
	
	@Before
	public void Before(){
		pathInit();
		suffix = ".jpg";
		TfsName_temp = null;
		TfsName_with_suffix = null;
		TfsName_without_suffix = null;
		bRet = false;
	}
	
	@After
	public void After(){
		/* Clear the dirty data */
		if ((TfsName_with_suffix != null) && (TfsName_with_suffix != ""))
		{
			/* unlink this unique file */
			long iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			if (iRet > 0)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			//Assert.assertTrue(bRet);
			/* fetch this unique file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
			//Assert.assertFalse(bRet);
			/* clean */
			deleteFile(TfsName_local);
		}
	}
	
	//@Test
	public void test_01_saveUniqueFile_with_suffix(){
		log.info("test_01_saveUniqueFile_with_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		
//		/* create a tfs file name */
//		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
//		/* save a local file to tfs */
//		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
//		Assert.assertNotNull(TfsName_with_suffix);
//		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_02_saveUniqueFile_with_empty_suffix(){
		log.info("test_02_saveUniqueFile_with_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = "";
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_03_saveUniqueFile_with_null_suffix(){
		log.info("test_02_saveUniqueFile_with_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_04_saveUniqueFile_with_wrong_filename_and_empty_suffix(){
		log.info("test_04_saveUniqueFile_with_wrong_filename_and_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = "";
		TfsName_without_suffix = "jfalsdjfalsdfjafd";
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		//Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_05_saveUniqueFile_with_wrong_filename_and_null_suffix(){
		log.info("test_05_saveUniqueFile_with_wrong_filename_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		TfsName_without_suffix = "jfalsdjfalsdfjafd";
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_06_saveUniqueFile_with_empty_filename_and_null_suffix(){
		log.info("test_06_saveUniqueFile_with_empty_filename_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		TfsName_without_suffix = "";
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_07_saveUniqueFile_with_null_filename_and_null_suffix(){
		log.info("test_07_saveUniqueFile_with_null_filename_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		TfsName_without_suffix = null;
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_08_saveUniqueFile_with_empty_localpath(){
		log.info("test_08_saveUniqueFile_with_null_filename_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		resource_file = "";
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_09_saveUniqueFile_with_empty_localpath_and_null_filename_null_suffix(){
		log.info("test_09_saveUniqueFile_with_empty_localpath_and_null_filename_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		TfsName_without_suffix = null;
		resource_file = "";
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}
	
	//@Test
	public void test_10_saveUniqueFile_with_null_localpath_and_null_filename_null_suffix(){
		log.info("test_10_saveUniqueFile_with_null_localpath_and_null_filename_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		TfsName_without_suffix = null;
		resource_file = null;
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}

	//@Test
	public void test_11_saveUniqueFile_then_saveFile(){
		log.info("test_11_saveUniqueFile_then_saveFile");
		/* create a tfs file name */
		//TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		TfsName_without_suffix = null;
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		System.out.println("First:" + TfsName_with_suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* save a local file to tfs by saveFile */
		String TfsName_with_suffix_new = tfsManager.saveFile(resource_file_big, TfsName_with_suffix, suffix);
		System.out.println("Second:"+TfsName_with_suffix_new);
		Assert.assertNotNull(TfsName_with_suffix_new);
		Assert.assertTrue(TfsName_with_suffix_new.equals(TfsName_with_suffix));
		/* save a local file to tfs by saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		System.out.println("Third:"+TfsName_with_suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertTrue(TfsName_with_suffix_new.equals(TfsName_with_suffix));
		/* save a local file to tfs by saveFile */
		TfsName_with_suffix_new = tfsManager.saveFile(resource_file, TfsName_with_suffix, suffix);
		System.out.println("Second:"+TfsName_with_suffix_new);
		Assert.assertNotNull(TfsName_with_suffix_new);
		Assert.assertTrue(TfsName_with_suffix_new.equals(TfsName_with_suffix));
		/* fetch this file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean this local file */
		deleteFile(TfsName_local);
	}

}
