package org.darcy.algorithm;

/**
 * 古代数学巨著《九章算数》中有这么一道题叫“五家共井，甲二绠（汲水用的井绳）不足，如（接上）乙一绠；乙三绠不足，如丙一绠；
 * 
 * 丙四绠不足，如丁一绠；丁五绠不足，如戊一绠；戊六绠不足，如甲一绠，皆及。
 * 
 * 意思就是说五家人共用一口井，甲家的绳子用两条不够，还要再用乙家的绳子一条才能打到井水；乙家的绳子用三条不够，还要再用丙家的绳子
 * 
 * 一条才能打到井水；丙家的绳子用四条不够，还要再用丁家的绳子一条才能打到井水；丁家的绳子用五条不够，还要再用戊家的绳子一条才能打
 * 
 * 到井水；戊家的绳子用六条不够，还要再用甲家的绳子一条才能打到井水。
 * 
 * 最后问：井有多深？每家的绳子各有多长
 * */

public class FiveWells {
	public static void main(String[] args) {
		FiveWells f = new FiveWells();
		f.solution1();
	}

	/*
	 * 2a+b=h ①
	 * 
	 * 3b+c=h ②
	 * 
	 * 4c+d=h ③
	 * 
	 * 5d+e=h ④
	 * 
	 * 6e+a=h ⑤
	 */
	public void solution1() {
		int a, b, c, d, e, h;
		a = b = c = d = e = h = 0;
		boolean flag = true;
		while (flag) {
			// 4的倍数
			e += 4;
			a = 0;
			while (flag) {
				// 5的倍数
				a += 5;
				d = e + a / 5;
				c = d + e / 4;
				if (c % 2 != 0)
					continue;
				if (d % 3 != 0)
					continue;
				b = c + d / 3;
				if (b + c / 2 < a)
					break;
				if (b + c / 2 == a)
					flag = false;
			}
		}
		h = 2 * a + b;
		System.out
				.printf("a=%d,b=%d,c=%d,d=%d,e=%d --> h=%d", a, b, c, d, e, h);
		// a=265,b=191,c=148,d=129,e=76 --> h=721
	}

	/**
	 * b=h-2a ⑥
	 * 
	 * c=h-3b ⑦
	 * 
	 * d=h-4c ⑧
	 * 
	 * e=h-5d ⑨
	 * 
	 * a=h-6e ⑩
	 * 
	 * 将⑥，⑧，⑨，⑩分别代入⑦，一阵痉挛后可知：
	 * 
	 * c=(148/721)h
	 * 
	 * 上面的公式也就表明了c和h的比例关系，我们令 h=721k,则 c=148k，将其代入⑥，⑦，⑧，⑨，⑩可得如下方程组
	 * 
	 * a=265k
	 * 
	 * b=191k
	 * 
	 * c=148k
	 * 
	 * d=129k
	 * 
	 * e=76k
	 * 
	 * x=721k
	 * */

	public void solution2() {
		for (int k = 1; k < 5; k++) {
			int h = 721 * k;
			int a = 265 * k;
			int b = 191 * k;
			int c = 148 * k;
			int d = 129 * k;
			int e = 76 * k;
			System.out.printf("a=%d,b=%d,c=%d,d=%d,e=%d --> h=%d", a, b, c, d,
					e, h);

		}
	}

}
