package com.taobao.common.tfs.testcase;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author zongluo
 * @modified by mingyan.zc: some one come here to modify them
 *
 */
public class TfsManager_09_saveUniqueFile_byte extends TfsBaseCase {

	/* (non-Javadoc)
	 * @see com.taobao.tfs.common.TfsBaseCase#test()
	 */
	private static FileReader fR;	
	private static String strBuff = "";
	private static int iBuff;
	private String suffix = ".jpg";
	private boolean bRet = false;
	private byte btBuff[];
	private ByteArrayOutputStream opStream = new ByteArrayOutputStream();
	private String TfsName_without_suffix = null;
	private String TfsName_with_suffix = null;
	//public static char cBuff;
	
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
			Assert.assertTrue(bRet);
			/* fetch this unique file to verify the result */
			bRet = tfsManager.fetchFile(TfsName_with_suffix, suffix, TfsName_local);
			//Assert.assertFalse(bRet);
		}
	}
	
	
	//@Test
	public void test_01_saveUniqueFile_with_right_suffix(){
		log.info( "test_01_saveUniqueFile_with_right_suffix" );		
		/* preparation */
		TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_02_saveUniqueFile_with_empty_suffix(){
		log.info( "test_02_saveUniqueFile_with_empty_suffix" );		
		/* preparation */
		suffix = "";
		TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_03_saveUniqueFile_with_null_suffix(){
		log.info( "test_03_saveUniqueFile_with_null_suffix" );		
		/* preparation */
		suffix = null;
		TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_04_saveUniqueFile_with_wrong_tfsName_and_null_suffix(){
		log.info( "test_04_saveUniqueFile_with_wrong_tfsName_and_null_suffix" );		
		/* preparation */
		suffix = null;
		TfsName_without_suffix = "sdfkjsof";
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse(bRet);
	}
	
	//@Test
	public void test_05_saveUniqueFile_with_wrong_tfsName_and_right_suffix(){
		log.info( "test_05_saveUniqueFile_with_wrong_tfsName_and_right_suffix" );		
		/* preparation */
		TfsName_without_suffix = "sdfkjsof";
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse(bRet);	
	}
	
	//@Test
	public void test_06_saveUniqueFile_with_wrong_tfsName_and_empty_suffix(){
		log.info( "test_06_saveUniqueFile_with_wrong_tfsName_and_empty_suffix" );		
		/* preparation */
		suffix = "";
		TfsName_without_suffix = "sdfkjsof";
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse(bRet);
			
	}
	
	//@Test
	public void test_07_saveUniqueFile_with_empty_tfsName_and_right_suffix(){
		log.info( "test_07_saveUniqueFile_with_empty_tfsName_and_right_suffix" );		
		/* preparation */
		TfsName_without_suffix = "";
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_08_saveUniqueFile_with_empty_tfsName_and_empty_suffix(){
		log.info( "test_08_saveUniqueFile_with_empty_tfsName_and_empty_suffix" );		
		/* preparation */
		suffix = "";
		TfsName_without_suffix = "";
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_09_saveUniqueFile_with_empty_tfsName_and_null_suffix(){
		log.info( "test_09_saveUniqueFile_with_empty_tfsName_and_null_suffix" );		
		/* preparation */
		suffix = null;
		TfsName_without_suffix = "";
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_10_saveUniqueFile_with_null_tfsName_and_right_suffix(){
		log.info( "test_10_saveUniqueFile_with_null_tfsName_and_right_suffix" );		
		/* preparation */
		TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_11_saveUniqueFile_with_null_tfsName_and_empty_suffix(){
		log.info( "test_11_saveUniqueFile_with_null_tfsName_and_empty_suffix" );		
		/* preparation */
		suffix = "";
		TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));	
			
	}
	
	//@Test
	public void test_12_saveUniqueFile_with_null_tfsName_and_null_suffix(){
		log.info( "test_12_saveUniqueFile_with_null_tfsName_and_null_suffix" );		
		/* preparation */
		suffix = null;
		TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	//@Test
	public void test_13_saveUniqueFile_with_empty_data(){
		log.info( "test_13_saveUniqueFile_with_empty_data" );		
		/* preparation */
		btBuff = strBuff.getBytes();
		btBuff = new byte[ 1 ];
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(opStream.toString().equals("\0"));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(opStream.toString().equals("\0"));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue(bRet);
		Assert.assertTrue(opStream.toString().equals("\0"));	
	}
	
	//@Test
	public void test_14_saveUniqueFile_with_null_data(){
		log.info( "test_14_saveUniqueFile_with_null_data" );		
		/* preparation */
		btBuff = strBuff.getBytes();
		btBuff = null;
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse(bRet);
		//Assert.assertNull(opStream.toString());
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse(bRet);
	//	Assert.assertNull(opStream.toString());
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse(bRet);
	//	Assert.assertNull(opStream.toString());	
	}
	
	//@Test
	public void test_15_saveUniqueFile_with_null_data_null_tfsName_null_suffix(){
		log.info( "test_15_saveUniqueFile_with_null_data_null_tfsName_null_suffix" );		
		/* preparation */
		suffix = null;
		TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		btBuff = null;
		/* saveUniqueFile */
		TfsName_with_suffix = tfsManager.saveUniqueFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse(bRet);
		//Assert.assertNull(opStream.toString());	
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse(bRet);
		//Assert.assertNull(opStream.toString());	
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse(bRet);
		//Assert.assertNull(opStream.toString());		
	}

}
