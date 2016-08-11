package com.taobao.common.tfs.testcase;

import junit.framework.Assert;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;

/**
 * @author weilong.pt
 *
 */
public class TfsManager_20_fetchFile_offset_length extends TfsBaseCase {
	
	public static OutputStream output = new ByteArrayOutputStream();
	public String suffix = ".jpg";
	public static byte data[] = null;
	public static File localfile = null;
	public static InputStream In = null ; 
	public static String str=null;
    public static StringBuffer buffer = new StringBuffer();
	@BeforeClass
	public static void beforeClass() throws IOException{
        try{
        	
        localfile=new File(TfsBaseCase.RESPATH +"100k.jpg");
        
        In=new FileInputStream(localfile);

        
        byte[] tmp = new byte[100];

        int length = 0;

        while((length = In.read(tmp)) != -1)//每次读10字节
        {
           buffer.append(new String(tmp,0,length));
        }
          str=buffer.toString();
          data=str.getBytes();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
        if(In!= null)
        {
          In.close();
        }
        }

	}
	
	@Before
	public void Before(){
		pathInit();
	}
	
	@Test
	public void test_01_saveFile_then_fetchFile_with_filename_suffix_offset_length_of_Long_MAX_VALUE(){
		log.info( "test_01_saveFile_then_fetchFile_with_filename_suffix_offset_length_of_Long_MAX_VALUE" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(data, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		
		/* fetchFile */
		long offset = 0;
		long length = Long.MAX_VALUE ;
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", offset, length, output);
		
		Assert.assertTrue("Fetching file with right suffix should be true", bRet);	

		/* compare to verify the output stream  */
		Assert.assertTrue(str.equals(output.toString()));
	}

	@Test
	public void test_02_saveFile_then_fetchFile_with_filename_suffix_offset_length_less_than_size(){
		log.info( "test_02_saveFile_then_fetchFile_with_filename_suffix_offset_length_less_than_size" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(data, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		
		/* fetchFile */
		long offset = 0;
		long length = data.length/2;
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", offset, length, output);
		
		Assert.assertTrue("Fetching file with right suffix should be true", bRet);	

		/* compare to verify the output stream  */

		Assert.assertNotNull(output.toString());
	}
 
	@Test
	public void test_03_saveFile_then_fetchFile_with_filename_suffix_offset_at_10_length_less_than_size(){
		log.info( "test_03_saveFile_then_fetchFile_with_filename_suffix_offset_at_10_length_less_than_size" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(data, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		
		/* fetchFile */
		long offset = 10;
		long length = data.length/2;
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", offset, length, output);
		
		Assert.assertTrue("Fetching file with right suffix should be true", bRet);	

		/* compare to verify the output stream  */
		Assert.assertNotNull(output.toString());
	}

	@Test
	public void test_04_saveFile_then_fetchFile_with_filename_suffix_offset_invalid_length_less_than_size(){
		log.info( "test_04_saveFile_then_fetchFile_with_filename_suffix_offset_invalid_length_less_than_size" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(data, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		
		/* fetchFile */
		long offset = -1;
		long length = data.length/2;
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", offset, length, output);

        Assert.assertEquals(false, bRet);
	}

	@Test
	public void test_05_saveFile_then_fetchFile_with_filename_suffix_offset_at_0_length_is_0(){
		log.info( "test_05_saveFile_then_fetchFile_with_filename_suffix_offset_at_0_length_is_0" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(data, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		
		/* fetchFile */
		long offset = 0;
		long length = 0;
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", offset, length, output);
		
		Assert.assertEquals(true, bRet);
	}
	@Test
	public void test_06_saveFile_then_fetchFile_with_filename_suffix_offset_at_0_length_is_negative_1(){
		log.info( "test_06_saveFile_then_fetchFile_with_filename_suffix_offset_at_0_length_is_negative_1" );
		
		/* preparation */
		String TfsName_without_suffix = tfsManager.newTfsFileName( "aa" );
		System.out.println("Created tfsName is " + TfsName_without_suffix);
		
		/* saveFile */
		String TfsName_with_suffix = tfsManager.saveFile(data, TfsName_without_suffix, ".jpg");
		Assert.assertNotNull(TfsName_with_suffix);
		
		/* fetchFile */
		long offset = 0;
		long length = -1;
		boolean bRet = tfsManager.fetchFile(TfsName_without_suffix, ".jpg", offset, length, output);
		
		Assert.assertEquals(false, bRet);
	}
}

