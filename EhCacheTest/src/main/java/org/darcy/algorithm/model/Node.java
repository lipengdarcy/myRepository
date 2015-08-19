package org.darcy.algorithm.model;

public class Node {
	// / <summary>
	// / 左孩子
	// / </summary>
	public Node left;

	// / <summary>
	// / 右孩子
	// / </summary>
	public Node right;

	// / <summary>
	// / 父节点
	// / </summary>
	public Node parent;

	// / <summary>
	// / 节点字符
	// / </summary>
	public char c;

	// / <summary>
	// / 节点权重
	// / </summary>
	public int weight;

	// 赫夫曼“0"or“1"
	public char huffmancode;

	// / <summary>
	// / 标记是否为叶子节点
	// / </summary>
	public boolean isLeaf;
}