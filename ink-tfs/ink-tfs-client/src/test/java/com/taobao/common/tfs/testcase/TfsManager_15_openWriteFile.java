package com.taobao.common.tfs.testcase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;
import com.taobao.common.tfs.etc.TfsConstant;


public class TfsManager_15_openWriteFile extends TfsBaseCase{
	
	public String suffix = ".jpg";
	public static byte btBuff[];
	public static File localfile = null;
	public static InputStream In = null ; 
	public static String str=null;
    public static StringBuffer buffer = new StringBuffer();
	@BeforeClass
	public static void beforeClass() throws IOException{
        try{
        	
        localfile=new File(resource_file);
        
        In=new FileInputStream(localfile);

        
        byte[] tmp = new byte[10];

        int length = 0;

        while((length = In.read(tmp)) != -1)//每次读10字节
        {
           buffer.append(new String(tmp,0,length));
        }
          str=buffer.toString();
          btBuff=str.getBytes();
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
		suffix = ".jpg";
	}
	@Test
	public void test_01_openWriteFile_with_empty_filename_null_suffix_right_key(){
		log.info( "test_01_openWriteFile_with_empty_filename_null_suffix_right_key" );
		/* preparation */
		String key=resource_file;
		int fd=-1;
//		String TfsName_without_suffix = tfsManager.newTfsFileName("");
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, btBuff, 0, btBuff.length);
        Assert.assertEquals(btBuff.length, ret);
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
	    
    }
	@Test
	public void test_02_openWriteFile_with_empty_filename_empty_suffix_right_key(){
		log.info( "test_02_openWriteFile_with_empty_filename_empty_suffix_right_key" );
		/* preparation */
		String key=resource_file;
		int fd=-1;
		suffix="";
//		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
	    Assert.assertTrue(fd>0);
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, btBuff, 0, btBuff.length);
        Assert.assertEquals(btBuff.length, ret);
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }	
	@Test
	public void test_03_openWriteFile_with_empty_filename_null_suffix_right_key(){
		log.info( "test_03_openWriteFile_with_empty_filename_null_suffix_right_key" );
		/* preparation */
		String key=resource_file;
		int fd=-1;
		suffix=null;
//		String TfsName_without_suffix = tfsManager.newTfsFileName( "" );
		/* openWriteFile */
		fd=tfsManager.openWriteFile("",suffix, key); 
	    Assert.assertTrue(fd>0);
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, btBuff, 0, btBuff.length);
        Assert.assertEquals(btBuff.length, ret);
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }	
	@Test
	public void test_04_openWriteFile_with_null_filename_null_suffix_right_key(){
		log.info( "test_04_openWriteFile_with_null_filename_null_suffix_right_key" );
		/* preparation */
		String key=resource_file;
		int fd=-1;
		suffix=null;
		/* openWriteFile */
		fd=tfsManager.openWriteFile(null,suffix ,key); 
	    Assert.assertTrue(fd>0);
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, btBuff, 0, btBuff.length);
        Assert.assertEquals(btBuff.length, ret);
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }	
	@Test
	public void test_05_openWriteFile_with_newtfsFilename_null_suffix_right_key(){
		log.info( "test_05_openWriteFile_with_newtfsFilename_null_suffix_right_key" );
		/* preparation */
		String key=resource_file;
		int fd=-1;
		suffix=null;
		String TfsName_without_suffix = tfsManager.newTfsFileName("");
		/* openWriteFile */
		fd=tfsManager.openWriteFile(TfsName_without_suffix,suffix ,key); 
	    Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, fd);
    }		
	@Test
	public void test_06_openWriteFile_with_empty_filename_null_suffix_empty_key(){
		log.info( "test_06_openWriteFile_with_empty_filename_null_suffix_empty_key" );
		/* preparation */
//		String key="";
		int fd=-1;
		suffix=null;
		/* openWriteFile */
		fd=tfsManager.openWriteFile("",null ,""); 
		log.error("return fd:"+fd);
	    Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, fd);

    }	
	@Test
	public void test_07_openWriteFile_with_empty_filename_null_suffix_null_key(){
		log.info( "test_07_openWriteFile_with_empty_filename_null_suffix_null_key" );
		/* preparation */
		String key=null;
		int fd=-1;
		suffix=null;
//		String TfsName_without_suffix = tfsManager.newTfsFileName("");
		/* openWriteFile */
		fd=tfsManager.openWriteFile("",suffix ,key); 
	    Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, fd);

    }	

}
