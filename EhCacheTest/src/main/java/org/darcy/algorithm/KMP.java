package org.darcy.algorithm;

/**
 * 设 next[j]=k。根据公式我们有
 * 
 * -1 当j=0时
 * 
 * next[j] = max{k| 0<k<j 且 P0P1..Pk-1=Pj-kPj-k+1...Pj-1}
 * 
 * 0 其他情况
 * */
public class KMP {
	public static void main(String[] args) {
		String zstr = "ababcabababdc";
		String mstr = "babdc";
		int index = KMP(zstr, mstr);
		if (index == -1)
			System.out.println("没有匹配的字符串！");
		else
			System.out.println("哈哈，找到字符啦，位置为：" + index);

	}

	public static int KMP(String bigstr, String smallstr) {
		int i = 0;
		int j = 0;

		// 计算“前缀串”和“后缀串“的next
		int[] next = GetNextVal(smallstr);

		while (i < bigstr.length() && j < smallstr.length()) {
			if (j == -1 || bigstr.charAt(i) == smallstr.charAt(j)) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}

		if (j == smallstr.length())
			return i - smallstr.length();

		return -1;
	}

	// / <summary>
	// / p0,p1....pk-1 （前缀串）
	// / pj-k,pj-k+1....pj-1 （后缀串)
	// / </summary>
	// / <param name="match"></param>
	// / <returns></returns>
	static int[] GetNextVal(String smallstr) {
		// 前缀串起始位置("-1"是方便计算）
		int k = -1;
		// 后缀串起始位置（"-1"是方便计算）
		int j = 0;
		int[] next = new int[smallstr.length()];
		// 根据公式： j=0时，next[j]=-1
		next[j] = -1;

		while (j < smallstr.length() - 1) {
			if (k == -1 || smallstr.charAt(k) == smallstr.charAt(j)) {
				// pk=pj的情况: next[j+1]=k+1 => next[j+1]=next[j]+1
				next[++j] = ++k;
			} else {
				// pk != pj 的情况:我们递推 k=next[k];
				// 要么找到，要么k=-1中止
				k = next[k];
			}
		}

		return next;
	}
}
