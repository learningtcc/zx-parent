/**
 * 
 */
package com.taobao.common.tfs.testcase;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author zongluo
 *
 */
public class TfsManager_10_fetchFile_byte extends TfsBaseCase {

	/* (non-Javadoc)
	 * @see com.taobao.tfs.common.TfsBaseCase#test()
	 */
	private ByteArrayOutputStream opStream = new ByteArrayOutputStream();
	private OutputStream outStream = new ByteArrayOutputStream();
	public static FileReader fR;	
	public static String strBuff = "";
	public static char cBuff;
	public static int iBuff;
	public String suffix = ".jpg";
	public boolean bRet = false;
	public int iRet = 0;
	public byte btBuff[];
	
	@BeforeClass
	public static void beforeClass(){
		/* Get the data from local file */
		try{
			fR = new FileReader( resource_file );
		} catch (Exception e){
			//Do nothing
		}
		try{
			while( (iBuff = fR.read())!= -1)
			{
				strBuff = strBuff + (char)iBuff;
			}
		} catch (Exception e)
		{
			/* Do nothing */
		}
	}
	
	@Before
	public void before(){
		pathInit();
		suffix = ".jpg";
	}
	
	@Test
	public void test_01_saveFile_then_fetchFile_with_right_suffix() throws InterruptedException {
		log.info( "test_01_saveFile_then_fetchFile_with_right_suffic" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		Thread.sleep(1000);
		/* Set some condition */
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", opStream);
		Assert.assertTrue("Fetching file with right suffix should be true", bRet);	
		/* Compare */
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_02_saveFile_then_fetchFile_with_wrong_suffix() {
		log.info( "test_02_saveFile_then_fetchFile_with_wrong_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = ".txt";
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_03_saveFile_then_fetchFile_with_empty_suffix() {
		log.info( "test_03_saveFile_then_fetchFile_with_empty_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = "";
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_04_saveFile_then_fetchFile_with_null_suffix() {
		log.info( "test_04_saveFile_then_fetchFile_with_null_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = null;
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_05_saveFile_then_fetchFile_with_wrong_tfsName() {
		log.info( "test_05_saveFile_then_fetchFile_with_wrong_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		TfsName_without_suffix = "fsfasd";
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_06_saveFile_then_fetchFile_with_empty_tfsName() {
		log.info( "test_06_saveFile_then_fetchFile_with_empty_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		TfsName_without_suffix = "";
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_07_saveFile_then_fetchFile_with_null_tfsName() {
		log.info( "test_07_saveFile_then_fetchFile_with_null_tfsName" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		TfsName_without_suffix = null;
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_08_saveFile_then_fetchFile_with_null_tfsName_and_null_suffix() {
		log.info( "test_08_saveFile_then_fetchFile_with_null_tfsName_and_null_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = null;
		TfsName_without_suffix = null;
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_09_saveFile_then_fetchFile_with_null_tfsName_and_null_suffix_null_opStream() {
		log.info( "test_09_saveFile_then_fetchFile_with_null_tfsName_and_null_suffix_null_opStream" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = null;
		TfsName_without_suffix = null;
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, (ByteArrayOutputStream)null);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_10_saveFile_without_suffix_then_fetchFile() {
		log.info( "test_10_saveFile_without_suffix_then_fetchFile" );
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertTrue(bRet);	
		/* Compare */
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_11_saveFile_without_suffix_then_fetchFile_with_suffix() {
		log.info( "test_11_saveFile_without_suffix_then_fetchFile_with_suffix" );
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = ".jpg";
		TfsName_without_suffix = null;
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertFalse(bRet);	
		/* Compare */
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_12_saveFile_without_suffix_then_fetchFile_with_empty_suffix() {
		log.info( "test_12_saveFile_without_suffix_then_fetchFile_with_empty_suffix" );
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = "";
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, opStream);
		Assert.assertTrue(bRet);	
		/* Compare */
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}

	@Test
	public void test_13_saveFile_without_suffix_then_fetchFile_with_null_stream() {
		log.info( "test_13_saveFile_without_suffix_then_fetchFile_with_null_stream" );
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		suffix = "";
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, suffix, (ByteArrayOutputStream)null);
		Assert.assertFalse(bRet);	
		/* Compare */
	//	Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_14_saveFile_then_fetchFile_with_right_suffix() {
		log.info( "test_14_saveFile_then_fetchFile_with_right_suffix" );
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		@SuppressWarnings("unused")
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);	
		/* Set some condition */
		/* fetchFile */
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", outStream);
		Assert.assertTrue("Fetching file with right suffix should be true", bRet);	
		/* Compare */
		Assert.assertTrue(strBuff.equals(outStream.toString()));		
	}

}
