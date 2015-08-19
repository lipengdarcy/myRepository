package org.darcy.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Huffman2 {
	private List<Double> nums;
	private List<Double> numsMo;
	private List<Tree> trees;
	private String temp;

	public Huffman2() {
		nums = new ArrayList<Double>();
		numsMo = new ArrayList<Double>();
		trees = new ArrayList<Tree>();
		temp = "";
	}

	public void addNums() {// 给定一组数
		System.out.println("请输入一组数，中间用空格分隔：");
		Scanner sca = new Scanner(System.in);
		String str = sca.nextLine();
		String[] strs = str.split(" ");
		for (int i = 0; i < strs.length; i++) {
			nums.add(Double.parseDouble(strs[i]));
			numsMo.add(Double.parseDouble(strs[i]));
		}
	}

	public void compareNum(List<Double> nums, List<Tree> trees) {// 递归算法
		double[] min = new double[2];
		if (nums.size() > 1) {
			min = minTwo(nums);
			Tree t = new Tree(min[0], min[1], min[0] + min[1]);
			nums.remove(Double.valueOf(min[0]));
			nums.remove(Double.valueOf(min[1]));
			nums.add(min[0] + min[1]);
			trees.add(t);
			compareNum(nums, trees);
		}
	}

	public void print(double num) {// 递归打印编码
		for (Tree t : trees) {
			if (num == t.getRchild()) {
				temp = 1 + temp;
				print(t.getParents());
				break;
			} else if (num == t.getLchild()) {
				temp = 0 + temp;
				print(t.getParents());
				break;
			}
		}
	}

	public void write(double d) {
		temp = "";
		System.out.print(d + " : ");
		print(d);
		System.out.print(temp);
		System.out.println(" 码长：" + temp.length());
	}

	public double[] minTwo(List<Double> nums) {// 在一组数中选则最小的两个，按递增排序返回
		Double temp = 0.0;
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i < nums.size(); i++) {
				if (nums.get(i - 1) < nums.get(i)) {
					temp = nums.get(i);
					nums.set(i, nums.get(i - 1));
					nums.set(i - 1, temp);
				}
			}
		}
		double[] n = { nums.get(nums.size() - 1), nums.get(nums.size() - 2) };
		return n;
	}

	public void start() {
		addNums();
		compareNum(nums, trees);
		while (numsMo.size() > 1) {
			double[] mins = minTwo(numsMo);
			if (mins[0] != mins[1]) {
				numsMo.remove(Double.valueOf(mins[0]));
				write(mins[0]);
			}
		}
		if (!numsMo.isEmpty()) {
			write(numsMo.get(0));
		}
	}

	public class Tree {
		double lChild, rChild, parent;

		public Tree(double lChild, double rChild, double parent) {
			this.lChild = lChild;
			this.rChild = rChild;
			this.parent = parent;
		}

		public double getLchild() {
			return lChild;
		}

		public void setLchild(double lChild) {
			this.lChild = lChild;
		}

		public double getRchild() {
			return rChild;
		}

		public void setRchild(double rChild) {
			this.rChild = rChild;
		}

		public double getParents() {
			return parent;
		}

		public void setParents(double root) {
			this.parent = root;
		}

	}

	public static void main(String[] args) {
		new Huffman2().start();
	}
}