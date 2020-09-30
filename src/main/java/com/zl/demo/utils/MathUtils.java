package com.zl.demo.utils;

import java.math.BigDecimal;

public class MathUtils {
	
	public static Integer StrToInteger(String str){
		Integer i = 0;
		if(null!=str && !("null".equals(str))){
			i = new Integer(str);
		}
		return i;
	}
	
	public static BigDecimal StrToBigDecimal(String str){
		BigDecimal bd = null;
		if(null!=str && !("null".equals(str))){
			bd = new BigDecimal(str);
		}
		return bd;
	}
	
	/**
	 * 获取渠道最后几位数
	 * @param b
	 * @return
	 */
	public static String getNum(Integer b) {
		String c = "";
		if (b < 10) {
			c = String.format("%06d", b);
		} else if (b >= 10 && b < 100) {
			c = String.format("%06d", b);
		} else if (b >= 100 && b < 1000) {
			c = String.format("%06d", b);
		} else if (b >= 1000 && b < 10000) {
			c = String.format("%06d", b);
		}else if (b >= 10000 && b < 100000) {
			c = String.format("%06d", b);
		} else {
			c = b.toString();
		}
		return c;
	}
}
