package com.runlion.shop.service.region;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspRegionsExample;
import com.runlion.shop.entity.BspShipaddresses;

@Service
public class RegionsService {
	@Autowired
	private BspRegionsMapper regionsMapper;

	@Autowired
	private BspProductsregionsMapper bspProductsregionsMapper;

	/**
	 * 根据收货地址查找区域地址
	 * 
	 * @param shipaddresses
	 * @return
	 */
	public BspRegions getResionsByAddress(BspShipaddresses shipaddresses) {
		if (shipaddresses != null) {
			BspRegionsExample bsae = new BspRegionsExample();
			bsae.or().andRegionidEqualTo(shipaddresses.getRegionid());
			List<BspRegions> bsalist = regionsMapper.selectByExample(bsae);
			if (bsalist != null && bsalist.size() > 0) {
				return bsalist.get(0);
			}
		}
		return null;
	}

	/**
	 * 获取区域
	 * 
	 * @param id
	 * @return
	 */
	public BspRegions getResionsById(int id) {
		return regionsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取区域的全称
	 * 
	 * @param id
	 * @return
	 */
	public String getResionsFullNameById(int id) {
		String name = "";
		BspRegions region = regionsMapper.selectByPrimaryKey(id);
		if (region == null)
			return name;

		switch (region.getLayer()) {
		// 省级:
		case 1:
			name = region.getName().trim();
			break;
		// 市级:
		case 2:
			name = region.getProvincename().trim() + "-"
					+ region.getName().trim();
			break;
		// 县级:
		case 3:
			name = region.getProvincename().trim() + "-"
					+ region.getCityname().trim() + "-"
					+ region.getName().trim();
			break;
		// 街道级:
		case 4:
			name = region.getProvincename().trim() + "-"
					+ region.getCityname().trim() + "-"
					+ region.getCountyname().trim() + "-"
					+ region.getName().trim();
			break;
		// 小区级（村级）:
		case 5:
			name = region.getProvincename().trim() + "-"
					+ region.getCityname().trim() + "-"
					+ region.getCountyname().trim() + "-"
					+ region.getStreetname().trim() + "-"
					+ region.getName().trim();
			break;
		default:
			break;
		}

		return name;
	}

	/**
	 * 获取区域的所有下级区域id
	 * 
	 * @param regionsId
	 * @author lipeng
	 * @return
	 */
	public List<BspRegions> getResionsChildrenById(int regionsId) {
		List<BspRegions> rlist = new ArrayList<BspRegions>();

		BspRegions region = regionsMapper.selectByPrimaryKey(regionsId);
		if (region == null)
			return rlist;

		switch (region.getLayer()) {
		// 省级: 下级太多了，暂时不处理！
		case 1:
			break;
		// 市级:
		case 2:
			BspRegionsExample e1 = new BspRegionsExample();
			e1.or().andParentidEqualTo(regionsId);
			rlist = regionsMapper.selectByExample(e1);
			for (BspRegions b : rlist) {
				BspRegionsExample e2 = new BspRegionsExample();
				e2.or().andParentidEqualTo(b.getRegionid());
				List<BspRegions> tmpList = regionsMapper.selectByExample(e2);
				for (BspRegions inner : tmpList) {
					BspRegionsExample e3 = new BspRegionsExample();
					e3.or().andParentidEqualTo(inner.getRegionid());
					rlist.addAll(regionsMapper.selectByExample(e3));
				}
			}
			break;
		// 县级:
		case 3:
			e1 = new BspRegionsExample();
			e1.or().andParentidEqualTo(regionsId);
			rlist = regionsMapper.selectByExample(e1);
			List<BspRegions> tmpList = new ArrayList<BspRegions>();
			Iterator<BspRegions> iterator = rlist.iterator();

			while (iterator.hasNext()) {
				BspRegions b = iterator.next();
				BspRegionsExample e2 = new BspRegionsExample();
				e2.or().andParentidEqualTo(b.getRegionid());
				tmpList.addAll(regionsMapper.selectByExample(e2));
			}
			rlist.addAll(tmpList);
			break;
		// 街道级:
		case 4:
			BspRegionsExample e = new BspRegionsExample();
			e.or().andParentidEqualTo(regionsId);
			rlist = regionsMapper.selectByExample(e);
			break;
		// 小区级（村级）: 无下级
		case 5:
			break;
		default:
			break;
		}
		return rlist;

	}

	/**
	 * 获取区域的所有上级区域id
	 * 
	 * @param regionsId
	 * @author lipeng
	 * @return
	 */
	public List<Integer> getParentRegionList(int regionsId) {
		List<Integer> list = new ArrayList<Integer>();
		BspRegions region = regionsMapper.selectByPrimaryKey(regionsId);
		if (region == null)
			return list;

		switch (region.getLayer()) {
		// 省级:无上级
		case 1:
			break;
		// 市级:
		case 2:
			if (region.getProvinceid() > 0)
				list.add(region.getProvinceid());
			else
				list.add(region.getParentid());
			break;
		// 县级:
		case 3:
			list.add(region.getCityid());
			list.add(region.getProvinceid());
			break;
		// 街道级:
		case 4:
			list.add(region.getCountyid());
			list.add(region.getCityid());
			list.add(region.getProvinceid());
			break;
		// 小区级（村级）:
		case 5:
			list.add(region.getStreetid());
			list.add(region.getCountyid());
			list.add(region.getCityid());
			list.add(region.getProvinceid());
			break;
		default:
			break;
		}
		return list;
	}

	public List<BspProductsregions> getSealAddrProRegions(Integer sealAddrId,
			int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		return bspProductsregionsMapper.getSaleAddressProRegions(sealAddrId);
	}

	public String calCid(BspRegions bspRegions, Integer regid) {
		Byte layer = bspRegions.getLayer();
		String cid = "";
		if (layer == 1) {
			cid = regid + "-0-0-0-0";
		} else if (layer == 2) {
			cid = bspRegions.getProvinceid() + "-" + regid + "-0-0-0";
		} else if (layer == 3) {
			cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
					+ "-" + regid + "-0-0";
		} else if (layer == 4) {
			cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
					+ "-" + bspRegions.getCountyid() + "-" + regid + "-0";
		} else if (layer == 5) {
			cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
					+ "-" + bspRegions.getCountyid() + "-"
					+ bspRegions.getStreetid() + "-" + regid;
		}
		return cid;
	}

	public String calCid(Integer regid) {
		BspRegions bspRegions = this.getResionsById(regid);
		String cid = "";
		if (bspRegions != null) {
			Byte layer = bspRegions.getLayer();

			if (layer == 1) {
				cid = regid + "-0-0-0-0";
			} else if (layer == 2) {
				cid = bspRegions.getProvinceid() + "-" + regid + "-0-0-0";
			} else if (layer == 3) {
				cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
						+ "-" + regid + "-0-0";
			} else if (layer == 4) {
				cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
						+ "-" + bspRegions.getCountyid() + "-" + regid + "-0";
			} else if (layer == 5) {
				cid = bspRegions.getProvinceid() + "-" + bspRegions.getCityid()
						+ "-" + bspRegions.getCountyid() + "-"
						+ bspRegions.getStreetid() + "-" + regid;
			}
		}

		return cid;
	}

}
