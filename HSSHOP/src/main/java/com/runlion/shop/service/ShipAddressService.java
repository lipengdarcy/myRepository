package com.runlion.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspShipaddressesMapper;
import com.runlion.shop.entity.BspShipaddresses;
import com.runlion.shop.entity.BspShipaddressesExample;
@Service
public class ShipAddressService {
	public static final byte TRUE = 1;
	@Autowired
	BspShipaddressesMapper shipaddressesMapper;
	
	/**
	 * 查询用户的默认收货地址
	 * @param userId
	 * @return
	 */
	public BspShipaddresses getDefaultAddress(int userId) {
		BspShipaddressesExample bsae = new BspShipaddressesExample();
		BspShipaddressesExample.Criteria criteria = bsae.createCriteria();
		criteria.andUidEqualTo(userId);
		criteria.andIsdefaultEqualTo(TRUE);
		List<BspShipaddresses> bsalist = shipaddressesMapper.selectByExample(bsae);
		if(bsalist != null && bsalist.size() > 0) {
			return bsalist.get(0);
		}
		
		return null;
	}
	
	
	/**
	 * 查询用户的收货地址
	 * @param userId
	 * @return
	 */
	public List<BspShipaddresses> getShipAddress(int userId) {
		BspShipaddressesExample bsae = new BspShipaddressesExample();
		BspShipaddressesExample.Criteria criteria = bsae.createCriteria();
		criteria.andUidEqualTo(userId);
		
		List<BspShipaddresses> bsalist = shipaddressesMapper.selectByExample(bsae);
		return bsalist;
	}
	
	/**
	 * 根据id获取地址
	 * @param said
	 * @return
	 */
	public BspShipaddresses getShipAddressById(int said) {
		return shipaddressesMapper.selectByPrimaryKey(said);
	}
	
	
	
}
