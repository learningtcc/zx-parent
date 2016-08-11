package com.taobao.common.tfs.testcase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



import com.taobao.common.tfs.TfsBaseCase;

import com.taobao.common.tfs.etc.TfsConstant;


public class TfsManager_19_readFile_fileoffset extends TfsBaseCase{
	
	public String suffix = ".jpg";
	public static String strBuff = "";
	public static int iBuff;
	public static byte data[] = null;
	public static File localfile = null;
	public static InputStream In = null ;
	public static String str=null;
	public static byte null_data[]=null;
	public int readCount = 1024*1024;
	@BeforeClass
	public static void beforeClass() throws IOException{
        try{
        	
        localfile=new File(TfsBaseCase.RESPATH +"4M.jpg");

        In=new FileInputStream(localfile);
        StringBuffer buffer = new StringBuffer();

        byte[] tmp = new byte[10];

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
	public void test_01_readFile_with_fd_fileOffset_data_offset_length(){
		log.info( "test_01_readFile_with_fd_fileOffset_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        log.error("write file return"+ ret);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset=0;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0,readCount);
		Assert.assertEquals(readCount, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }

	@Test
	public void test_02_readFile_with_fd_fileOffset_data_offset_at_1_length(){
		log.info( "test_02_readFile_with_fd_fileOffset_data_offset_at_1_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = 0;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 1,readCount-1);
		Assert.assertEquals(readCount-1, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_03_readFile_with_fd_fileOffset_data_offset_length_4_times(){
		log.info( "test_03_readFile_with_fd_fileOffset_data_offset_length_4_times" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		long fileOffset = 0;
		for(int loop=0;loop<4;loop++){
		   byte[] readBuff = new byte[readCount];
		   ret=tfsManager.readFile(fd, fileOffset, readBuff, 0,readCount);
		   Assert.assertEquals(readCount, ret);
		}
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }

	@Test
	public void test_04_readFile_with_fd_is_0_fileOffset_data_offset_length(){
		log.info( "test_04_readFile_with_fd_is_0_fileOffset_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = 0;
		ret=tfsManager.readFile(0, fileOffset, readBuff, 0,readCount);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_05_readFile_with_fd_is_negative_1_fileOffset_data_offset_length(){
		log.info( "test_05_readFile_with_fd_is_negative_1_fileOffset_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = 0;
		ret=tfsManager.readFile(-1, fileOffset, readBuff, 0,readCount);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_06_readFile_with_fd_fileOffset_data_is_null_offset_length(){
		log.info( "test_06_readFile_with_fd_fileOffset_data_is_null_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = null;
		long fileOffset = 0;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0, readCount);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_07_readFile_with_fd_fileOffset_data_offset_at_negative_1_length(){
		log.info( "test_07_readFile_with_fd_fileOffset_data_offset_at_negative_1_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		long fileOffset = 0;
		byte[] readBuff = new byte[readCount];
		ret=tfsManager.readFile(fd, fileOffset, readBuff, -1, readCount);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_08_readFile_with_fd_fileOffset_data_offset_length_plus_1(){
		log.info( "test_08_readFile_with_fd_fileOffset_data_offset_length_plus_1" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = 0;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0,readCount+1);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_09_readFile_with_fd_fileOffset_data_offset_length_is_0(){
		log.info( "test_09_readFile_with_fd_fileOffset_data_offset_length_is_0" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = 0;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0,0);
		Assert.assertEquals(0, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_10_readFile_with_fd_fileOffset_data_offset_length_is_negative_1(){
		log.info( "test_10_readFile_with_fd_fileOffset_data_offset_length_is_negative_1" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = 0;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0, -1);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_11_readFile_with_fd_fileOffset_at_1M_data_offset_length(){
		log.info( "test_11_readFile_with_fd_fileOffset_at_1M_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        log.error("write file return"+ ret);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset=1024*1024;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0, readCount);
		Assert.assertEquals(readCount, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	
	@Test
	public void test_12_readFile_with_fd_fileOffset_at_negative_1_data_offset_length(){
		log.info( "test_12_readFile_with_fd_fileOffset_at_negative_1_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        log.error("write file return"+ ret);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = -1;
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0, readCount);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_13_readFile_with_fd_fileOffset_at_4M_plus_1_data_offset_length(){
		log.info( "test_13_readFile_with_fd_fileOffset_at_4M_plus_1_data_offset_length" );
		/* preparation */
		String key=resource_file;
		int fd=-1;

		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key);
		Assert.assertTrue(fd>0);
		
        /*check if the byte[] data is as large as the file it read from*/
		Assert.assertEquals((int)localfile.length(), data.length);
		
		/* Write file to verify the result */		
        int ret=tfsManager.writeFile(fd, data, 0, data.length);
        log.error("write file return"+ ret);
        Assert.assertEquals(data.length, ret);

        /* close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);

        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[readCount];
		long fileOffset = (4*1024*1024)+1;
		log.error("fileoffset:"+fileOffset);
		ret=tfsManager.readFile(fd, fileOffset, readBuff, 0, readCount);
		log.error("return "+ret);
		//Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, ret);
		Assert.assertEquals(0, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
}
