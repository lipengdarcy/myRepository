package org.darcy.algorithm.model;

import java.util.HashSet;

/// <summary>
/// 评分实体类
/// </summary>
public class Rating {
	// / <summary>
	// / 记录差值
	// / </summary>
	public float Value;

	// / <summary>
	// / 记录评分人数，方便公式中的 m 和 n 的值
	// / </summary>
	public int Freq;

	// / <summary>
	// / 记录打分用户的ID
	// / </summary>
	public HashSet<Integer> hash_user = new HashSet<Integer>();

	// / <summary>
	// / 平均值
	// / </summary>
	public float AverageValue = Value / Freq;

}