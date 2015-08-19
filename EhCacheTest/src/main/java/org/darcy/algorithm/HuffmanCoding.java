package org.darcy.algorithm;

//哈弗曼编码的实现类
public class HuffmanCoding {
	private int charsAndWeight[][];// [][0]是 字符，[][1]存放的是字符的权值（次数）
	private int hfmcoding[][];// 存放哈弗曼树
	private int i = 0;// 循环变量
	private String hcs[];

	public HuffmanCoding(int[][] chars) {
		// TODO 构造方法
		charsAndWeight = new int[chars.length][2];
		charsAndWeight = chars;
		hfmcoding = new int[2 * chars.length - 1][4];// 为哈弗曼树分配空间
	}

	// 哈弗曼树的实现
	public void coding() {
		int n = charsAndWeight.length;
		if (n == 0)
			return;
		int m = 2 * n - 1;
		// 初始化哈弗曼树
		for (i = 0; i < n; i++) {
			hfmcoding[i][0] = charsAndWeight[i][1];// 初始化哈弗曼树的权值
			hfmcoding[i][1] = 0;// 初始化哈弗曼树的根节点
			hfmcoding[i][2] = 0;// 初始化哈弗曼树的左孩子
			hfmcoding[i][3] = 0;// 初始化哈弗曼树的右孩子
		}
		for (i = n; i < m; i++) {
			hfmcoding[i][0] = 0;// 初始化哈弗曼树的权值
			hfmcoding[i][1] = 0;// 初始化哈弗曼树的根节点
			hfmcoding[i][2] = 0;// 初始化哈弗曼树的左孩子
			hfmcoding[i][3] = 0;// 初始化哈弗曼树的右孩子
		}
		// 构建哈弗曼树
		for (i = n; i < m; i++) {
			int s1[] = select(i);// 在哈弗曼树中查找双亲为零的 weight最小的节点
			hfmcoding[s1[0]][1] = i;// 为哈弗曼树最小值付双亲
			hfmcoding[s1[1]][1] = i;
			hfmcoding[i][2] = s1[0];// 新节点的左孩子
			hfmcoding[i][3] = s1[1];// 新节点的右孩子
			hfmcoding[i][0] = hfmcoding[s1[0]][0] + hfmcoding[s1[1]][0];// 新节点的权值是左右孩子的权值之和
		}
	}

	// 查找双亲为零的 weight最小的节点
	private int[] select(int w) {
		// TODO Auto-generated method stub
		int s[] = { -1, -1 }, j = 0;// s1 最小权值且双亲为零的节点的序号 ， i 是循环变量
		int min1 = 32767, min2 = 32767;
		for (j = 0; j < w; j++) {
			if (hfmcoding[j][1] == 0) {// 只在尚未构造二叉树的结点中查找（双亲为零的节点）
				if (hfmcoding[j][0] < min1) {
					min2 = min1;
					s[1] = s[0];
					min1 = hfmcoding[j][0];
					s[0] = j;
				} else if (hfmcoding[j][0] < min2) {
					min2 = hfmcoding[j][0];
					s[1] = j;
				}
			}
		}
		return s;
	}

	public String[] CreateHCode() {// 根据哈夫曼树求哈夫曼编码
		int n = charsAndWeight.length;
		int i, f, c;
		String hcodeString = "";
		hcs = new String[n];
		for (i = 0; i < n; i++) {// 根据哈夫曼树求哈夫曼编码
			c = i;
			hcodeString = "";
			f = hfmcoding[i][1]; // f 哈弗曼树的根节点
			while (f != 0) {// 循序直到树根结点
				if (hfmcoding[f][2] == c) {// 处理左孩子结点
					hcodeString += "0";
				} else {
					hcodeString += "1";
				}
				c = f;
				f = hfmcoding[f][1];
			}
			hcs[i] = new String(new StringBuffer(hcodeString).reverse());
		}
		return hcs;
	}

	public String show(String s) {// 对字符串显示编码
		String textString = "";
		char c[];
		int k = -1;
		c = new char[s.length()];
		c = s.toCharArray();// 将字符串转化为字符数组
		for (int i = 0; i < c.length; i++) {
			k = c[i];
			for (int j = 0; j < charsAndWeight.length; j++)
				if (k == charsAndWeight[j][0])
					textString += hcs[j];
		}
		return textString;
	}

	// 哈弗曼编码反编译
	public String reCoding(String s) {
		String text = "";// 存放反编译后的字符
		int k = 0, m = hfmcoding.length - 1;// 从根节点开始查询
		char c[];
		c = new char[s.length()];
		c = s.toCharArray();
		k = m;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '0') {
				k = hfmcoding[k][2];// k的值为根节点左孩子的序号
				if (hfmcoding[k][2] == 0 && hfmcoding[k][3] == 0)// 判断是不是叶子节点，条件（左右孩子都为零）
				{
					text += (char) charsAndWeight[k][0];
					k = m;
				}
			}
			if (c[i] == '1') {
				k = hfmcoding[k][3];// k的值为根节点右孩子的序号
				if (hfmcoding[k][2] == 0 && hfmcoding[k][3] == 0)// 判断是不是叶子节点，条件（左右孩子都为零）
				{
					text += (char) charsAndWeight[k][0];
					k = m;
				}
			}
		}
		return text;
	}
}
