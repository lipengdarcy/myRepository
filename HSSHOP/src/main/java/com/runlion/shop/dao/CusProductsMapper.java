package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.vo.SalerProInfor;

public interface CusProductsMapper {
	/**
	 * 获取允销目录
	 * 
	 * @param par
	 * @return
	 */
	List<SalerProInfor> selectSalerProlist(Map<String, Object> par);

	/**
	 * 获取允销目录的总条目数
	 * 
	 * @param par
	 * @return
	 */
	int countSalerProlist(Map<String, Object> par);

	/**
	 * 获取属性值和属性名
	 * 
	 * @param par
	 * @return
	 */
	Map<String, Object> getProAttrvalAndName(Map<String, Object> par);

	/**
	 * 获取所有产品的列表
	 * 
	 * @param par
	 * @return
	 */
	List<BspProducts> getAllProList(Map<String, Object> par);

	/**
	 * 获取所有产品的数目
	 * 
	 * @param par
	 * @return
	 */
	int countAllProList(Map<String, Object> par);
}