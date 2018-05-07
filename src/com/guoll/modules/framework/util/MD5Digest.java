package com.guoll.modules.framework.util;

import java.security.MessageDigest;

/**
 * MD5 加密解密 公用类
 */
public class MD5Digest {

	//测试用
	public static void main(String[] args){
		MD5Digest md5 = new MD5Digest();
		System.out.println(md5.getMD5("ceshi"));
		//76D685085245436426390915AD71D2E7
		//System.out.println(md5.md5crypt("111111"));
		
	}
	
	private MessageDigest __md5 = null;
	private StringBuffer __digestBuffer = null;

	public MD5Digest() {
		try {
			__md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		__digestBuffer = new StringBuffer();
	}

	private String md5crypt(String s) {
		__digestBuffer.setLength(0);
		byte abyte0[] = __md5.digest(s.getBytes());
		for (int i = 0; i < abyte0.length; i++)
			__digestBuffer.append(toHex(abyte0[i]));

		__digestBuffer.reverse();

		return __digestBuffer.toString();
	}

	public String getMD5(String s) {
		return md5crypt(md5crypt(md5crypt(s)));
	}

	public String toHex(byte one) {
		String HEX = "0123456789ABCDEF";
		char[] result = new char[2];
		result[0] = HEX.charAt((one & 0xf0) >> 4);
		result[1] = HEX.charAt(one & 0x0f);
		String mm = new String(result);
		return mm;
	}
}