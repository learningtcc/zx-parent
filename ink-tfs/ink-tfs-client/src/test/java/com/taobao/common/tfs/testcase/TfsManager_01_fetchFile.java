package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author zongluo
 *
 */
public class TfsManager_01_fetchFile extends TfsBaseCase {
	
//	public static String resource_file = "src/test/resources/100k.jpg";
//	public static String TfsName_local = "src/test/resources/temp.jpg";
	
	@Before
	public void Before(){
		pathInit();
	}
	
	@Test
	public void test_01_saveFile_then_fetchFile_with_right_suffix(){
		log.info( "test_01_saveFile_then_fetchFile_with_right_suffic" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		log.info("test_01" + TfsName_without_suffix + TfsName_with_suffix);
//		Assert.assertFalse(TfsName_without_suffix.equals(TfsName_with_suffix));
//		TfsUtil.getInstance().sleep(1000);
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", TfsName_local);
		Assert.assertTrue("Fetching file with right suffix should be true", bRet);	
		/* clean */
		deleteFile( TfsName_local );
	}
	
	@Test
	public void test_02_saveFile_then_fetchFile_with_wrong_suffix(){
		log.info( "test_02_saveFile_then_fetchFile_with_wrong_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".txt", TfsName_local);
		Assert.assertFalse("Fetching file with wrong suffix should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_03_saveFile_then_fetchFile_with_empty_suffix(){
		log.info( "test_03_saveFile_then_fetchFile_with_empty_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, "", TfsName_local);
		Assert.assertFalse("Fetching file with empty suffix should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_04_saveFile_then_fetchFile_with_null_suffix(){
		log.info( "test_04_saveFile_then_fetchFile_with_null_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, null, TfsName_local);
		Assert.assertFalse("Fetching file with null suffix should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_05_saveFile_then_fetchFile_with_wrong_tfsName(){
		log.info( "test_05_saveFile_then_fetchFile_with_wrong_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(resource_file, ".jpg", TfsName_local);
		Assert.assertFalse("Fetching file with wrong tfsName should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_06_saveFile_then_fetchFile_with_empty_tfsName(){
		log.info( "test_06_saveFile_then_fetchFile_with_empty_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile("", ".jpg", TfsName_local);
		Assert.assertFalse("Fetching file with empty tfsName should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_07_saveFile_then_fetchFile_with_null_tfsName(){
		log.info( "test_07_saveFile_then_fetchFile_with_null_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(null, ".jpg", TfsName_local);
		Assert.assertFalse("Fetching file with null tfsName should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_08_saveFile_then_fetchFile_with_null_tfsName_suffix(){
		log.info( "test_08_saveFile_then_fetchFile_with_null_tfsName_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(null, null, TfsName_local);
		Assert.assertFalse("Fetching file with null tfsName and suffix should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_09_saveFile_then_fetchFile_with_empty_localPath(){
		log.info( "test_09_saveFile_then_fetchFile_with_empty_localPath" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", "");
		Assert.assertFalse("Fetching file with empty localPath should be false", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_10_saveFile_then_fetchFile_with_null_localPath(){
		log.info( "test_10_saveFile_then_fetchFile_with_null_localPath" );
		/* preparation */
		TfsName_local = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", TfsName_local);
		Assert.assertFalse("Fetching file with null localPath should be false", bRet);	
		/* clean */
		//deleteFile( TfsName_local );		
	}

	@Test
	public void test_11_saveFile_then_fetchFile_with_null_localPath_tfsName_suffix(){
		log.info( "test_11_saveFile_then_fetchFile_with_null_localPath_tfsName_suffix" );
		/* preparation */
		TfsName_local = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", TfsName_local);
		Assert.assertFalse("Fetching file with null localPath and tfsName and suffix should be false", bRet);	
		/* clean */
	//	deleteFile( TfsName_local );		
	}

	@Test
	public void test_12_saveFile_then_fetchFile_with_full_tfsName(){
		log.info( "test_12_saveFile_then_fetchFile_with_full_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		log.info("***************************" + TfsName_without_suffix);
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_with_suffix, null, TfsName_local);
		log.info("******************" + bRet + "***" + TfsName_local);
		Assert.assertTrue("Fetching file with full tfsName should be true", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_13_saveFile_then_fetchFile_with_full_tfsName_and_right_suffix(){
		log.info( "test_13_saveFile_then_fetchFile_with_full_tfsName_and_right_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertTrue("Fetching file with full tfsName and right suffix should be true", bRet);	
		/* clean */
		deleteFile( TfsName_local );		
	}

	@Test
	public void test_14_saveFile_then_fetchFile_with_full_tfsName_and_wrong_suffix(){
		log.info( "test_14_saveFile_then_fetchFile_with_full_tfsName_and_wrong_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_with_suffix, ".txt", TfsName_local);
		Assert.assertFalse("Fetching file with full tfsName and wrong suffix should be wrong", bRet);	
//		/* clean */
//		deleteFile( TfsName_local );		
	}

	@Test
	public void test_15_saveFile_then_fetchFile_with_full_tfsName_and_empty_localPath(){
		try{
		log.info( "test_15_saveFile_then_fetchFile_with_full_tfsName_and_empty_localPath" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		TfsName_local = null;
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(resource_file, TfsName_without_suffix, ".jpg");
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertFalse("Fetching file with full tfsName and empty localPath should be false", bRet);	
		/* clean */
	//	deleteFile( TfsName_local );		
		} finally {
			/* clean */
	//		deleteFile( TfsName_local );
		}
	}
	
	@Test
	public void test_16_saveLargeFile_then_fetchFile_it(){
		try{
		log.info( "test_16_saveLargeFile_then_fetchFile_it" );
		/* preparation */
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveTfsFile(RESPATH + "10M.jpg", "", ".jpg");
		Assert.assertTrue(TfsName_with_suffix != null);
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertTrue(bRet);	
		/* clean */
		deleteFile( TfsName_local );		
		} finally {
			/* clean */
			deleteFile( TfsName_local );
		}
	}
	
	@Test
	public void test_17_saveFile_then_fetchHiddenFile(){
		try{
		log.info( "test_17_saveFile_then_fetchHiddenFile" );
		/* preparation */
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveTfsFile(RESPATH + "1m.jpg", "", ".jpg");
		Assert.assertTrue(TfsName_with_suffix != null);
    log.info("ret name: " + TfsName_with_suffix);
    /* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_with_suffix, ".jpg", 1);
		Assert.assertTrue(bRet);	
		/* fetchFile */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertFalse(bRet);	
		/* fetchFileForce */
		bRet = tfsManager.fetchFileForce(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertTrue(bRet);	
		/* clean */
		deleteFile( TfsName_local );		
		} finally {
			/* clean */
			deleteFile( TfsName_local );
		}
	}

	@Test
	public void test_18_saveLargeFile_then_fetchHiddenFile(){
		try{
		log.info( "test_18_saveLargeFile_then_fetchHiddenFile" );
		/* preparation */
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveLargeFile(RESPATH + "10M.jpg", null, ".jpg");
		Assert.assertTrue(TfsName_with_suffix != null);
    log.info("ret name: " + TfsName_with_suffix);
    /* hideFile */
		boolean bRet = tfsManager.hideFile(TfsName_with_suffix, ".jpg", 1);
		Assert.assertTrue(bRet);	
		/* fetchFile */
		bRet = tfsManager.fetchFile(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertFalse(bRet);	
		/* fetchFileForce */
		bRet = tfsManager.fetchFileForce(TfsName_with_suffix, ".jpg", TfsName_local);
		Assert.assertTrue(bRet);	
		/* clean */
		deleteFile( TfsName_local );		
		} finally {
			/* clean */
			deleteFile( TfsName_local );
		}
	}


}

