package com.runlion.shop.common.util.lambdaj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生产产品的工厂
 * 
 * @author 乔学士
 *
 */
public class ProductFactory {

	public static List<Product> genRandomProduct(int amount) {
		if (amount < 0) {
			amount = 1;
		}
		Random random = new Random();
		List<Product> ps = new ArrayList<Product>(amount);
		for (int i = 0; i < amount; i++) {
			Product p = new Product();
			int randomNum = random.nextInt(100);
			p.setId(i + 1);
			p.setId(i + 1);
			p.setName("name" + randomNum);
			p.setPrice(Math.round(random.nextDouble() * 1000));
			p.setWeight(Math.round(random.nextDouble() * 1000));
			p.setType("type" + randomNum);
			ps.add(p);
		}

		return ps;
	}
}