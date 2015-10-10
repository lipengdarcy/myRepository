package com.runlion.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.runlion.shop.common.Constant;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.dao.BspAreaManagerMapper;
import com.runlion.shop.dao.BspAttributesMapper;
import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.dao.BspFavoritesMapper;
import com.runlion.shop.dao.BspProductattributesMapper;
import com.runlion.shop.dao.BspProductimagesMapper;
import com.runlion.shop.dao.BspProductsMapper;
import com.runlion.shop.dao.BspProductskusMapper;
import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspProductsregionspriceMapper;
import com.runlion.shop.dao.BspProductstocksMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspSkugroupMapper;
import com.runlion.shop.dao.BspUnitMapper;
import com.runlion.shop.dao.BspWorktimeMapper;
import com.runlion.shop.dao.CusNcenterinforMapper;
import com.runlion.shop.dao.CusProductsMapper;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.BspAttributes;
import com.runlion.shop.entity.BspAttributesExample;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspAttributevaluesExample;
import com.runlion.shop.entity.BspFavorites;
import com.runlion.shop.entity.BspFavoritesExample;
import com.runlion.shop.entity.BspProductattributes;
import com.runlion.shop.entity.BspProductattributesExample;
import com.runlion.shop.entity.BspProductimages;
import com.runlion.shop.entity.BspProductimagesExample;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsExample;
import com.runlion.shop.entity.BspProductskus;
import com.runlion.shop.entity.BspProductskusExample;
import com.runlion.shop.entity.BspProductskusRTM;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsExample;
import com.runlion.shop.entity.BspProductsregionsbrandplace;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProductstocks;
import com.runlion.shop.entity.BspProductstocksExample;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspSkugroup;
import com.runlion.shop.entity.BspSkugroupExample;
import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.BspWorktimeExample;
import com.runlion.shop.entity.ProductComboInfo;
import com.runlion.shop.entity.ProductLink;
import com.runlion.shop.entity.SkuInfo;
import com.runlion.shop.service.region.RegionsBrandService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.ProductAttrVO;
import com.runlion.shop.vo.ProductSkuVO;
import com.runlion.shop.vo.SalerProInfor;
import com.runlion.shop.vo.SalerProSelParaVO;
import com.runlion.shop.vo.SkuShowVO;
import com.runlion.shop.vo.product.SelAllProlistVO;
import com.runlion.shop.vo.product.SelStoreAndNcProinfor;
import com.runlion.shop.vo.product.StoreAndNcProinfor;

@Service
public class BspProductsService {
	@Autowired
	private BspProductsMapper mapper;

	@Autowired
	private CusProductsMapper cusmapper;

	@Autowired
	private BspAttributesMapper bspAttributesMapper;

	@Autowired
	private BspProductimagesMapper bspProductimagesMapper;

	@Autowired
	private BspAttributevaluesMapper bspAttributevaluesMapper;

	@Autowired
	private BspSaleaddressMapper bspSaleaddressMapper;

	@Autowired
	private BspFavoritesMapper bspFavoritesMapper;

	@Autowired
	private BspProductskusMapper bspProductskusMapper;

	@Autowired
	BspProductattributesMapper bspProductattributesMapper;

	@Autowired
	BspProductstocksMapper bspProductstocksMapper;

	@Autowired
	BspAreaManagerMapper bspAreaManagerMapper;

	@Autowired
	BspProductsregionspriceMapper bspProductsregionspriceMapper;

	@Autowired
	BspUnitMapper bspUnitMapper;

	@Autowired
	BspSkugroupMapper bspSkugroupMapper;

	@Autowired
	BspRegionsMapper bspRegionsMapper;

	@Autowired
	BspProductsregionsMapper bspProductsregionsMapper;

	@Autowired
	BspWorktimeMapper bspWorktimeMapper;

	@Autowired
	RegionsProductService regionsProductService;

	@Autowired
	RegionsBrandService regionsBrandService;

	@Autowired
	RegionsService regionsService;
	@Autowired
	CusNcenterinforMapper cusNcenterinforMapper;

	public BspProductsregions getBspProductsregions(int pid, int regionsId) {
		BspProductsregionsExample e = new BspProductsregionsExample();
		e.createCriteria().andProductidEqualTo(pid);
		List<BspProductsregions> list = bspProductsregionsMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return null;
		return list.get(0);
	}

	public BspProducts selectByPrimaryKey(int pid) {
		BspProducts entity = mapper.selectByPrimaryKey(pid);
		return entity;
	}

	/**
	 * TODO(查询带状态显示的产品信息后台预览使用)
	 * 
	 * 修复BUG，管理员查看所有状态 非管理员只能查看上架商品
	 * 
	 * @param pid
	 * @param bspAdminuser
	 * @return BspProducts 返回类型
	 */
	public BspProducts selectByPrimaryKeyState(int pid,
			BspAdminuser bspAdminuser, int areaId) {
		int state = 0;// 默认状态 上架预览
		if (null != bspAdminuser) {
			state = -1;// 显示所有状态
		}

		BspProducts entity = mapper.selectByPrimaryKeyState(pid, state, areaId);
		return entity;
	}

	public int updateProductByPrimaryKey(BspProducts record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	// 分页查询 测试
	public List<BspProducts> selectByExample(BspProductsExample example,
			int pageNumber, int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		List<BspProducts> list = mapper.selectByExample(example);
		return list;
	}

	// 获取扩展属性
	public List<BspAttributes> getAllAttributes() throws Exception {
		BspAttributesExample e = new BspAttributesExample();
		e.setOrderByClause("displayorder desc");
		return bspAttributesMapper.selectByExample(e);
	}

	// 获取单个扩展属性
	public BspAttributes getAttributeById(int id) throws Exception {
		return bspAttributesMapper.selectByPrimaryKey((short) id);
	}

	// 获取扩展属性的value
	public List<BspAttributevalues> getAttributesValue(short Attrid)
			throws Exception {
		BspAttributevaluesExample e = new BspAttributevaluesExample();
		BspAttributevaluesExample.Criteria c = e.createCriteria();
		c.andAttridEqualTo(Attrid);
		c.andIsinputEqualTo((byte) 0);
		List<BspAttributevalues> list = bspAttributevaluesMapper
				.selectByExample(e);
		return list;
	}

	// 获取产品分类属性：品种、强度、配送方式、生产厂家、包装、品牌
	public Map<String, Object> getProductAttributes() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 1.get BspAttributes
		List<BspAttributes> bspAttributes = getAllAttributes();
		// 2.get BspAttributevalues
		for (BspAttributes attr : bspAttributes)
			map.put(attr.getAttrid().toString(),
					getAttributesValue(attr.getAttrid()));

		return map;
	}

	// 产品筛选(单条件)
	public List<BspProducts> query(int typeid, int id, int pageNumber,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		List<BspProducts> list = null;
		switch (typeid) {
		case 47:
			list = mapper.selectByBZ(id);
			break;
		case 48:
			list = mapper.selectByPZ(id);
			break;
		case 49:
			list = mapper.selectByQD(id);
			break;
		case 50:
			list = mapper.selectByPSFS(id);
			break;
		case 51:
			list = mapper.selectBySCCJ(id);
			break;
		case 52:
			list = mapper.selectByPP(id);
			break;
		default:
			list = null;
			break;
		}
		return list;
	}

	// 产品筛选(组合条件) 本级区域找不到，则找上级区域，一直找到顶级，若没有，则结束。
	public List<BspProducts> comboQuery(Map map, int areaID, int pageNumber,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		List<BspProducts> list = mapper.comboQuery(map, areaID);
		// if (list == null || list.size() == 0) {
		// for (Integer regId : this.getParentRegionList(areaID)) {
		// PageHelper.startPage(pageNumber, pageSize);
		// list = mapper.comboQuery(map, regId);
		// if (list == null || list.size() == 0)
		// continue;
		// else
		// return list;
		// }
		//
		// }
		return list;
	}

	// 热门产品
	public List<BspProducts> hotProducts(int count) throws Exception {
		BspProductsExample e = new BspProductsExample();
		e.createCriteria().andStateEqualTo((byte) 0).andIshotEqualTo((byte) 1);
		e.setOrderByClause("pid desc");
		// 只显示最新4条
		e.setStart(0);
		e.setLimit(count);

		List<BspProducts> list = mapper.selectByExample(e);
		return list;
	}

	// 最新产品
	public List<BspProducts> newestProducts() throws Exception {
		BspProductsExample e = new BspProductsExample();
		e.createCriteria().andStateEqualTo((byte) 0).andIsnewEqualTo((byte) 1);
		e.setOrderByClause("pid desc");
		// 只显示最新4条
		e.setStart(0);
		e.setLimit(4);
		List<BspProducts> list = mapper.selectByExample(e);
		return list;
	}

	// 推荐产品
	public List<BspProducts> recommendedProducts() throws Exception {
		// to do: 推荐算法
		return newestProducts();
	}

	// product pic
	public List<BspProductimages> getProductPics(int pid) throws Exception {
		PageHelper.startPage(1, 20);
		BspProductimagesExample example = new BspProductimagesExample();
		example.createCriteria().andPidEqualTo(pid)
				.andIsmainLessThanOrEqualTo((byte) 1);
		List<BspProductimages> list = bspProductimagesMapper
				.selectByExample(example);
		return list;
	}

	// 获取详情页展示用的图片，只有普通图片
	public List<BspProductimages> getProductNormPics(int pid) throws Exception {
		PageHelper.startPage(1, 20);
		BspProductimagesExample example = new BspProductimagesExample();
		example.createCriteria().andPidEqualTo(pid).andIsmainEqualTo((byte) 0);
		List<BspProductimages> list = bspProductimagesMapper
				.selectByExample(example);
		return list;
	}

	// 后台显示的商品的图片，包含所有
	public List<BspProductimages> getAllProductPics(int pid) throws Exception {
		PageHelper.startPage(1, 20);
		BspProductimagesExample example = new BspProductimagesExample();
		example.createCriteria().andPidEqualTo(pid);

		List<BspProductimages> list = bspProductimagesMapper
				.selectByExample(example);
		return list;
	}

	// 首页的图片
	public List<BspProductimages> getIndexProductPics(int pid, byte ismain)
			throws Exception {
		PageHelper.startPage(1, 20);
		BspProductimagesExample example = new BspProductimagesExample();
		BspProductimagesExample.Criteria c = example.createCriteria();
		c.andPidEqualTo(pid).andIsmainEqualTo(ismain);
		/*
		 * .andIsmainEqualTo( (byte) 2); BspProductimagesExample .Criteria c2 =
		 * example .createCriteria (); c2.andPidEqualTo (pid ).andIsmainEqualTo
		 * ((byte) 3);
		 */

		example.or(c);
		// example.or(c2);

		List<BspProductimages> list = bspProductimagesMapper
				.selectByExample(example);
		return list;
	}

	// 获取产品的主图
	public BspProductimages getProductMainPic(int pid) throws Exception {
		BspProductimagesExample example = new BspProductimagesExample();
		example.createCriteria().andPidEqualTo(pid).andIsmainEqualTo((byte) 1);
		List<BspProductimages> list = bspProductimagesMapper
				.selectByExample(example);
		if (list == null || list.size() == 0)
			return null;
		return list.get(0);
	}

	// 产品搜索
	public List<BspProducts> search(int areaId, String keywords,
			int pageNumber, int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		// BspProductsExample e = new BspProductsExample();
		// String[] keys = keywords.split(" ");
		// for (String key : keys) {
		// if (key.equals(""))
		// continue;
		// // System.out.println(key);
		// e.or().andNameLike(key);
		// }

		// List<BspProducts> list = mapper.selectByExample(e);
		List<BspProducts> list = mapper.search(areaId, keywords);
		return list;
	}

	// 获取单个产品的属性
	public List<BspAttributevalues> getProductAttrByID(int id) throws Exception {
		return bspAttributevaluesMapper.getProductAttrByID(id);
	}

	// <!-- 根据区域id获取门店和工厂。 type=1门店，type=2工厂 -->
	public BspSaleaddress getProductAddress(Integer regionsId, Integer placeId,
			Integer type) throws Exception {
		BspSaleaddress address = null;
		if (type == 1) {
			address = bspSaleaddressMapper.getProductStore(regionsId);
			// if (address == null) {
			// for (Integer regId : this.getParentRegionList(regionsId)) {
			// address = bspSaleaddressMapper.getProductStore(regId);
			// if (address == null)
			// continue;
			// else
			// return address;
			// }
			//
			// }

			return address;
		} else {
			address = bspSaleaddressMapper
					.getProductFactory(regionsId, placeId);
			// if (address == null) {
			// for (Integer regId : this.getParentRegionList(regionsId)) {
			// address = bspSaleaddressMapper.getProductFactory(regId,
			// placeId);
			// if (address == null)
			// continue;
			// else
			// return address;
			// }
			//
			// }
			return address;
		}
	}

	// 根据主键获取门店和工厂
	public BspSaleaddress getBspSaleaddressById(Integer id) throws Exception {
		return bspSaleaddressMapper.selectByPrimaryKey(id);
	}

	// 收藏产品
	public int addFavorites(BspFavorites record) throws Exception {
		BspFavoritesExample e = new BspFavoritesExample();
		e.or().andPidEqualTo(record.getPid()).andUidEqualTo(record.getUid());
		List<BspFavorites> list = bspFavoritesMapper.selectByExample(e);
		if (list.isEmpty())
			return bspFavoritesMapper.insert(record);
		else
			return 0;
	}

	// 预览状态下，可以看到下架产品，非预览状态下，只能看到上架产品
	public List<BspProductskusRTM> getProductSku(int pid, boolean isPreview)
			throws Exception {
		if (isPreview)
			return bspProductskusMapper.getProductSkuForPreview(pid);
		else
			return bspProductskusMapper.getProductSku(pid);
	}

	public List<BspProductskusRTM> getProductSkuList(int pid) throws Exception {
		return bspProductskusMapper.getProductSkuList(pid);
	}

	public List<ProductComboInfo> initLink(String path) throws Exception {
		List<ProductComboInfo> productComboInfoList = new ArrayList<ProductComboInfo>();
		// 1.get BspAttributes
		List<BspAttributes> bspAttributes = getAllAttributes();

		if (path.length() == 0) {
			if (bspAttributes.size() > 0) {
				StringBuilder attrRouteValue = new StringBuilder();
				for (int i = 0; i < bspAttributes.size(); i++) {
					attrRouteValue.append("0-");
				}
				path = attrRouteValue.deleteCharAt(attrRouteValue.length() - 1)
						.toString();
			} else {
				path = "0";
			}
		}

		// 2.get BspAttributevalues
		int order = 1;
		String startStr;
		String endStr;
		String centerStr;
		for (BspAttributes attr : bspAttributes) {

			ProductComboInfo pc = new ProductComboInfo();
			pc.setAttrid(attr.getAttrid());
			pc.setAttrname(attr.getName());
			List<BspAttributevalues> attrList = getAttributesValue(attr
					.getAttrid());
			List<ProductLink> productLinkList = new ArrayList<ProductLink>();

			if (attrList.size() > 0) {
				startStr = order != 1 ? path.substring(0,
						StringHandler.IndexOf(path, order - 1) + 1) : "";
				endStr = order != bspAttributes.size() ? path
						.substring(StringHandler.IndexOf(path, order)) : "";
				centerStr = StringHandler.TrimEnd(
						StringHandler.TrimStart(path, startStr), endStr);

				// 增加一个全选
				ProductLink pl_all = new ProductLink();
				BspAttributevalues pl_attrValue = new BspAttributevalues();
				String sall = "不限";
				String sdt = "0";
				if (centerStr.equals("0")) {
					sdt = "1";
				}
				pl_attrValue.setAttrvalue(sall);
				pl_all.setPath(startStr + "0" + endStr);
				pl_all.setPathcenterStr(sdt);
				pl_all.setAttrValue(pl_attrValue);
				productLinkList.add(pl_all);

				for (BspAttributevalues attrValue : attrList) {
					ProductLink pl = new ProductLink();

					sdt = "0";
					if (centerStr.equals(attrValue.getAttrvalueid().toString())) {
						sdt = "1";
					}
					pl.setAttrValue(attrValue);
					pl.setPath(startStr + attrValue.getAttrvalueid() + endStr);
					pl.setPathcenterStr(sdt);
					productLinkList.add(pl);
				}
			}
			pc.setProductlinklist(productLinkList);
			productComboInfoList.add(pc);

			order++;
		}
		return productComboInfoList;
	}

	public List<ProductComboInfo> initLink2() throws Exception {
		List<ProductComboInfo> productComboInfoList = new ArrayList<ProductComboInfo>();
		// 1.get BspAttributes
		List<BspAttributes> bspAttributes = getAllAttributes();

		String defaultPath = "";
		if (bspAttributes.size() > 0) {
			StringBuilder attrRouteValue = new StringBuilder();
			for (int i = 0; i < bspAttributes.size(); i++) {
				attrRouteValue.append("0-");
			}
			defaultPath = attrRouteValue.deleteCharAt(
					attrRouteValue.length() - 1).toString();
		}

		// 2.get BspAttributevalues
		for (int j = 0; j < bspAttributes.size(); j++) {

			ProductComboInfo pc = new ProductComboInfo();
			BspAttributes attr = bspAttributes.get(j);
			pc.setAttrid(attr.getAttrid());
			pc.setAttrname(attr.getName());
			List<BspAttributevalues> attrList = getAttributesValue(attr
					.getAttrid());
			List<ProductLink> productLinkList = new ArrayList<ProductLink>();

			if (attrList.size() > 0) {
				// 增加一个全选 , 颜色为红色
				ProductLink pl_all = new ProductLink();
				BspAttributevalues pl_attrValue = new BspAttributevalues();
				pl_attrValue.setAttrvalue("不限");
				pl_all.setPath(defaultPath);
				pl_all.setPathcenterStr("1");
				pl_all.setAttrValue(pl_attrValue);
				productLinkList.add(pl_all);

				for (BspAttributevalues attrValue : attrList) {
					ProductLink pl = new ProductLink();
					String newOne = StringHandler.updateByIndex(defaultPath, j,
							attrValue.getAttrvalueid());
					pl.setPath(newOne);
					pl.setAttrValue(attrValue);
					pl.setPathcenterStr("0");
					productLinkList.add(pl);
				}
			}
			pc.setProductlinklist(productLinkList);
			productComboInfoList.add(pc);
		}
		return productComboInfoList;
	}

	// 更新筛选产品链接地址

	public List<ProductComboInfo> generateProductLink(int areaId, String path)
			throws Exception {
		List<ProductComboInfo> productComboInfoList = initLink(path);
		// List<ProductComboInfo> productComboInfoList = initLink2();
		// 3.get BspAttributevalues link
		// 更新产品筛选的链接

		List<BspAttributes> bspAttributes = getAllAttributes();
		if (path.length() == 0) {
			if (bspAttributes.size() > 0) {
				StringBuilder attrRouteValue = new StringBuilder();
				for (int i = 0; i < bspAttributes.size(); i++) {
					attrRouteValue.append("0-");
				}
				path = attrRouteValue.deleteCharAt(attrRouteValue.length() - 1)
						.toString();
			} else {
				path = "0";
			}
		}

		String[] pathList = path.split("-");

		for (int i = 0; i < pathList.length; i++) {
			int integerP = Integer.parseInt(pathList[i]);

			if (integerP > 0) {
				// 更新所有相关path
				for (int j = 0; j < productComboInfoList.size(); j++) {

					ProductComboInfo pc = productComboInfoList.get(j);
					List<ProductLink> list = pc.getProductlinklist();
					// 跳过所在行
					if (j == i)
						continue;
					for (int k = 0; k < list.size(); k++) {
						ProductLink pl = list.get(k);
						String newOne = StringHandler.updateByIndex(
								pl.getPath(), i, integerP);
						pl.setPath(newOne);

					}
				}
			}
		}

		// areaId = 0，直接返回所有链接，不作地区筛选
		if (areaId == 0)
			return productComboInfoList;

		// 根据收货地址，过滤品牌和生产厂家
		for (ProductComboInfo cell : productComboInfoList) {
			// 区域品牌对应关系
			if (cell.getAttrid() == Constant.brandId) {
				List<BspAttributevalues> brandList = this
						.getBrandListByAreaId(areaId);
				List<ProductLink> oldList = cell.getProductlinklist();
				List<ProductLink> newList = new ArrayList<ProductLink>();
				for (ProductLink p : oldList)
					if (p.getAttrValue().getAttrvalueid() == null) {
						// 添加 "不限"品牌
						newList.add(p);
						break;
					}

				for (BspAttributevalues b : brandList)
					for (ProductLink p : oldList) {
						// "不限" 跳过，因为已经添加
						if (p.getAttrValue().getAttrvalueid() == null)
							continue;
						if (p.getAttrValue().getAttrvalueid()
								.equals(b.getAttrvalueid())) {

							newList.add(p);
							break;
						}
					}
				cell.setProductlinklist(newList);
			}
			// 区域产地对应关系
			if (cell.getAttrid() == Constant.factoryId) {
				List<BspAttributevalues> factoryList = this
						.getFactoryListByAreaId(areaId);
				List<ProductLink> oldList = cell.getProductlinklist();
				List<ProductLink> newList = new ArrayList<ProductLink>();
				for (ProductLink p : oldList)
					if (p.getAttrValue().getAttrvalueid() == null) {
						// 添加 "不限"产地
						newList.add(p);
						break;
					}
				for (BspAttributevalues b : factoryList)
					for (ProductLink p : oldList) {
						// 不限
						if (p.getAttrValue().getAttrvalueid() == null)
							continue;
						if (p.getAttrValue().getAttrvalueid()
								.equals(b.getAttrvalueid())) {
							newList.add(p);
							break;
						}
					}
				cell.setProductlinklist(newList);
			}
		}

		// 根据收货地址，关联品牌和生产厂家
		List<ProductLink> allFactory = null;
		for (ProductComboInfo cell : productComboInfoList) {
			if (cell.getAttrid() == Constant.factoryId) {
				allFactory = cell.getProductlinklist();
			}

		}
		for (ProductComboInfo cell : productComboInfoList) {
			if (cell.getAttrid() == Constant.brandId) {
				List<ProductLink> plist = cell.getProductlinklist();

				for (ProductLink p : plist) {
					Map<Integer, List<ProductLink>> brand_factory = new HashMap<Integer, List<ProductLink>>();
					// "不限": 添加所有的产地
					if (p.getAttrValue().getAttrvalueid() == null) {
						brand_factory.put(null, allFactory);
						p.setBrand_factory(brand_factory);
						continue;
					}
					// 获取单个品牌的区域产地
					List<BspAttributevalues> brandfactorylist = this
							.getFactoryListByBrand(areaId, p.getAttrValue()
									.getAttrvalueid());

					List<ProductLink> bflist = new ArrayList<ProductLink>();
					for (ProductLink pp : allFactory) {
						if (pp.getAttrValue().getAttrvalueid() == null) {
							bflist.add(pp);
							continue;
						}
						for (BspAttributevalues bb : brandfactorylist)
							if (pp.getAttrValue().getAttrvalueid()
									.equals(bb.getAttrvalueid())) {
								bflist.add(pp);
							}
					}
					brand_factory
							.put(p.getAttrValue().getAttrvalueid(), bflist);
					p.setBrand_factory(brand_factory);
				}
				break;
			}

		}

		return productComboInfoList;

	}// end of updateLink

	// 生成产品的sku信息
	public List<SkuInfo> genarateSku(Integer areaId, Integer pid,
			boolean isPreview) throws Exception {

		List<BspProductskusRTM> list = getProductSku(pid, isPreview);
		List<BspProductskusRTM> attridList = new ArrayList<BspProductskusRTM>();

		// 1.get attrid list
		for (int i = 0; i < list.size(); i++) {
			BspProductskusRTM sku = list.get(i);
			if (sku.getPid().equals(pid))
				attridList.add(sku);
		}

		HashMap<Integer, List<BspProductskusRTM>> pidSkuMap = new HashMap<Integer, List<BspProductskusRTM>>();

		// GroupBy pid
		// 1.初始化pidSkuMap
		Set<Integer> pidSet = new HashSet<Integer>();
		for (int i = 0; i < list.size(); i++) {
			pidSet.add(list.get(i).getPid());
		}
		Iterator<Integer> it = pidSet.iterator();
		while (it.hasNext()) {
			Integer i = it.next();
			List<BspProductskusRTM> alist = new ArrayList<BspProductskusRTM>();
			pidSkuMap.put(i, alist);
		}

		// 2.pidSkuMap赋值
		for (int j = 0; j < list.size(); j++) {
			BspProductskusRTM cell = list.get(j);
			if (pidSkuMap.get(cell.getPid()) != null)
				pidSkuMap.get(cell.getPid()).add(cell);
		}

		// 3.初始化skuList
		List<SkuInfo> skuList = new ArrayList<SkuInfo>();
		for (int i = 0; i < attridList.size(); i++) {
			BspProductskusRTM sku = attridList.get(i);
			SkuInfo skuinfo = new SkuInfo();
			skuinfo.setAttrid(sku.getAttrid());
			skuinfo.setAttrname(sku.getAttrname());
			List<BspProductskusRTM> groupList = new ArrayList<BspProductskusRTM>();
			// groupList.add(sku);
			skuinfo.setSkuList(groupList);
			skuList.add(skuinfo);
		}

		// 4.skuList赋值
		for (int j = 0; j < list.size(); j++) {
			BspProductskusRTM groupSku = list.get(j);
			for (int i = 0; i < attridList.size(); i++) {
				List<BspProductskusRTM> groupList = skuList.get(i).getSkuList();

				if (skuList.get(i).getAttrid().equals(groupSku.getAttrid())
						&& isValid(groupSku, list, attridList, pidSkuMap)) {
					groupList.add(groupSku);
					break;
				}
			}
		}

		// m.put("skuList", skuList);
		// 排序 attrid降序
		Collections.sort(skuList);
		SkuInfo brandSku = null;
		for (SkuInfo sku : skuList) {
			// 排序 pid升序
			Collections.sort(sku.getSkuList());
			// 品牌 产地对应关系
			if (sku.getAttrid() == Constant.brandId) {
				Map<Integer, List<BspProductskusRTM>> brand_factory = sku
						.getBrand_factory();
				if (brand_factory == null) {
					brand_factory = new HashMap<Integer, List<BspProductskusRTM>>();
					for (BspProductskusRTM cell : sku.getSkuList())
						brand_factory.put(cell.getAttrvalueid(),
								new ArrayList<BspProductskusRTM>());
					// 品牌置空
					// sku.setSkuList(null);

				}
				sku.setBrand_factory(brand_factory);
				brandSku = sku;
			} else if (sku.getAttrid() == Constant.factoryId) {
				Map<Integer, List<BspProductskusRTM>> brand_factory = null;
				if (brandSku == null)
					brand_factory = new HashMap<Integer, List<BspProductskusRTM>>();
				else
					brand_factory = brandSku.getBrand_factory();
				if (brand_factory == null)
					brand_factory = new HashMap<Integer, List<BspProductskusRTM>>();
				// 筛选
				Set<Integer> keySet = brand_factory.keySet();
				Iterator<Integer> it_brand_factory = keySet.iterator();
				while (it_brand_factory.hasNext()) {
					Integer key = it_brand_factory.next();
					// 获取区域品牌
					int prid = regionsProductService.getProductsRegionsId(-1,
							areaId);
					int prBrandId = regionsBrandService
							.getProductsRegionsBrandId(key, prid);
					if (prBrandId < 0) {
						// 该区域未设置此品牌，因此去除
						it_brand_factory.remove();
						brand_factory.remove(key);
						try {
							Iterator<BspProductskusRTM> iterator = brandSku
									.getSkuList().iterator();
							while (iterator.hasNext()) {
								BspProductskusRTM p = iterator.next();
								if (p.getAttrvalueid().equals(key))
									iterator.remove();
							}
						} catch (Exception e) {
							// java.lang.IllegalStateException
							e.printStackTrace();
						}

						continue;
					}
					List<BspProductskusRTM> factoryList = new ArrayList<BspProductskusRTM>();
					// 获取区域产地
					List<BspProductsregionsbrandplace> placeList = regionsBrandService
							.getRegionsPlaceList(key, prid);
					// 筛选区域和品牌的关系
					for (BspProductsregionsbrandplace a : placeList) {
						Integer placeId = a.getPlaceid();
						for (BspProductskusRTM b : sku.getSkuList()) {
							if (b.getAttrvalueid().equals(placeId))
								factoryList.add(b);
						}
						brand_factory.put(key, factoryList);
					}

				}
			}

		}
		return skuList;

	}

	// 判断sku的元素是否有效，有效则在页面显示
	public boolean isValid(BspProductskusRTM sku, List<BspProductskusRTM> list,
			List<BspProductskusRTM> attridList,
			HashMap<Integer, List<BspProductskusRTM>> pidSkuMap) {
		// if (sku.getPid().equals(attridList.get(0).getPid()))
		// return false;
		// 取sku的相同pid的元素
		List<BspProductskusRTM> skupidList = pidSkuMap.get(sku.getPid());
		Boolean[] flags = new Boolean[attridList.size()];
		for (int j = 0; j < flags.length; j++)
			flags[j] = false;
		for (int i = 0; i < skupidList.size(); i++) {
			BspProductskusRTM cell = skupidList.get(i);
			// 本身不作判断
			if (cell.getRecordid() == sku.getRecordid()) {
				flags[i] = true;
				continue;
			}
			// 对于相同pid的其他元素，需要和attridList的一致
			for (int j = 0; j < attridList.size(); j++) {
				BspProductskusRTM innercell = attridList.get(j);
				if (cell.getAttrid().equals(innercell.getAttrid())
						&& cell.getAttrvalueid().equals(
								innercell.getAttrvalueid())
						&& !innercell.getPid().equals(sku.getAttrvalueid())) {
					flags[i] = true;
					break;
				}
			}
		}

		for (Boolean flag : flags)
			if (!flag)
				return false;
		return true;
	}

	/**
	 * 更新筛选产品链接地址，以后备用
	 * */
	public List<ProductComboInfo> updateLinkzw(String path) throws Exception {

		List<ProductComboInfo> productComboInfoList = new ArrayList<ProductComboInfo>();
		// 1.get BspAttributes
		List<BspAttributes> bspAttributes = getAllAttributes();

		StringBuilder s = new StringBuilder();
		for (int j = 0; j < bspAttributes.size(); j++)
			s.append("0-");
		s.deleteCharAt(bspAttributes.size() * 2 - 1);
		String defaultPath = s.toString();

		// 2.get BspAttributevalues
		for (int j = 0; j < bspAttributes.size(); j++) {
			ProductComboInfo pc = new ProductComboInfo();
			BspAttributes attr = bspAttributes.get(j);
			pc.setAttrid(attr.getAttrid());
			pc.setAttrname(attr.getName());
			List<BspAttributevalues> attrList = getAttributesValue(attr
					.getAttrid());
			List<ProductLink> productLinkList = new ArrayList<ProductLink>();
			for (BspAttributevalues attrValue : attrList) {
				ProductLink pl = new ProductLink();
				pl.setAttrValue(attrValue);
				// 链接处理
				//

				String newPath = StringHandler.updateByIndex(defaultPath, j,
						attrValue.getAttrvalueid());

				//
				pl.setPath(newPath);
				productLinkList.add(pl);
			}
			pc.setProductlinklist(productLinkList);
			productComboInfoList.add(pc);
		}

		// 3.get BspAttributevalues link
		// 更新产品筛选的链接
		String[] pathList = path.split("-");
		for (int i = 0; i < pathList.length; i++) {
			int integerP = Integer.parseInt(pathList[i]);
			if (integerP > 0) {
				// 更新所有相关path
				for (int j = 0; j < productComboInfoList.size(); j++) {

					ProductComboInfo pc = productComboInfoList.get(j);
					List<ProductLink> list = pc.getProductlinklist();
					for (int k = 0; k < list.size(); k++) {

						String newOne = StringHandler.updateByIndex(list.get(k)
								.getPath(), i, integerP);
						list.get(k).setPath(newOne);
					}
				}
			}
		}

		return productComboInfoList;

	}// end of updateLink

	public int countSkugname(String name) {
		BspSkugroupExample bse = new BspSkugroupExample();
		bse.createCriteria().andSkugnameEqualTo(name);
		return bspSkugroupMapper.countByExample(bse);
	}

	/**
	 * 根据前台传来的sku及产品信息生成并存储新的产品
	 * 
	 * @param psVO
	 * @return
	 */
	public boolean saveSrcProductSkuVO(ProductSkuVO psVO, String[] skuNames) {

		BspProducts product = psVO.getProduct();
		BspSkugroup skuGroup = psVO.getSkuGroup();
		skuGroup.setCateid(Integer.valueOf(product.getCateid()));
		product.setAddtime(new Date());
		product.setName(skuGroup.getSkugname());

		bspSkugroupMapper.insertSelective(skuGroup);
		int skugid = skuGroup.getSkugid();// genSkuGid();

		product.setSkugid(skugid);

		List<BspProductskus> skuList = psVO.getSkuList();
		// 处理SKUList
		Map<Integer, List> pidSLMap = new HashMap();
		// 首先将产品sku关联信息按照产品虚拟id分组
		for (int li = 0; li < skuList.size(); li++) {
			BspProductskus psku = skuList.get(li);
			psku.setSkugid(skugid);
			Integer pid = psku.getPid();
			List tpskList = pidSLMap.get(pid);
			if (tpskList == null) {
				tpskList = new ArrayList();
				pidSLMap.put(pid, tpskList);
			}
			tpskList.add(psku);
		}
		// 存储分组后的产品及其相关联的sku属性信息列表
		List<ProductSkuVO> saveList = new ArrayList();
		Iterator ite = pidSLMap.keySet().iterator();
		if (pidSLMap.keySet().size() != skuNames.length) {
			return false;
		}
		int sni = 0;
		while (ite.hasNext()) {
			Integer tpid = (Integer) ite.next();
			BspProducts newPro = cloneProduct(product);
			newPro.setName(/* newPro.getName() + " " + */skuNames[sni]);
			sni++;
			List tpskList = pidSLMap.get(tpid);

			ProductSkuVO newPskVO = new ProductSkuVO();
			newPskVO.setProduct(newPro);
			newPskVO.setSkuList(tpskList);

			saveList.add(newPskVO);
		}

		return this.savaProductSkuList(saveList);

	}

	/**
	 * 根据前台传来的sku及产品信息重新生成并存储新的产品
	 * 
	 * @param psVO
	 * @return
	 */
	public boolean upSrcProductSkuVO(ProductSkuVO psVO, String[] skuNames) {

		BspProducts product = psVO.getProduct();
		BspSkugroup skuGroup = psVO.getSkuGroup();
		skuGroup.setCateid(Integer.valueOf(product.getCateid()));
		product.setAddtime(new Date());
		product.setName(skuGroup.getSkugname());

		// bspSkugroupMapper.insertSelective(skuGroup);
		int skugid = skuGroup.getSkugid();// genSkuGid();
		product.setSkugid(skugid);

		bspSkugroupMapper.updateByPrimaryKeySelective(skuGroup);

		// 首先删除所有的关联了skugid的产品
		BspProductsExample bpe = new BspProductsExample();
		bpe.createCriteria().andSkugidEqualTo(skugid);
		List<BspProducts> oldproList = mapper.selectByExample(bpe);
		for (int oi = 0; oi < oldproList.size(); oi++) {
			int opid = oldproList.get(oi).getPid();
			this.delProductByPid(opid);
		}

		// 走正常的插入新产品的程序,但是优先使用刚刚删除的产品的id
		List<BspProductskus> skuList = psVO.getSkuList();
		// 处理SKUList
		Map<Integer, List> pidSLMap = new HashMap();
		// 首先将产品sku关联信息按照产品虚拟id分组
		for (int li = 0; li < skuList.size(); li++) {
			BspProductskus psku = skuList.get(li);
			psku.setSkugid(skugid);
			Integer pid = psku.getPid();
			List tpskList = pidSLMap.get(pid);
			if (tpskList == null) {
				tpskList = new ArrayList();
				pidSLMap.put(pid, tpskList);
			}
			tpskList.add(psku);
		}
		// 存储分组后的产品及其相关联的sku属性信息列表
		List<ProductSkuVO> saveList = new ArrayList();
		Iterator ite = pidSLMap.keySet().iterator();
		if (pidSLMap.keySet().size() != skuNames.length) {
			return false;
		}
		int sni = 0;
		while (ite.hasNext()) {
			Integer tpid = (Integer) ite.next();
			BspProducts newPro = cloneProduct(product);
			newPro.setName("");
			newPro.setPid(null);
			// 优先使用以前产品的id
			if (sni < oldproList.size()) {
				int oid = oldproList.get(sni).getPid();
				newPro.setPid(oid);
			}
			newPro.setName(newPro.getName() + "" + skuNames[sni]);
			sni++;
			List tpskList = pidSLMap.get(tpid);

			ProductSkuVO newPskVO = new ProductSkuVO();
			newPskVO.setProduct(newPro);
			newPskVO.setSkuList(tpskList);

			saveList.add(newPskVO);
		}

		return this.savaProductSkuList(saveList);

	}

	/**
	 * 存储加工好的产品sku列表
	 * 
	 * @param saveList
	 * @return
	 */
	public boolean savaProductSkuList(List<ProductSkuVO> saveList) {

		for (int i = 0; i < saveList.size(); i++) {
			ProductSkuVO psvo = saveList.get(i);
			boolean rs = this.saveProductSku(psvo);
			if (rs == false) {

			}
		}
		return true;
	}

	/**
	 * 保存加工好的产品sku
	 * 
	 * @param skuVo
	 * @return
	 */
	public boolean saveProductSku(ProductSkuVO skuVo) {
		BspProducts product = skuVo.getProduct();
		int rsi = mapper.insertSelective(product);
		if (rsi > 0) {
			int pid = product.getPid();
			List<BspProductskus> skuList = skuVo.getSkuList();
			for (int si = 0; si < skuList.size(); si++) {
				BspProductskus skuItem = skuList.get(si);
				skuItem.setPid(pid);

				bspProductskusMapper.insertSelective(skuItem);

				BspProductattributes proAttr = this
						.cloneProAttrBySkuItem(skuItem);

				bspProductattributesMapper.insertSelective(proAttr);

			}
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param pavo
	 * @return
	 */
	public boolean saveProductWithAttr(ProductAttrVO pavo) {
		BspProducts product = pavo.getProduct();
		product.setAddtime(new Date());
		product.setSkugid(0);
		int rsi = mapper.insertSelective(product);
		if (rsi > 0) {
			int pid = product.getPid();

			BspProductstocks productstocks = pavo.getProductstocks();
			productstocks.setPid(pid);
			if (productstocks.getLimit() == null) {
				productstocks.setLimit(0);
			}
			if (productstocks.getNumber() == null) {
				productstocks.setNumber(0);
			}
			bspProductstocksMapper.insert(productstocks);

			List<BspProductattributes> attrList = pavo.getSkuList();
			for (int si = 0; si < attrList.size(); si++) {
				BspProductattributes attrItem = attrList.get(si);
				attrItem.setPid(pid);

				bspProductattributesMapper.insertSelective(attrItem);

			}

			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param pavo
	 * @return
	 */
	public boolean upProductWithAttr(ProductAttrVO pavo) {
		BspProducts product = pavo.getProduct();
		product.setAddtime(new Date());
		if (product.getIsbest() == null) {
			product.setIsbest((byte) 0);
		}
		if (product.getIshot() == null) {
			product.setIshot((byte) 0);
		}
		if (product.getIsnew() == null) {
			product.setIsnew((byte) 0);
		}
		// product.setSkugid(0);
		int rsi = mapper.updateByPrimaryKeySelective(product);
		if (rsi > 0) {
			int pid = product.getPid();

			BspProductstocksExample bpse = new BspProductstocksExample();
			bpse.createCriteria().andPidEqualTo(pid);
			bspProductstocksMapper.deleteByExample(bpse);

			BspProductstocks productstocks = pavo.getProductstocks();
			productstocks.setPid(pid);
			if (productstocks.getLimit() == null) {
				productstocks.setLimit(0);
			}
			if (productstocks.getNumber() == null) {
				productstocks.setNumber(0);
			}
			bspProductstocksMapper.insert(productstocks);

			// BspProductattributesExample bpbe = new
			// BspProductattributesExample();
			// bpbe.createCriteria().andPidEqualTo(pid);
			// bspProductattributesMapper.deleteByExample(bpbe);
			//
			// List<BspProductattributes> attrList = pavo.getSkuList();
			// for (int si = 0; si < attrList.size(); si++) {
			// BspProductattributes attrItem = attrList.get(si);
			// attrItem.setPid(pid);
			// bspProductattributesMapper.insertSelective(attrItem);
			// }
			return true;
		} else {
			return false;
		}

	}

	private BspProductattributes cloneProAttrBySkuItem(BspProductskus skuItem) {
		BspProductattributes proAttr = new BspProductattributes();
		proAttr.setAttrid(skuItem.getAttrid());
		proAttr.setAttrvalueid(skuItem.getAttrvalueid());
		proAttr.setInputvalue(skuItem.getInputvalue());
		proAttr.setPid(skuItem.getPid());
		return proAttr;
	}

	private BspProducts cloneProduct(BspProducts product) {
		BspProducts tpro = new BspProducts();

		tpro.setAddtime(product.getAddtime());
		tpro.setBrandid(product.getBrandid());
		tpro.setCateid(product.getCateid());
		tpro.setCostprice(product.getCostprice());
		tpro.setDescription(product.getDescription());
		tpro.setDisplayorder(product.getDisplayorder());
		tpro.setIsbest(product.getIsbest());
		tpro.setIshot(product.getIshot());
		tpro.setIsnew(product.getIsnew());
		tpro.setMarketprice(product.getMarketprice());
		tpro.setName(product.getName());
		tpro.setPid(product.getPid());
		tpro.setPsn(product.getPsn());
		tpro.setReviewcount(product.getReviewcount());
		tpro.setSalecount(product.getSalecount());
		tpro.setShopprice(product.getShopprice());
		tpro.setShowimg(product.getShowimg());
		tpro.setSkugid(product.getSkugid());
		tpro.setStar1(product.getStar1());
		tpro.setStar2(product.getStar2());
		tpro.setStar3(product.getStar3());
		tpro.setStar4(product.getStar4());
		tpro.setStar5(product.getStar5());

		tpro.setState(product.getState());
		tpro.setVisitcount(product.getVisitcount());
		tpro.setWeight(product.getWeight());

		tpro.setWeightunitid(product.getWeightunitid());
		tpro.setQuantityunitid(product.getQuantityunitid());

		return tpro;
	}

	private int genSkuGid() {
		Random random = new Random();

		int gid = random.nextInt();
		// 此处应该查询数据库查看是否已经生成过该值
		if (gid < 0) {
			gid = 0 - gid;
		}

		BspProductsExample bpe = new BspProductsExample();
		bpe.createCriteria().andSkugidEqualTo(gid);
		int count = mapper.countByExample(bpe);
		if (count > 0) {
			gid = genSkuGid();
		}

		return gid;
	}

	public List<BspProducts> selOnProductList(String keywords, int pageNumber,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		BspProductsExample e = new BspProductsExample();

		e.createCriteria().andNameLike("%" + keywords + "%")
				.andStateEqualTo((byte) 0);

		e.setOrderByClause(" pid desc");

		List<BspProducts> list = mapper.selectByExample(e);
		return list;
	}

	public int selOnProductListCount(String keywords) {
		BspProductsExample e = new BspProductsExample();
		e.createCriteria().andNameLike("%" + keywords + "%")
				.andStateEqualTo((byte) 0);

		return mapper.countByExample(e);

	}

	public List<BspSkugroup> selSkugroupList(String keywords, int pageNumber,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		BspSkugroupExample e = new BspSkugroupExample();
		e.or().andSkugnameLike("%" + keywords + "%");
		e.setOrderByClause(" skugid desc");

		List<BspSkugroup> list = bspSkugroupMapper.selectByExample(e);
		return list;
	}

	public int selSkugroupListCount(String keywords) throws Exception {
		BspSkugroupExample e = new BspSkugroupExample();
		e.createCriteria().andSkugnameLike("%" + keywords + "%");
		e.setOrderByClause(" skugid desc");

		return bspSkugroupMapper.countByExample(e);
	}

	public List<BspProducts> selDownProductList(String keywords,
			int pageNumber, int pageSize) throws Exception {
		PageHelper.startPage(pageNumber, pageSize);
		BspProductsExample e = new BspProductsExample();

		e.createCriteria().andNameLike("%" + keywords + "%")
				.andStateEqualTo((byte) 1);
		e.setOrderByClause(" pid desc");

		List<BspProducts> list = mapper.selectByExample(e);
		return list;
	}

	public int selDownProductListCount(String keywords) {
		BspProductsExample e = new BspProductsExample();
		e.createCriteria().andNameLike("%" + keywords + "%")
				.andStateEqualTo((byte) 1);
		return mapper.countByExample(e);

	}

	public List<BspProducts> selProductsBySkugid(Integer skugId) {
		BspProductsExample bpe = new BspProductsExample();
		bpe.createCriteria().andSkugidEqualTo(skugId);
		return mapper.selectByExample(bpe);
	}

	public boolean delSkugroupById(Integer skugId) {
		List<BspProducts> proList = this.selProductsBySkugid(skugId);
		for (int i = 0; i < proList.size(); i++) {
			int pid = proList.get(i).getPid();
			this.delProductByPid(pid);
		}
		bspSkugroupMapper.deleteByPrimaryKey(skugId);
		return true;

	}

	public boolean delProductByPid(int pid) {
		// 删除相关的图片
		// TODO: 临时注释，保留功能 2015年8月7日
		/*
		 * BspProductimagesExample bpie = new BspProductimagesExample();
		 * bpie.createCriteria().andPidEqualTo(pid);
		 * bspProductimagesMapper.deleteByExample(bpie);
		 */
		// 删除SKU
		BspProductskusExample bpke = new BspProductskusExample();
		bpke.createCriteria().andPidEqualTo(pid);
		bspProductskusMapper.deleteByExample(bpke);
		// 删除属性
		BspProductattributesExample bpae = new BspProductattributesExample();
		bpae.createCriteria().andPidEqualTo(pid);
		bspProductattributesMapper.deleteByExample(bpae);
		// 库存
		BspProductstocksExample bpse = new BspProductstocksExample();
		bpse.createCriteria().andPidEqualTo(pid);
		bspProductstocksMapper.deleteByExample(bpse);
		// 删除区域费用等信息
		bspProductsregionspriceMapper.deleteByRefRegionsPid(pid);
		// 删除区域信息
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andProductidEqualTo(pid);
		bspProductsregionsMapper.deleteByExample(bpre);

		// 删除物料本身
		int rs = mapper.deleteByPrimaryKey(pid);
		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}

	public BspProducts selProductByPid(int pid) {
		return mapper.selectByPrimaryKey(pid);
	}

	public List<BspProductattributes> selBspProductattributesByPId(int pid) {
		BspProductattributesExample e = new BspProductattributesExample();
		e.createCriteria().andPidEqualTo(pid);
		return bspProductattributesMapper.selectByExample(e);
	}

	public List<SkuShowVO> getSkuShowVOList(int pid) {
		BspProductskusExample bpe = new BspProductskusExample();
		bpe.createCriteria().andPidEqualTo(pid);
		List<BspProductskus> pskuList = bspProductskusMapper
				.selectByExample(bpe);
		List<Integer> avidList = new ArrayList();
		for (int i = 0; i < pskuList.size(); i++) {
			BspProductskus psku = pskuList.get(i);
			avidList.add(psku.getAttrvalueid());
		}

		BspAttributevaluesExample bae = new BspAttributevaluesExample();
		if (avidList.size() > 0)
			bae.createCriteria().andAttrvalueidIn(avidList);
		List<BspAttributevalues> bavList = bspAttributevaluesMapper
				.selectByExample(bae);

		List<SkuShowVO> skuShowList = new ArrayList();

		for (int bi = 0; bi < bavList.size(); bi++) {
			SkuShowVO skuShowVO = new SkuShowVO();
			BspAttributevalues bav = bavList.get(bi);
			skuShowVO.setAttrid(bav.getAttrid());
			skuShowVO.setAttrName(bav.getAttrname());
			skuShowVO.setAttrVal(bav.getAttrvalue());
			skuShowVO.setAttrValid(bav.getAttrvalueid());
			Byte isInput = bav.getIsinput();
			skuShowVO.setIsInput(bav.getIsinput());
			if (isInput == 1) {
				BspProductskus bps = this.getBspProductskusByAVId(pskuList,
						bav.getAttrvalueid());
				if (bps != null)
					skuShowVO.setInputVal(bps.getInputvalue());
			}
			skuShowList.add(skuShowVO);
		}
		return skuShowList;
	}

	private BspProductskus getBspProductskusByAVId(
			List<BspProductskus> pskuList, int avid) {
		for (int i = 0; i < pskuList.size(); i++) {
			BspProductskus bps = pskuList.get(i);
			if (bps.getAttrvalueid() == avid) {
				return bps;
			}
		}
		return null;
	}

	// 根据收货地址获取sku产地id列表
	public List<BspProductskusRTM> getFactorySkuByBrand(int areaId, int brandId)
			throws Exception {
		return bspProductskusMapper.getFactorySkuByBrand(areaId, brandId);

	}

	// 根据收货地址id获取品牌列表
	public List<BspAttributevalues> getBrandListByAreaId(int areaId)
			throws Exception {
		List<BspAttributevalues> list = bspAttributevaluesMapper
				.getBrandListByAreaId(areaId);
		// if (list == null || list.size() == 0) {
		// for (Integer regId : this.getParentRegionList(areaId)) {
		// list = bspAttributevaluesMapper.getBrandListByAreaId(regId);
		// if (list == null || list.size() == 0)
		// continue;
		// else
		// return list;
		// }
		//
		// }
		return list;

	}

	// 根据收货地址id获取产地列表
	public List<BspAttributevalues> getFactoryListByAreaId(int id)
			throws Exception {
		// 先取品牌，然后根据品牌所在区域id取产地
		List<BspAttributevalues> brandlist = bspAttributevaluesMapper
				.getBrandListByAreaId(id);
		List<BspAttributevalues> list = bspAttributevaluesMapper
				.getFactoryListByAreaId(id);
		// if (brandlist == null || brandlist.size() == 0) {
		// for (Integer regId : this.getParentRegionList(id)) {
		// brandlist = bspAttributevaluesMapper
		// .getBrandListByAreaId(regId);
		// if (brandlist == null || brandlist.size() == 0)
		// continue;
		// else {
		// list = bspAttributevaluesMapper
		// .getFactoryListByAreaId(regId);
		// return list;
		//
		// }
		// }
		//
		// }
		return list;

	}

	// 根据收货地址id, 品牌id获取产地列表
	public List<BspAttributevalues> getFactoryListByBrand(int RegionsId,
			int brandId) throws Exception {
		// 先取品牌，然后根据品牌所在区域id取产地
		// List<BspAttributevalues> brandlist = bspAttributevaluesMapper
		// .getBrandListByAreaId(RegionsId);
		List<BspAttributevalues> list = bspAttributevaluesMapper
				.getFactoryListByBrand(RegionsId, brandId);
		// if (brandlist == null || brandlist.size() == 0) {
		// for (Integer regId : this.getParentRegionList(RegionsId)) {
		// brandlist = bspAttributevaluesMapper
		// .getBrandListByAreaId(regId);
		// if (brandlist == null || brandlist.size() == 0)
		// continue;
		// else {
		// list = bspAttributevaluesMapper.getFactoryListByBrand(
		// regId, brandId);
		// return list;
		//
		// }
		// }
		//
		// }
		return list;

	}

	public BspProductstocks selProductstocksByPid(int pid) {
		return bspProductstocksMapper.selectByPrimaryKey(pid);
	}

	public boolean changeProductState(Integer id, Integer pid) {
		BspProductsExample bpme = new BspProductsExample();
		bpme.createCriteria().andPidEqualTo(pid);

		BspProducts bpModle = new BspProducts();
		bpModle.setState(id.byteValue());

		mapper.updateByExampleSelective(bpModle, bpme);

		return true;
	}

	// 获取区域品牌对应的产地
	public List<Map<String, Object>> getProductsregionsbrandplace(
			int regionsId, int brandId) {
		List<Map<String, Object>> tmplist = null;
		List<Map<String, Object>> list = bspAreaManagerMapper
				.getProductsregionsbrandplace(regionsId, brandId);
		// if (list == null || list.size() == 0) {
		// for (Integer regId : this.getParentRegionList(regionsId)) {
		// tmplist = bspAreaManagerMapper.getProductsregionsbrandplace(
		// regId, brandId);
		// if (tmplist == null || tmplist.size() == 0)
		// continue;
		// else
		// list.addAll(tmplist);
		// }
		// }
		return list;

	}

	// 获取产品的区域价格
	public List<BspProductsregionsprice> getProductsregionsPrice(int productId,
			int regionsId) {
		List<BspProductsregionsprice> list = bspProductsregionspriceMapper
				.getProductsregionsPrice(productId, regionsId);
		// if (list == null || list.size() == 0) {
		// for (Integer regId : this.getParentRegionList(regionsId)) {
		// list = bspProductsregionspriceMapper.getProductsregionsPrice(
		// productId, regId);
		// if (list == null || list.size() == 0)
		// continue;
		// else
		// return list;
		// }
		//
		// }
		return list;
	}

	// 区域运费
	public List<BspProductsregionsprice> getRegionsTransPrice(int regionsId) {
		List<BspProductsregionsprice> list = bspProductsregionspriceMapper
				.getRegionsTransPrice(regionsId);
		if (list == null || list.size() == 0) {
			for (Integer regId : regionsService.getParentRegionList(regionsId)) {
				list = bspProductsregionspriceMapper
						.getRegionsTransPrice(regId);
				if (list == null || list.size() == 0)
					continue;
				else
					return list;
			}

		}
		return list;
	}

	// 区域装卸费
	public List<BspProductsregionsprice> getRegionsLoadPrice(int regionsId) {
		List<BspProductsregionsprice> list = bspProductsregionspriceMapper
				.getRegionsLoadPrice(regionsId);
		if (list == null || list.size() == 0) {
			for (Integer regId : regionsService.getParentRegionList(regionsId)) {
				list = bspProductsregionspriceMapper.getRegionsLoadPrice(regId);
				if (list == null || list.size() == 0)
					continue;
				else
					return list;
			}

		}
		return list;
	}

	// 获取产品的计量单位
	public BspUnit getUnit(int unitId) {
		return bspUnitMapper.selectByPrimaryKey(unitId);
	}

	public BspSkugroup selSkugroupByKeyid(Integer skugid) {
		return bspSkugroupMapper.selectByPrimaryKey(skugid);
	}

	public List<BspProducts> selSkugProductList(String keywords, String skugid,
			String status, int pageNumber, int pageSize) throws Exception {
		int iskugid = Integer.valueOf(skugid);
		PageHelper.startPage(pageNumber, pageSize);
		BspProductsExample e = new BspProductsExample();

		if (status != null) {
			Byte bst = Byte.valueOf(status);
			e.createCriteria().andNameLike("%" + keywords + "%")
					.andSkugidEqualTo(iskugid).andStateEqualTo(bst);
		} else {
			e.createCriteria().andNameLike("%" + keywords + "%")
					.andSkugidEqualTo(iskugid);
		}
		e.setOrderByClause(" pid desc");

		List<BspProducts> list = mapper.selectByExample(e);
		return list;
	}

	public BspRegions getBspRegions(int regionid) {
		return bspRegionsMapper.selectByPrimaryKey(regionid);
	}

	public int countSkugProductList(String keywords, String skugid,
			String status) throws Exception {
		int iskugid = Integer.valueOf(skugid);
		BspProductsExample e = new BspProductsExample();
		e.createCriteria().andNameLike("%" + keywords + "%")
				.andSkugidEqualTo(iskugid);

		if (status != null) {
			Byte bst = Byte.valueOf(status);
			e.createCriteria().andStateEqualTo(bst);
		}
		e.setOrderByClause(" pid desc");

		int size = mapper.countByExample(e);
		return size;
	}

	public List<BspWorktime> getStoreWorkTime(int storeId, int type) {
		BspWorktimeExample e = new BspWorktimeExample();
		e.createCriteria().andWtpickpointidEqualTo(storeId)
				.andWttypeEqualTo((short) type);
		return bspWorktimeMapper.selectByExample(e);
	}

	/**
	 * 
	 * @param ncPronum
	 * @return
	 */
	public boolean hasNcPronum(String ncPronum, Integer nowpid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ncPronum", ncPronum);
		par.put("nowpid", nowpid);
		int rsi = mapper.countNCPronum(par);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 获取某个经销商关联的门店的允销目录
	 * 
	 * @param salerid
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public List<BspProducts> selectSalerProlist(int salerid, int page,
			int pagesize) {
		int start = page * pagesize;
		Map<String, Object> par = new HashMap();
		par.put("SALERID", salerid);
		par.put("STARTROW", start);
		par.put("ROWNUM", pagesize);
		return mapper.selectSalerProlist(par);
	}

	public List<SalerProInfor> selectSalerProInforlist(int salerid,
			SalerProSelParaVO paravo) {
		Integer page = paravo.getPageNumber();
		Integer pagesize = paravo.getPageSize();
		if (page > 0) {
			page -= 1;
		}
		int start = page * pagesize;
		Map<String, Object> par = new HashMap();
		par.put("SALERID", salerid);
		par.put("STARTROW", start);
		par.put("ROWNUM", pagesize);
		par.put("NCPRONUM", paravo.getNcpronum());
		par.put("PRONAME", paravo.getProname());
		List<SalerProInfor> prolist = cusmapper.selectSalerProlist(par);

		for (int i = 0; i < prolist.size(); i++) {
			SalerProInfor pro = prolist.get(i);
			int pid = pro.getPid();
			Map<String, Object> bpar = new HashMap();
			bpar.put("PID", pid);
			bpar.put("ATTRID", Constant.brandId);
			Map<String, Object> bmap = cusmapper.getProAttrvalAndName(bpar);

			if (bmap != null) {
				Integer brandid = (Integer) bmap.get("attrvalueid");
				String brandname = (String) bmap.get("attrvalue");
				pro.setBrandid(brandid);
				pro.setBranname(brandname);
			}

			Map<String, Object> fpar = new HashMap();
			fpar.put("PID", pid);
			fpar.put("ATTRID", Constant.factoryId);
			Map<String, Object> fmap = cusmapper.getProAttrvalAndName(fpar);
			if (fmap != null) {
				Integer placeid = (Integer) fmap.get("attrvalueid");
				String placename = (String) fmap.get("attrvalue");
				pro.setPlaceid(placeid);
				pro.setPlacename(placename);
			}
		}

		return prolist;

	}

	/**
	 * 允销目录的查询结果的条数
	 * 
	 * @param salerid
	 * @param paravo
	 * @return
	 */
	public int countSalerProInforlist(int salerid, SalerProSelParaVO paravo) {
		Map<String, Object> par = new HashMap();
		par.put("SALERID", salerid);
		par.put("NCPRONUM", paravo.getNcpronum());
		par.put("PRONAME", paravo.getProname());
		return cusmapper.countSalerProlist(par);
	}

	/**
	 * 获取某个经销商关联的门店的允销目录的条数
	 * 
	 * @param salerid
	 * @return
	 */
	public int countSalerProlist(int salerid) {
		Map<String, Object> par = new HashMap();
		par.put("SALERID", salerid);
		return mapper.countSalerProlist(par);
	}

	/**
	 * 获取nc编号或者产品名称为关键子的产品的列表
	 * 
	 * @param selVo
	 * @return
	 */
	public List<BspProducts> getAllProList(SelAllProlistVO selVo) {
		Integer pagenum = selVo.getPageNum();
		Integer pagesize = selVo.getNumPerPage();
		if (pagenum == null || pagenum < 0) {
			pagenum = 0;
		} else {
			pagenum = pagenum - 1;
		}
		if (pagesize == null) {
			pagesize = 20;
		}
		int start = pagenum * pagesize;
		Map<String, Object> par = new HashMap();
		par.put("STARTROW", start);
		par.put("ROWNUM", pagesize);
		par.put("KEYWORDS", selVo.getKeywords());
		return cusmapper.getAllProList(par);
	}

	/**
	 * 获取nc编号或者产品名称为关键子的产品的数量
	 * 
	 * @param selVo
	 * @return
	 */
	public int countAllProList(SelAllProlistVO selVo) {
		Map<String, Object> par = new HashMap();
		par.put("KEYWORDS", selVo.getKeywords());
		return cusmapper.countAllProList(par);
	}

	/**
	 * 获取商店和nc产品信息列表
	 * 
	 * @param selVo
	 * @return
	 */
	public List<StoreAndNcProinfor> getStoreAndNcProinforList(
			SelStoreAndNcProinfor selVo) {
		Integer pagenum = selVo.getPageNum();
		Integer pagesize = selVo.getNumPerPage();
		if (pagenum == null || pagenum < 0) {
			pagenum = 0;
		} else {
			pagenum = pagenum - 1;
		}
		if (pagesize == null) {
			pagesize = 20;
		}
		int start = pagenum * pagesize;
		Map<String, Object> par = new HashMap();
		par.put("STARTROW", start);
		par.put("ROWNUM", pagesize);
		par.put("storeKeyWord", selVo.getStoreKeyWord());
		par.put("facKeyWord", selVo.getFacKeyWord());
		par.put("proKeyWord", selVo.getProKeyWord());
		return cusNcenterinforMapper.selStoreAndNcProinfor(par);
	}

	/**
	 * 获取nc编号或者产品名称为关键子的产品的数量
	 * 
	 * @param selVo
	 * @return
	 */
	public int countStoreAndNcProinforList(SelStoreAndNcProinfor selVo) {
		Map<String, Object> par = new HashMap();
		par.put("storeKeyWord", selVo.getStoreKeyWord());
		par.put("facKeyWord", selVo.getFacKeyWord());
		par.put("proKeyWord", selVo.getProKeyWord());
		return cusNcenterinforMapper.countStoreAndNcProinfor(par);
	}

}
