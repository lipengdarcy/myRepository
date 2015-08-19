package org.darcy.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.darcy.algorithm.model.Node;

public class Huffman {

	public static void main(String[] args) {
		File f = new File("test");
		System.out.println("文件路径： " + f.getAbsolutePath());
		StringBuilder sb = new StringBuilder();

		String content = readFileByLines("1.log");

		Huffman huffman = new Huffman(content);

		long startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
		System.out.println("开始 " + startMili);

		huffman.Build();

		long endMili = System.currentTimeMillis();
		System.out.println("结束 " + endMili);
		System.out.println("构建赫夫曼树总耗时为：" + (endMili - startMili) + "毫秒");

		// 将8位二进制转化为ascII码
		String s = huffman.Encode();

		int remain = s.length() % 8;

		List<Character> list = new ArrayList<Character>();

		int start = 0;

		for (int i = 8; i < s.length(); i = i + 8) {
			String ascii = s.substring(i - 8, 8);
			for (int k = 0; k < ascii.length(); k++)
				list.add(ascii.charAt(k));
			start = i;
		}

		String result = new String(list.toString());

		// 当字符编码不足8位时， 用‘艹'来标记，然后拿出’擦‘以后的所有0,1即可
		result += "艹" + s.substring(start);

		writeFileByLines("2.txt", result);
		System.out.println("压缩完毕！");

		// 解码
		String str = readFileByLines("2.txt");

		sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			int ua = (int) str.charAt(i);

			// 说明已经取完毕了 用'艹'来做标记
			if (ua == 33401)
				sb.append(str.substring(i));
			else
				// sb.append(Convert.ToString(ua, 2).PadLeft(8, '0'));
				sb.append(ua);
		}

		String sss = huffman.Decode(sb.toString());

	}

	// / 赫夫曼节点
	Comparator<Node> OrderByWeight = new Comparator<Node>() {
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			int numbera = o1.weight;
			int numberb = o2.weight;
			if (numberb > numbera) {
				return 1;
			} else if (numberb < numbera) {
				return -1;
			} else {
				return 0;
			}
		}
	};
	Queue<Node> queue = new PriorityQueue<Node>(10, OrderByWeight);

	// / <summary>
	// / 编码对应表（加速用）
	// / </summary>
	Map<Character, String> huffmanEncode = new HashMap<Character, String>();

	// / <summary>
	HashMap<String, Character> huffmanDecode = new HashMap<String, Character>();

	// / <summary>
	// / 明文
	// / </summary>
	String word = "";

	public Node root = new Node();

	public Huffman(String str) {
		this.word = str;
		HashMap<Character, Integer> dic = new HashMap<Character, Integer>();
		for (int k = 0; k < str.length(); k++) {
			char c = str.charAt(k);
			if (dic.containsKey(c))
				dic.put(c, dic.get(c) + 1);
			else
				dic.put(c, 1);
		}

		for (char item : dic.keySet()) {
			Node node = new Node();
			node.c = item;
			node.weight = dic.get(item);

			// 入队
			// queue.Eequeue(node, dic.get(item));
			queue.add(node);
		}
	}

	// / 构建赫夫曼树

	public void Build() {
		// 构建
		while (queue.size() > 0) {
			// 如果只有一个节点，则说明已经到根节点了
			if (queue.size() == 1) {
				root = queue.poll();
				break;
			}

			// 节点1
			Node node1 = queue.poll();

			// 节点2
			Node node2 = queue.poll();

			// 标记左孩子
			node1.huffmancode = '0';

			// 标记为右孩子
			node2.huffmancode = '1';

			// 判断当前节点是否为叶子节点,hufuman无度为1点节点（方便计算huffman编码）
			if (node1.left == null)
				node1.isLeaf = true;

			if (node2.left == null)
				node2.isLeaf = true;

			// 父节点
			root = new Node();

			root.left = node1;

			root.right = node2;

			root.weight = node1.weight + node2.weight;

			// 当前节点为根节点
			node1.parent = node2.parent = root;

			// 将当前节点的父节点入队列
			queue.add(root);
		}

		// 深度优先统计各个字符的编码
		DFS(root);
	}

	// 赫夫曼编码
	public String Encode() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < word.length(); k++) {
			sb.append(huffmanEncode.get(word.charAt(k)));
		}
		return sb.toString();
	}

	// 赫夫曼解码
	public String Decode(String str) {
		StringBuilder decode = new StringBuilder();
		String temp = "";
		for (int i = 0; i < str.length(); i++) {
			temp += str.charAt(i);
			// 如果包含 O(N)时间
			if (huffmanDecode.containsKey(temp)) {
				decode.append(huffmanDecode.get(temp));
				temp = "";
			}
		}

		return decode.toString();
	}

	// 深度优先遍历子节点，统计各个节点的赫夫曼编码

	public void DFS(Node node) {
		if (node == null)
			return;
		// 遍历左子树
		DFS(node.left);
		// 遍历右子树
		DFS(node.right);
		// 如果当前叶节点
		if (node.isLeaf) {
			String code = "";
			Node temp = node;
			// 回溯的找父亲节点的huffmancode LgN 的时间
			while (temp.parent != null) {
				// 注意，这里最后形成的 “反过来的编码”
				code += temp.huffmancode;
				temp = temp.parent;
			}

			String codetemp = new StringBuffer(code).reverse().toString();
			huffmanEncode.put(node.c, codetemp);
			huffmanDecode.put(codetemp, node.c);
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static String readFileByLines(String fileName) {
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				sb.append(tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	public static void writeFileByLines(String fileName, String content) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}