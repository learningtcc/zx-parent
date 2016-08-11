package com.taobao.common.tfs.testcase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.taobao.common.tfs.TfsBaseCase;
import com.taobao.common.tfs.etc.TfsConstant;


public class TfsManager_16_openReadFile extends TfsBaseCase{
	
	public String suffix = ".jpg";
	public static byte L_Buff[];
	public static byte S_Buff[];
	public static File l_file = new File(resource_file_big);
	public static File s_file = new File(resource_file);
	public static InputStream  In = null ; 
	public static String str=null;
    public static StringBuffer buffer1 = new StringBuffer();
    public static StringBuffer buffer2 = new StringBuffer();
	@BeforeClass
	public static void beforeClass() throws IOException{
        try{
 
        In = new FileInputStream(l_file);

        byte[] tmp = new byte[10];

        int length = 0;

        while((length = In.read(tmp)) != -1)//每次读10字节
        {
           buffer1.append(new String(tmp,0,length));
        }
        str=buffer1.toString();
        L_Buff=str.getBytes();
        str=null;
        In = new FileInputStream(s_file);
        while((length = In.read(tmp)) != -1)//每次读10字节
        {
           buffer2.append(new String(tmp,0,length));
        }
        str=buffer2.toString();
        S_Buff=str.getBytes();
        
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
	public void test_01_WriteFile_then_openReadFile_with_filename_and_suffix(){
		log.info( "test_01_WriteFile_then_openReadFile_with_filename_and_suffix" );
		String key=resource_file;
		int fd=-1;
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
        log.error("open rad file return"+fd);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/
		byte[] readBuff = new byte[L_Buff.length];
		ret=tfsManager.readFile(fd, readBuff, 0,L_Buff.length);
		Assert.assertEquals(L_Buff.length, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_02_WriteFile_then_openReadFile_with_filename_and_empty_suffix(){
		log.info( "test_02_WriteFile_then_openReadFile_with_filename_and_empty_suffix" );
		String key=resource_file;
		int fd=-1;
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        
        /*openReadFile*/
        suffix="";
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/		
		byte[] readBuff = new byte[L_Buff.length];
		ret=tfsManager.readFile(fd, readBuff, 0,L_Buff.length);
		Assert.assertEquals(L_Buff.length, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_03_WriteFile_then_openReadFile_with_filename_and_null_suffix(){
		log.info( "test_03_WriteFile_then_openReadFile_with_filename_and_null_suffix" );
		String key=resource_file;
		int fd=-1;
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile*/
        suffix=null;
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[L_Buff.length];
		ret=tfsManager.readFile(fd, readBuff, 0,L_Buff.length);
		Assert.assertEquals(L_Buff.length, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_04_WriteFile_with_empty_suffix_then_openReadFile_with_filename_and_empty_suffix(){
		log.info( "test_04_WriteFile_with_empty_suffix_then_openReadFile_with_filename_and_empty_suffix" );
		String key=resource_file;
		int fd=-1;
		suffix="";
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile*/
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[L_Buff.length];
		ret=tfsManager.readFile(fd, readBuff, 0,L_Buff.length);
		Assert.assertEquals(L_Buff.length, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_05_WriteFile_with_empty_suffix_then_openReadFile_with_filename_and_null_suffix(){
		log.info( "test_05_WriteFile_with_empty_suffix_then_openReadFile_with_filename_and_null_suffix" );
		String key=resource_file;
		int fd=-1;
		suffix="";
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile*/
        suffix=null;
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/	
		byte[] readBuff = new byte[L_Buff.length];
		ret=tfsManager.readFile(fd, readBuff, 0,L_Buff.length);
		Assert.assertEquals(L_Buff.length, ret);
		
		/*close the fd and verify the filename to be not null*/
		filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
    }
	@Test
	public void test_06_WriteFile_with_empty_suffix_then_openReadFile_with_empty_filename_and_empty_suffix(){
		log.info( "test_06_WriteFile_with_empty_suffix_then_openReadFile_with_empty_filename_and_empty_suffix" );
		String key=resource_file;
		int fd=-1;
		suffix="";
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile*/
        suffix=null;
        filename="";
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, fd);
		
		/*fail and close the fd */
		tfsManager.closeFile(fd);
    }
	@Test
	public void test_07_WriteFile_with_empty_suffix_then_openReadFile_with_null_filename_and_empty_suffix(){
		log.info( "test_07_WriteFile_with_empty_suffix_then_openReadFile_with_null_filename_and_empty_suffix" );
		String key=resource_file;
		int fd=-1;
		suffix="";
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, L_Buff, 0, L_Buff.length);
        Assert.assertEquals(L_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile */
        filename=null;
        suffix="";
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertEquals(TfsConstant.EXIT_INVALIDFD_ERROR, fd);
		
		/*fail and close the fd */
        tfsManager.closeFile(fd);

    }
	@Test
	public void test_08_Write_small_File_with_empty_suffix_then_openReadFile_with_filename_and_empty_suffix(){
		log.info( "test_08_Write_small_File_with_empty_suffix_then_openReadFile_with_filename_and_empty_suffix" );
		String key=resource_file;
		int fd=-1;
		suffix="";
		/* openWriteFile */
		fd=tfsManager.openWriteFile("", suffix, key); 
		Assert.assertTrue(fd>0);
		
		/* Write file to verify the result */
        int ret=tfsManager.writeFile(fd, S_Buff, 0, S_Buff.length);
        Assert.assertEquals(S_Buff.length, ret);
        
        /*close the fd and verify the filename to be not null*/
        String filename=tfsManager.closeFile(fd);
        Assert.assertNotNull(filename);
        
        /*openReadFile */
        fd=tfsManager.openReadFile(filename, suffix);
		Assert.assertTrue(fd>0);
		
		/*read file to local buff*/
		byte[] readBuff = new byte[S_Buff.length];
		ret=tfsManager.readFile(fd, readBuff, 0, S_Buff.length);
        Assert.assertEquals(S_Buff.length, ret);
        
		/*fail and close the fd */
        filename = tfsManager.closeFile(fd);
        Assert.assertNotNull(filename); 
        log.error("end test");
    }
}
