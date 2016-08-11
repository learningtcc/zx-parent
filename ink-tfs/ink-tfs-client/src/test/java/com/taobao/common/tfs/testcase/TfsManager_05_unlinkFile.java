package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author zongluo
 *
 */
public class TfsManager_05_unlinkFile extends TfsBaseCase {

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
	
	@Test
	public void test_01_unlinkFile_with_right_suffix(){
		log.info("test_01_unlinkFile_with_right_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
		Assert.assertTrue(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_local, TfsName_without_suffix, suffix);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_local, TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_02_unlinkFile_with_wrong_suffix(){
		log.info("test_02_unlinkFile_with_wrong_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_without_suffix, ".txt");
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_local, TfsName_without_suffix, suffix);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_03_unlinkFile_with_empty_suffix(){
		log.info("test_03_unlinkFile_with_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_without_suffix, "");
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_04_unlinkFile_with_null_suffix(){
		log.info("test_04_unlinkFile_with_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_without_suffix, null);
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_05_unlinkFile_with_empty_tfsName(){
		log.info("test_05_unlinkFile_with_empty_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile("", suffix);
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_06_unlinkFile_with_null_tfsName(){
		log.info("test_06_unlinkFile_with_null_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(null, suffix);
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_07_unlinkFile_with_full_tfsName(){
		log.info("test_07_unlinkFile_with_full_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_with_suffix, suffix);
		Assert.assertTrue(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_08_unlinkFile_with_full_tfsName_and_wrong_suffix(){
		log.info("test_08_unlinkFile_with_full_tfsName");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_with_suffix, ".txt");
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_09_unlinkFile_with_full_tfsName_and_empty_suffix(){
		log.info("test_09_unlinkFile_with_full_tfsName_and_empty_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_with_suffix, "");
		Assert.assertTrue(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_10_unlinkFile_with_full_tfsName_and_null_suffix(){
		log.info("test_10_unlinkFile_with_full_tfsName_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(TfsName_with_suffix, null);
		Assert.assertTrue(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_11_unlinkFile_with_null_tfsName_and_null_suffix(){
		log.info("test_11_unlinkFile_with_null_tfsName_and_null_suffix");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		bRet = tfsManager.unlinkFile(null, null);
		Assert.assertFalse(bRet);
		if (bRet == true){
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
		} else {
			/* fetch this file to verify the result of unlinking */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_12_one_file_is_unlinked_several_times_than_save_file(){
		log.info("test_12_one_file_is_unlinked_several_times_than_save_file");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* unlink this tfs file */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			bRet = tfsManager.unlinkFile(TfsName_with_suffix, null);
			if (iLoop == 0){
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			if (bRet == true){
				/* fetch this file to verify the result of unlinking */
				bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
				Assert.assertFalse(bRet);
			} else {
				/* fetch this file to verify the result of unlinking */
				bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
				Assert.assertFalse(bRet);
			}
		}
		/* save a local file to tfs */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		/* fetch this file to verify the result of unlinking */
		bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
		Assert.assertTrue(bRet);
		/* clean the local file */
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_13_save_file_several_times_than_unlinked_several_times(){
		log.info("test_13_save_file_several_times_than_unlinked_several_times");
		/* create a tfs file name */
        TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			/* save a local file to tfs */
            TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			log.info(TfsName_with_suffix + iLoop);
			/* unlink this tfs file */		
			bRet = tfsManager.unlinkFile(TfsName_with_suffix, null);
			Assert.assertTrue(bRet);
            try {
            Thread.sleep(100);
            } catch (Exception e) {
            }
			if (bRet == true){
				/* fetch this file to verify the result of unlinking */
				bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
				Assert.assertFalse(bRet);
			} else {
				/* fetch this file to verify the result of unlinking */
				bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
				Assert.assertFalse(bRet);
			}
		}
		deleteFile(TfsName_local);
	}
	
	@Test
	public void test_14_save_file_several_times_than_unlinked_several_times(){
		log.info("test_14_save_file_several_times_than_unlinked_several_times");
		/* create a tfs file name */
		TfsName_without_suffix = tfsManager.newTfsFileName(suffix);
		/* preparation */
		suffix = null;
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			/* save a local file to tfs */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			/* unlink this tfs file */		
			bRet = tfsManager.unlinkFile(TfsName_with_suffix, null);
			Assert.assertTrue(bRet);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
			if (bRet == true){
				/* fetch this file to verify the result of unlinking */
				bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
				Assert.assertFalse(bRet);
			} else {
				/* fetch this file to verify the result of unlinking */
				bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
				Assert.assertFalse(bRet);
			}
		}
		deleteFile(TfsName_local);
	}

}
