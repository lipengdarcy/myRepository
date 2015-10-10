package com.runlion.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.dao.BspProductsregionspriceMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspRegionsExample;
import com.runlion.shop.vo.ItemAreaInfor;

/**
 * @2015年7月2日 by linyj
 */
@Service
public class BspRegionsService {
	public static byte MAX_LAYER = 5;

	@Autowired
	private BspRegionsMapper bspRegionsMapper;

	@Autowired
	private BspProductsregionspriceMapper bspProductsregionspriceMapper;

	@Autowired
	private BspAreaManagerService bspAreaManagerService;

	public BspRegions queryRegionByid(int id) {
		return bspRegionsMapper.selectByPrimaryKey(id);
	}

	public List<BspRegions> queryCity(int parentid) {
		BspRegionsExample e = new BspRegionsExample();
		e.setDistinct(true);
		e.createCriteria().andParentidEqualTo(parentid);
		e.setOrderByClause("displayorder,regionid");
		List<BspRegions> list = bspRegionsMapper.selectByExample(e);
		return list;
	}

	public List<BspRegions> queryProvince() {
		BspRegionsExample e = new BspRegionsExample();
		e.setDistinct(true);
		e.createCriteria().andParentidEqualTo(0);
		e.setOrderByClause("displayorder,regionid");
		List<BspRegions> list = bspRegionsMapper.selectByExample(e);
		return list;
	}

	/**
	 * TODO(根据条件返回总数)
	 * 
	 * @param e
	 * @return int 返回类型
	 */
	public int countByExample(BspRegionsExample e) {
		return bspRegionsMapper.countByExample(e);
	}

	/**
	 * 保存一个新的区域信息
	 * 
	 * @param parid
	 * @param areaname
	 * @return BspRegions
	 * @throws Exception
	 */
	@Transactional
	public List<BspRegions> saveNewItem(Integer parid, String[] areanames)
			throws Exception {
		List<BspRegions> rsList = new ArrayList();
		for (int i = 0; i < areanames.length; i++) {
			//
			String areaname = areanames[i];
			BspRegions newItem = null;
			//
			if (parid != null && parid != 0) {// 需要父级区域
				BspRegions parRegion = bspRegionsMapper
						.selectByPrimaryKey(parid);
				if (parRegion == null) {// 没有找到父级区域
					throw new Exception("需要父级区域，但数据库中不存在该记录！parid=" + parid);
				} else {// 找到父级区域
					if (parRegion.getLayer() >= MAX_LAYER) {
						throw new Exception("父级区域层级为" + parRegion.getLayer()
								+ ",已达到最大允许层级，不允许添加子区域。");
					}
					//
					newItem = this.fillNewItem(parRegion, areaname);
				}
			} else {// 不需要父级区域
				newItem = this.fillNewItem(null, areaname);
			}
			int rsi = bspRegionsMapper.insertSelective(newItem);
			if (rsi > 0) {
				rsList.add(newItem);
			}
		}
		return rsList;

	}

	/**
	 * 为新生成的区域条目填充数值
	 * 
	 * @param parRegion
	 * @param areaname
	 * @return
	 */
	private BspRegions fillNewItem(BspRegions parRegion, String areaname) {
		BspRegions newItem = new BspRegions();
		newItem.setName(areaname);
		if (parRegion == null) {
			newItem.setParentid(0);
			newItem.setLayer((byte) 1);
			newItem.setDisplayorder(0);
			newItem.setProvinceid(0);
			newItem.setCityid(0);
			newItem.setCountyid(0);
			newItem.setStreetid(0);
		} else {
			newItem.setParentid(parRegion.getRegionid());
			newItem.setLayer((byte) (parRegion.getLayer() + 1));
			newItem.setDisplayorder(0);
			newItem.setProvinceid(parRegion.getProvinceid());
			newItem.setProvincename(parRegion.getProvincename());
			newItem.setCityid(parRegion.getCityid());
			newItem.setCityname(parRegion.getCityname());
			newItem.setCountyid(parRegion.getCountyid());
			newItem.setCountyname(parRegion.getCountyname());
			newItem.setStreetid(parRegion.getStreetid());
			newItem.setStreetname(newItem.getStreetname());
			switch (parRegion.getLayer()) {
			case 1: {
				newItem.setProvinceid(parRegion.getRegionid());
				newItem.setProvincename(parRegion.getName());
				break;
			}
			case 2: {
				newItem.setCityid(parRegion.getRegionid());
				newItem.setCityname(parRegion.getName());
				break;
			}
			case 3: {
				newItem.setCountyid(parRegion.getRegionid());
				newItem.setCountyname(parRegion.getName());
				break;
			}
			case 4: {
				newItem.setStreetid(parRegion.getRegionid());
				newItem.setStreetname(parRegion.getName());
				break;
			}
			default:
				break;
			}
		}
		return newItem;
	}

	/**
	 * 删除选中的区域及其子区域
	 * 
	 * @param parid
	 * @param areaname
	 * @return BspRegions
	 * @throws Exception
	 */
	@Transactional
	public int delSelAreas(String ids) throws Exception {
		if (ids == null) {
			return 0;
		} else {
			String[] idArr = ids.split(",");
			List<Integer> idList = new ArrayList();
			for (int i = 0; i < idArr.length; i++) {
				int iid = Integer.valueOf(idArr[i]);
				if (iid != 0) {
					idList.add(iid);
				}
			}
			BspRegionsExample bre = new BspRegionsExample();
			bre.or(bre.createCriteria().andRegionidIn(idList));
			bre.or(bre.createCriteria().andProvinceidIn(idList));
			bre.or(bre.createCriteria().andCityidIn(idList));
			bre.or(bre.createCriteria().andCountyidIn(idList));
			bre.or(bre.createCriteria().andStreetidIn(idList));
			return bspRegionsMapper.deleteByExample(bre);
		}

	}

	/**
	 * 根据id修改区域名称,首先修改本区域的名称，然后修改子区域使用到自己的名称
	 * 
	 * @param id
	 * @param areaname
	 * @return BspRegions
	 * @throws Exception
	 */
	@Transactional
	public ItemAreaInfor editItem(Integer id, String areaname) throws Exception {
		BspRegions currRegion = bspRegionsMapper.selectByPrimaryKey(id);
		ItemAreaInfor itemInfor = new ItemAreaInfor();
		if (currRegion != null) {
			// 修改本级区域信息
			BspRegions region = new BspRegions();
			region.setRegionid(id);
			region.setName(areaname);

			itemInfor = new ItemAreaInfor();
			itemInfor.setId(id);
			itemInfor.setName(areaname);
			itemInfor.setProvinceId(currRegion.getProvinceid());
			itemInfor.setCityId(currRegion.getCityid());
			itemInfor.setCountyId(currRegion.getCountyid());
			itemInfor.setStreetId(currRegion.getStreetid());

			int rsi = bspRegionsMapper.updateByPrimaryKeySelective(region);
			// 修改相关联的子级区域信息
			BspRegionsExample bre = new BspRegionsExample();
			BspRegions subregion = new BspRegions();
			// 如果是一级，所有省份信息都要更新名称
			if (currRegion.getLayer() == 1) {
				bre.createCriteria().andProvinceidEqualTo(id);
				subregion.setProvincename(areaname);
				// 如果是二级，所有城市信息都要更新名称
			} else if (currRegion.getLayer() == 2) {
				subregion.setCityname(areaname);
				bre.createCriteria().andCityidEqualTo(id);
				// 如果是三级，所有乡镇信息都要更新名称
			} else if (currRegion.getLayer() == 3) {
				bre.createCriteria().andCountyidEqualTo(id);
				subregion.setCountyname(areaname);
				// 如果是四级，所有街道信息都要更新名称
			} else if (currRegion.getLayer() == 4) {
				subregion.setStreetname(areaname);
				bre.createCriteria().andStreetidEqualTo(id);

			}
			if (currRegion.getLayer() != 0 && currRegion.getLayer() != 5) {
				rsi += bspRegionsMapper
						.updateByExampleSelective(subregion, bre);
			}
		}

		return itemInfor;
	}

}
