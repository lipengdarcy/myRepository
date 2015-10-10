package com.runlion.shop.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 分组实例工具
 * 
 * @author zhaowei
 * @datetime 2015-07-07
 */
public class GroupByFeatureInJava {
	public static void main(String[] args) {
		ProductBean p1 = new ProductBean("P1", 20, new Date());
		ProductBean p2 = new ProductBean("P1", 30, new Date());
		ProductBean p3 = new ProductBean("P2", 20, new Date());
		ProductBean p4 = new ProductBean("P1", 20, new Date());
		ProductBean p5 = new ProductBean("P3", 60, new Date());
		ProductBean p6 = new ProductBean("P1", 20, new Date());

		List<ProductBean> list = new ArrayList<ProductBean>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductBean bean = (ProductBean) iterator.next();
			System.out.println(bean);
		}
		System.out.println("******** AFTER GROUP BY PRODUCT_ID ******");
		Collections.sort(list, new ProductBean().new CompareByProductID());
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductBean bean = (ProductBean) iterator.next();
			System.out.println(bean);
		}

		System.out.println("******** AFTER GROUP BY PRICE ******");
		Collections.sort(list, new ProductBean().new CompareByProductPrice());
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductBean bean = (ProductBean) iterator.next();
			System.out.println(bean);
		}
	}
}

class ProductBean {
	String productId;
	int price;
	Date date;

	@Override
	public String toString() {
		return "ProductBean [" + productId + " " + price + " " + date + "]";
	}

	ProductBean() {
	}

	ProductBean(String productId, int price, Date date) {
		this.productId = productId;
		this.price = price;
		this.date = date;
	}

	class CompareByProductID implements Comparator<ProductBean> {
		public int compare(ProductBean p1, ProductBean p2) {
			if (p1.productId.compareTo(p2.productId) > 0) {
				return 1;
			}
			if (p1.productId.compareTo(p2.productId) < 0) {
				return -1;
			}
			// at this point all a.b,c,d are equal... so return "equal"
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return super.equals(obj);
		}
	}

	class CompareByProductPrice implements Comparator<ProductBean> {
		@Override
		public int compare(ProductBean p1, ProductBean p2) {
			// this mean the first column is tied in thee two rows
			if (p1.price > p2.price) {
				return 1;
			}
			if (p1.price < p2.price) {
				return -1;
			}
			return 0;
		}

		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return super.equals(obj);
		}
	}

	class CompareByCreateDate implements Comparator<ProductBean> {
		@Override
		public int compare(ProductBean p1, ProductBean p2) {
			if (p1.date.after(p2.date)) {
				return 1;
			}
			if (p1.date.before(p2.date)) {
				return -1;
			}
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return super.equals(obj);
		}
	}
}