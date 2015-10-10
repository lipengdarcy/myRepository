package com.runlion.shop.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.CartMapper;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.Cart;
import com.runlion.shop.vo.CartSnapVO;

@Service
public class CartService {
	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BspProductsService bspProductsService;

	@Autowired
	BspAreaManagerService bspAreaManagerService;

	/**
	 * 保存
	 * 
	 * @param cart
	 * @return
	 */
	public int insert(Cart cart) {
		if (cart != null) {
			return cartMapper.insert(cart);
		} else {
			return 0;
		}

	}

	/**
	 * 保存或更新 保证同一个用户id和产品id只有一条记录
	 * 
	 * @param cart
	 * @return
	 */
	public int insertOrUpdate(Cart cart) {
		if (cart != null) {
			Cart oldCart = getCart(cart);
			if (oldCart != null) {
				cart.setId(oldCart.getId());
				return cartMapper.updateAddCount(cart);
			} else {
				return cartMapper.insert(cart);
			}
		} else {
			return 0;
		}
	}

	/**
	 * 更新数量
	 * 
	 * @param cart
	 * @return
	 */
	public int updateCount(Cart cart) {
		if (cart != null) {
			int count = cart.getCount();
			if (count == 0) {
				return cartMapper.deleteCartByUserIdAndPid(cart);
			} else {
				return cartMapper.updateCount(cart);
			}
		}
		return 0;
	}

	/**
	 * 通过用户id和产品id获取购物车
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 */
	public Cart getCart(Cart cart) {
		Cart c = cartMapper.getCart(cart);
		return c;
	}

	/**
	 * 根据用户名获取购物车中产品数量
	 * 
	 * @param userId
	 * @return
	 */
	public int getCartCountByUserId(Integer userId) {
		if (userId != null) {
			Integer result = cartMapper.getCartCountByUserId(userId);
			if (result == null) {
				result = 0;
			}
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * 根据用户id获取购物车快照
	 * 
	 * @param userId
	 * @return
	 */
	public List<CartSnapVO> getCartSnapByUserId(Integer userId) {

		if (userId != null) {
			return cartMapper.getCartSnapByUserId(userId);
		} else {
			return null;
		}
	}

	/**
	 * 根据用户id和产品id删除购物车
	 * 
	 * @param cart
	 * @return
	 */
	public int deleteCartByUserIdAndPid(Cart cart) {
		return cartMapper.deleteCartByUserIdAndPid(cart);
	}

	/**
	 * 根据用户id和产品id删除购物车
	 * 
	 * @param cart
	 * @return
	 */
	public int deleteCartByUserId(int userId) {
		return cartMapper.deleteCartByUserId(userId);
	}

	/**
	 * 购物页面html
	 * 
	 * @param cartSnap
	 * @param url
	 * @return
	 */
	public String createCartHtml(List<CartSnapVO> list, int regionsId,
			String url, List<Integer> pidList,
			Map<Integer, Boolean> overStockMap) {
		StringBuffer result = new StringBuffer();
		StringBuffer productIds = new StringBuffer();
		int totalCount = 0;
		BigDecimal totalPrice = new BigDecimal(0);
		if (list != null && list.size() > 0) {
			StringBuffer sb = new StringBuffer();
			boolean noCheckAll = false;
			boolean include = true;
			String check = "";
			String freight = "";
			String carry = "";
			for (CartSnapVO cartSnap : list) {
				if (pidList != null && !pidList.contains(cartSnap.getPid())) {
					include = false;
					noCheckAll = true;
				} else {
					include = true;
				}
				BspProductsregions proRegion = null;
				BigDecimal startHand = BigDecimal.ZERO;
				BigDecimal startShip = BigDecimal.ZERO;
				// 获取产品的产品ID
				int pid = cartSnap.getPid();
				String areaPrice = null;
				String itemPrice = null;
				// 区域运费 & 装卸费
				List<BspProductsregionsprice> transPriceList = new ArrayList<BspProductsregionsprice>();
				List<BspProductsregionsprice> loadPriceList = new ArrayList<BspProductsregionsprice>();
				// 区域未设置
				if (regionsId == 0) {
					areaPrice = cartSnap.getMarketprice();
				} else {
					// 优先查找pid和区域id对应的产品区域信息
					proRegion = bspAreaManagerService
							.getYesProidProregionsWithUp(regionsId, pid);
					// 如果区域信息存在则判断运费和装卸费是否使用的默认值，如果是则去查找其该区域的运费、装卸费设置
					if (proRegion != null) {
						if (proRegion.getStarthand().compareTo(BigDecimal.ZERO) == 0) {
							List<BspProductsregions> tproRegions = bspAreaManagerService
									.getNoProidProregionsWithUp(regionsId);
							if (tproRegions.size() > 0) {
								BspProductsregions tproRegion = tproRegions
										.get(0);
								proRegion.setStarthand(tproRegion
										.getStarthand());
							}
						}
						if (proRegion.getStartship().compareTo(BigDecimal.ZERO) == 0) {
							List<BspProductsregions> tproRegions = bspAreaManagerService
									.getNoProidProregionsWithUp(regionsId);
							if (tproRegions.size() > 0) {
								BspProductsregions tproRegion = tproRegions
										.get(0);
								proRegion.setStartship(tproRegion
										.getStartship());
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

					if (proRegion != null) {
						startHand = proRegion.getStarthand();
						startShip = proRegion.getStartship();
					}

					// 获取产品的产品区域价格(每吨)
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
					}
				}
				// 将数量转换成重量
				BigDecimal transWeight = new BigDecimal(cartSnap.getCount());
				transWeight = transWeight.multiply(new BigDecimal(cartSnap
						.getWeight()));
				if (cartSnap.getQuantityunitid() == 4) {
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
					itemPrice = ton.toString();
				}
				if (transPriceList.size() == 0) {
					transPriceList = bspProductsService
							.getRegionsTransPrice(Integer.valueOf(regionsId));
				}
				if (loadPriceList.size() == 0) {
					loadPriceList = bspProductsService
							.getRegionsLoadPrice(Integer.valueOf(regionsId));
				}

				Collections.sort(transPriceList);
				Collections.sort(loadPriceList);
				// 运费计算
				freight = orderService.getCost(cartSnap, transPriceList);
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
				// 装卸费计算
				carry = orderService.getCost(cartSnap, loadPriceList);
				if (carry == "" || carry == null) {
					// 起步运费
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

				// 选中的商品才计算价钱
				BigDecimal itemTotalMny = new BigDecimal(areaPrice);
				BigDecimal itemcount = new BigDecimal(cartSnap.getCount());
				itemTotalMny = itemTotalMny.multiply(itemcount);
				itemTotalMny = itemTotalMny.add(new BigDecimal(freight))
						.add(new BigDecimal(carry))
						.setScale(2, BigDecimal.ROUND_HALF_UP);

				if (include) {
					productIds.append(cartSnap.getPid()).append(",");
					check = "checked=\"checked\"";
					totalCount += cartSnap.getCount();
					BigDecimal temp = new BigDecimal(areaPrice);
					BigDecimal bcount = new BigDecimal(cartSnap.getCount());
					if (temp != null && bcount != null) {
						temp = temp.multiply(bcount);
						totalPrice = totalPrice.add(temp);
						if (freight != "" && freight != null) {
							totalPrice = totalPrice
									.add(new BigDecimal(freight));
						}
						if (carry != "" && carry != null) {
							totalPrice = totalPrice.add(new BigDecimal(carry));
						}
					}
				} else {
					check = "";
				}

				// 是否超过库存
				String overStockStr = "";
				Boolean overStock = overStockMap.get(cartSnap.getPid());
				if (overStock == null) {
					overStock = false;
				}
				// if (overStock) {
				// overStockStr = "<div style='color:red'>最多只能购买"
				// + cartSnap.getCount() + "件</di>";
				// }
				BigDecimal itemTotalWeight = new BigDecimal(cartSnap.getCount());
				itemTotalWeight = itemTotalWeight.multiply(new BigDecimal(
						cartSnap.getWeight()));
				itemTotalWeight = itemTotalWeight.setScale(2,
						BigDecimal.ROUND_HALF_UP);

				String body = "";
				String itemWeight = "";
				if (cartSnap.getQuantityunitid() == 4) {
					body = " <div class=\"cell priceC\">￥" + itemPrice
							+ "元/吨</div>\n";
					itemWeight = "<div class=\"cell companyC\">"
							+ /* cartSnap.getWeight() */itemTotalWeight
							+ "吨</div>\n";
				} else {
					body = " <div class=\"cell priceC\">￥" + areaPrice
							+ "元/包</div>\n";
					itemWeight = "<div class=\"cell companyC\">"
							+ /* cartSnap.getWeight() */itemTotalWeight
							+ "包</div>\n";
				}

				String data = " <div class=\"buyItme\">\n"
						+ "        <div class=\"buyItmeC\">\n"
						+ "            <div class=\"cell checkC\"><input type=\"checkbox\" name=\"cartItemCheckbox\""
						+ check
						+ " value=\""
						+ cartSnap.getPid()
						+ "\" onclick=\"cancelOrSelectCartItem()\"/>&nbsp;</div>\n"
						+ "            <div class=\"cell productC\">\n"
						+ "                <div class=\"productC1\">\n"
						+ "                    <img src=\""
						+ url
						+ "/upload/product/show/thumb60_60/" 
						+ cartSnap.getShowimg()
						+ "\" width=\"50\" height=\"50\" onerror='nofind();'/>\n"
						+ "                    <a href='"
						+ url
						+ "/product/detail.do?id="
						+ cartSnap.getPid()
						+ "'>"
						+ cartSnap.getName()
						+ "</a>\n"
						+ "<div class=\"clear\"></div>\n"
						+ "</div>\n"
						+ "</div>\n"
						+ body
						+ "<div class=\"cell freightC\">￥"
						+ freight
						+ "</div>\n"
						+ "            <div class=\"cell carryC\">￥"
						+ carry
						+ "</div>\n"
						+ "            <div class=\"cell numberC\"><dl class=\"buyNB\">\n"
						+ "            <dd>\n"
						+ "            <a href=\"javascript:void(0)\" onclick=\"changePruductCount("
						+ cartSnap.getPid()
						+ ","
						+ (cartSnap.getCount() - 1)
						+ ")\" class=\"down\">-</a>\n"
						+ "            <input type=\"text\" onfocus=\"numberFocus(this)\" onblur=\"numberBlur(this,"
						+ cartSnap.getPid()
						+ ",0)\" value=\""
						+ cartSnap.getCount()
						+ "\" />包\n"
						+ "            <a href=\"javascript:void(0)\" onclick=\"changePruductCount("
						+ cartSnap.getPid()
						+ ","
						+ (cartSnap.getCount() + 1)
						+ ")\" class=\"up\">+</a>\n"
						+ overStockStr
						+ "            </dd>\n"
						+ "            <div class=\"clear\"></div>\n"
						+ "            </dl></div>\n"
						// + itemWeight
						+ "<div class=\"cell totalMnyC\">￥"
						+ itemTotalMny
						+ "</div>"
						+ "            <div class=\"cell actionC\"><a href=\"javascript:void(0)\" onclick=\"if (confirm('您确定要把该商品移出购物车吗？')) delCartProduct("
						+ cartSnap.getPid()
						+ ",0)\">删除</a></div>\n"
						+ "            <div class=\"clear\"></div>\n"
						+ "        </div>\n" + "</div>";
				sb.append(data);
			}

			// 是否全选
			String temp = "";
			if (!noCheckAll) {
				temp = "checked=\"checked\"";
			}

			String td = "<div id=\"buyDT\">\n"
					+ "     <ul>\n"
					+ "         <li class=\"checkT\"><label><input type=\"checkbox\" "
					+ temp
					+ " id=\"selectAllBut_top\" onclick=\"cancelOrSelectAllCartItem(this)\"/>全选</label>&nbsp;</li>\n"
					+ "         <li class=\"productT\">商品</li>\n"
					+ "         <li class=\"priceT\">价格</li>\n"
					+ "         <li class=\"freightT\">运费</li>\n"
					+ "         <li class=\"carryT\">装卸费</li>\n"
					+ "         <li class=\"numberT\">数量</li>\n"
					// + "			<li class=\"companyT\">重量</li>\n"
					+ "			<li class=\"totalMnyT\">总价</li>\n"
					+ "         <li class=\"actionT\">操作</li>\n"
					+ "     </ul>\n" + " </div>";
			// 选中的产品id
			if (productIds.length() > 1) {
				productIds.deleteCharAt(productIds.length() - 1);
			}

			String total = "<div class=\"buySum\">\n"
					+ "    <div class=\"left sum1\"><label><input type=\"checkbox\" checked=\"checked\" id=\"selectAllBut_bottom\" onclick=\"cancelOrSelectAllCartItem(this)\"/>&nbsp;全选</label><a href=\"javascript:void(0)\" onclick=\"clearCart(0)\">清空购物车</a> <a href=\""
					+ url
					+ "/index.do\">继续购物</a></div>\n"
					+ "    <div class=\"right sum2\"><div class=\"left\"><font color=\"red\" id=\"totalCount\">"
					+ totalCount
					+ "</font>件商品</div><div class=\"left sum3\"><p>总计：<span class=\"right\" id=\"productAmount\">￥"
					+ totalPrice
					+ "</span></p></div><div class=\"clear\"></div></div>\n"
					+ "    <div class=\"clear\"></div>\n"
					+ "    <div class=\"buySumBt\">总计： <b id=\"orderAmount\">￥"
					+ totalPrice
					+ "</b><form action=\""
					+ url
					+ "/order/confirmorder.do\" method=\"post\"><input name=\"selectedCartItemKeyList\" id=\"selectedCartItemKeyList\" value=\""
					+ productIds.toString()
					+ "\" type=\"hidden\" /><a href=\"javascript:void(0)\" onclick=\"goConfirmOrder()\" class=\"redBT\">去结算</a></form></div>\n"
					+ "</div>";

			result.append(td);
			result.append(sb.toString());
			result.append(total);
		} else {
			String nobuy = "<ul id=\"noBuy\">\n"
					+ "\t  <li>购物车内暂时没有商品，<a href=\"" + url
					+ "/index.do\">去首页</a>挑选喜欢的商品</li>\n" + "</ul>";
			result.append(nobuy);
		}

		return result.toString();
	}

	/**
	 * 构造购物车快照html
	 * 
	 * @param list
	 * @param realPath
	 * @return
	 */
	public String createCartSnapHtml(List<CartSnapVO> list, int regionsId,
			String realPath) {
		StringBuffer snap = new StringBuffer();
		StringBuffer productIds = new StringBuffer();
		boolean price = true; // 用于判断是否去市场值
		if (list == null || list.size() == 0) {
			snap.append("<div class='shoppingNone'>购物车中还没有商品，赶紧选购吧！</div>");
			snap.append("<div id='csProudctCount' style=' display:none;'>0</div>");
		} else {

			BigDecimal totalPrice = new BigDecimal(0);
			int count = 0;
			snap.append("<div class='shoppingT'>最新加入的商品</div>");
			snap.append("<div class='cartSnap'>");
			for (CartSnapVO vo : list) {
				price = true;
				count += vo.getCount();

				// 获取产品的区域价格
				String areaPrice = orderService.getProductPriceByArea(vo,
						regionsId);
				// 如果产品的区域价格为空，取产品的区域价格
				if (areaPrice == null) {
					// CartSnapVO cart = new CartSnapVO();
					// cart.setPid(-1);
					// areaPrice = orderService.getProductPriceByArea(vo,
					// regionsId);
					// // 如果产品的区域价格为空，取产品的市场价
					// if (areaPrice == null) {
					areaPrice = vo.getMarketprice();
					price = false;
					// }
				}
				String originalPrice = "";
				if (vo.getQuantityunitid() == 4) {
					// 转化为件的价格
					// BigDecimal per = new BigDecimal(0);
					// BigDecimal weight = new BigDecimal(vo.getWeight());
					// weight = weight.divide(new BigDecimal(1000));
					// 直接取重量单位
					BigDecimal per = new BigDecimal(0.000);
					BigDecimal weight = new BigDecimal(vo.getWeight());
					// 每吨价格
					BigDecimal ton = new BigDecimal(areaPrice);
					per = ton.multiply(weight);
					per = per.setScale(2, RoundingMode.HALF_UP);

					areaPrice = per.toString();
					originalPrice = ton.toString() + "元/吨";

				} else {
					originalPrice = areaPrice + "元/包";
				}

				BigDecimal temp = new BigDecimal(areaPrice);
				BigDecimal bcount = new BigDecimal(vo.getCount());
				if (temp != null && bcount != null) {
					temp = temp.multiply(bcount);
					totalPrice = totalPrice.add(temp);
				}

				productIds.append(vo.getPid()).append(",");

				snap.append("<div class='shoppingI'>");
				snap.append("<img src='" + realPath
						+ "/upload/product/show/thumb60_60/" + vo.getShowimg()
						+ "' width='64' height='64' onerror='nofind();'/>");

				String company = "";
				if (vo.getQuantityunitid() == 4) {
					company = "吨";
				} else if (vo.getQuantityunitid() == 3) {
					company = "包";
				}

				snap.append("<em><a href='" + realPath
						+ "/product/detail.do?id=" + vo.getPid() + "'>"
						+ vo.getName() + "</a></em> <b>" + originalPrice
						+ "<span>×" + vo.getWeight() + company + "<span>×"
						+ vo.getCount() + "包"
						+ "</span><a href='javascript:void(0)' "
						+ "onclick='delCartProduct(" + vo.getPid()
						+ ",1)'>删除</a></b>");
				snap.append("<div class='clear'></div>");
				snap.append("</div>");

			}

			// 选中的产品id
			if (productIds.length() > 1) {
				productIds.deleteCharAt(productIds.length() - 1);
			}
			snap.append("</div>");

			snap.append("<div class='shoppingJS'>共<strong>" + count
					+ "</strong>件商品 (不计运费与装卸费) 共计<b>￥" + totalPrice
					+ "</b></div>");
			snap.append("<div style='text-align: right;background-color:#f5f5f5; padding:10px 15px;'><form action=\""
					+ realPath
					+ "/order/confirmorder.do\" method=\"post\"><input name=\"selectedCartItemKeyList\" id=\"selectedCartItemKeyList\" value=\""
					+ productIds.toString()
					+ "\" type=\"hidden\" />"
					+ "<a href='javascript:void(0)' onclick=\"goConfirmOrder1()\"  class='goPay'>去结算</a> "
					+ "<a href='javascript:void(0)' onclick='clearCart(1)' class='clearList'>清空购物车</a></form></div>");
			snap.append("<div id='csProudctCount' style=' display:none;'>"
					+ count + "</div>");
		}

		return snap.toString();
	}

	/**
	 * 获取cookie中的区域id
	 * 
	 * @param cookies
	 * @return
	 */
	public int getAreaIdFromCookies(Cookie[] cookies) {
		int result = 0;
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if ("lastarea".equals(name)) {
					String value = cookie.getValue();
					try {
						result = Integer.parseInt(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	public CartSnapVO getCartSnapByUidAndPid(Integer uid, Integer pid) {
		return cartMapper.getCartSnapByUidAndPid(uid, pid);

	}

}
