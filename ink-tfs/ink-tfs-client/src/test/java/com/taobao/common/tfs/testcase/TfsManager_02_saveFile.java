package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;
/**
 * @author zongluo
 *
 */
public class TfsManager_02_saveFile extends TfsBaseCase {

	public String suffix = ".jpg";
	public boolean bRet = false;
	
	@Before
	public void Before(){
		pathInit();
		suffix = ".jpg";
		bRet = false;
	}
	
	@Test
	public void test_01_saveFile_with_right_suffix(){
		log.info( "test_01_saveFile_with_right_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, TfsName_local );
		Assert.assertTrue("Saving File with right suffix should be true", bRet);
		deleteFile( TfsName_local );
	}

	@Test
	public void test_02_saveFile_with_empty_suffix(){
		log.info( "test_02_saveFile_with_empty_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = "";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertEquals(TfsName_without_suffix, TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, TfsName_local );
		Assert.assertTrue("Saving File with right suffix should be true", bRet);
		deleteFile( TfsName_local );
	}

	@Test
	public void test_03_saveFile_with_null_suffix(){
		log.info( "test_02_saveFile_with_empty_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = null;
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		Assert.assertEquals(TfsName_without_suffix, TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, TfsName_local );
		Assert.assertTrue("Saving File with empty suffix should be true", bRet);
		deleteFile( TfsName_local );
	}	

	@Test
	public void test_04_saveFile_with_empty_tfsName(){
		log.info( "test_04_saveFile_with_empty_tfsName" );
		/* preparation */
		@SuppressWarnings("unused")
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, "", suffix);
		//System.out.println("#######"+ (null == TfsName_with_suffix));
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, null, TfsName_local );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		deleteFile( TfsName_local );
	}	

	@Test
	public void test_05_saveFile_with_null_tfsName(){
		log.info( "test_05_saveFile_with_null_tfsName" );
		/* preparation */
		@SuppressWarnings("unused")
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, null, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, null, TfsName_local );
		Assert.assertTrue("Saving File with null tfsName should be true", bRet);
		deleteFile( TfsName_local );
	}
	
	@Test
	public void test_06_saveFile_with_empty_localPath(){
		log.info( "test_06_saveFile_with_empty_localPath" );
		/* preparation */
		resource_file = "";
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, TfsName_local );
		Assert.assertFalse("Saving File with empty localPath should be false", bRet);
		deleteFile( TfsName_local );
	}

	@Test
	public void test_07_saveFile_with_null_localPath(){
		log.info( "test_07_saveFile_with_null_localPath" );
		/* preparation */
		resource_file = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, TfsName_local );
		Assert.assertFalse("Saving File with null localPath should be false", bRet);
		deleteFile( TfsName_local );
	}

	@Test
	public void test_08_saveFile_to_override_exist_file_big(){
		log.info( "test_08_saveFile_to_override_exist_file_big" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		TfsName_with_suffix = tfsManager.saveFile(resource_file_big, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		System.out.println("wo gan o!!!"+TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, TfsName_local );
		Assert.assertTrue("Saving File to override the big TFS file should be True", bRet);
		deleteFile( TfsName_local );
	}
	
	@Test
	public void test_09_saveFile_to_override_exist_file(){
		log.info( "test_09_saveFile_to_override_exist_file" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		System.out.println("wo cao o!!!"+TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, TfsName_local );
		Assert.assertTrue("Saving File to override the TFS file should be true", bRet);
		deleteFile( TfsName_local );
	}
	
	@Test
	public void test_10_saveFile_to_override_exist_file_small(){
		log.info( "test_09_saveFile_to_override_exist_file_small" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		suffix = ".jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		TfsName_with_suffix = tfsManager.saveFile(resource_file_small, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		System.out.println("wo ri o !!!"+TfsName_with_suffix);
		/* fetch file to verify the result */
		boolean bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, TfsName_local );
		Assert.assertTrue("Saving File to override the small TFS file should be true", bRet);
		deleteFile( TfsName_local );
	}

	
	@Test
	public void test_11_saveFile_without_localFile(){
		log.info( "test_11_saveFile_without_localFile" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "jpg" );
		suffix = "jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile("test.jpg", TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
	}
	
	@Test
	public void test_12_saveFile_with_wrong_tfsname(){
		log.info( "test_11_saveFile_without_localFile" );
		/* preparation */
		String TfsName_without_suffix = "fasdfadfsaf";
		suffix = "jpg";
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
	}


}
