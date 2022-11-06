package com.ljz.Util;

import java.util.Random;

public class StringUtil {
	public static boolean isEmpty(String str) {            //判断是否输入为空的方法
		if(str==null||"".equals(str.trim())) {
			return true;
		}else {
		return false;
		}
	}
	public static boolean isNotEmpty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return true;
		}else {
		return false;
		}
	}
	public static String RegsiterString() {        //随机生成验证码字符串
		String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "p", "q", "r", "s", "t" };
		String text = "";
		int number = str.length;
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			text += str[random.nextInt(number)];
		}
		return text;
	}
}
