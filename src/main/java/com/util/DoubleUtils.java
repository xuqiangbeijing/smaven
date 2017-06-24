package com.util;

import java.math.BigDecimal;

public class DoubleUtils {
	/**
	 * 设置double精度位数,默认两位小数，四舍五入
	 * @param d
	 * @return
	 */
	public static double decimaldouble(double d){
		BigDecimal bd = new BigDecimal(d);   
	       //bd = bd.setScale(20,BigDecimal.ROUND_HALF_EVEN);   
		 bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);   
	       double d1 = bd.doubleValue(); 
	       return d1;
	}
	/**
	 * 设置double精度位数,四舍五入
	 * @param d double数值
	 * @param num 保留小数点后位数
	 * @return
	 */
	public static double decimaldouble(double d,int num){
		BigDecimal bd = new BigDecimal(d);   
		 bd = bd.setScale(num,BigDecimal.ROUND_HALF_UP);   
	       double d1 = bd.doubleValue(); 
	       return d1;
	}
}
