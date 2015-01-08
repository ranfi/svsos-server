package com.svsos.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密和解密工具类
 *
 * @author ranfi
 */
public class EncryptUtils {
    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String md5Encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte b[] = md.digest(str.getBytes());
            int i;
            StringBuffer buf = new StringBuffer(32);
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unsupported Algorithm Exception", e);
        }
    }


    /**
     * MD5加密,用户跟PHP的MD5加密后字符相同
     *
     * @param source
     * @return
     */
    public static String md5Encrypt(byte[] source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            StringBuffer buf = new StringBuffer();
            for (byte b : md.digest())
                buf.append(String.format("%02x", b & 0xff));
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        String str = "123456";
        try {
            String abc = md5Encrypt((md5Encrypt(str.getBytes("GBK"))).getBytes("GBK"));
            System.out.println(abc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
