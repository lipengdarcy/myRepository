package org.darcy.algorithm.model;

/// <summary>
/// 产品类
/// </summary>
public class Product {
	public int ProductID;

	public String ProductName;

	// / <summary>
	// / 对产品的打分
	// / </summary>
	public float Score;

	public Product(int id, String name, float score) {
		this.ProductID = id;
		this.ProductName = name;
		this.Score = score;
	}
}