package com.ink.channel.core.minsheng;



import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

//import com.msds.baseUtil.exception.AppException;


/**
 * 支持 DES对称加密的工具类.
 * 
 * 支持Hex与Base64两种编码方式.
 * 
 * @author wangwch
 */
public class CryptoUtils {

	private static final String DES = "DES";
	private static final String DEFAULT_ENCODING = "UTF-8";
	/** 加密算法 */
	private final static String ALGORITHM = "DES/CBC/PKCS5Padding";

	/**
	 * 传输的关键数据加�?
	 * @param keyData �?��密�?
	 * @param timestamp  时间�?
	 * @param dataSecret  密钥
	 * @return String 加密数据
	 */
	public static String encryptKeyData(String keyData, Long timestamp, String dataSecret) {

		DateFormat df = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
		keyData = df.format(new Date(timestamp)) + keyData;
		return desEncryptToBase64(keyData, dataSecret.getBytes());
	}
	/**
	 * 传输的关键数据加�?
	 * @param keyData �?��密�?
	 * @param timestamp  时间�?
	 * @param dataSecret  密钥
	 * @return String 加密数据
	 */
	public static String encryptKeyData(String keyData, String timestamp, String dataSecret){
		keyData = timestamp + keyData;
		return desEncryptToBase64(keyData, dataSecret.getBytes());
	}

	//-- DES function --//
	/**
	 * 使用DES加密原始字符�? 返回Hex编码的结�?
	 * 
	 * @param input 原始输入字符�?
	 * @param keyBytes 符合DES要求的密�?
	 */
	public static String desEncryptToHex(String input, byte[] keyBytes) {
		byte[] encryptResult = null;
		try {
			encryptResult = des(input.getBytes(DEFAULT_ENCODING), keyBytes, Cipher.ENCRYPT_MODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return EncodeUtils.hexEncode(encryptResult);
	}

	/**
	 * 使用DES加密原始字符�? 返回Base64编码的结�?
	 * 
	 * @param input 原始输入字符�?
	 * @param keyBytes 符合DES要求的密�?
	 */
	public static String desEncryptToBase64(String input, byte[] keyBytes) {
		byte[] encryptResult = null;
		try {
			encryptResult = des(input.getBytes(DEFAULT_ENCODING), keyBytes, Cipher.ENCRYPT_MODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return EncodeUtils.base64Encode(encryptResult);
	}

	/**
	 * 使用DES解密Hex编码的加密字符串, 返回原始字符�?
	 * 
	 * @param input Hex编码的加密字符串
	 * @param keyBytes 符合DES要求的密�?
	 */
	public static String desDecryptFromHex(String input, byte[] keyBytes) {
		byte[] decryptResult = des(EncodeUtils.hexDecode(input), keyBytes, Cipher.DECRYPT_MODE);
		String decryptString = null;
		try {
			decryptString = new String(decryptResult, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decryptString;
	}

	/**
	 * 使用DES解密Base64编码的加密字符串, 返回原始字符�?
	 * 
	 * @param input Base64编码的加密字符串
	 * @param keyBytes 符合DES要求的密�?
	 */
	public static String desDecryptFromBase64(String input, byte[] keyBytes) {
		byte[] decryptResult = des(EncodeUtils.base64Decode(input), keyBytes, Cipher.DECRYPT_MODE);
		String decryptString = null;
		try {
			decryptString = new String(decryptResult, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decryptString;
	}

	/**
	 * 使用DES加密或解密无编码的原始字节数�? 返回无编码的字节数组结果.
	 * 通用的java .net php
	 * @param inputBytes 原始字节数组
	 * @param keyBytes 符合DES要求的密�?
	 * @param mode Cipher.ENCRYPT_MODE �?Cipher.DECRYPT_MODE
	 */
	private static byte[] des(byte[] inputBytes, byte[] keyBytes, int mode) {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			//密钥
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			// 偏移�?
			IvParameterSpec iv = new IvParameterSpec(keyBytes);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(mode, secretKey, iv);
			return cipher.doFinal(inputBytes);
		} catch (GeneralSecurityException e) {
			throw convertRuntimeException(e);
		}
	}

	/**
	 * 生成符合DES要求的密�? 长度�?4�?8字节).
	 */
	public static byte[] generateDesKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (GeneralSecurityException e) {
			throw convertRuntimeException(e);
		}
	}

	/**
	 * 生成符合DES要求的Hex编码密钥, 长度�?6字符.
	 */
	public static String generateDesHexKey() {
		return EncodeUtils.hexEncode(generateDesKey());
	}

	private static IllegalStateException convertRuntimeException(GeneralSecurityException e) {
		return new IllegalStateException("Security exception", e);
	}
	
	/**
	 * 获取关键数据的原�?
	 * @param encryptKeyData
	 * @param appKey
	 * @param signV 签名版本�?
	 * @param timestamp
	 * @return keyData  原始值为�?返回为空,否则取原始�?
	 * @throws ApiException
	 */
	public static String getKeyData(String encryptKeyData, String dataSecret, String timestamp){
		if (encryptKeyData == null) {
			return null;
		}
		String keyData = null;
		try{
			keyData = CryptoUtils.desDecryptFromBase64(encryptKeyData, dataSecret.getBytes());
		}catch(Exception e){
			e.printStackTrace();
			//throw new AppException("EMB0000004");
		}
		
		if (!keyData.startsWith(timestamp)) {
			//throw new AppException("EMB0000004");
		}
		//返回原始数据,去除固定�?时间�?aop,�?7�?
		keyData = keyData.substring(14, keyData.length());

		return keyData;
	}
}