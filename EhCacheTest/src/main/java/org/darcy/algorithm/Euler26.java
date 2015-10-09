package org.darcy.algorithm;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of
 * the unit fractions with denominators 2 to 10 are given:
 * 
 * 1/2 = 0.5 1/3 = 0.(3) 1/4 = 0.25 1/5 = 0.2 1/6 = 0.1(6) 1/7 = 0.(142857) 1/8
 * = 0.125 1/9 = 0.(1) 1/10 = 0.1
 * 
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be
 * seen that 1/7 has a 6-digit recurring cycle.
 * 
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle
 * in its decimal fraction part.
 * 
 * */
public class Euler26 {

	public static void main(String[] args) {

		NumberFormat f = NumberFormat.getNumberInstance();
		f.setMaximumFractionDigits(100);// 设置小数点后面位数为
		System.out.println(f.format(3.1415));

		for (int i = 1; i < 1000; i++) {
			BigDecimal b = new BigDecimal(1).divide(new BigDecimal(i), 20, 1);
			System.out.println(i + ": " + b);
		}

		try {
			BigDecimal bigA = new BigDecimal(1);
			BigDecimal bigB = new BigDecimal(3);
			System.out.println("a/b=" + bigA.divide(bigB));
			// return false;
		} catch (Exception e) {
			// return true;
			e.printStackTrace();
		}

	}

	//
	// 判断numerator/denominatoe是否是循环小数
	public static Boolean isRepeating(int numerator, int denominator) {
		int simpleNum = 0;
		int simpleDen = 0;
		if (numerator < denominator) {
			for (int i = numerator; i > 0; i--) {
				if (numerator % i == 0 && denominator % i == 0) {
					simpleNum = numerator / i;
					simpleDen = denominator / i;
					break;
				}
			}
		} else {
			for (int i = denominator; i > 0; i--) {
				if (numerator % i == 0 && denominator % i == 0) {
					simpleNum = numerator / i;
					simpleDen = denominator / i;
					break;
				}
			}
		}
		// 如果分数化简后分母除了2,5之外还有素因数，就是无限循环小数
		for (int i = 2; i <= simpleDen; i++) {
			if (simpleDen % i == 0) {
				if (isPrime(i)) {
					if (i != 2 && i != 5)
						return true;
				}
			}
		}
		return false;
	}

	// 判断是否是素数
	public static boolean isPrime(int n) {
		if (n == 1 || n == 2 || n == 3)
			return true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	// 判断是否是纯循环小数，其中numerator<denominator
	public static boolean isPureRepeating(int numerator, int denominator) {
		int simpleNum = 0;
		int simpleDen = 0;
		if (numerator < denominator) {
			for (int i = numerator; i > 0; i--) {
				if (numerator % i == 0 && denominator % i == 0) {
					simpleNum = numerator / i;
					simpleDen = denominator / i;
					break;
				}
			}
		} else {
			for (int i = denominator; i > 0; i--) {
				if (numerator % i == 0 && denominator % i == 0) {
					simpleNum = numerator / i;
					simpleDen = denominator / i;
					break;
				}
			}
		}
		// 如果化简后的分母能分解成两个或两个以上素数的乘积，则是混合无限循环小数
		if (isResolveToTwoPrime(simpleDen))
			return false;
		else
			return true;
	}

	// 判断分母是否能分解成两个以上素数的乘积，如果是返回false，否则true
	public static boolean isResolveToTwoPrime(int num) {
		int i;
		int count = 0;
		for (i = 2; i < num; i++) {
			while (i != num) {
				if (num % i == 0) {
					count++;
					num /= i;
				} else
					break;
			}
		}
		if (count >= 1)
			return true;
		else
			return false;
	}
}
