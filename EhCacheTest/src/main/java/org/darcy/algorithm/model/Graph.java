package org.darcy.algorithm.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 图的存储有很多方式，邻接矩阵，邻接表，十字链表等等，当然都有自己的适合场景，下面用邻接矩阵来玩玩，邻接矩阵需要采用两个数组，
 * 
 * ①. 保存顶点信息的一维数组，
 * 
 * ②. 保存边信息的二维数组。
 * */
public class Graph {

	public static void main(String[] args) {
		Graph martix = new Graph();
		martix.graph = new Graph();

		martix.Build();

		Map<Character, Edge> dic = martix.Prim();

		System.out.println("最小生成树为：");

		for (char key : dic.keySet()) {
			System.out.printf("(%c,%c)(%d)", dic.get(key).startEdge,
					dic.get(key).endEdge, dic.get(key).weight);
		}

	}

	// 顶点个数
	public Graph graph;
	public char[] vertexs;
	// 边的条数
	public int[][] edges;
	// 顶点个数
	public int vertexsNum;

	// / <summary>
	// / 边的个数
	// / </summary>
	public int edgesNum;

	// 矩阵的构建
	public void Build() {
		// 顶点数
		graph.vertexsNum = 6;
		// 边数
		graph.edgesNum = 8;
		graph.vertexs = new char[graph.vertexsNum];
		graph.edges = new int[graph.vertexsNum][graph.vertexsNum];
		// 构建二维数组
		for (int i = 0; i < graph.vertexsNum; i++) {
			// 顶点
			graph.vertexs[i] = (char) (i + 65);
			for (int j = 0; j < graph.vertexsNum; j++) {
				graph.edges[i][j] = Integer.MAX_VALUE;
			}
		}

		graph.edges[0][1] = graph.edges[1][0] = 80;
		graph.edges[0][3] = graph.edges[3][0] = 100;
		graph.edges[0][5] = graph.edges[5][0] = 20;
		graph.edges[1][2] = graph.edges[2][1] = 90;
		graph.edges[2][5] = graph.edges[5][2] = 70;
		graph.edges[3][2] = graph.edges[2][3] = 100;
		graph.edges[4][5] = graph.edges[5][4] = 40;
		graph.edges[3][4] = graph.edges[4][3] = 60;
		graph.edges[2][3] = graph.edges[3][2] = 10;
	}

	/**
	 * prim算法需要两个字典。
	 * 
	 * ①：保存当前节点的字典，其中包含该节点的起始边和终边以及权值，用weight=-1来记录当前节点已经访问过，用weight=int.
	 * MaxValue表示
	 * 
	 * 两节点没有边。
	 * 
	 * ②：输出节点的字典，存放的就是我们的N集合。
	 * */
	public Map<Character, Edge> Prim() {
		Map<Character, Edge> dic = new HashMap<Character, Edge>();
		// 统计结果
		Map<Character, Edge> outputDic = new HashMap<Character, Edge>();
		// weight=MaxValue:标识没有边
		for (int i = 0; i < graph.vertexsNum; i++) {
			// 起始边
			char startEdge = (char) (i + 65);
			dic.put(startEdge, new Edge(Integer.MAX_VALUE));
		}

		// 取字符的开始位置
		int index = 65;
		// 取当前要使用的字符
		char start = (char) (index);
		for (int i = 0; i < graph.vertexsNum; i++) {
			// 标记开始边已使用过
			dic.put(start, new Edge(-1));
			for (int j = 1; j < graph.vertexsNum; j++) {
				// 获取当前 c 的 邻边
				char end = (char) (j + index);
				// 取当前字符的权重
				int weight = graph.edges[(int) (start) - index][j];
				if (weight < dic.get(end).weight) {
					dic.put(end, new Edge(weight, start, end));
				}
			}

			int min = Integer.MAX_VALUE;

			char minkey = ' ';

			for (char key : dic.keySet()) {
				// 取当前 最小的 key(使用过的除外)
				if (min > dic.get(key).weight && dic.get(key).weight != -1) {
					min = dic.get(key).weight;
					minkey = key;
				}
			}
			start = minkey;
			// 边为顶点减去1
			if (outputDic.size() < graph.vertexsNum - 1
					&& !outputDic.containsKey(minkey))
				outputDic.put(minkey,
						new Edge(dic.get(minkey).weight,
								dic.get(minkey).startEdge,
								dic.get(minkey).endEdge));

		}
		return outputDic;
	}

	class Edge {
		public int weight;
		public char startEdge;
		public char endEdge;

		public Edge(int w) {
			this.weight = w;
		}

		public Edge(int w, char startEdge, char endEdge) {
			this.weight = w;
			this.startEdge = startEdge;
			this.endEdge = endEdge;
		}

	}

}