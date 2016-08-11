package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;



import com.taobao.common.tfs.TfsBaseCase;

import com.taobao.common.tfs.etc.TfsConstant;


public class TfsManager_17_writeFile extends TfsBaseCase{
	
	public String suffix = ".jpg";
	public static int iBuff;
	public static byte data[] = null;
	public static File localfile = null;
	public static InputStream In = null ; 
	public static String str=null;
	public static byte null_data[]=null;

	@BeforeClass
	public static void beforeClass() throws IOException{
        try{
        	
        localfile=new File(TfsBaseCase.RESPATH +"1m.jpg");
        
        In=new FileInputStream(localfile);
        StringBuffer buffer = new StringBuffer();
        
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
        suffix=".jpg";

	}
	@Test
	public void test_01_WriteFile_with_fd_data_offset_length(){
		log.info( "test_01_WriteFile_with_fd_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		log.error("trickyrtrickychop:"+data.length);
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);
        
        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

    }
	@Test
	public void test_02_WriteFile_with_fd_data_offset_file_length_minus_1(){
		log.info( "test_02_WriteFile_with_fd_data_offset_file_length_minus_1" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length-1);
        Assert.assertEquals(data.length-1, ret);
        
        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_03_WriteFile_with_fd_data_offset_at_1_file_length(){
		log.info( "test_03_WriteFile_with_fd_data_offset_at_1_file_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 1, data.length-1);
        Assert.assertEquals(data.length-1, ret);
        
        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_04_WriteFile_with_fd_is_0_data_offset_file_length(){
		log.info( "test_04_WriteFile_with_fd_is_0_data_offset_file_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(0, data, 0, data.length);
        Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
        
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
	@Test
	public void test_05_WriteFile_with_fd_is_negtive_1_data_offset_file_length(){
		log.info( "test_05_WriteFile_with_fd_is_negtive_1_data_offset_file_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(-1, data, 0, data.length);
        Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
        
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
	@Test
	public void test_06_WriteFile_with_fd_null_data_offset_file_length(){
		log.info( "test_06_WriteFile_with_fd_null_data_offset_file_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret = tfsManager.writeFile(fd, null_data, 0, data.length);
//        log.error("writeFile return "+ret);
        Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
	@Test
	public void test_07_WriteFile_with_fd_data_offset_is_negative_file_length(){
		log.info( "test_07_WriteFile_with_fd_data_offset_is_negative_file_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, -1, data.length);
        Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
        
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
	@Test
	public void test_08_WriteFile_with_fd_data_offset_file_length_plus_1(){
		log.info( "test_08_WriteFile_with_fd_data_offset_file_length_plus_1" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length+1);
        Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
        
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
	@Test
	public void test_09_WriteFile_with_fd_data_offset_file_length_is_0(){
		log.info( "test_09_WriteFile_with_fd_data_offset_file_length_is_0" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, 0);
        Assert.assertEquals(0, ret);
        
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
	@Test
	public void test_10_WriteFile_with_fd_data_offset_file_length_is_negative_1(){
		log.info( "test_10_WriteFile_with_fd_data_offset_file_length_is_negative_1" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		//log.error("return file descriptor:"+fd);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, -1);
        Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
        
        /* close the fd and verify the filename to be not null*/
        tfsManager.closeFile(fd);
    }
}