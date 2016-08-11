package com.ink.channel.core.epro;


/*
 --------------------------------------------**********--------------------------------------------

 ���㷨��1977����������ʡ�?ѧԺMIT(Massachusetts Institute of Technology)��Ronal Rivest��Adi Shamir��Len Adleman��λ�������������������˵�����Rivest��Shamir��Adlernan����ΪRSA�㷨����һ��֧�ֱ䳤��Կ�Ĺ�����Կ�㷨����Ҫ���ܵ��ļ���ĳ���Ҳ�ǿɱ��!

 ��νRSA�����㷨���������ϵ�һ���ǶԳƼ����㷨��Ҳ�����۵ĵ�һ��ʵ��Ӧ�á�����㷨���£�

 1.�������ǳ��������p��q��ͨ��p��q����155ʮ����λ����512ʮ����λ��������n=pq��k=(p-1)(q-1)��

 2.�����ı��������M����֤M��С��0����С��n��

 3.��ȡһ������e����֤e��k���ʣ�����e��С��0����С��k������Կ�ף�������Կ����(e, n)��

 4.�ҵ�һ������d��ʹ��ed����k��������1��ֻҪe��n��������������d�϶����ڣ�������Կ�ף�������Կ����(d, n)��

 ���ܹ�̣� ���ܺ�ı���C����M��e�η�����n��õ�����

 ���ܹ�̣� ���ܺ�ı���N����C��d�η�����n��õ�����

 ֻҪe��d��n����������������M����N��

 --------------------------------------------**********--------------------------------------------
 */
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSA {
    /** ָ��key�Ĵ�С */
    private static int KEYSIZE = 1024;

    /**
     * �����Կ��
     */
    public static Map<String, String> generateKeyPair() throws Exception {
        /** RSA�㷨Ҫ����һ�������ε������Դ */
        SecureRandom sr = new SecureRandom();
        /** ΪRSA�㷨����һ��KeyPairGenerator���� */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        /** ���������������Դ��ʼ�����KeyPairGenerator���� */
        kpg.initialize(KEYSIZE, sr);
        /** ����ܳ׶� */
        KeyPair kp = kpg.generateKeyPair();
        /** �õ���Կ */
        Key publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String pub = new String(Base64.encodeBase64(publicKeyBytes),
                ConfigureEncryptAndDecrypt.CHAR_ENCODING);
        /** �õ�˽Կ */
        Key privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String pri = new String(Base64.encodeBase64(privateKeyBytes),
                ConfigureEncryptAndDecrypt.CHAR_ENCODING);

        Map<String, String> map = new HashMap<String, String>();
        map.put("publicKey", pub);
        map.put("privateKey", pri);
        RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
        BigInteger bint = rsp.getModulus();
        byte[] b = bint.toByteArray();
        byte[] deBase64Value = Base64.encodeBase64(b);
        String retValue = new String(deBase64Value);
        map.put("modulus", retValue);
        return map;
    }

    /**
     * ���ܷ��� source�� Դ���
     */
    public static String encrypt(String source, String publicKey)
            throws Exception {
        Key key = getPublicKey(publicKey);
        /** �õ�Cipher������ʵ�ֶ�Դ��ݵ�RSA���� */
        Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** ִ�м��ܲ��� */
        byte[] b1 = cipher.doFinal(b);
        return new String(Base64.encodeBase64(b1),
                ConfigureEncryptAndDecrypt.CHAR_ENCODING);
    }

    /**
     * �����㷨 cryptograph:����
     */
    public static String decrypt(String cryptograph, String privateKey)
            throws Exception {
        Key key = getPrivateKey(privateKey);
        /** �õ�Cipher��������ù�Կ���ܵ���ݽ���RSA���� */
        Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
        /** ִ�н��ܲ��� */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * �õ���Կ
     * 
     * @param key
     *            ��Կ�ַ�����base64���룩
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * �õ�˽Կ
     * 
     * @param key
     *            ��Կ�ַ�����base64���룩
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static String sign(String content, String privateKey) {
        String charset = ConfigureEncryptAndDecrypt.CHAR_ENCODING;
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(privateKey.getBytes()));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance("SHA1WithRSA");

            signature.initSign(priKey);
            signature.update(content.getBytes(charset));

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {

        }

        return null;
    }
    
    public static boolean checkSign(String content, String sign, String publicKey)
    {
        try 
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode2(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

        
            java.security.Signature signature = java.security.Signature
            .getInstance("SHA1WithRSA");
        
            signature.initVerify(pubKey);
            signature.update( content.getBytes("utf-8") );
        
            boolean bverify = signature.verify( Base64.decode2(sign) );
            return bverify;
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return false;
    }   

}