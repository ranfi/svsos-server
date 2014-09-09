package com.svsos.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密和解密工具类
 * 
 * @author ranfi
 * 
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
			throw new IllegalArgumentException(
					"Unsupported Algorithm Exception", e);
		}
	}
}
