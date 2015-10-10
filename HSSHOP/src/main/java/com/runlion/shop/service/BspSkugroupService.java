package com.runlion.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspSkugroupMapper;
import com.runlion.shop.entity.BspSkugroup;

@Service
public class BspSkugroupService {

	@Autowired
	BspSkugroupMapper bspSkugroupMapper;

	public BspSkugroup selectByPrimaryKey(Integer skugid) {
		return bspSkugroupMapper.selectByPrimaryKey(skugid);
	}
}
