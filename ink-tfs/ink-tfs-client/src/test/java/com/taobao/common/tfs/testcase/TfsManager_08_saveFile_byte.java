/**
 * 
 */
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
 *
 */
public class TfsManager_08_saveFile_byte extends TfsBaseCase {

	/* (non-Javadoc)
	 * @see com.taobao.tfs.common.TfsBaseCase#test()
	 */


	public static FileReader fR;	
	public static String strBuff = "";
	public static char cBuff;
	public static int iBuff;
	public String suffix = ".jpg";
	public boolean bRet = false;
	public int iRet = 0;
	public byte btBuff[];
	public ByteArrayOutputStream opStream = new ByteArrayOutputStream();
	//public static char cBuff;
	
	@BeforeClass
	public static void beforeClass(){
		/* Get the data from local file */
		try{
			fR = new FileReader( resource_file);
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
	public void test_01_saveFile_with_right_suffix(){
		log.info( "test_01_saveFile_with_right_suffix" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with right suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with right suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with right suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with right suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_02_saveFile_with_empty_suffix(){
		log.info( "test_02_saveFile_with_empty_suffix" );
		
		/* preparation */
		suffix = "";
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_03_saveFile_with_null_suffix(){
		log.info( "test_03_saveFile_with_null_suffix" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with null suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with null suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with null suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with null suffix should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_04_saveFile_with_wrong_tfsName_and_null_suffix(){
		log.info( "test_04_saveFile_with_wrong_tfsName_and_null_suffix" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = "sdfkjsof";
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_05_saveFile_with_wrong_tfsName_and_suffix(){
		log.info( "test_05_saveFile_with_wrong_tfsName_and_suffix" );
		
		/* preparation */
		suffix = ".jpg";
		String TfsName_without_suffix = "sdfkjsof";
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_06_saveFile_with_wrong_tfsName_and_empty_suffix(){
		log.info( "test_06_saveFile_with_wrong_tfsName_and_empty_suffix" );
		
		/* preparation */
		suffix = "";
		String TfsName_without_suffix = "sdfkjsof";
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse("Saving File with wrong tfsName should be False", bRet);
		Assert.assertFalse(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_07_saveFile_with_empty_tfsName(){
		log.info( "test_07_saveFile_with_empty_tfsName" );
		
		/* preparation */
		suffix = ".jpg";
		String TfsName_without_suffix = "";
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
//		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_08_saveFile_with_empty_tfsName_and_empty_suffix(){
		log.info( "test_08_saveFile_with_empty_tfsName_and_empty_suffix" );
		
		/* preparation */
		suffix = "";
		String TfsName_without_suffix = "";
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
//		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_09_saveFile_with_empty_tfsName_and_null_suffix(){
		log.info( "test_09_saveFile_with_empty_tfsName_and_null_suffix" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = "";
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
//		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_10_saveFile_with_null_tfsName(){
		log.info( "test_10_saveFile_with_null_tfsName" );
		
		/* preparation */
		suffix = ".jpg";
		String TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
//		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_11_saveFile_with_null_tfsName_and_empty_suffix(){
		log.info( "test_11_saveFile_with_null_tfsName_and_empty_suffix" );
		
		/* preparation */
		suffix = "";
		String TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
//		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_12_saveFile_with_null_tfsName_and_null_suffix(){
		log.info( "test_12_saveFile_with_null_tfsName_and_null_suffix" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = null;
		btBuff = strBuff.getBytes();
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
//		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty tfsName should be true", bRet);
		Assert.assertTrue(strBuff.equals(opStream.toString()));		
	}
	
	@Test
	public void test_13_saveFile_with_empty_data(){
		log.info( "test_13_saveFile_with_empty_data" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		//btBuff = strBuff.getBytes();
		btBuff = new byte[10];
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNotNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty data should be true", bRet);
		Assert.assertTrue(opStream.toString().equals("\0\0\0\0\0\0\0\0\0\0"));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertTrue("Saving File with empty data should be true", bRet);
		Assert.assertTrue(opStream.toString().equals("\0\0\0\0\0\0\0\0\0\0"));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertTrue("Saving File with empty data should be true", bRet);
		Assert.assertTrue(opStream.toString().equals("\0\0\0\0\0\0\0\0\0\0"));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertTrue("Saving File with empty data should be true", bRet);
		Assert.assertTrue(opStream.toString().equals("\0\0\0\0\0\0\0\0\0\0"));
	}
	
	@Test
	public void test_14_saveFile_with_null_data(){
		log.info( "test_14_saveFile_with_null_data" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		//btBuff = strBuff.getBytes();
		btBuff = null;
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with null data should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with null data should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse("Saving File with null data should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse("Saving File with null data should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
	}
	
	@Test
	public void test_15_saveFile_with_null(){
		log.info( "test_15_saveFile_with_null" );
		
		/* preparation */
		suffix = null;
		String TfsName_without_suffix = null;
		//btBuff = strBuff.getBytes();
		btBuff = null;
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(btBuff, TfsName_without_suffix, suffix);
		Assert.assertNull(TfsName_with_suffix);
		/* fetch file with suffix to verify the result */
//		bRet = tfsManager.fetchFile( TfsName_without_suffix, suffix, opStream );
//		Assert.assertFalse("Saving File with null should be False", bRet);
//		Assert.assertTrue(opStream.toString().equals(""));
		/* Fetch file without suffix to verify the result */
		/* with right suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, suffix, opStream );
		Assert.assertFalse("Saving File with null should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
		/* with empty suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, "", opStream );
		Assert.assertFalse("Saving File with null should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
		/* with null suffix */
		/* Reset outputstream */
		opStream.reset();
		bRet = tfsManager.fetchFile( TfsName_with_suffix, null, opStream );
		Assert.assertFalse("Saving File with null should be False", bRet);
		Assert.assertTrue(opStream.toString().equals(""));
	}
	
	@After
	public void After(){
		/* Reset outputstream */
		opStream.reset();
	}
}
