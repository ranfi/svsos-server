package com.svsos.backend.weixin.util;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/** 
 * 工具类 
 *  
 * @author zhouliangjun 
 * @date 2014-11-09
 */ 
public class DESTools 
{
	
	
	public static  DESTools instance;
	
	public static DESTools   getInstace()
	{
		if(instance == null)
		{
			instance = new DESTools();
		}
		return instance;
	}

	Key key;

	/**
	 * 密钥
	 */
	private static byte[] BOSS_SECRET_KEY = { 0x0b, 0x13, (byte) 0xe7,
			(byte) 0xb2, 0x51, 0x0d, 0x75, (byte) 0xc2, 0x4e, (byte) 0xdd,
			(byte) 0x4b, (byte) 0x51, 0x24, 0x36, (byte) 0xa8, (byte) 0x28,
			0x0b, 0x13, (byte) 0xe2, (byte) 0xb2, 0x31, 0x0d, 0x75, (byte) 0xc1 };

	public DESTools() 
	{
		setKey(BOSS_SECRET_KEY);
	}

	/**
	 * 根据参数生成KEY
	 */
	public void setKey(byte[] strKey) 
	{
		try 
		{
			DESKeySpec dks = new DESKeySpec(BOSS_SECRET_KEY);
			SecretKeyFactory keyFactory;
			keyFactory = SecretKeyFactory.getInstance("DES");
			this.key = keyFactory.generateSecret(dks);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("Error initializing DESTOOLS class. Cause: " + e);
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 */
	public String getEncString(String strMing) 
	{
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try 
		{
			byteMing = strMing.getBytes("UTF8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		}
		catch (Exception e) 
		{
			throw new RuntimeException(
					"Error initializing DESTOOLS class. Cause: " + e);
		}
		finally 
		{
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMi) 
	{
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try
		{
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("Error initializing DESTOOLS class. Cause: " + e);
		} 
		finally 
		{
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) 
	{
		byte[] byteFina = null;
		Cipher cipher;
		try 
		{
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("Error initializing DESTOOLS class. Cause: " + e);
		} 
		finally 
		{
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD)
	{
		Cipher cipher;
		byte[] byteFina = null;
		try
		{
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		}
		catch (Exception e) 
		{
			throw new RuntimeException("Error initializing DESTOOLS class. Cause: " + e);
		} 
		finally 
		{
			cipher = null;
		}
		return byteFina;
	}

	public static void main(String args[]) 
	{
		DESTools des = new DESTools();
		String str1 = "shmall";
		//DES加密
		String str2 = des.getEncString(str1);
		String deStr = des.getDesString(str2);
		System.out.println("密文:" + str2);
		//DES解密
		System.out.println("明文:" + deStr);
	}
}
