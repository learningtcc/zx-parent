package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;


/**
 * @author zongluo
 *
 */
public class TfsManager_03_hideFile extends TfsBaseCase {

	public String suffix = ".jpg";
	public boolean bRet = false;
	
	@Before
	public void Before(){
		pathInit();
		suffix = ".jpg";
		bRet = false;
	}
	
	@Test
	public void test_01_hideFile_with_right_suffix(){
		log.info("test_01_hideFile_with_right_suffix");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
		Assert.assertTrue(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_with_suffix, null, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);	
		}
	}
	
	@Test
	public void test_02_hideFile_with_wrong_suffix(){
		log.info("test_02_hideFile_with_wrong_suffix");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = false;
		bRet = tfsManager.hideFile(TfsName_without_suffix, ".txt", 1);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_03_hideFile_with_empty_suffix(){
		log.info("test_03_hideFile_with_empty_suffix");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_without_suffix, "", 1);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_04_hideFile_with_null_suffix(){
		log.info("test_04_hideFile_with_null_suffix");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_without_suffix, null, 1);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}

	@Test
	public void test_05_hideFile_with_empty_tfsName(){
		log.info("test_05_hideFile_with_empty_tfsName");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile("", suffix, 1);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_06_hideFile_with_null_tfsName(){
		log.info("test_06_hideFile_with_null_tfsName");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(null, suffix, 1);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_07_hideFile_with_wrong_option(){
		log.info("test_07_hideFile_with_wrong_option");
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, -1);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_08_hideFile_with_full_tfsName(){
		log.info("test_08_hideFile_with_full_tfsName");
		/* preparation */
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
		Assert.assertTrue(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_09_hideFile_with_full_tfsName_and_empty_suffix(){
		log.info("test_09_hideFile_with_full_tfsName_and_empty_suffix");
		/* preparation */
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_with_suffix, "", iOpt);
		Assert.assertTrue(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}

	@Test
	public void test_10_hideFile_with_full_tfsName_and_wrong_suffix(){
		log.info("test_10_hideFile_with_full_tfsName_and_wrong_suffix");
		/* preparation */
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_with_suffix, ".txt", iOpt);
		Assert.assertFalse(bRet);
		/* Verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertFalse(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* hideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 1);
			Assert.assertTrue(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);
		}
	}
	
	@Test
	public void test_11_unhideFile_with_right_suffix(){
		log.info("test_11_unhideFile_with_right_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
		Assert.assertTrue(bRet);
		/* verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_12_unhideFile_with_wrong_suffix(){
		log.info("test_12_unhideFile_with_wrong_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_without_suffix, ".txt", iOpt);
		Assert.assertFalse(bRet);
		/* verify */
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_13_unhideFile_with_empty_suffix(){
		log.info("test_13_unhideFile_with_empty_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_without_suffix, "", iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_14_unhideFile_with_null_suffix(){
		log.info("test_14_unhideFile_with_null_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_without_suffix, null, iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_15_unhideFile_with_empty_tfsName(){
		log.info("test_15_unhideFile_with_empty_tfsName");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile("", suffix, iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_16_unhideFile_with_null_tfsName(){
		log.info("test_16_unhideFile_with_null_tfsName");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(null, suffix, iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_17_unhideFile_with_null_tfsName_and_suffix(){
		log.info("test_17_unhideFile_with_null_tfsName_and_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(null, null, iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_18_unhideFile_with_full_tfsName_and_null_suffix(){
		log.info("test_18_unhideFile_with_full_tfsName_and_null_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
		Assert.assertTrue(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_19_unhideFile_with_full_tfsName_and_empty_suffix(){
		log.info("test_19_unhideFile_with_full_tfsName_and_empty_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_with_suffix, "", iOpt);
		Assert.assertTrue(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_20_unhideFile_with_full_tfsName_and_wrong_suffix(){
		log.info("test_20_unhideFile_with_full_tfsName_and_wrong_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_without_suffix, ".txt", iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_21_unhideFile_with_full_tfsName_and_empty_suffix(){
		log.info("test_21_unhideFile_with_full_tfsName_and_empty_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_without_suffix, "", iOpt);
		Assert.assertTrue(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_22_unhideFile_with_full_tfsName_and_null_suffix(){
		log.info("test_22_unhideFile_with_full_tfsName_and_null_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_with_suffix, null, iOpt);
		Assert.assertTrue(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}
	
	@Test
	public void test_23_unhideFile_with_full_tfsName_and_wrong_suffix(){
		log.info("test_23_unhideFile_with_full_tfsName_and_wrong_suffix");
		/* preparation */
		boolean bRet = false;
		int iOpt = 1;
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* hideFile */
		for(int iLoop = 0; iLoop < 3; iLoop ++)
		{
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, iOpt);
			if (iLoop == 0)
			{
				Assert.assertTrue(bRet);
			} else {
				Assert.assertFalse(bRet);
			}
			
		}
		/* preparation for unhiding */
		iOpt = 0;
		/* unhideFile */
		bRet = tfsManager.hideFile(TfsName_with_suffix, ".txt", iOpt);
		Assert.assertFalse(bRet);
		/* verify */		
		if (bRet == true){
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue(bRet);
			/* Overwrite this file to verify the result */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			/* Read this file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, TfsName_local);
			Assert.assertTrue("After overwriting,this file should be unhided",bRet);
			/* unhideFile */
			bRet = tfsManager.hideFile(TfsName_without_suffix, suffix, 0);
			Assert.assertFalse(bRet);
			/* Unlink this file to verify the result */
			bRet = tfsManager.unlinkFile(TfsName_without_suffix, suffix);
			Assert.assertTrue(bRet);			
		}		
	}

}
