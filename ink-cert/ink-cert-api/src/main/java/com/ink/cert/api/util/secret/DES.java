package com.ink.cert.api.util.secret;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES通用类
 * @version 1.0.0_1
 *
 */
public class DES {

	/**
	 *加密方法
	 * @param text 明文
	 * @param key 密钥 BASE64
	 * @param charset 字符集
	 * @return 密文
	 * @throws Exception
	 */
	public static String encrypt(String text, String key, String charset)
			throws Exception {
		byte[] keyBase64DecodeBytes = Base64.decode(key);//base64解码key
		DESKeySpec desKeySpec = new DESKeySpec(keyBase64DecodeBytes);//前8个字节做为密钥
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] textBytes = text.getBytes(charset);//明文UTF-8格式
		byte[] bytes = cipher.doFinal(textBytes);//DES加密

		return new String(Base64.encode(bytes));
	}

	/**
	 * 解密方法
	 * @param text 密文
	 * @param key 密钥 BASE64
	 * @param charset　字符集
	 * @return 明文
	 * @throws Exception
	 */
	public static String decrypt(String text, String key, String charset)
			throws Exception {
		byte[] keyBase64DecodeBytes = Base64.decode(key);

		DESKeySpec desKeySpec = new DESKeySpec(keyBase64DecodeBytes);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] textBytes = Base64.decode(text);
		byte[] bytes = cipher.doFinal(textBytes);

		return new String(bytes, charset);
	}

}