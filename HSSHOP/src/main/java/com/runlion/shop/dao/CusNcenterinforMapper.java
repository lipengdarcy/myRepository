package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import com.runlion.shop.vo.product.StoreAndNcProinfor;

public interface CusNcenterinforMapper {

	/**
	 * 
	 * @param par
	 * @return
	 */
	public List<StoreAndNcProinfor> selStoreAndNcProinfor(
			Map<String, Object> par);

	/**
	 * 
	 * @param par
	 * @return
	 */
	public int countStoreAndNcProinfor(Map<String, Object> par);

}
