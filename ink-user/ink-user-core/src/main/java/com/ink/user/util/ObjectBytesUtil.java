package com.ink.user.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 *
 */
public class ObjectBytesUtil {
	
	public byte[] objectToBytes(Object value) throws IOException{
		if(value == null)
			return null;
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = null;
		try{
			outputStream = new ObjectOutputStream(arrayOutputStream);
			outputStream.writeObject(value);
		}catch(IOException e){
			throw new IOException("[ERROR] ObjectBytesUtil.objectToBytes ", e);
		}finally{
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					throw new IOException("[ERROR] ObjectBytesUtil.objectToBytes ", e);
				}
			}
			if(arrayOutputStream != null){
				try {
					arrayOutputStream.close();
				} catch (IOException e) {
					throw new IOException("[ERROR] ObjectBytesUtil.objectToBytes ", e);
				}
			}
		}
		return arrayOutputStream.toByteArray();
	}
	
	public Object bytesToObject(byte[] bytes) throws IOException{
		if(bytes == null || bytes.length == 0){
			return null;
		}
		ObjectInputStream inputStream = null;
		try{
			inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = inputStream.readObject();
			return obj;
		}catch(Exception e){
			throw new IOException("[ERROR] ObjectBytesUtil.bytesToObject ", e);
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new IOException("[ERROR] ObjectBytesUtil.bytesToObject ", e);
				}
			}
		}
	}
	
}
