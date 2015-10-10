package com.runlion.shop.common.util.lambdaj;

import static ch.lambdaj.Lambda.closure;
import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.extractProperty;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.index;
import static ch.lambdaj.Lambda.joinFrom;
import static ch.lambdaj.Lambda.max;
import static ch.lambdaj.Lambda.maxFrom;
import static ch.lambdaj.Lambda.of;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static ch.lambdaj.Lambda.selectMin;
import static ch.lambdaj.Lambda.sort;
import static ch.lambdaj.Lambda.var;
import static ch.lambdaj.collection.LambdaCollections.with;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ch.lambdaj.function.closure.Closure;

/**
 * 测试类，方便起见，没有用jUnit
 * 
 * @author 乔学士
 *
 */
public class LambdajTest {

	public static void main(String[] args) {
		List<Product> ps = ProductFactory.genRandomProduct(10);

		printl(1);
		// 1.简单例子
		// 对每个product都设置price为100
		// forEach(ps).setPrice(100); //对每个product都设置setPrice为100
		// 按重量从小到大进行排序
		ps = sort(ps, on(Product.class).getWeight());
		print(ps);

		printl(2);
		// 2.joinFrom 连接某个属性,中间默认用逗号加空格(", "), 可以自定义分隔符
		// 连接每个product对象的name
		String names = joinFrom(ps).getName();
		// 连接每个product对象的name，间隔符为 ":"
		String names2 = joinFrom(ps, ":").getName();
		System.out.println(names);
		System.out.println(names2);

		printl(3);
		// 3.select
		// 从ps中选择出重量大于等于500的
		List<Product> ps2 = select(
				ps,
				// 注意下面greaterThanOrEqualTo方法中参数的类型一定要和getWeight严格相等
				having(on(Product.class).getWeight(),
						greaterThanOrEqualTo(new Double(500))));
		System.out.println(ps2);

		printl(4);
		// 4.selectMin找到最小的, also selectMax
		// 找到重量最轻的产品
		Product p = selectMin(ps, on(Product.class).getWeight());
		System.out.println(p);

		printl(5);
		// 5.max and maxFrom, also min and minFrom
		// 找到最重的产品的重量（两种方法）
		// 5.1
		double weight1 = max(ps, on(Product.class).getWeight());
		// 5.2
		double weight2 = maxFrom(ps).getWeight();
		System.out.println(weight1 + "," + weight2);

		printl(6);
		// 6.extract,抽取所有对象的某一列返回值
		// 得到每个产品的id
		// 6.1 使用on
		List<Integer> ids = extract(ps, on(Product.class).getId());
		System.out.println(ids);
		// 6.2 使用属性名
		List<?> ids2 = extractProperty(ps, "id");
		System.out.println(ids2);

		printl(7);
		// 7.index 返回一个map，value是每个对象，key是对象的某个属性
		// 返回一个key为id，value是对象的Map
		Map<Integer, ?> map = index(ps, on(Product.class).getId());
		print(map);

		printl(8);
		// 8.with 使用更为流畅的方法
		// 找到重量大于400的产品的id列表，并排序

		// 8.1原始方法
		List<Double> prices1 = sort(
				extract(select(
						ps,
						having(on(Product.class).getWeight(),
								greaterThan(400.0))), on(Product.class)
						.getPrice()), on(Double.class).doubleValue());

		// 8.2使用with的方法
		List<Double> prices2 = with(ps)
				.retain(having(on(Product.class).getWeight(),
						greaterThan(400.0)))
				.extract(on(Product.class).getPrice())
				.sort(on(Double.class).doubleValue());

		System.out.println(prices1);
		System.out.println(prices2);

		printl(9);
		// 9.闭包 ，of(T t) 返回T类型
		Closure println = closure(); // 创建一个closure，放到ThreadLocal中
		of(System.out).println(var(String.class)); // of方法从ThreadLocal中得到这个Closure
		println.apply("hello");
		println.each("hello", "oschina");
	}

	static void print(Iterable<?> ps) {
		Iterator<?> it = ps.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	static void print(Map<?, ?> map) {
		for (Map.Entry<?, ?> e : map.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}

	static void printl(int itemNo) {
		System.out.println("--------------------" + itemNo
				+ "---------------------");
	}
}