package com.runlion.shop.common.util.lambdaj;

/**
 * 测试用bean对象，产品
 * 
 * @author 乔学士
 *
 */
public class Product {

	private int id;
	private String name;
	private double price;
	private double weight;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", type=" + type + ", weight=" + weight + "]";
	}
}