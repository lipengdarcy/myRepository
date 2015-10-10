package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import com.runlion.shop.vo.pay.StoremnyInforVO;

public interface CusPayorderMapper {
	/**
	 * 
	 * @param par
	 * @return
	 */
	public List<StoremnyInforVO> selStoremnyVOList(Map<String, Object> par);

	public int countStoremnyVOList(Map<String, Object> par);
}