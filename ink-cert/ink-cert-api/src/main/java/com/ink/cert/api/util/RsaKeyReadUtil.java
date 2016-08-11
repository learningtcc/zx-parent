package com.ink.cert.api.util;

import com.ink.cert.api.constant.SecretConstant;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

/**
 * 公私钥读取工具
 * 
 * @author zhengchao
 * @create 2016-3-03 下午14:21:00 *
 */
public final class RsaKeyReadUtil {

	/**
	 * 获取私钥
	 * @param certPath 证书路径
	 * @param merchantCode 商户编号
	 * @param certCode 证书编号
	 * @param secretKey 证书密码
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String certPath,String merchantCode,String certCode,String secretKey)throws Exception{
		String cacheKey = merchantCode + "_" + certCode;
		PrivateKey privateKey = (PrivateKey) CacheKeyUtil.getPrivateKey(cacheKey);
		if(privateKey == null){
			synchronized (cacheKey) {
				if(!CacheKeyUtil.getPrivateKeyMap().containsKey(cacheKey)){
					privateKey= getPrivateKeyFromFile(certPath, secretKey);
					CacheKeyUtil.putPrivateKey(cacheKey,privateKey);
				}
			}
		}
		return privateKey;
	}

	/**
	 * 获取公钥
	 * @param certPath  证书路径
	 * @param merchantCode 商户编号
	 * @param certCode 证书编号
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String certPath,String merchantCode,String certCode)throws Exception{
		String cacheKey = merchantCode + "_" + certCode;
		PublicKey publicKey = (PublicKey) CacheKeyUtil.getPublicKey(cacheKey);
		if(publicKey == null){
			synchronized (cacheKey) {
				if(!CacheKeyUtil.getPublicKeyMap().containsKey(cacheKey)){
					publicKey= getPublicKeyFromFile(certPath);
					CacheKeyUtil.putPublicKey(cacheKey, publicKey);
				}
			}
		}
		return publicKey;
	}

	/**
	 * 根据Cer文件读取公钥
	 * 
	 * @param pubCerPath
	 * @return
	 */
	public static PublicKey getPublicKeyFromFile(String pubCerPath)throws Exception{
		InputStream pubKeyStream = null;
		try {
			pubKeyStream = new FileInputStream(pubCerPath);
			byte[] reads = new byte[pubKeyStream.available()];
			pubKeyStream.read(reads);
			return getPublicKeyByText(new String(reads));
		} catch (Exception e) {
			throw (e);
		}  finally {
			if (pubKeyStream != null) {
				try {
					pubKeyStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据公钥Cer文本串读取公钥
	 * 
	 * @param pubKeyText
	 * @return
	 */
	public static PublicKey getPublicKeyByText(String pubKeyText) throws  Exception{
		try {
			CertificateFactory certificateFactory = CertificateFactory.getInstance(SecretConstant.KEY_X509);
			BufferedReader br = new BufferedReader(new StringReader(pubKeyText));
			String line = null;
			StringBuilder keyBuffer = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("-")) {
					keyBuffer.append(line);
				}
			}
			Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(keyBuffer.toString())));
			return certificate.getPublicKey();
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * 根据私钥路径读取私钥
	 * 
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	public static PrivateKey getPrivateKeyFromFile(String pfxPath, String priKeyPass)throws  Exception{
		InputStream priKeyStream=null;
		try {
			 priKeyStream = new FileInputStream(pfxPath);
			byte[] reads = new byte[priKeyStream.available()];
			priKeyStream.read(reads);
			return getPrivateKeyByStream(reads, priKeyPass);
		} catch (Exception e) {
			 throw (e);
		} finally {
			if (priKeyStream != null) {
				priKeyStream.close();
			}
		}

	}

	/**
	 * 根据PFX私钥字节流读取私钥
	 * 
	 * @param pfxBytes
	 * @param priKeyPass
	 * @return
	 */
	public static PrivateKey getPrivateKeyByStream(byte[] pfxBytes, String priKeyPass)throws Exception{
		try {
			KeyStore ks = KeyStore.getInstance(SecretConstant.KEY_PKCS12);
			char[] charPriKeyPass = priKeyPass.toCharArray();
			ks.load(new ByteArrayInputStream(pfxBytes), charPriKeyPass);
			Enumeration<String> aliasEnum = ks.aliases();
			String keyAlias = null;
			if (aliasEnum.hasMoreElements()) {
				keyAlias = (String) aliasEnum.nextElement();
			}
			return (PrivateKey) ks.getKey(keyAlias, charPriKeyPass);
		} catch (Exception e) {
			throw (e);
		}
	}
}
