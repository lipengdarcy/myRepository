package org.darcy.algorithm;

/**
 * LCS是Longest Common Subsequence的缩写
 * 动态规划的一个重要性质特点就是解决“子问题重叠”的场景，可以有效的避免重复计算，根据上面的公式其实可以发现C
 * [i,j]一直保存着当前(Xi,Yi)的最大子序列长度。
 * */
public class LongestCommonSubsequence {
	private int[][] martix;
	private String[][] flag;

	public static void main(String[] args) {
		LongestCommonSubsequence f = new LongestCommonSubsequence();
		String str1 = "cnblogs";
		String str2 = "belong";
		f.martix = new int[str1.length() + 1][str2.length() + 1];
		f.flag = new String[str1.length() + 1][str2.length() + 1];
		f.LCS(str1, str2);
		f.LCS2(str1, str2);

		// 打印子序列
		f.SubSequence(str1.length(), str2.length(), str1, str2);

		// 只要拿出矩阵最后一个位置的数字即可
		System.out.printf("当前最大公共子序列的长度为:%d",
				f.martix[str1.length()][str2.length()]);

	}

	public void LCS(String str1, String str2) {
		// 初始化边界，过滤掉0的情况
		for (int i = 0; i <= str1.length(); i++)
			martix[i][0] = 0;

		for (int j = 0; j <= str2.length(); j++)
			martix[0][j] = 0;

		// 填充矩阵
		for (int i = 1; i <= str1.length(); i++) {
			for (int j = 1; j <= str2.length(); j++) {
				// 相等的情况
				if (str1.charAt(i - 1) == (str2.charAt(j - 1))) {
					martix[i][j] = martix[i - 1][j - 1] + 1;
				} else {
					// 比较“左边”和“上边“，根据其max来填充
					if (martix[i - 1][j] >= martix[i][j - 1])
						martix[i][j] = martix[i - 1][j];
					else
						martix[i][j] = martix[i][j - 1];
				}
			}
		}
	}// end of lcs

	public void LCS2(String str1, String str2) {
		// 初始化边界，过滤掉0的情况
		for (int i = 0; i <= str1.length(); i++)
			martix[i][0] = 0;

		for (int j = 0; j <= str2.length(); j++)
			martix[0][j] = 0;

		// 填充矩阵
		for (int i = 1; i <= str1.length(); i++) {
			for (int j = 1; j <= str2.length(); j++) {
				// 相等的情况
				if (str1.charAt(i - 1) == (str2.charAt(j - 1))) {
					martix[i][j] = martix[i - 1][j - 1] + 1;
					flag[i][j] = "left_up";
				} else {
					// 比较“左边”和“上边“，根据其max来填充
					if (martix[i - 1][j] >= martix[i][j - 1]) {
						martix[i][j] = martix[i - 1][j];
						flag[i][j] = "left";
					} else {
						martix[i][j] = martix[i][j - 1];
						flag[i][j] = "up";
					}
				}
			}
		}
	}// end of lcs

	public void SubSequence(int i, int j, String str1, String str2) {
		if (i == 0 || j == 0)
			return;

		if (flag[i][j] == "left_up") {
			System.out.printf("%c: 当前坐标:（%d,%d）", str2.charAt(j - 1), i - 1,
					j - 1);
			// 左前方
			SubSequence(i - 1, j - 1, "", str2);
		} else {
			if (flag[i][j] == "up") {
				SubSequence(i, j - 1, "", str2);
			} else {
				SubSequence(i - 1, j, "", str2);
			}
		}
	}
}
