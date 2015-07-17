package org.darcy.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.darcy.algorithm.model.Product;
import org.darcy.algorithm.model.Rating;

/**
 * 相信大家对如下的Category都很熟悉，很多网站都有类似如下的功能，“商品推荐”,"猜你喜欢“，在实体店中我们有导购来为我们服务，在网络上
 * 
 * 我们需要同样的一种替代物，如果简简单单的在数据库里面去捞，去比较，几乎是完成不了的,这时我们就需要一种协同推荐算法，来高效的推荐浏览者喜
 * 
 * 欢的商品。 rb = (n * (ra - R(A->B)) + m * (rc - R(C->B)))/(m+n)
 * 
 * 注意： a,b,c 代表“商品”。
 * 
 * ra 代表“商品的打分值”。
 * 
 * ra->b 代表“A组到B组的平均差（均值化）”。
 * 
 * m,n 代表人数。
 * 
 * 
 * */

public class SlopeOne {

	public static void main(String[] args) {
		SlopeOne test = new SlopeOne();

		Map<Integer, List<Product>> userRating = new HashMap<Integer, List<Product>>();

		// 第一位用户
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1, "洗衣机", 5));
		list.add(new Product(2, "电冰箱", 10));
		list.add(new Product(3, "彩电", 10));
		list.add(new Product(4, "空调", 5));

		userRating.put(1000, list);

		test.AddUserRatings(userRating);

		userRating.clear();
		userRating.put(1000, list);

		test.AddUserRatings(userRating);

		// 第二位用户
		List<Product> list2 = new ArrayList<Product>();
		list2.add(new Product(1, "洗衣机", 4));
		list2.add(new Product(2, "电冰箱", 5));
		list2.add(new Product(3, "彩电", 4));
		list2.add(new Product(4, "空调", 10));

		userRating.clear();
		userRating.put(2000, list2);

		test.AddUserRatings(userRating);

		// 第三位用户
		List<Product> list3 = new ArrayList<Product>();
		list3.add(new Product(1, "洗衣机", 4));
		list3.add(new Product(2, "电冰箱", 10));
		list3.add(new Product(4, "空调", 5));

		userRating.clear();
		userRating.put(3000, list3);

		test.AddUserRatings(userRating);

		// 那么我们预测userID=3000这个人对 “彩电” 的打分会是多少？
		int userID = userRating.keySet().iterator().next();
		List<Product> result = userRating.get(userID);

		Map<Integer, Float> predictions = test.Predict(result);

		for (Entry<Integer, Float> rating : predictions.entrySet())
			System.out.println("ProductID = " + rating.getKey() + " Rating: "
					+ rating.getValue());
	}

	// / <summary>
	// / 评分系统
	// / </summary>
	public Map<Integer, Product> dicRatingSystem = new HashMap<Integer, Product>();

	public Map<String, Rating> dic_Martix = new HashMap<String, Rating>();

	public HashSet<Integer> hash_items = new HashSet<Integer>();

	// / 接收一个用户的打分记录

	public void AddUserRatings(Map<Integer, List<Product>> userRatings) {
		for (Entry<Integer, List<Product>> user1 : userRatings.entrySet())
		// foreach (var user1 in userRatings)
		{

			// 遍历所有的Item
			for (Product item1 : user1.getValue()) {
				// 该产品的编号（具有唯一性）
				int item1Id = item1.ProductID;
				// 该项目的评分
				float item1Rating = item1.Score;
				// 将产品编号字存放在hash表中
				hash_items.add(item1.ProductID);

				for (Entry<Integer, List<Product>> user2 : userRatings
						.entrySet()) {
					// 再次遍历item，用于计算俩俩 Item 之间的差值
					for (Product item2 : user2.getValue()) {
						// 过滤掉同名的项目
						if (item2.ProductID <= item1Id)
							continue;
						// 该产品的名字
						int item2Id = item2.ProductID;
						// 该项目的评分
						float item2Rating = item2.Score;
						Rating ratingDiff;
						// 用表的形式构建矩阵
						String key = GetKey(item1Id, item2Id);

						// 将俩俩 Item 的差值 存放到 Rating 中
						if (dic_Martix.containsKey(key))
							ratingDiff = dic_Martix.get(key);
						else {
							ratingDiff = new Rating();
							dic_Martix.put(key, ratingDiff);
						}

						// 方便以后以后userrating的编辑操作，（add)
						if (!ratingDiff.hash_user.contains(user1.getKey())) {
							// value保存差值
							ratingDiff.Value += item1Rating - item2Rating;

							// 说明计算过一次
							ratingDiff.Freq += 1;
						}

						// 记录操作人的ID，方便以后再次添加评分
						ratingDiff.hash_user.add(user1.getKey());
					}
				}
			}
		}
	}

	// / <summary>
	// / 根据矩阵的值，预测出该Rating中的值
	// / </summary>
	// / <param name="userRatings"></param>
	// / <returns></returns>
	public Map<Integer, Float> Predict(List<Product> userRatings) {
		Map<Integer, Float> predictions = new HashMap<Integer, Float>();

		List<Integer> productIDs = new ArrayList<Integer>();
		for (Product p : userRatings)
			productIDs.add(p.ProductID);

		// 循环遍历_Items中所有的Items
		for (Integer itemId : this.hash_items) {
			// 过滤掉不需要计算的产品编号
			if (productIDs.contains(itemId))
				continue;

			Rating itemRating = new Rating();

			// 内层遍历userRatings
			for (Product userRating : userRatings) {
				if (userRating.ProductID == itemId)
					continue;

				int inputItemId = userRating.ProductID;

				// 获取该key对应项目的两组AVG的值
				String key = GetKey(itemId, inputItemId);

				if (dic_Martix.containsKey(key)) {
					Rating diff = dic_Martix.get(key);
					// 关键点：运用公式求解（这边为了节省空间，对角线两侧的值呈现奇函数的特性）
					itemRating.Value += diff.Freq
							* (userRating.Score + diff.AverageValue
									* ((itemId < inputItemId) ? 1 : -1));
					// 关键点：运用公式求解 累计每两组的人数
					itemRating.Freq += diff.Freq;
				}
			}

			predictions.put(itemId, itemRating.AverageValue);
		}

		return predictions;
	}

	public String GetKey(int Item1Id, int Item2Id) {
		return (Item1Id < Item2Id) ? Item1Id + "->" + Item2Id : Item2Id + "->"
				+ Item1Id;
	}
}
