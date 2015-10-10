package com.runlion.shop.dao;

import com.runlion.shop.entity.BspUsercarinfor;

public interface CusUsercarinforMapper {

	/**
	 * 插入一条记录，并且将主键添加到记录中
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(BspUsercarinfor record);

}