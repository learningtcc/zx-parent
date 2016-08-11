package com.taobao.common.tfs.testcase;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author zongluo
 *
 */
public class TfsManager_04_newTfsFileName extends TfsBaseCase {

	public String suffix = ".jpg";
	public String TfsName_temp = null;
	public String TfsName_with_suffix = null;
	public String TfsName_without_suffix = null;
	public boolean bRet = false;
	
	@Before
	public void Before()
	{
		pathInit();
		suffix = ".jpg";
	}
	
	@Test
	public void test_01_newTfsFileName_with_suffix(){
		log.info("test_01_newTfsFileName_with_suffix");
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
			Assert.assertNotNull(TfsName_temp);
			if (TfsName_temp != TfsName_without_suffix)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);			
			TfsName_without_suffix = TfsName_temp;
			/* Use created tfs file name to save a local file */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".txt");
			Assert.assertNotNull(TfsName_with_suffix);
			if (!TfsName_with_suffix.equals(TfsName_without_suffix))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}		
	}
	
	@Test
	public void test_02_newTfsFileName_with_empty_suffix(){
		log.info("test_02_newTfsFileName_with_empty_suffix");
		/* preparation */
		suffix = "";
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
			Assert.assertNotNull(TfsName_temp);
			if (TfsName_temp != TfsName_without_suffix)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);			
			TfsName_without_suffix = TfsName_temp;
			/* Use created tfs file name to save a local file */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".txt");
			Assert.assertNotNull(TfsName_with_suffix);
			if (!TfsName_with_suffix.equals(TfsName_without_suffix))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}		
	}
	
	@Test
	public void test_03_newTfsFileName_with_null_suffix(){
		log.info("test_03_newTfsFileName_with_null_suffix");
		/* preparation */
		suffix = null;
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
			Assert.assertNotNull(TfsName_temp);
			if (TfsName_temp != TfsName_without_suffix)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);			
			TfsName_without_suffix = TfsName_temp;
			/* Use created tfs file name to save a local file */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".txt");
			Assert.assertNotNull(TfsName_with_suffix);
			if (!TfsName_with_suffix.equals(TfsName_without_suffix))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}		
	}
	
	@Test
	public void test_04_newTfsFileName_with_suffix_then_create(){
		log.info("test_04_newTfsFileName_with_suffix_then_create");
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
			Assert.assertNotNull(TfsName_temp);
			if (TfsName_temp != TfsName_without_suffix)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);			
			TfsName_without_suffix = TfsName_temp;
			/* Use created tfs file name to save a local file */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			if (!TfsName_with_suffix.equals(TfsName_without_suffix))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}		
	}
	
	@Test
	public void test_05_newTfsFileName_with_empty_suffix_then_create(){
		log.info("test_05_newTfsFileName_with_empty_suffix_then_create");
		/* preparation */
		suffix = "";
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
			Assert.assertNotNull(TfsName_temp);
			if (TfsName_temp != TfsName_without_suffix)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);			
			TfsName_without_suffix = TfsName_temp;
			/* Use created tfs file name to save a local file */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			if (TfsName_with_suffix.equals(TfsName_without_suffix))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}		
	}
	
	@Test
	public void test_06_newTfsFileName_with_null_suffix_then_create(){
		log.info("test_06_newTfsFileName_with_null_suffix_then_create");
		/* preparation */
		suffix = null;
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 100; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
			Assert.assertNotNull(TfsName_temp);
			if (TfsName_temp != TfsName_without_suffix)
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);			
			TfsName_without_suffix = TfsName_temp;
			/* Use created tfs file name to save a local file */
			TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
			Assert.assertNotNull(TfsName_with_suffix);
			if (TfsName_with_suffix.equals(TfsName_without_suffix))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}		
	}
	
	@Test
	public void test_07_newTfsFileName_with_many_times(){
		log.info("test_07_newTfsFileName_with_many_times");
		/* preparation */
		ArrayList<String> arrayName = new ArrayList<String>(); 
		suffix = null;
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 10000; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
//			Assert.assertNotNull(TfsName_temp);
//			if (TfsName_temp != TfsName_without_suffix)
//			{
//				bRet = true;
//			} else {
//				bRet = false;
//			}
//			Assert.assertTrue(bRet);
			if (arrayName.contains(TfsName_temp))
			{
				bRet = false;
			} else {
				bRet = true;
				arrayName.add(TfsName_temp);
			}
			Assert.assertTrue(bRet);
		}
		TfsName_without_suffix = TfsName_temp;
		/* Use created tfs file name to save a local file */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		if (TfsName_with_suffix.equals(TfsName_without_suffix))
		{
			bRet = true;
		} else {
			bRet = false;
		}
		Assert.assertTrue(bRet);
	}
	
	@Test
	public void test_08_newTfsFileName_with_many_times_check_tfsName(){
		log.info("test_08_newTfsFileName_with_many_times_check_tfsName");
		/* preparation */
		suffix = null;
		/* Create 100 Tfs file name */
		for(int iLoop = 0; iLoop < 10000; iLoop ++){
			TfsName_temp = tfsManager.newTfsFileName(suffix);
			/* verify */
//			Assert.assertNotNull(TfsName_temp);
//			if (TfsName_temp != TfsName_without_suffix)
//			{
//				bRet = true;
//			} else {
//				bRet = false;
//			}
//			Assert.assertTrue(bRet);
			if (TfsName_temp.contains("T1"))
			{
				bRet = true;
			} else {
				bRet = false;
			}
			Assert.assertTrue(bRet);
		}	
		TfsName_without_suffix = TfsName_temp;
		/* Use created tfs file name to save a local file */
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		if (TfsName_with_suffix.equals(TfsName_without_suffix))
		{
			bRet = true;
		} else {
			bRet = false;
		}
		Assert.assertTrue(bRet);
	}

}
