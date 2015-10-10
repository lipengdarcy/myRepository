package com.runlion.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspUnitMapper;
import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.BspUnitExample;

@Service
public class BspUnitService {

	@Autowired
	BspUnitMapper bspUnitMapper;

	public List<BspUnit> searchUnit(String keyWord) {
		BspUnitExample bue = new BspUnitExample();
		//
		BspUnitExample.Criteria remarksCri = bue.createCriteria();
		remarksCri.andRemarksLike("%" + keyWord + "%");
		//
		BspUnitExample.Criteria nameCri = bue.createCriteria();
		nameCri.andUnitnameLike("%" + keyWord + "%");
		//
		bue.or(nameCri);
		return bspUnitMapper.selectByExample(bue);
	}

	public List<BspUnit> selUnitsByType(Integer id) {
		BspUnitExample bue = new BspUnitExample();
		BspUnitExample.Criteria typeCri = bue.createCriteria();
		typeCri.andUnittypeEqualTo(id);
		return bspUnitMapper.selectByExample(bue);
	}

	public BspUnit selUnitById(Integer id) {
		return bspUnitMapper.selectByPrimaryKey(id);
	}

	public boolean insertUnit(BspUnit bu) {
		int rsi = bspUnitMapper.insertSelective(bu);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean upUnit(BspUnit bu) {
		int rsi = bspUnitMapper.updateByPrimaryKeySelective(bu);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteUnit(Integer id) {
		int rsi = bspUnitMapper.deleteByPrimaryKey(id);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}
}
