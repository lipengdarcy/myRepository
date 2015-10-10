package com.runlion.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.dao.BspAttributegroupsMapper;
import com.runlion.shop.dao.BspAttributesMapper;
import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.dao.BspCategoriesMapper;
import com.runlion.shop.entity.BspAttributegroups;
import com.runlion.shop.entity.BspAttributes;
import com.runlion.shop.entity.BspAttributesExample;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspAttributevaluesExample;
import com.runlion.shop.entity.BspCategories;
import com.runlion.shop.entity.BspCategoriesExample;
import com.runlion.shop.vo.AttrValsVO;
import com.runlion.shop.vo.CategoryParentVo;

@Service
public class BspCategoriesService {
	@Autowired
	BspCategoriesMapper bspCategoriesMapper;

	@Autowired
	BspAttributesMapper bspAttributesMapper;

	@Autowired
	BspAttributegroupsMapper bspAttributegroupsMapper;

	@Autowired
	BspAttributevaluesMapper bspAttributevaluesMapper;

	@Transactional(readOnly = true)
	public BspCategories selectByPrimaryKey(short id) {
		return bspCategoriesMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public List<BspCategories> selectByKeyWord(String keyWord) {
		return bspCategoriesMapper.selectByKeyWord(keyWord);
	}

	public boolean updateByPrimaryKeySelective(BspCategories record,
			Short oldParId) {
		Short parId = record.getParentid();
		int rsi = bspCategoriesMapper.updateByPrimaryKeySelective(record);
		// 测试自身是否是孩子节点
		List zslist = bspCategoriesMapper.selectByParKey(record.getCateid());
		BspCategories bspCategories = new BspCategories();
		bspCategories.setCateid(record.getCateid());
		if (zslist.size() <= 0) {
			bspCategories.setHaschild((byte) 0);
		} else {
			bspCategories.setHaschild((byte) 1);
		}
		bspCategoriesMapper.updateByPrimaryKeySelective(bspCategories);

		// 测试老的父节点还有没有孩子节点
		if (parId != oldParId) {
			List slist = bspCategoriesMapper.selectByParKey(oldParId);
			if (slist.size() <= 0) {
				BspCategories tbspCategories = new BspCategories();
				tbspCategories.setCateid(oldParId);
				tbspCategories.setHaschild((byte) 0);
				bspCategoriesMapper.updateByPrimaryKeySelective(tbspCategories);
			}

		}
		// 更新新的父节点为有子节点
		if (parId != 0) {
			BspCategories tbspCategories = new BspCategories();
			tbspCategories.setCateid(parId);
			tbspCategories.setHaschild((byte) 1);
			bspCategoriesMapper.updateByPrimaryKeySelective(tbspCategories);
		}
		return true;
	}

	@Transactional(readOnly = true)
	public List<CategoryParentVo> getCateParentVos() {
		List<BspCategories> categoryList = bspCategoriesMapper
				.selectByKeyWord("");
		List<CategoryParentVo> cateParents = new ArrayList();
		for (int ci = 0; ci < categoryList.size(); ci++) {
			BspCategories cate = categoryList.get(ci);
			CategoryParentVo cpVo = new CategoryParentVo();
			cpVo.setCateId(cate.getCateid());
			cpVo.setLayer(cate.getLayer());
			cpVo.setHasChildren(cate.getHaschild());
			String blank = "";
			for (int li = 0; li < cate.getLayer(); li++) {
				blank += "&nbsp;&nbsp;";
			}
			cpVo.setName(blank + cate.getName());
			cateParents.add(cpVo);
		}
		return cateParents;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addBspCategories(BspCategories cate) {

		BspCategories pcate = null;
		if (cate.getParentid() != 0) {
			pcate = bspCategoriesMapper.selectByPrimaryKey(cate.getParentid());
		}

		int rsi = bspCategoriesMapper.insertSelective(cate);

		if (rsi > 0) {
			String ppath = "";
			byte layer = 0;
			if (pcate != null) {
				ppath = pcate.getPath().trim() + ",";
				layer = pcate.getLayer();
				// 更新父节点是否有子节点的字段标记
				if (pcate.getHaschild() == 0) {
					pcate.setHaschild((byte) 1);
					bspCategoriesMapper.updateByPrimaryKeySelective(pcate);
				}
			}

			cate.setPath(ppath + cate.getCateid());
			cate.setLayer((byte) (layer + 1));
			// 设置是否有子节点的标志
			short cateid = cate.getCateid();
			List slist = bspCategoriesMapper.selectByParKey(cateid);
			if (slist != null && slist.size() > 0) {
				cate.setHaschild((byte) 1);
			} else {
				cate.setHaschild((byte) 0);
			}

			int usi = bspCategoriesMapper.updateByPrimaryKeySelective(cate);
			if (usi > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * 删除节点及子节点
	 * 
	 * @param cateid
	 * @return
	 */
	public boolean deleteByPrimaryKey(Short cateid) {
		int rsi = bspCategoriesMapper.deleteByPrimaryKey(cateid);
		if (rsi > 0) {
			int prsi = bspCategoriesMapper.deleteByParKey(cateid);

			return true;

		} else {
			return false;
		}
	}

	/**
	 * 获取属性复合vo列表
	 * 
	 * @param cateid
	 * @return List
	 */
	public List<AttrValsVO> selAttrValsVOList(Short cateid) {
		List<AttrValsVO> voList = new ArrayList();
		BspAttributesExample attrExample = new BspAttributesExample();

		attrExample.createCriteria().andCateidEqualTo(cateid);
		attrExample.setOrderByClause("skuorder asc");
		//
		List<BspAttributes> attrList = bspAttributesMapper
				.selectByExample(attrExample);
		//
		if (attrList != null && attrList.size() > 0) {
			for (int ai = 0; ai < attrList.size(); ai++) {
				BspAttributes attr = attrList.get(ai);
				AttrValsVO valVo = new AttrValsVO();
				valVo.setAttributes(attr);

				List<BspAttributevalues> attrValList = bspAttributevaluesMapper
						.selectByAttrid(attr.getAttrid());
				valVo.setAttrValList(attrValList);
				voList.add(valVo);
			}
		}

		return voList;
	}

	public List<BspAttributegroups> getAttributegrouplist(Short cateid) {
		// TODO Auto-generated method stub
		return bspAttributegroupsMapper.getAttributegrouplist(cateid);
	}

	public List getAttributelist(Short cateid) {
		// TODO Auto-generated method stub
		return bspAttributesMapper.getAttributelist(cateid);
	}

	public BspAttributegroups getAttributegroup(Short id) {
		// TODO Auto-generated method stub
		return bspAttributegroupsMapper.selectByPrimaryKey(id);
	}

	public int deleteAttributegroup(Short id) {
		// TODO Auto-generated method stub
		return bspAttributegroupsMapper.deleteByPrimaryKey(id);
	}

	public int updateAttrGroup(BspAttributegroups bg) {
		// TODO Auto-generated method stub
		return bspAttributegroupsMapper.updateByPrimaryKeySelective(bg);
	}

	public void saveAttrGroup(BspAttributegroups bg) {
		bspAttributegroupsMapper.insert(bg);
	}

	public int deleteAttribute(Short attrid) {
		// TODO Auto-generated method stub
		return bspAttributesMapper.deleteByPrimaryKey(attrid);
	}

	public BspAttributes getAttribute(Short attrid) {
		// TODO Auto-generated method stub
		return bspAttributesMapper.selectByPrimaryKey(attrid);
	}

	public int updateAttr(BspAttributes record) {
		// TODO Auto-generated method stub
		return bspAttributesMapper.updateByPrimaryKeySelective(record);
	}

	public int saveAttrGroup(BspAttributes ba) {
		// TODO Auto-generated method stub
		return bspAttributesMapper.insertSelective(ba);
	}

	public List<BspAttributevalues> getBspAttributevaluesByAttrid(Short attrid) {
		BspAttributevaluesExample bae = new BspAttributevaluesExample();
		bae.createCriteria().andAttridEqualTo(attrid)
				.andIsinputNotEqualTo((byte) 1);
		bae.setOrderByClause("attrvaluedisplayorder desc");

		List<BspAttributevalues> list = bspAttributevaluesMapper
				.selectByExample(bae);
		return list;
	}

	public Map<Short, String> getCateIdNameMap(List idList) {
		BspCategoriesExample bcre = new BspCategoriesExample();
		bcre.createCriteria().andCateidIn(idList);
		List<BspCategories> cateList = bspCategoriesMapper
				.selectByExample(bcre);
		Map<Short, String> idNameMap = new HashMap();
		for (int i = 0; i < cateList.size(); i++) {
			BspCategories cateInfor = cateList.get(i);
			idNameMap.put(cateInfor.getCateid(), cateInfor.getName());
		}
		return idNameMap;

	}
}
