package com.ink.cert.api.util.secret;

import com.ink.cert.api.constant.SecretConstant;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.api.util.FormatUtil;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RAS加解密
 * Created by aiyungui on 2016/6/23.
 */
public class RSA {

    /**
     * 私钥加密
     * @param message
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String message, PrivateKey privateKey)throws Exception{

        byte[] destBytes = rsaByPrivateKey(message.getBytes(), privateKey, Cipher.ENCRYPT_MODE);

        if (destBytes == null) {
            return null;
        }

        return FormatUtil.byte2Hex(destBytes);
    }

    /**
     *  私钥解密
     * @param message
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String message, PrivateKey privateKey) throws Exception{
        if (FormatUtil.isEmpty(message)) {
            return null;
        }
        byte[] destBytes = rsaByPrivateKey(FormatUtil.hex2Bytes(message), privateKey, Cipher.DECRYPT_MODE);
        if (destBytes == null) {
            return null;
        }
        return new String(destBytes, SecretConstant.ENCODE);
    }
    /**
     * 公钥加密
     * @param message
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String message, PublicKey publicKey) throws Exception{
        byte[] destBytes = rsaByPublicKey(message.getBytes(), publicKey, Cipher.ENCRYPT_MODE);

        if (destBytes == null) {
            return null;
        }

        return FormatUtil.byte2Hex(destBytes);
    }

    /**
     * 公钥解密
     * @param message
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String message, PublicKey publicKey) throws Exception{

        byte[] destBytes = rsaByPublicKey(FormatUtil.hex2Bytes(message), publicKey, Cipher.DECRYPT_MODE);
        if (destBytes == null) {
            return null;
        }
        return new String(destBytes, SecretConstant.ENCODE);
    }

    /**
     * 公钥算法
     * @param messageData
     * @param publicKey
     * @param mode
     * @return
     */
    public static byte[] rsaByPublicKey(byte[] messageData, PublicKey publicKey, int mode) throws Exception{
        Cipher cipher = Cipher.getInstance(SecretConstant.RSA_CHIPER);
        cipher.init(mode, publicKey);
        int blockSize = (mode == Cipher.ENCRYPT_MODE) ? SecretConstant.ENCRYPT_KEYSIZE : SecretConstant.DECRYPT_KEYSIZE;
        byte[] encryptedData = null;
        for (int i = 0; i < messageData.length; i += blockSize) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(CertUtil.subarray(messageData, i, i + blockSize));
            encryptedData = CertUtil.addAll(encryptedData, doFinal);
        }
        return encryptedData;
    }

    /**
     * 私钥算法
     * @param messageData
     * @param privateKey
     * @param mode
     * @return
     */
    public static byte[] rsaByPrivateKey(byte[] messageData, PrivateKey privateKey, int mode)throws Exception{

        Cipher cipher = Cipher.getInstance(SecretConstant.RSA_CHIPER);
        cipher.init(mode, privateKey);
        int blockSize = (mode == Cipher.ENCRYPT_MODE) ? SecretConstant.ENCRYPT_KEYSIZE : SecretConstant.DECRYPT_KEYSIZE;
        byte[] decryptData = null;
        for (int i = 0; i < messageData.length; i += blockSize) {
            byte[] doFinal = cipher.doFinal(CertUtil.subarray(messageData, i, i + blockSize));
            decryptData = CertUtil.addAll(decryptData, doFinal);
        }
        return decryptData;
    }

}
