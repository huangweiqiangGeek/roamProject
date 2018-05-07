package test;

import java.io.UnsupportedEncodingException;

public class TestBoolean {

	public static void change(boolean[] boolean1) throws UnsupportedEncodingException {
	
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String string = "中华人民共和国";
		byte[] u8 = string.getBytes("utf8");
		String u82 = new String(u8, "utf-8");
		byte[] gb = u82.getBytes("gbk");
		System.out.println(new String(gb, "gbk"));
		
	}
	
	
}
