
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
public class TfsManager_07_unlinkUniqueFile extends TfsBaseCase {
	
	public String suffix = ".jpg";
	public String TfsName_temp = null;
	public String TfsName_with_suffix = "";
	public String TfsName_without_suffix = null;
	public boolean bRet = false;
	public long iRet = -1;
	
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
			suffix = ".jpg";
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_with_suffix, suffix);	
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
			Assert.assertFalse(bRet);
			/* clean */
			deleteFile(TfsName_local);
		}
	}
	
	//@Test
	public void test_01_unlinkUniqueFile_with_suffix(){
		log.info("test_01_unlinkUniqueFile_with_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		log.info("test_01 " + TfsName_with_suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		
		/* unlink this unique file */
		
		iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
    bRet = iRet > 0 ? true : false;
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean */
		deleteFile(TfsName_local);
		
	}

	//@Test
	public void test_02_unlinkUniqueFile_with_wrong_suffix(){
		log.info("test_02_unlinkUniqueFile_with_wrong_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		for( int iLoop = 0;iLoop < 2;iLoop++ )
		{
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
			log.info("test_02 " + TfsName_with_suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		}
		/* unlink this unique file */
    iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, ".txt");
    bRet = iRet > 0 ? true : false;
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		/* unlink this unique file */
		for( int iLoop = 0;iLoop < 2;iLoop++ )
		{
      iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
      bRet = iRet > 0 ? true : false;
      Assert.assertTrue(bRet);
    }
	
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_03_unlinkUniqueFile_with_empty_suffix(){
		log.info("test_03_unlinkUniqueFile_with_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		log.info("test_03 "+TfsName_with_suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		/* unlink this unique file */
		iRet = tfsManager.unlinkUniqueFile(TfsName_without_suffix, "");
    bRet = iRet > 0 ? true : false;
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		/* unlink this unique file */
		iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
    bRet = iRet > 0 ? true : false;
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_04_unlinkUniqueFile_with_null_suffix(){
		log.info("test_04_unlinkUniqueFile_with_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		log.info("test_04 "+TfsName_with_suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		/* unlink this unique file */
		iRet = tfsManager.unlinkUniqueFile(TfsName_without_suffix, null);
    bRet = iRet > 0 ? true : false;
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		/* unlink this unique file */
		iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
    bRet = iRet > 0 ? true : false;
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_05_unlinkUniqueFile_with_wrong_tfsName(){
		log.info("test_05_unlinkUniqueFile_with_wrong_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		log.info("test_05 "+TfsName_with_suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		/* unlink this unique file */
		iRet = tfsManager.unlinkUniqueFile("skajflakdjf", suffix);
		System.out.println("244+======="+iRet);
		if (iRet > 0)
		{
			bRet = true;
		} else {
			bRet = false;
		}
		
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_06_unlinkUniqueFile_with_empty_tfsName(){
		log.info("test_06_unlinkUniqueFile_with_empty_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile("", suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_07_unlinkUniqueFile_with_null_tfsName(){
		log.info("test_07_unlinkUniqueFile_with_null_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(null, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_08_unlinkUniqueFile_with_null_tfsName_and_null_suffix(){
		log.info("test_08_unlinkUniqueFile_with_null_tfsName_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(null, null);
			System.out.println("397######"+iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_09_unlinkUniqueFile_with_full_tfsName(){
		log.info("test_09_unlinkUniqueFile_with_full_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean the local file */
		deleteFile(TfsName_local);
		
	}
	
	//@Test
	public void test_10_unlinkUniqueFile_with_full_tfsName_wrong_suffix(){
		log.info("test_09_unlinkUniqueFile_with_full_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, ".txt");
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		deleteFile(TfsName_local);		
	}
	
	//@Test
	public void test_11_unlinkUniqueFile_with_full_tfsName_empty_suffix(){
		log.info("test_11_unlinkUniqueFile_with_full_tfsName_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, "");
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean the local file */
		deleteFile(TfsName_local);		
	}
	
	//@Test
	public void test_12_unlinkUniqueFile_with_full_tfsName_null_suffix(){
		log.info("test_12_unlinkUniqueFile_with_full_tfsName_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, null);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, null, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean the local file */
		deleteFile(TfsName_local);	
	}
	
	//@Test
	public void test_13_saveUniqueFile_several_times_then_unlinkUniqueFile(){
		log.info("test_13_saveUniqueFile_several_times_then_unlinkUniqueFile");
		int iLoop = 0;
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save 100 unique files to tfs */
		for(iLoop = 0; iLoop < 100; iLoop ++)
		{
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		}
		for(;;)
		{
			iLoop --;
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println("606#####"+iRet);
			Assert.assertEquals(iRet, iLoop);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
			Assert.assertTrue(bRet);
			/* fetch this unique file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		/* clean the local file */
		deleteFile(TfsName_local);		
	}
	
	//@Test
	public void test_14_saveUniqueFile_then_unlinkFile_then_unlinkUniqueFile(){
		log.info("test_14_saveUniqueFile_then_unlinkFile_then_unlinkUniqueFile");
		int iLoop = 0;
		try{
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		for(iLoop = 0; iLoop < 1; iLoop ++)
		{
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		}
		/* unlink this unique file */
		bRet = tfsManager.unlinkFile(TfsName_with_suffix, suffix);
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);		
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		} finally {
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		deleteFile(TfsName_local);	
		}
	}
	
	//@Test
	public void test_15_saveUniqueFile_several_times_then_unlinkFile_then_unlinkUniqueFile(){
		log.info("test_15_saveUniqueFile_several_times_then_unlinkFile_then_unlinkUniqueFile");
		int iLoop = 0;
		String tfsName_temp = null;
		try{
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		for(iLoop = 0; iLoop < 100; iLoop ++)
		{
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
			if(iLoop == 0)
			{
				tfsName_temp = TfsName_with_suffix;
			}
			Assert.assertEquals(TfsName_with_suffix, tfsName_temp);
		}
		/* unlink this unique file */
		bRet = tfsManager.unlinkFile(TfsName_with_suffix, suffix);
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);		
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		} finally {
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		deleteFile(TfsName_local);	
		}
	}
	
	//@Test
	public void test_16_saveFile_then_unlinkUniqueFile(){
		log.info("test_16_saveFile_then_unlinkUniqueFile");
		try{
		//resource_file = "50k.jpg";
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);

		/* save a file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		log.info("test_16" + TfsName_with_suffix + TfsName_without_suffix);
		iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
		Assert.assertTrue(iRet == -1);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));	
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_without_suffix, suffix);
			System.out.println("776######"+iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		} finally {
			/* clean */
			for(;;)
			{
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
		//deleteFile(TfsName_local);	
		}
	}
	
	//@Test
	public void test_17_saveFile_then_unlinkUniqueFile_with_full_tfsName(){
		log.info("test_17_saveFile_then_unlinkUniqueFile_with_full_tfsName");
		try{
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));	
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		}finally{
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		deleteFile(TfsName_local);
		}
	}
	
	//@Test
	public void test_18_saveFile_then_unlinkUniqueFile_with_full_tfsName_empty_suffix(){
		log.info("test_18_saveFile_then_unlinkUniqueFile_with_full_tfsName_empty_suffix");
		try {
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));	
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, "");
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		}finally{
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		deleteFile(TfsName_local);	
		}
	}
	
	//@Test
	public void test_19_saveFile_then_unlinkUniqueFile_with_full_tfsName_null_suffix(){
		log.info("test_19_saveFile_then_unlinkUniqueFile_with_full_tfsName_null_suffix");
		try{
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));	
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, null);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertFalse(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		}finally{
		/* clean */
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
			System.out.println(iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		deleteFile(TfsName_local);	
		}
	}
	
	//@Test
	public void test_20_saveFile_then_saveUniqueFile_then_unlinkUniquefile(){
		log.info("test_20_saveFile_then_saveUniqueFile_then_unlinkUniquefile");
		int iLoop = 0;
		try{
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a  file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));	
		for(iLoop = 0; iLoop < 4; iLoop ++)
		{
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		}
		for(;;)
		{
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, null);
			System.out.println("991######"+iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		}finally{
			/* clean */
			for(;;)
			{
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
			deleteFile(TfsName_local);		
		}
	}
	
	//@Test
	public void test_21_saveUniqueFile_then_saveFile_then_unlinkUniquefile(){
		log.info("test_21_saveUniqueFile_then_saveFile_then_unlinkUniquefile");
		int iLoop = 0;
		try{
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a unique file several times to tfs */
		for(iLoop = 0; iLoop < 2; iLoop ++)
		{
			TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
		}
		/* save a unique file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));	
		for(;;)
		{
			iLoop --;
			/* unlink this unique file */
			iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, null);
			System.out.println(iRet);
			Assert.assertEquals(iLoop, iRet);
			if (iRet > 0)
			{
				bRet = true;
			} else if(iRet == 0) {
				bRet = true;
				break;
			} else {
				bRet = false;
				break;
			}
		}
		Assert.assertTrue(bRet);
		/* fetch this unique file to verify the result */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
		Assert.assertFalse(bRet);
		}finally{
			/* clean */
			for(;;)
			{
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
			deleteFile(TfsName_local);		
		}
	}
	
	//@Test
	public void test_22_saveUniqueFile_several_times_with_diffname_then_unlinkUniquefile_with_full_tfsName(){
		log.info("test_22_saveUniqueFile_several_times_with_diffname_then_unlinkUniquefile_with_full_tfsName");
		int iLoop = 0;
		try{
			/* save a unique file several times to tfs */
			for(iLoop = 0; iLoop < 10; iLoop ++)
			{
				/* create a tfs file name */
				TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
				if (iLoop % 2 == 0)
				{
					TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
					Assert.assertNotNull(TfsName_with_suffix);
					Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
				} else {
					TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
					Assert.assertNotNull(TfsName_with_suffix);
					Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
				}
			}
			for(;;)
			{
				iLoop --;
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				Assert.assertEquals(iLoop, iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
			Assert.assertTrue(bRet);
			
			/* fetch this unique file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		}finally{
			/* clean */
			for(;;)
			{
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
			//Assert.assertTrue(bRet);
			deleteFile(TfsName_local);
		}
	}
	
	//@Test
	public void test_23_saveUniqueFile_several_times_with_diffname_then_unlinkUniquefile(){
		log.info("test_23_saveUniqueFile_several_times_with_diffname_then_unlinkUniquefile");
		int iLoop = 0;
		try{
			/* save a unique file several times to tfs */
			for(iLoop = 0; iLoop < 10; iLoop ++)
			{
				/* create a tfs file name */
				TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
				if (iLoop % 2 == 0)
				{
					TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
					Assert.assertNotNull(TfsName_with_suffix);
					Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
				} else {
					TfsName_with_suffix = tfsManager.saveUniqueFile(resource_file, TfsName_without_suffix, suffix);
					Assert.assertNotNull(TfsName_with_suffix);
					Assert.assertFalse(TfsName_with_suffix.equals(TfsName_without_suffix));
				}
			}
			for(;;)
			{
				iLoop --;
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				Assert.assertEquals(iLoop, iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
			Assert.assertTrue(bRet);
			
			/* fetch this unique file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		}finally{
			/* clean */
			for(;;)
			{
				/* unlink this unique file */
				iRet = tfsManager.unlinkUniqueFile(TfsName_with_suffix, suffix);
				System.out.println(iRet);
				if (iRet > 0)
				{
					bRet = true;
				} else if(iRet == 0) {
					bRet = true;
					break;
				} else {
					bRet = false;
					break;
				}
			}
			//Assert.assertTrue(bRet);
			deleteFile(TfsName_local);
		}
	}

}
