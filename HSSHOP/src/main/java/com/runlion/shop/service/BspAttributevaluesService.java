package com.runlion.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspAttributegroupsMapper;
import com.runlion.shop.dao.BspAttributesMapper;
import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.entity.BspAttributegroups;
import com.runlion.shop.entity.BspAttributes;
import com.runlion.shop.entity.BspAttributevalues;

@Service
public class BspAttributevaluesService {

	@Autowired
	BspAttributesMapper bspAttributesMapper;
	@Autowired
	BspAttributegroupsMapper bspAttributegroupsMapper;
	@Autowired
	BspAttributevaluesMapper bspAttributevaluesMapper;

	public boolean saveAttrVal(BspAttributevalues attrVal) {

		Short attrid = attrVal.getAttrid();
		BspAttributes attr = bspAttributesMapper.selectByPrimaryKey(attrid);

		Short attrGId = attr.getAttrgroupid();
		BspAttributegroups attrGroup = bspAttributegroupsMapper
				.selectByPrimaryKey(attrGId);

		attrVal.setAttrdisplayorder(attr.getDisplayorder());
		attrVal.setAttrname(attr.getName());
		attrVal.setAttrshowtype(attr.getShowtype());

		attrVal.setAttrgroupdisplayorder(attrGroup.getDisplayorder());
		attrVal.setAttrgroupid(attrGroup.getAttrgroupid());
		attrVal.setAttrgroupname(attrGroup.getName());

		List<BspAttributevalues> defVals = bspAttributevaluesMapper
				.selectByAttrid(attrid);
		if (defVals == null || defVals.size() == 0) {
			BspAttributevalues handleVal = new BspAttributevalues();

			handleVal.setIsinput((byte) 1);
			handleVal.setAttrvalue("手动输入");
			handleVal.setAttrdisplayorder(0);
			handleVal.setAttrid(attrid);

			handleVal.setAttrdisplayorder(attr.getDisplayorder());
			handleVal.setAttrname(attr.getName());
			handleVal.setAttrshowtype(attr.getShowtype());

			handleVal.setAttrgroupdisplayorder(attrGroup.getDisplayorder());
			handleVal.setAttrgroupid(attrGroup.getAttrgroupid());
			handleVal.setAttrgroupname(attrGroup.getName());

			bspAttributevaluesMapper.insertSelective(handleVal);
		}

		int rsi = bspAttributevaluesMapper.insertSelective(attrVal);

		if (rsi > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean delAttrVal(Integer attrId) {
		int rsi = bspAttributevaluesMapper.deleteByPrimaryKey(attrId);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	public BspAttributevalues getBspAttributevaluesById(Integer id) {
		return bspAttributevaluesMapper.selectByPrimaryKey(id);
	}

	public boolean uptBspAttributevalues(BspAttributevalues u) {
		int rsi = bspAttributevaluesMapper.updateByPrimaryKeySelective(u);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}
}
