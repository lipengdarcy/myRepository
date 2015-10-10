package com.runlion.shop.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspOrdersMapper;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.vo.CartSnapVO;
import com.runlion.shop.vo.OrderProductPrice;

/**
 * 产品订单价格
 * 
 * @author hsrj05
 *
 */
@Service
public class OrderProductPriceService {

	@Autowired
	private BspOrdersMapper ordersMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	BspAreaManagerService bspAreaManagerService;

	/**
	 * 计算价格(显示运费装卸费)
	 * 
	 * @param cartSnapList
	 * @param regions
	 */
	public OrderProductPrice caculatePrice(List<CartSnapVO> cartSnapList,
			BspRegions regions) {

		int regionsId = 0;
		if (regions != null) {
			regionsId = regions.getRegionid();
		}
		OrderProductPrice orderProductPrice = new OrderProductPrice(
				cartSnapList, regionsId);

		int totalCount = 0;
		float totalWeight = 0;
		int pid = 0;
		boolean msg = true; // 用于判断是否去市场值
		BigDecimal totalProductPrice = new BigDecimal(0);
		BigDecimal totalOrderPrice = new BigDecimal(0);
		BigDecimal totalShipFee = new BigDecimal(0);
		BigDecimal totalHandlingCost = new BigDecimal(0);
		if (cartSnapList != null && cartSnapList.size() > 0) {
			for (CartSnapVO cartSnap : cartSnapList) {
				BspProductsregions proRegion = null;
				msg = true;
				// 获取产品的ID
				pid = cartSnap.getPid();
				// 优先查找pid和区域id对应的产品区域信息
				proRegion = bspAreaManagerService.getYesProidProregionsWithUp(
						regionsId, pid);
				// 如果区域信息存在则判断运费和装卸费是否使用的默认值，如果是则去查找其该区域的运费、装卸费设置
				if (proRegion != null) {
					if (proRegion.getStarthand().compareTo(BigDecimal.ZERO) == 0) {
						List<BspProductsregions> tproRegions = bspAreaManagerService
								.getNoProidProregionsWithUp(regionsId);
						if (tproRegions.size() > 0) {
							BspProductsregions tproRegion = tproRegions.get(0);
							proRegion.setStarthand(tproRegion.getStarthand());
						}
					}
					if (proRegion.getStartship().compareTo(BigDecimal.ZERO) == 0) {
						List<BspProductsregions> tproRegions = bspAreaManagerService
								.getNoProidProregionsWithUp(regionsId);
						if (tproRegions.size() > 0) {
							BspProductsregions tproRegion = tproRegions.get(0);
							proRegion.setStartship(tproRegion.getStartship());
						}
					}
				}
				//
				if (proRegion == null) {
					List<BspProductsregions> proRegions = bspAreaManagerService
							.getNoProidProregionsWithUp(regionsId);
					if (proRegions.size() > 0) {
						proRegion = proRegions.get(0);
					}
				}

				BigDecimal startHand = BigDecimal.ZERO;
				BigDecimal startShip = BigDecimal.ZERO;
				if (proRegion != null) {
					startHand = proRegion.getStarthand();
					startShip = proRegion.getStartship();
				}

				String areaPrice = null;

				List<BspProductsregionsprice> transPriceList = new ArrayList<BspProductsregionsprice>();
				List<BspProductsregionsprice> loadPriceList = new ArrayList<BspProductsregionsprice>();
				// 获取产品的区域价格（每吨的价格）
				List<BspProductsregionsprice> priceList = bspProductsService
						.getProductsregionsPrice(pid, regionsId);
				if (priceList.size() > 0) {
					try {
						Iterator<BspProductsregionsprice> iterator = priceList
								.iterator();
						while (iterator.hasNext()) {
							BspProductsregionsprice p = iterator.next();
							if (p.getPricetype().equals("2")) {
								transPriceList.add(p);
								iterator.remove();
							} else if (p.getPricetype().equals("3")) {
								loadPriceList.add(p);
								iterator.remove();
							} else if (p.getPricetype().equals("1"))
								areaPrice = p.getPrice().toString();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 如果产品的区域价格为空，取产品的区域价格
				if (areaPrice == null) {
					areaPrice = cartSnap.getMarketprice();
					msg = false;
				}
				if (msg) {
					// 转化为件的价格
					// BigDecimal per = new BigDecimal(0);
					// BigDecimal weight = new BigDecimal(cartSnap.getWeight());
					// weight = weight.divide(new BigDecimal(1000));
					// 直接取重量单位
					BigDecimal per = new BigDecimal(0.000);
					BigDecimal weight = new BigDecimal(cartSnap.getWeight());
					// 每吨价格
					BigDecimal ton = new BigDecimal(areaPrice);
					per = ton.multiply(weight);
					per = per.setScale(2, RoundingMode.HALF_UP);
					areaPrice = per.toString();
					cartSnap.setMarketprice(areaPrice);
				}
				// 计算数量
				totalCount += cartSnap.getCount();
				// 计算重量
				totalWeight += cartSnap.getCount() * cartSnap.getWeight();

				BigDecimal price = new BigDecimal(cartSnap.getMarketprice());
				BigDecimal bcount = new BigDecimal(cartSnap.getCount());

				price = price.multiply(bcount);

				// 将数量转换成重量，并设置到购物车VO
				BigDecimal transWeight = new BigDecimal(cartSnap.getCount());
				transWeight = transWeight.multiply(new BigDecimal(cartSnap
						.getWeight()));
				transWeight = transWeight.setScale(2, RoundingMode.HALF_UP);

				if (transPriceList.size() == 0)
					transPriceList = bspProductsService
							.getRegionsTransPrice(Integer.valueOf(regionsId));
				if (loadPriceList.size() == 0)
					loadPriceList = bspProductsService
							.getRegionsLoadPrice(Integer.valueOf(regionsId));

				Collections.sort(transPriceList);
				Collections.sort(loadPriceList);

				String freight = "0";
				String carry = "0";
				freight = orderService.getCost(cartSnap, transPriceList);
				// 如果没有找到区间运输费用，则使用起步价
				if (freight == "" || freight == null) {
					if (proRegion != null) {
						freight = proRegion.getStartship().toString();
					} else {
						freight = "0";
					}

				} else {
					freight = (new BigDecimal(freight)).multiply(transWeight)
							.setScale(2, RoundingMode.HALF_UP).toString();
					// 运费未达到起步价则使用起步价
					if (startShip.compareTo(new BigDecimal(freight)) > 0) {
						freight = startShip.setScale(2, RoundingMode.HALF_UP)
								.toString();
					}
				}

				// 如果没有找到区间装卸费用，则使用起步价
				carry = orderService.getCost(cartSnap, loadPriceList);
				if (carry == "" || carry == null) {
					// if (proRegion != null) {
					// carry = proRegion.getStarthand().toString();
					// } else {
					// carry = "0";
					// }
					carry = "0";
				} else {
					carry = (new BigDecimal(carry)).multiply(transWeight)
							.setScale(2, RoundingMode.HALF_UP).toString();
					// 装卸费未达到起步价则使用起步价
					// if (startHand.compareTo(new BigDecimal(carry)) > 0) {
					// carry = startHand.setScale(2, RoundingMode.HALF_UP)
					// .toString();
					// }
				}

				BigDecimal shipFee = new BigDecimal(freight);
				BigDecimal handlingCost = new BigDecimal(carry);

				cartSnap.setFreight(shipFee);
				cartSnap.setCarry(handlingCost);
				// 总价钱
				totalProductPrice = totalProductPrice.add(price);
				totalShipFee = totalShipFee.add(shipFee);
				totalHandlingCost = totalHandlingCost.add(handlingCost);
			}

			// 总价钱
			totalOrderPrice = totalProductPrice.add(totalShipFee).add(
					totalHandlingCost);

			orderProductPrice.setCartSnapList(cartSnapList);
			orderProductPrice.setTotalCount(totalCount);
			orderProductPrice.setTotalWeight(totalWeight);
			orderProductPrice.setTotalOrderPrice(totalOrderPrice);
			orderProductPrice.setTotalProductPrice(totalProductPrice);
			orderProductPrice.setShipFee(totalShipFee);
			orderProductPrice.setHandlingCost(totalHandlingCost);

		}

		return orderProductPrice;
	}

	public String getProductPriceByArea(CartSnapVO cartSnap, int regionsId) {
		if (cartSnap != null) {
			// int totalweight = cartSnap.getWeight() * cartSnap.getCount() /
			// 1000;
			// int level = getPriceLevel(totalweight);
			return ordersMapper.getProductPriceByArea(cartSnap.getPid(),
					regionsId);
		}
		return null;

	}

	/**
	 * 获取区域的运费或装卸费
	 * 
	 * @param totalWeight
	 * @param priceType
	 * @param regionsId
	 * @return
	 */
	public BigDecimal getFee(int totalWeight, int priceType, int regionsId) {
		BigDecimal result = new BigDecimal(0);
		int level = getPriceLevel(totalWeight / 1000);
		String fee = ordersMapper.getProductRegionPrice(regionsId, priceType,
				level);
		if (fee != null) {
			result = new BigDecimal(fee);
		}
		return result;
	}

	/**
	 * 获取价格等级，<=1吨 等级为1，1<x=<10吨 等级为2；大于12吨，等级为3
	 * 
	 * @param count
	 * @return
	 */
	public int getPriceLevel(float totalweight) {
		if (totalweight <= 1) {
			return 1;
		} else if (totalweight > 1 && totalweight <= 10) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * 计算价格(不计算运费装卸费)
	 * 
	 * @param cartSnapList
	 * @param regions
	 */
	public OrderProductPrice portionPrice(List<CartSnapVO> cartSnapList,
			BspRegions regions, int pickId) {

		int regionsId = 0;
		if (regions != null) {
			regionsId = regions.getRegionid();
		}

		OrderProductPrice orderProductPrice = new OrderProductPrice(
				cartSnapList, regionsId);

		int totalCount = 0;
		float totalWeight = 0;
		int pid = 0;
		boolean msg = true; // 用于判断是否去市场值
		BigDecimal totalProductPrice = new BigDecimal(0);
		BigDecimal totalOrderPrice = new BigDecimal(0);
		BigDecimal totalShipFee = new BigDecimal(0);
		BigDecimal totalHandlingCost = new BigDecimal(0);
		if (cartSnapList != null && cartSnapList.size() > 0) {
			for (CartSnapVO cartSnap : cartSnapList) {
				BspProductsregions proRegion = null;
				BspProductsregions proExtRegion = null;
				BspProregionextprice extprice = null;
				msg = true;
				// 获取产品的ID
				pid = cartSnap.getPid();
				// 优先查找pid和区域id对应的产品区域信息
				proRegion = bspAreaManagerService.getYesProidProregionsWithUp(
						regionsId, pid);
				// 查找pid和区域id对应的产品的区域信息，但是不向上追溯，用来获取附加产品费用
				proExtRegion = bspAreaManagerService
						.getYesProidProregionsWithNotUp(regionsId, pid);
				// proExtRegion = null;
				if (proExtRegion == null) {
					proExtRegion = bspAreaManagerService
							.getYesProidProregionsWithNotUp(regionsId, -1);
				}
				if (proExtRegion != null) {
					extprice = bspAreaManagerService.getBspProregionextprice(
							proExtRegion.getId(), pickId);
				}

				// 如果区域信息存在则判断运费和装卸费是否使用的默认值，如果是则去查找其该区域的运费、装卸费设置
				if (proRegion != null) {
					if (proRegion.getStarthand().compareTo(BigDecimal.ZERO) == 0) {
						List<BspProductsregions> tproRegions = bspAreaManagerService
								.getNoProidProregionsWithUp(regionsId);
						if (tproRegions.size() > 0) {
							BspProductsregions tproRegion = tproRegions.get(0);
							proRegion.setStarthand(tproRegion.getStarthand());
						}
					}
					if (proRegion.getStartship().compareTo(BigDecimal.ZERO) == 0) {
						List<BspProductsregions> tproRegions = bspAreaManagerService
								.getNoProidProregionsWithUp(regionsId);
						if (tproRegions.size() > 0) {
							BspProductsregions tproRegion = tproRegions.get(0);
							proRegion.setStartship(tproRegion.getStartship());
						}
					}
				}
				//
				if (proRegion == null) {
					List<BspProductsregions> proRegions = bspAreaManagerService
							.getNoProidProregionsWithUp(regionsId);
					if (proRegions.size() > 0) {
						proRegion = proRegions.get(0);
					}
				}

				BigDecimal startHand = BigDecimal.ZERO;
				BigDecimal startShip = BigDecimal.ZERO;
				if (proRegion != null) {
					startHand = proRegion.getStarthand();
					startShip = proRegion.getStartship();
				}

				String areaPrice = null;
				// 区域运费 & 装卸费
				List<BspProductsregionsprice> transPriceList = new ArrayList<BspProductsregionsprice>();
				List<BspProductsregionsprice> loadPriceList = new ArrayList<BspProductsregionsprice>();
				List<BspProductsregionsprice> priceList = bspProductsService
						.getProductsregionsPrice(pid, regionsId);

				if (priceList.size() > 0) {
					try {
						Iterator<BspProductsregionsprice> iterator = priceList
								.iterator();
						while (iterator.hasNext()) {
							BspProductsregionsprice p = iterator.next();
							if (p.getPricetype().equals("2")) {
								transPriceList.add(p);
								iterator.remove();
							} else if (p.getPricetype().equals("3")) {
								loadPriceList.add(p);
								iterator.remove();
							} else if (p.getPricetype().equals("1"))
								areaPrice = p.getPrice().toString();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 如果产品的区域价格为空，取产品的市场价
				// 测试是否存在额外费用,该费用与产品区域关联
				if (extprice != null) {
					BigDecimal numextprice = extprice.getValue();
					BigDecimal bareaPrice = new BigDecimal(areaPrice);
					if (numextprice != null) {
						bareaPrice = bareaPrice.add(numextprice);
						areaPrice = bareaPrice.toString();
						cartSnap.setMarketprice(areaPrice.toString());
					}
				}
				if (areaPrice == null) {
					areaPrice = cartSnap.getMarketprice();
					msg = false;
				} else {
					cartSnap.setOriginalPrice(areaPrice);
					cartSnap.setMarketprice(areaPrice);
				}

				if (cartSnap.getQuantityunitid() == 4) {
					// 直接取重量单位
					BigDecimal per = new BigDecimal(0.000);
					BigDecimal weight = new BigDecimal(cartSnap.getWeight());
					// 每吨价格
					BigDecimal ton = new BigDecimal(areaPrice);
					per = ton.multiply(weight);
					per = per.setScale(2, RoundingMode.HALF_UP);
					areaPrice = per.toString();
					cartSnap.setMarketprice(areaPrice);
				}

				// 计算数量
				totalCount += cartSnap.getCount();
				// 计算重量
				totalWeight += cartSnap.getCount() * cartSnap.getWeight();
				// totalWeight = new BigDecimal(totalWeight).setScale(4,
				// RoundingMode.HALF_UP).floatValue();

				BigDecimal price = new BigDecimal(cartSnap.getMarketprice());

				BigDecimal bcount = new BigDecimal(cartSnap.getCount());

				price = price.multiply(bcount);

				// 将数量转换成重量，并设置到购物车VO
				BigDecimal transWeight = new BigDecimal(cartSnap.getCount());
				transWeight = transWeight.multiply(new BigDecimal(cartSnap
						.getWeight()));
				transWeight = transWeight.setScale(2, RoundingMode.HALF_UP);
				//
				String freight = "0";
				String carry = "0";
				if (pickId == 3) {
					if (transPriceList.size() == 0)
						transPriceList = bspProductsService
								.getRegionsTransPrice(Integer
										.valueOf(regionsId));
					if (loadPriceList.size() == 0)
						loadPriceList = bspProductsService
								.getRegionsLoadPrice(Integer.valueOf(regionsId));

					Collections.sort(transPriceList);
					Collections.sort(loadPriceList);

					freight = orderService.getCost(cartSnap, transPriceList);
					// 如果没有找到区间运输费用
					if (freight == "" || freight == null) {
						if (proRegion != null) {
							freight = proRegion.getStartship().toString();
						} else {
							freight = "0";
						}

					} else {
						freight = (new BigDecimal(freight))
								.multiply(transWeight)
								.setScale(2, RoundingMode.HALF_UP).toString();
						// 运费未达到起步价则使用起步价
						if (startShip.compareTo(new BigDecimal(freight)) > 0) {
							freight = startShip.setScale(2,
									RoundingMode.HALF_UP).toString();
						}
					}

					// 如果没有找到区间装卸费用，则使用起步价
					carry = orderService.getCost(cartSnap, loadPriceList);
					if (carry == "" || carry == null) {
						// if (proRegion != null) {
						// carry = proRegion.getStarthand().toString();
						// } else {
						// carry = "0";
						// }
						carry = "0";
					} else {
						carry = (new BigDecimal(carry)).multiply(transWeight)
								.setScale(2, RoundingMode.HALF_UP).toString();
						// 装卸费未达到起步价则使用起步价
						// if (startHand.compareTo(new BigDecimal(carry)) > 0) {
						// carry = startHand.setScale(2,
						// RoundingMode.HALF_UP).toString();
						// }
					}

				}
				if (freight != null) {
					orderProductPrice.setFreight(freight);
				} else {
					orderProductPrice.setFreight("0");
				}
				if (carry != null) {
					orderProductPrice.setCarry(carry);
				} else {
					orderProductPrice.setCarry("0");
				}
				BigDecimal shipFee = new BigDecimal(freight);
				BigDecimal handlingCost = new BigDecimal(carry);

				cartSnap.setWeight(transWeight.floatValue());
				cartSnap.setFreight(shipFee);
				cartSnap.setCarry(handlingCost);
				BigDecimal itemTotalMny = price.add(shipFee).add(handlingCost);
				cartSnap.setItemTotalMny(itemTotalMny);
				// 总价钱
				totalProductPrice = totalProductPrice.add(price);
				totalShipFee = totalShipFee.add(shipFee);
				totalHandlingCost = totalHandlingCost.add(handlingCost);
			}

			// 总价钱
			totalOrderPrice = totalProductPrice.add(totalShipFee).add(
					totalHandlingCost);

			orderProductPrice.setCartSnapList(cartSnapList);
			orderProductPrice.setTotalCount(totalCount);
			orderProductPrice.setTotalWeight(totalWeight);
			orderProductPrice.setTotalOrderPrice(totalOrderPrice);
			orderProductPrice.setTotalProductPrice(totalProductPrice);
			orderProductPrice.setShipFee(totalShipFee);
			orderProductPrice.setHandlingCost(totalHandlingCost);

		}

		return orderProductPrice;
	}
}
