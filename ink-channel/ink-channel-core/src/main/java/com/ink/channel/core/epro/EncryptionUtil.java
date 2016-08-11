package com.ink.channel.core.epro;
import java.security.MessageDigest;

public class EncryptionUtil {
    
    // ���ַ����md5����
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                int v = (int) b[i];
                v = v < 0 ? 0x100 + v : v;
                String cc = Integer.toHexString(v);
                if (cc.length() == 1)
                    sb.append('0');
                sb.append(cc);
            }

            return sb.toString();
        } catch (Exception e) {
        }
        return "";
    }
    
    // ���ַ����sha256����
    public static String sha256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());

            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                int v = (int) b[i];
                v = v < 0 ? 0x100 + v : v;
                String cc = Integer.toHexString(v);
                if (cc.length() == 1)
                    sb.append('0');
                sb.append(cc);
            }

            return sb.toString();
        } catch (Exception e) {
        }
        return "";
    }
    // ���ַ����sha1����
    public static String sha1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());

            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                int v = (int) b[i];
                v = v < 0 ? 0x100 + v : v;
                String cc = Integer.toHexString(v);
                if (cc.length() == 1)
                    sb.append('0');
                sb.append(cc);
            }

            return sb.toString();
        } catch (Exception e) {
        }
        return "";
    }
    
}
