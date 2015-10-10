package com.runlion.shop.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.runlion.shop.common.Constant;
import com.runlion.shop.common.defaultval.AdminuserDefVal;
import com.runlion.shop.common.util.EnumsUtil;
import com.runlion.shop.common.util.HttpRequest;
import com.runlion.shop.common.util.JspToHtml;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.date.DateStyle;
import com.runlion.shop.common.util.date.DateUtil;
import com.runlion.shop.common.util.uploadfile.FileUpload;
import com.runlion.shop.common.util.uploadfile.UploadFilePathEnum;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspAreaManagerMapper;
import com.runlion.shop.dao.BspCancelorderPicMapper;
import com.runlion.shop.dao.BspCancelorderReasonMapper;
import com.runlion.shop.dao.BspInvoiceMapper;
import com.runlion.shop.dao.BspOrderactionsMapper;
import com.runlion.shop.dao.BspOrderextMapper;
import com.runlion.shop.dao.BspOrderpricemnyMapper;
import com.runlion.shop.dao.BspOrderproductsMapper;
import com.runlion.shop.dao.BspOrdersMapper;
import com.runlion.shop.dao.BspPayorderMapper;
import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspSendorderMapper;
import com.runlion.shop.dao.BspUsersMapper;
import com.runlion.shop.dao.BspUsersPermissionMapper;
import com.runlion.shop.dao.BspWorktimeMapper;
import com.runlion.shop.dao.BsporderVerificationMapper;
import com.runlion.shop.dao.MessageMapper;
import com.runlion.shop.entity.BspAttributevalues;
import com.runlion.shop.entity.BspCancelorderPic;
import com.runlion.shop.entity.BspCancelorderPicExample;
import com.runlion.shop.entity.BspCancelorderReason;
import com.runlion.shop.entity.BspCancelorderReasonExample;
import com.runlion.shop.entity.BspInvoice;
import com.runlion.shop.entity.BspInvoiceExample;
import com.runlion.shop.entity.BspMessage;
import com.runlion.shop.entity.BspOrderactions;
import com.runlion.shop.entity.BspOrderactionsExample;
import com.runlion.shop.entity.BspOrderext;
import com.runlion.shop.entity.BspOrderextExample;
import com.runlion.shop.entity.BspOrderpricemny;
import com.runlion.shop.entity.BspOrderpricemnyExample;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrderproductsExample;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspOrdersExample;
import com.runlion.shop.entity.BspOrdersExample.Criteria;
import com.runlion.shop.entity.BspPayorder;
import com.runlion.shop.entity.BspPayorderExample;
import com.runlion.shop.entity.BspProductattributes;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsExample;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspSaleaddressExample;
import com.runlion.shop.entity.BspSendorder;
import com.runlion.shop.entity.BspSendorderExample;
import com.runlion.shop.entity.BspShipaddresses;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.entity.BspUsersPermission;
import com.runlion.shop.entity.BspUsersPermissionExample;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.BsporderVerification;
import com.runlion.shop.entity.common.BusinessRequestParam;
import com.runlion.shop.entity.common.ClientRequestParam;
import com.runlion.shop.entity.common.ResponseData;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.service.region.RegionsService;
import com.runlion.shop.vo.CartSnapVO;
import com.runlion.shop.vo.OrderProductPrice;
import com.runlion.shop.vo.OrderVO;

@Service
public class OrderService {
	private static Logger logger = Logger.getLogger(OrderService.class);
	// 门店
	public static final String STORE = "1";
	// 工厂
	public static final String FACTORY = "2";

	// 产品价格
	public static final int PRODUCT_PRICE = 1;
	// 运费
	public static final int SHIP_FEE = 2;
	// 装卸价格
	public static final int HANDLING_COST = 3;

	@Autowired
	private BspSaleaddressMapper saleaddressMapper;

	@Autowired
	private BspOrdersMapper ordersMapper;

	@Autowired
	private BspUsersPermissionMapper bspUsersPermissionMapper;

	@Autowired
	private BspSendorderMapper bspSendorderMapper;

	@Autowired
	private BspOrderpricemnyMapper bspOrderpricemnyMapper;

	@Autowired
	private BspPayorderMapper bspPayorderMapper;

	@Autowired
	private BspCancelorderReasonMapper bspCancelorderReasonMapper;

	@Autowired
	private BspCancelorderPicMapper bspCancelorderPicMapper;

	@Autowired
	private RegionsService regionsService;
	@Autowired
	private ShipAddressService shipAddressService;
	@Autowired
	private BspOrderproductsMapper orderproductsMapper;

	@Autowired
	private BspOrderactionsMapper orderactionsMapper;

	@Autowired
	private BspProductsService productsService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderProductPriceService orderProductPriceService;

	@Autowired
	private BspWorktimeMapper mapper;

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private BsporderVerificationMapper bsporderVerificationMapper;
	@Autowired
	private BspProductsService bspProductsService;
	@Autowired
	private BspProductsregionsMapper bspProductsregionsMapper;
	@Autowired
	private BspAreaManagerMapper bspAreaManagerMapper;
	@Autowired
	private BspSaleaddressMapper bspSaleaddressMapper;
	@Autowired
	private BspUsersMapper bspUsersMapper;
	@Autowired
	private BspOrderextMapper bspOrderextMapper;
	@Autowired
	private BspInvoiceMapper bspInvoiceMapper;
	@Autowired
	BspRegionsMapper brm;

	/**
	 * 根据订单号获取订单
	 * 
	 * @param orderNo
	 * @return
	 */
	public BspOrders getOrderByOrderNo(String orderNo) {
		BspOrdersExample e = new BspOrdersExample();
		e.or().andOsnEqualTo(orderNo);
		List<BspOrders> list = ordersMapper.selectByExample(e);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	/**
	 * 查询支付状态未确认的订单
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<BspOrders> getUncertainOrderList(Date d1, Date d2) {
		BspOrdersExample e = new BspOrdersExample();
		e.createCriteria().andPaymodeEqualTo((byte) 4)
				.andOrderstateEqualTo((byte) 0).andPaystateEqualTo("0")
				.andAddtimeBetween(d1, d2);
		List<BspOrders> list = ordersMapper.selectByExample(e);

		return list;
	}

	/**
	 * 获取工厂地址
	 * 
	 * @param shipAddress
	 * @return
	 */
	public Map<String, String> getFactoryAddress(BspShipaddresses shipAddress) {
		if (shipAddress != null) {
			List<BspSaleaddress> addressList = saleaddressMapper
					.getSaleaddress(shipAddress.getSaid());
			if (addressList != null && addressList.size() > 0) {
				Map<String, String> result = new HashMap<String, String>();
				for (BspSaleaddress addr : addressList) {
					result.put(addr.getType(), addr.getAddress());
				}
				return result;
			}
		}
		return null;
	}

	/**
	 * 获取门店工厂地址
	 * 
	 * @param shipAddress
	 * @return
	 */
	public List<BspSaleaddress> getAddressByList(BspShipaddresses shipAddress,
			int type) {
		if (shipAddress != null) {
			List<BspSaleaddress> addressList = saleaddressMapper
					.getSaleaddressByRegId(shipAddress.getSaid(), type);
			if (addressList.size() == 0) {
				for (Integer regId : regionsService
						.getParentRegionList(shipAddress.getRegionid())) {
					addressList = saleaddressMapper.getSaleaddressByRid(regId,
							type);
					if (addressList.size() == 0) {
						continue;
					} else {
						return addressList;
					}
				}
			}
			return addressList;
		}
		return null;
	}

	/**
	 * 获取工厂地址
	 * 
	 * @param shipAddress
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<BspSaleaddress>> getAddressByRid(int regionId)
			throws Exception {
		List<BspSaleaddress> storeaddressList = saleaddressMapper
				.getSaleaddressByRid(regionId, 1);
		List<BspSaleaddress> factoryaddressList = saleaddressMapper
				.getSaleaddressByRid(regionId, 2);
		if (storeaddressList.size() == 0) {
			for (Integer regId : regionsService.getParentRegionList(regionId)) {
				storeaddressList = saleaddressMapper.getSaleaddressByRid(regId,
						1);
				if (storeaddressList.size() == 0) {
					continue;
				} else {
					break;
				}
			}
		}
		if (factoryaddressList.size() == 0) {
			for (Integer regId : regionsService.getParentRegionList(regionId)) {
				factoryaddressList = saleaddressMapper.getSaleaddressByRid(
						regId, 2);
				if (factoryaddressList.size() == 0) {
					continue;
				} else {
					break;
				}
			}
		}
		// List<BspSaleaddress> factorylist = new ArrayList<BspSaleaddress>();
		// List<BspSaleaddress> storelist = new ArrayList<BspSaleaddress>();
		// if (addressList != null) {
		// for (int i = 0; i < addressList.size(); i++) {
		// if (OrderService.FACTORY.equals(addressList.get(i).getType())) {
		// factorylist.add(addressList.get(i));
		// }
		// if (OrderService.STORE.equals(addressList.get(i).getType())) {
		// storelist.add(addressList.get(i));
		// }
		// }
		//
		// }
		Map<String, List<BspSaleaddress>> result = new HashMap<String, List<BspSaleaddress>>();
		if (storeaddressList.size() > 0) {
			// List<BspSaleaddress> list = new ArrayList<BspSaleaddress>();
			// list.add(storeBean.getAddress());
			// list.add(storeBean.getId().toString());
			result.put(STORE, storeaddressList);
		}
		if (factoryaddressList.size() > 0) {
			// List<String> list = new ArrayList<String>();
			// list.add(factoryBean.getAddress());
			// list.add(factoryBean.getId().toString());
			result.put(FACTORY, factoryaddressList);
		}
		return result;
	}

	/**
	 * 获取区域的产品价格
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	public String getProductPriceByArea(CartSnapVO cartSnap, int regionsId) {
		if (cartSnap != null) {
			// int totalweight = cartSnap.getWeight() * cartSnap.getCount() /
			// 1000;
			// int level = orderProductPriceService.getPriceLevel(totalweight);
			return ordersMapper.getProductPriceByArea(cartSnap.getPid(),
					regionsId);
		}
		return null;

	}

	/**
	 * 查询产品的库存
	 * 
	 * @return
	 */
	public int getProductStock(int productId) {
		Integer count = ordersMapper.getProductStock(productId);
		if (count == null) {
			count = 0;
		}
		return count;
	}

	/**
	 * 下订单订单
	 * 
	 * @param user
	 * @param ordervo
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, String> placedOrder(int userId, OrderVO ordervo,
			HttpServletRequest request/* , String verificationCode */)
			throws Exception {
		String LOGINNAME = (String) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_LOGINNAME);
		String realPath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

		List<CartSnapVO> cartSnapList = cartService.getCartSnapByUserId(userId);

		// 选择选中的产品
		List<CartSnapVO> selectOrderProductList = selectProduct(
				ordervo.getProductids(), cartSnapList);

		Map<String, String> map = checkOrder(selectOrderProductList);
		if (map.isEmpty()) {
			// 区域
			BspRegions regions = regionsService.getResionsById(ordervo
					.getRegionId());
			// 获取产品价格
			OrderProductPrice orderProductPrice = orderProductPriceService
					.portionPrice(selectOrderProductList, regions,
							Integer.parseInt(ordervo.getShipType()));

			//

			BspOrders savedOrder = saveOrder(orderProductPrice, userId,
					ordervo, request, selectOrderProductList);

			int orderId = savedOrder.getOid();

			for (CartSnapVO cartSnapVO : selectOrderProductList) {
				String allarea = "0-0-0-0-0-0";
				BspRegions b = regionsService.getResionsById(savedOrder
						.getRegionid());
				if (b != null)
					allarea = b.getParentid() + "-" + b.getCityid() + "-"
							+ b.getCountyid() + "-" + b.getStreetid() + "-"
							+ b.getRegionid() + "-0";

				Date date = new Date();
				String filename = sdf.format(date) + cartSnapVO.getPid();
				String url = realPath + "/product/detail.do?id="
						+ cartSnapVO.getPid();
				String myCookie = "JSESSIONID=A57F6493C5D456A0B6487A3AD31B6F1F; allarea="
						+ allarea
						+ "; a2404_pages=5; a2404_times=1;lastarea="
						+ Integer.toString(orderProductPrice.getRegionsId());

				// 路径
				UploadFilePathEnum orderState = EnumsUtil.valueOf(
						UploadFilePathEnum.class, (byte)9);
				String source = FileUpload.getUploadFilePath(orderState);

				String download = source + filename + ".htm";
				cartSnapVO.setUrl(filename + ".htm");
				JspToHtml.JspToHtmlByURL(url, download, cartSnapVO.getPid(),
						realPath, myCookie);
			}
			// 保存产品订单
			saveorderProducts(selectOrderProductList, orderId, userId);
			// 保存订单动作
			addOrderAction(userId, "本人", (short) 0, (byte) 0, "非管理员",
					"您提交了订单，请等待系统确认", orderId);
			// 保存证件信息
			saveOrderextList(ordervo.getOrderExtList(), orderId);
			// 保存发票信息
			if ("1".equals(ordervo.getNeedinv())
					&& ordervo.getInvoice() != null) {
				BspInvoice invoice = ordervo.getInvoice();
				invoice.setOid(orderId);
				bspInvoiceMapper.insertSelective(invoice);
			}
			// 订单生成成功后向卖家发送短信
			// this.sendOrderMsg(orderId, LOGINNAME);

			// 删除购物车
			deleteCompleteCart(orderId);

			String pickName = savedOrder.getShipfriendname();
			String pickId = savedOrder.getShipsystemname();
			BigDecimal surmny = savedOrder.getSurplusmoney();

			String payModel = request.getParameter("paymode");
			Byte ipaymode = 0;
			if (payModel != null) {
				ipaymode = Byte.valueOf(payModel);
			}
			map.put("state", "success");
			map.put("content",
					"submitOrderSuccess.do?paymodel=" + ipaymode + "&orderid="
							+ orderId + "&ordernum=" + savedOrder.getOsn()
							+ "&pickName=" + pickName + "&pickId=" + pickId
							+ "&surmny=" + surmny.toString());

		}

		return map;

	}

	/**
	 * 校验订单
	 * 
	 * @param cartSnapList
	 * @return
	 */
	private Map<String, String> checkOrder(List<CartSnapVO> cartSnapList) {
		Map<String, String> map = new HashMap<String, String>();
		if (cartSnapList == null || cartSnapList.size() < 0) {
			map.put("state", "cartNull");
			map.put("content", "购物车为空，请重新添加购物车");
		} else {
			StringBuffer productNull = new StringBuffer();
			StringBuffer productStock = new StringBuffer();
			for (CartSnapVO cartSnap : cartSnapList) {
				BspProducts product = productsService
						.selectByPrimaryKey(cartSnap.getPid());
				if (product == null) {
					productNull.append(cartSnap.getName()).append(",");
				}
			}
			if (productNull.length() > 0 || productStock.length() > 0) {
				StringBuffer sb = new StringBuffer();
				map.put("state", "error");
				if (productNull.length() > 0) {
					productNull = productNull
							.deleteCharAt(productNull.length() - 1);
					sb.append(productNull + "产品已经下架\n");
				}

				if (productStock.length() > 0) {
					sb.append(productStock.toString());
				}
				map.put("content", sb.toString());
			}

		}

		return map;
	}

	/**
	 * 保存订单
	 * 
	 * @param user
	 * @param ordervo
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BspOrders saveOrder(OrderProductPrice orderProductPrice, int userId,
			OrderVO ordervo, /* String verificationCode, */
			HttpServletRequest request, List<CartSnapVO> selectOrderProductList)
			throws Exception {
		BspShipaddresses shipAddress = shipAddressService
				.getShipAddressById(ordervo.getSaId());

		String LOGINNAME = (String) request.getSession().getAttribute(
				BaseController.ACCOUNT_SESSION_LOGINNAME);
		// 订单号
		String orderNum = OrderNoGenerator.getOrderNo();
		BspOrders order = new BspOrders();
		String payModel = request.getParameter("paymode");
		Byte ipaymode = 0;
		if (payModel != null) {
			ipaymode = Byte.valueOf(payModel);
		}
		order.setPaymode(ipaymode);
		order.setOsn(orderNum);
		order.setUid(userId);
		// 状态,待确认
		order.setOrderstate((byte) OrderStateEnum.PreConfirm.ordinal());

		// 商品合计
		BigDecimal productamount = orderProductPrice.getTotalProductPrice();
		order.setProductamount(productamount);
		// 订单合计
		BigDecimal orderamount = orderProductPrice.getTotalOrderPrice();
		order.setOrderamount(orderamount);

		// 支付金额
		order.setSurplusmoney(orderamount);
		// 是否评价
		order.setIsreview((byte) 0);
		order.setAddtime(new Date());
		//
		order.setShipsystemname(String.valueOf(ordervo.getShipType()));
		// 配送方式
		order.setShipfriendname(getShipTypeName(ordervo.getShipType()));
		// 配送时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		order.setBesttime(sdf.parse(ordervo.getDate()));
		// 区域id
		order.setRegionid(ordervo.getRegionId());
		order.setConsignee(shipAddress.getConsignee());
		order.setMobile(shipAddress.getMobile());
		order.setPhone(shipAddress.getPhone());
		order.setEmail(shipAddress.getEmail());
		order.setZipcode(shipAddress.getZipcode());
		order.setAddress(shipAddress.getAddress());

		BigDecimal shipfee = orderProductPrice.getShipFee();
		order.setShipfee(shipfee);

		BigDecimal handlingCost = orderProductPrice.getHandlingCost();
		order.setHandlingcost(handlingCost);

		order.setWeight(orderProductPrice.getTotalWeight());

		order.setBuyerremark(ordervo.getBuyerRemark());
		order.setIp(ordervo.getIp());
		order.setBegindate(ordervo.getBeginDate());
		order.setEnddate(ordervo.getEndDate());
		order.setFactoryAddress(ordervo.getFactoryAddress());
		order.setStoreAddress(ordervo.getStoreAddress());
		order.setSalerid(ordervo.getSalerid());
		order.setNeedinv(ordervo.getNeedinv());

		int result = ordersMapper.insertSelective(order);

		return order;
	}

	/**
	 * 获取配送方式
	 * 
	 * @param type
	 * @return
	 */
	public String getShipTypeName(String type) {
		if ("1".equals(type)) {
			return "门店自提";
		}
		if ("2".equals(type)) {
			return "工厂自提";
		}

		if ("3".equals(type)) {
			return "配送到家";
		}
		return null;

	}

	/**
	 * 添加产品订单
	 * 
	 * @param orderproduct
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveorderProducts(List<CartSnapVO> CartSnapList, int orderId,
			Integer uid) {

		if (CartSnapList != null && CartSnapList.size() > 0) {
			for (CartSnapVO cartSnap : CartSnapList) {
				BspProducts product = productsService
						.selectByPrimaryKey(cartSnap.getPid());
				if (product != null) {
					BspOrderproducts orderproduct = new BspOrderproducts();
					orderproduct.setOid(orderId);
					orderproduct.setUid(uid);
					orderproduct.setPid(product.getPid());
					orderproduct.setPsn(product.getPsn());
					orderproduct.setCateid(product.getCateid());
					orderproduct.setBrandid(product.getBrandid());
					orderproduct.setName(product.getName());
					orderproduct.setShowimg(product.getShowimg());
					orderproduct.setShopprice(product.getShopprice());
					orderproduct.setCostprice(product.getCostprice());
					orderproduct.setMarketprice(new BigDecimal(cartSnap
							.getMarketprice()));
					orderproduct.setWeight(product.getWeight());

					orderproduct.setRealcount((double) cartSnap.getCount());
					orderproduct.setBuycount((double) cartSnap.getCount());
					orderproduct.setSendcount((double) 0);
					orderproduct.setAddtime(new Date());
					orderproduct.setUrl(cartSnap.getUrl());
					orderproduct.setItemtotalmny(cartSnap.getItemTotalMny());
					orderproduct.setShipmny(cartSnap.getFreight());
					orderproduct.setHandmny(cartSnap.getCarry());
					orderproductsMapper.insertSelective(orderproduct);
				}
			}
		}
	}

	/**
	 * 保存订单动作
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addOrderAction(int userId, String realname, short adminId,
			byte actionType, String adminTitle, String remark, int oid) {
		BspOrderactions action = new BspOrderactions();
		action.setOid(oid);
		action.setUid(userId);
		action.setRealname(realname);
		action.setAdmingid(adminId);
		action.setActiontype(actionType);
		action.setActiontime(new Date());
		action.setAdmingtitle(adminTitle);
		action.setActiondes(remark);
		orderactionsMapper.insertSelective(action);

	}

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	public List<BspOrders> getOrderList(Map<String, Object> params) {
		return ordersMapper.getOrderList(params);
	}

	/**
	 * 获取订单总数量
	 * 
	 * @param params
	 * @return
	 */
	public int getOrderCount(Map<String, Object> params) {
		return ordersMapper.getOrderCount(params);
	}

	/**
	 * 选择产品
	 * 
	 * @param selectedCartItemKeyList
	 * @param cartSnapList
	 * @return
	 */
	public List<CartSnapVO> selectProduct(String selectedCartItemKeyList,
			List<CartSnapVO> cartSnapList) {
		String[] selectedCartItemList = null;
		if (selectedCartItemKeyList != null) {
			selectedCartItemList = selectedCartItemKeyList.split(",");
		}
		List<CartSnapVO> selectOrderProductList = new ArrayList<CartSnapVO>();
		// 过滤没选择的产品
		if (selectedCartItemList != null) {
			for (String str : selectedCartItemList) {
				for (CartSnapVO csv : cartSnapList) {
					if (str.equals(String.valueOf(csv.getPid()))) {
						csv.setProductId(selectedCartItemKeyList);
						csv.setOriginalPrice(csv.getMarketprice());
						selectOrderProductList.add(csv);
					}
				}
			}
		} else {
			selectOrderProductList = cartSnapList;
		}
		return selectOrderProductList;
	}

	/**
	 * 处理订单状态
	 * 
	 * @param userId
	 * @param orderId
	 * @param optType
	 * @param admin
	 * @param remark
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean processOrder(int userId, int orderId, byte optType,
			int admin, String remark, String shipNO) throws Exception {

		String operName = "商家";
		BspUsersPermissionExample e = new BspUsersPermissionExample();
		e.createCriteria().andUidEqualTo(userId)
				.andRoleidEqualTo(String.valueOf(Constant.UserType_factory));
		List<BspUsersPermission> plist = bspUsersPermissionMapper
				.selectByExample(e);
		if (plist != null && plist.size() > 0)
			operName = "工厂";

		OrderStateEnum orderState = EnumsUtil.valueOf(OrderStateEnum.class,
				optType);
		if (remark == null || remark.equals("")) {
			remark = orderState.getDesc();
		}

		// 写日志
		if (0 == admin) {
			addOrderAction(userId, operName, (short) admin, optType, "商家",
					remark, orderId);
		} else if (1 == admin) {
			addOrderAction(userId, "管理员", (short) admin, optType, "系统管理员",
					remark, orderId);
		} else {
			addOrderAction(userId, "用户", (short) admin, optType, "非管理员",
					remark, orderId);
		}

		switch (orderState) {
		case PreConfirm:
			break;
		case Confirmed:
			// 待发货向商家发送短信
			// 通知门店或者工厂
			sendConfMsg(orderId);

			// 操作日志增加：已验证，只有b2c订单需要
			BspOrders order = ordersMapper.selectByPrimaryKey(orderId);
			if (order.getUserrole().equals(1))
				addOrderAction(userId, operName, (short) admin,
						(byte) OrderStateEnum.Verified.ordinal(), operName,
						OrderStateEnum.Verified.getDesc(), orderId);

			break;
		case PreVerify:
			break;
		case Verified:
			break;
		case PrePaid:
			break;
		case Paid:
			// 更新支付状态
			updateOrderPayState(orderId,
					String.valueOf(Constant.OrderPayState_finished));
			break;

		case PreSend:
			break;
		case Sended:
			// 已发货向客户 和 商家 发送短信
			// remark = remark+ ",配送单号：" + shipNO;
			updateOrderShip(orderId, shipNO);
			sendMsg(orderId);

			// 更新订单的remark字段
			break;
		case NoConfiremGet:
			break;
		case ConfiremGet:
			break;
		case Locked:
			break;
		case Cancel:
			break;
		default:
			break;
		}

		// 更新订单状态
		updateOrderState(orderId, optType);

		return true;

	}

	/**
	 * 处理订单状态
	 * 
	 * @param userId
	 * @param orderId
	 * @param optType
	 * @param admin
	 * @param remark
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean processOrder(int userId, int orderId, byte optType,
			int admin, String remark, String shipNO, int storetype)
			throws Exception {

		if (storetype == -1) {
			return processOrder(userId, orderId, optType, admin, remark, shipNO);
		} else {
			String operName = "商家";
			if (storetype == 2)
				operName = "工厂";

			OrderStateEnum orderState = EnumsUtil.valueOf(OrderStateEnum.class,
					optType);
			if (remark == null || remark.equals("")) {
				remark = orderState.getDesc();
			}

			// 写日志
			if (0 == admin) {
				addOrderAction(userId, operName, (short) admin, optType, "商家",
						remark, orderId);
			} else if (1 == admin) {
				addOrderAction(userId, "管理员", (short) admin, optType, "系统管理员",
						remark, orderId);
			} else {
				addOrderAction(userId, "用户", (short) admin, optType, "非管理员",
						remark, orderId);
			}

			switch (orderState) {
			case PreConfirm:
				break;
			case Confirmed:
				// 待发货向商家发送短信
				// 通知门店或者工厂
				sendConfMsg(orderId);

				// 操作日志增加：已验证，只有b2c订单需要
				BspOrders order = ordersMapper.selectByPrimaryKey(orderId);
				if (order.getUserrole().equals(1))
					addOrderAction(userId, operName, (short) admin,
							(byte) OrderStateEnum.Verified.ordinal(), operName,
							OrderStateEnum.Verified.getDesc(), orderId);

				break;
			case PreVerify:
				break;
			case Verified:
				break;
			case PrePaid:
				break;
			case Paid:
				// 更新支付状态
				updateOrderPayState(orderId,
						String.valueOf(Constant.OrderPayState_finished));
				break;

			case PreSend:
				break;
			case Sended:
				// 已发货向客户 和 商家 发送短信
				// remark = remark+ ",配送单号：" + shipNO;
				updateOrderShip(orderId, shipNO);
				sendMsg(orderId);

				// 更新订单的remark字段
				break;
			case NoConfiremGet:
				break;
			case ConfiremGet:
				break;
			case Locked:
				break;
			case Cancel:
				break;
			default:
				break;
			}

			// 更新订单状态
			updateOrderState(orderId, optType);

			return true;
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOrderShip(int orderId, String shipNO) {
		// 更新单号
		BspOrders order = new BspOrders();
		order.setOid(orderId);
		order.setShipsn(shipNO);
		order.setShiptime(new Date());
		ordersMapper.updateByPrimaryKeySelective(order);
	}

	/**
	 * 订单状态更改完成后发送短信
	 * 
	 * @param orderId
	 * @param userId
	 * @throws Exception
	 */
	public void sendMsg(int orderId) throws Exception {
		BspOrders order = ordersMapper.selectByPrimaryKey(orderId);
		if (order != null) {
			// 获取销售门店或者工厂的信息
			int salerid = order.getSalerid();
			BspSaleaddressExample bse = new BspSaleaddressExample();
			bse.createCriteria().andIdEqualTo(salerid);
			List<BspSaleaddress> salers = bspSaleaddressMapper
					.selectByExample(bse);
			BspSaleaddress saler = null;
			if (salers.size() > 0) {
				saler = salers.get(0);
			}
			//
			BspOrderproductsExample bpde = new BspOrderproductsExample();
			bpde.createCriteria().andOidEqualTo(order.getOid());
			List<BspOrderproducts> products = orderproductsMapper
					.selectByExample(bpde);
			String details = "";
			for (BspOrderproducts product : products) {
				int pid = product.getPid();
				List<BspAttributevalues> getProductAttrByID = bspProductsService
						.getProductAttrByID(pid);
				String attrName = "";
				String attrSpecification = "";
				for (BspAttributevalues bspAttributevalues : getProductAttrByID) {
					if (bspAttributevalues.getAttrid() == 52) {
						attrName += bspAttributevalues.getAttrvalue() + " ";
					}
					if (bspAttributevalues.getAttrid() == 49) {
						attrSpecification += bspAttributevalues.getAttrvalue()
								+ " ";
					}
					System.out.println(attrName);
				}
				details += attrName + attrSpecification.trim() + "规格"
						+ product.getWeight() + "吨";

			}
			// 发送提货信息给买家
			String carNum = "";

			if (!order.getShipsystemname().equals("3")) {
				BspOrderextExample boee = new BspOrderextExample();
				boee.createCriteria().andOexttypeEqualTo("1")
						.andOidEqualTo(order.getOid());
				List<BspOrderext> boelist = bspOrderextMapper
						.selectByExample(boee);
				if (boelist != null && !boelist.isEmpty()) {
					BspOrderext boe = boelist.get(0);
					carNum = "由" + boe.getOextvalue() + "车";
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String besttime = order.getBesttime() == null ? "" : sdf
					.format(order.getBesttime());

			String msgText = StringHandler.formateString(
					AdminuserDefVal.MSG_PICK_GOODS,
					besttime + " " + order.getBegindate() + "-"
							+ order.getEnddate() + carNum, saler.getName(),
					details, order.getSurplusmoney().toString(),
					AdminuserDefVal.TEL_NUM_HS_ELESHOP);
			String rsi = HttpRequest.sendMsm(order.getMobile(), msgText);
			if (!rsi.equals("1")) {// 短信发送失败
			}
			// 发送提货信息到门店或者工厂
			if (saler != null) {
				String salerMsgText = StringHandler.formateString(
						AdminuserDefVal.MSG_STORE_ALERT, details);
				String rs = HttpRequest.sendMsm(saler.getTel(), salerMsgText);
				if (!rs.equals("1")) {// 短信发送失败
				}
			}

		}
	}

	/**
	 * 订单状态更改完成后发送短信
	 * 
	 * @param orderId
	 * @param userId
	 * @throws Exception
	 */
	public void sendConfMsg(int orderId) throws Exception {
		BspOrders order = ordersMapper.selectByPrimaryKey(orderId);
		if (order != null) {
			// 获取销售门店或者工厂的信息
			int salerid = order.getSalerid();
			BspSaleaddressExample bse = new BspSaleaddressExample();
			bse.createCriteria().andIdEqualTo(salerid);
			List<BspSaleaddress> salers = bspSaleaddressMapper
					.selectByExample(bse);
			if (salers.size() > 0) {
				BspSaleaddress saler = salers.get(0);
				String salertel = saler.getTel();
				String msgTmp = AdminuserDefVal.MSG_HOME_DELIVERY_ALERT;
				if (order.getPaymode() == 1 || order.getPaymode() == 2) {
					msgTmp = AdminuserDefVal.MSG_SELF_PICK_ALERT;
				} else if (order.getPaymode() == 3) {
					msgTmp = AdminuserDefVal.MSG_HOME_DELIVERY_ALERT;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				BspUsers user = bspUsersMapper.selectByPrimaryKey(order
						.getUid());
				String time = order.getBesttime() == null ? "" : sdf
						.format(order.getBesttime());
				String storeMsg = StringHandler.formateString(msgTmp, user
						.getUsername(), order.getOsn(), order.getSurplusmoney()
						.toString(), time, order.getMobile());
				String srs = HttpRequest.sendMsm(salertel, storeMsg);
				// 向工厂货门店发送短信失败
				if (!srs.equals("1")) {

				}
			}
		}
	}

	/**
	 * 删除关联订单的购物车
	 * 
	 * @param oid
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteCompleteCart(int oid) {
		return ordersMapper.deleteCompleteCart(oid);
	}

	/**
	 * 更新订单状态
	 * 
	 * @param orderId
	 * @param optType
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateOrderState(int orderId, byte optType) {
		BspOrders order = new BspOrders();
		order.setOid(orderId);
		order.setOrderstate(optType);
		return ordersMapper.updateByPrimaryKeySelective(order);
	}

	/**
	 * 更新订单支付状态
	 * 
	 * @param orderId
	 * @param optType
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateOrderPayState(int orderId, String paystate) {
		BspOrders order = new BspOrders();
		order.setOid(orderId);
		order.setPaystate(paystate);
		return ordersMapper.updateByPrimaryKeySelective(order);
	}

	/**
	 * 删除订单
	 * 
	 * @param oid
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean deleteOrder(int oid) {
		try {
			// 删除订单
			ordersMapper.deleteByPrimaryKey(oid);
			// 删除订单产品
			BspOrderproductsExample ope = new BspOrderproductsExample();
			ope.or().andOidEqualTo(oid);
			orderproductsMapper.deleteByExample(ope);
			// 删除订单动作
			BspOrderactionsExample oae = new BspOrderactionsExample();
			oae.or().andOidEqualTo(oid);
			orderactionsMapper.deleteByExample(oae);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public BspOrders getOrderById(int oid) {
		return ordersMapper.selectByPrimaryKey(oid);
	}

	public List<BspOrderproducts> getOrderproductsByOrderId(int oid) {
		BspOrderproductsExample boe = new BspOrderproductsExample();
		boe.or().andOidEqualTo(oid);
		List<BspOrderproducts> boelist = orderproductsMapper
				.selectByExample(boe);
		return boelist;

	}

	public List<BspOrderactions> getOrderActionByOrderId(int oid) {
		BspOrderactionsExample boa = new BspOrderactionsExample();
		boa.or().andOidEqualTo(oid);
		List<BspOrderactions> boelist = orderactionsMapper.selectByExample(boa);
		return boelist;

	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteSelected(String ids) {
		List<Integer> idList = new ArrayList<Integer>();

		if (ids != null && ids.length() > 0) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				idList.add(Integer.parseInt(id));
			}
		}

		deleteOrders(idList);
		deleteOrdersAction(idList);
		deleteOrdersProducts(idList);
	}

	/**
	 * 批量删除订单
	 * 
	 * @param idList
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteOrders(List<Integer> idList) {
		BspOrdersExample oe = new BspOrdersExample();
		oe.or().andOidIn(idList);
		ordersMapper.deleteByExample(oe);
	}

	/**
	 * 批量删除订单操作记录
	 * 
	 * @param idList
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteOrdersAction(List<Integer> oidList) {
		BspOrderactionsExample oe = new BspOrderactionsExample();
		oe.or().andOidIn(oidList);
		orderactionsMapper.deleteByExample(oe);
	}

	/**
	 * 批量删除订单产品
	 * 
	 * @param idList
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteOrdersProducts(List<Integer> oidList) {
		BspOrderproductsExample oe = new BspOrderproductsExample();
		oe.or().andOidIn(oidList);
		orderproductsMapper.deleteByExample(oe);
	}

	/**
	 * 获取区域的运费 装卸费
	 *
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	public String getProductPriceByFreight(int pid, int regionsId,
			int priceType, int level) {
		return ordersMapper.getProductPriceByFreight(pid, regionsId, priceType,
				level);
	}

	/**
	 * 获取区域的产品吨位
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	public int getProductTonnage(CartSnapVO cartSnap) {
		if (cartSnap != null) {

			float totalweight = cartSnap.getWeight() * cartSnap.getCount()
					/ 1000;
			int level = orderProductPriceService.getPriceLevel(totalweight);
			return level;
		}
		return 0;
	}

	public List<BspWorktime> selectByWorkTime(int pid, String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int type = 1;
		// Calendar c = Calendar.getInstance();
		// try {
		// c.setTime(format.parse(date));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// int dayForWeek = 0;
		// if (c.get(Calendar.DAY_OF_WEEK) == 1) {
		// dayForWeek = 7;
		// } else {
		// dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		// }
		// if (dayForWeek == 6 || dayForWeek == 7) {
		// type = 2;
		// } else {
		// type = 1;
		// }
		List<BspWorktime> wtList = mapper.selectByWorkTime(pid, type);
		return wtList;
	}

	/**
	 * 写入短信表
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertMessage(BspMessage bspMessage) {
		messageMapper.insertMessage(bspMessage);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertMessageAll(BspMessage bspMessage) {
		messageMapper.insertMessageAll(bspMessage);
	}

	// 根据订单号查询短信
	public List<BspMessage> getUserMessageValidity(String orderNumber,
			String telNum, String verfcode) {
		Map<String, Object> par = new HashMap();
		par.put("VERIF_CODE", verfcode);
		par.put("TEL_NUM", telNum);
		par.put("orderNumber", orderNumber);
		return messageMapper.getUserMessageValidity(par);
	}

	/**
	 * 写入订单验证表
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertOrderVerification(
			BsporderVerification bsporderVerification) {
		bsporderVerificationMapper
				.insertOrderVerification(bsporderVerification);
	}

	/**
	 * 更新订单验证表
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOrderVerificationByOrder(String orderNumber,
			String VerificationCode) {
		bsporderVerificationMapper.updateOrderVerificationByOrder(orderNumber,
				VerificationCode);
	}

	/**
	 * 根据验证码和订单号查有效期
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	public String getPorderValidity(String orderNumber, String VerificationCode) {
		String result = "";
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMddhhmm").format(date);
		BsporderVerification bsporderVerification = bsporderVerificationMapper
				.getOrderVerification(orderNumber, VerificationCode);
		BspMessage bspMessage = messageMapper
				.getMessageValidity(VerificationCode);
		if (bsporderVerification == null) {
			result = "请核对验证码是否正确！";
		} else {
			String state = bspMessage.getState();
			if (!"0".equals(state)) {
				result = "该验证码已过期或已使用！";
			} else {
				String valid = bsporderVerification.getValid();
				if (valid.compareTo(dateStr) < 0) {
					messageMapper.updateMessageValidity(VerificationCode);
					result = "该验证码已过期，请重新获取验证码！";
				} else {
					messageMapper.updateMessageValidity(VerificationCode);
					bsporderVerificationMapper
							.updateOrderVerification(VerificationCode);
					result = "1";
				}
			}
		}
		return result;
	}

	/**
	 * 获取区域的运费 装卸费
	 *
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	public String getCost(CartSnapVO cartSnap,
			List<BspProductsregionsprice> list) {
		String priceRange = "";
		float heavy = cartSnap.getWeight() * cartSnap.getCount();
		// 取区域物品运费装卸费
		// List<OrderPriceVO> list = ordersMapper.getCost(pid, regionsId,
		// priceType);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size() - 1) {
					double weight1 = list.get(i).getBuyminquan();
					double weight2 = list.get(i + 1).getBuyminquan();
					if (weight1 <= heavy && heavy < weight2) {
						priceRange = list.get(i).getPrice().toString();
						break;
					}
				}
				if (i == list.size() - 1) {
					double weight = list.get(i).getBuyminquan();
					if (weight <= heavy) {
						priceRange = list.get(i).getPrice().toString();
					}
				}
			}
		}
		return priceRange;
	}

	/**
	 * 获取区域的产品吨位
	 * 
	 * @param cartSnap
	 * @param regionsId
	 * @return
	 */
	public int countProduct(int pid) {
		return ordersMapper.countProduct(pid);
	}

	/**
	 * 根据物品ID查生产厂家
	 * 
	 * @param params
	 * @return
	 */
	public BspProductattributes getManufacturerbyPid(int pid) {
		return ordersMapper.getManufacturerbyPid(pid);
	};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getRegionProductIds(int regionId, String productIds) {
		Map<Integer, Object> gcIdPidsMap = new HashMap();
		Map<String, Object> errInforMap = new HashMap();
		List<String> hasPidList = new ArrayList();
		errInforMap.put("status", true);
		// 首先获取区域内可以销售的商品的id的列表
		if (productIds != null) {
			String[] pids = productIds.split(",");
			for (int i = 0; i < pids.length; i++) {
				String pid = pids[i];
				int ipid = Integer.valueOf(pid);
				if (ipid == 0) {
					continue;
				}
				// 首先用产品的品牌属性值来判断该区域中是否有关联
				Map<String, Object> par = new HashMap();
				par.put("REGIONS_ID", regionId);
				par.put("PRODUCT_ID", ipid);
				List<Integer> ibList = bspAreaManagerMapper
						.selProidProRegionId(par);
				// 如果产品和区域已经关联了则放到map中
				if (ibList.size() > 0) {
					hasPidList.add(pid);
				} else {
					// 如果产品和区域还没有关联则设置状态为失败
					errInforMap.put("status", false);
					errInforMap.put("msg", "某些产品在该区域未销售，请修改购物车。");
					// break;
				}
			}
		}
		// 然后查找区域内
		// 区域下有产品则尝试寻找产品的门店
		List<BspSaleaddress> mdSaleaddrList = new ArrayList();
		List<BspSaleaddress> gcSaleaddrList = new ArrayList();
		// if ((Boolean) errInforMap.get("status") != false) {
		// 首先查找没有与产品关联的区域的id
		BspProductsregionsExample bpe = new BspProductsregionsExample();
		bpe.createCriteria().andRegionsidEqualTo(regionId)
				.andProductidEqualTo(-1);
		// 直接值查找门店信息即可
		List<BspProductsregions> proregionList = bspProductsregionsMapper
				.selectByExample(bpe);
		// 查找门店
		for (int pi = 0; pi < proregionList.size(); pi++) {
			BspProductsregions proregion = proregionList.get(pi);
			int proregionid = proregion.getId();
			// 获取门店的id
			List sealIdList = bspAreaManagerMapper
					.selSealidByProRegionId(proregionid);
			if (sealIdList.size() > 0) {
				for (int si = 0; si < sealIdList.size(); si++) {
					Integer sid = (Integer) sealIdList.get(si);
					BspSaleaddressExample bsre = new BspSaleaddressExample();
					bsre.createCriteria().andIdEqualTo(sid);
					List<BspSaleaddress> saleaddrList = saleaddressMapper
							.selectByExample(bsre);
					mdSaleaddrList.addAll(saleaddrList);
				}
			}

		}
		for (int pi = 0; pi < proregionList.size(); pi++) {
			BspProductsregions proregion = proregionList.get(pi);
			int proregionid = proregion.getId();
			String[] pids = productIds.split(",");
			for (int i = 0; i < pids.length; i++) {
				String pid = pids[i];
				int ipid = Integer.valueOf(pid);
				if (ipid == 0) {
					continue;
				}
				// 查找产品相关的工厂，首先查找产品的属性，通过placeid和proregionid来获取工厂信息
				Map<String, Object> par = new HashMap();
				par.put("PRODUCT_ID", ipid);
				par.put("PRODUCT_REGIONS_ID", proregionid);
				List gcids = bspAreaManagerMapper.selGCSealidByProRegionId(par);
				if (gcids.size() > 0) {
					for (int si = 0; si < gcids.size(); si++) {
						Integer sid = (Integer) gcids.get(si);
						BspSaleaddressExample bsre = new BspSaleaddressExample();
						bsre.createCriteria().andIdEqualTo(sid);
						List<BspSaleaddress> saleaddrList = saleaddressMapper
								.selectByExample(bsre);
						for (int ai = 0; ai < saleaddrList.size(); ai++) {
							BspSaleaddress addr = saleaddrList.get(ai);
							String mapPids = (String) gcIdPidsMap.get(addr
									.getId());
							if (mapPids != null) {
								mapPids += pid + ",";
							} else {
								mapPids = pid + ",";
								gcSaleaddrList.add(addr);
							}
							gcIdPidsMap.put(addr.getId(), mapPids);

						}
					}
				}
			}
		}

		//
		// }
		Iterator ite = gcIdPidsMap.keySet().iterator();
		String gcIdsMapStr = "";
		while (ite.hasNext()) {
			Integer gcid = (Integer) ite.next();
			String ids = (String) gcIdPidsMap.get(gcid);
			gcIdsMapStr += gcid + ":" + ids + "0;";
		}

		Map<String, Object> rsMap = new HashMap();
		if ((Boolean) errInforMap.get("status") != false) {
			rsMap.put("status", true);
		} else {
			rsMap.put("status", false);
			rsMap.put("msg", errInforMap.get("msg"));
		}
		rsMap.put("mdSaleaddrList", mdSaleaddrList);
		rsMap.put("gcSaleaddrList", gcSaleaddrList);
		rsMap.put("gcIdPidsMap", gcIdsMapStr);
		rsMap.put("hadPids", hasPidList);

		return rsMap;
	}

	/**
	 * 保存证件信息
	 * 
	 * @param orderExtlist
	 * @param orderId
	 */
	public void saveOrderextList(List<BspOrderext> orderExtlist, int orderId) {
		for (int i = 0; i < orderExtlist.size(); i++) {
			BspOrderext orderext = orderExtlist.get(i);
			orderext.setOid(orderId);
			bspOrderextMapper.insertSelective(orderext);
		}

	}

	/**
	 * 通过验证码验证订单并将待确认的订单状态转为待发货
	 * 
	 * @param ordernum
	 * @param vercode
	 * @return 验证是否通过
	 */
	public ResponseData verifyOrder(Integer userid, Integer orderid,
			String vercode) {

		BspOrders order = ordersMapper.selectByPrimaryKey(orderid);
		if (order == null)
			return new ResponseData("failed", "验证失败！订单不存在！");

		List<BspMessage> msgList = this.getUserMessageValidity(order.getOsn(),
				null, vercode);
		if (msgList != null && !msgList.isEmpty()) {
			BspMessage bmsg = msgList.get(0);
			String valid = bmsg.getValid();
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmm");
			String now = sFormat.format(new Date());
			// 验证码已过期
			if (now.compareTo(valid) > 0) {
				return new ResponseData("failed", "验证失败！验证码已过期！");
			} else {
				// 更新验证码状态
				Map<String, Object> par = new HashMap();
				par.put("id", bmsg.getId());
				messageMapper.updateMessageState(par);

				// 写日志
				addOrderAction(userid, "商家", (short) 0,
						(byte) OrderStateEnum.Verified.ordinal(), "商家",
						"商家通过通过验证码验证了订单！", orderid);
				addOrderAction(userid, "商家", (short) 0,
						(byte) OrderStateEnum.PreSend.ordinal(), "商家",
						"订单待发货！", orderid);
				// 验证通过了，更改订单的状态
				order.setOrderstate((byte) OrderStateEnum.Verified.ordinal());
				ordersMapper.updateByPrimaryKeySelective(order);
			}
		} else {
			return new ResponseData("failed", "验证失败！订单号或者验证码错误！");

		}

		return new ResponseData("验证成功！");
	}

	// 订单详情
	public Map<String, Object> OrderStateQuery(String ordernum) {
		Map<String, Object> map = new HashMap();

		BspOrdersExample boe = new BspOrdersExample();
		boe.createCriteria().andOsnEqualTo(ordernum);
		List<BspOrders> orders = ordersMapper.selectByExample(boe);
		map = this.queryRefInfor(orders);
		return map;
	}

	// 订单详情
	public Map<String, Object> queryOrderInforById(Integer id) {
		Map<String, Object> map = new HashMap();

		BspOrdersExample boe = new BspOrdersExample();
		boe.createCriteria().andOidEqualTo(id);
		List<BspOrders> orders = ordersMapper.selectByExample(boe);
		map = this.queryRefInfor(orders);
		return map;
	}

	// 订单详情
	private Map<String, Object> queryRefInfor(List<BspOrders> orders) {
		Map<String, Object> map = new HashMap();
		map.put("state", "success");
		if (orders != null && !orders.isEmpty()) {
			BspOrders order = orders.get(0);
			int oid = order.getOid();
			// 订单跟踪
			BspOrderactionsExample boae = new BspOrderactionsExample();
			boae.createCriteria().andOidEqualTo(oid);
			boae.setOrderByClause("actiontime desc");
			List<BspOrderactions> boalist = orderactionsMapper
					.selectByExample(boae);

			// 配送信息
			BspSendorderExample e = new BspSendorderExample();
			e.or().andOidEqualTo(oid);
			List<BspSendorder> sendList = bspSendorderMapper
					.selectByExampleWithBLOBs(e);
			BspSendorder sender = null;
			if (sendList != null && sendList.size() > 0)
				sender = sendList.get(0);

			// 支付信息
			BspPayorderExample e2 = new BspPayorderExample();
			e2.or().andOidEqualTo(oid);
			List<BspPayorder> payList = bspPayorderMapper
					.selectByExampleWithBLOBs(e2);
			BspPayorder payorder = null;
			if (payList != null && payList.size() > 0)
				payorder = payList.get(0);

			// 订单取消原因和图片
			BspCancelorderReason reason = null;
			BspCancelorderReasonExample e3 = new BspCancelorderReasonExample();
			e3.or().andOidEqualTo(oid);
			List<BspCancelorderReason> clist = bspCancelorderReasonMapper
					.selectByExampleWithBLOBs(e3);
			if (clist != null && clist.size() > 0)
				reason = clist.get(0);

			List<BspCancelorderPic> cancelpicList = null;
			if (reason != null) {
				BspCancelorderPicExample e4 = new BspCancelorderPicExample();
				e4.or().andReasonidEqualTo(reason.getId());
				cancelpicList = bspCancelorderPicMapper.selectByExample(e4);
			}

			// 工厂代收款
			BspOrderpricemny factoryMoney = null;
			BspOrderpricemnyExample e5 = new BspOrderpricemnyExample();
			List<BspOrderpricemny> BspOrderpricemnyList = bspOrderpricemnyMapper
					.selectByExample(e5);
			if (BspOrderpricemnyList != null && BspOrderpricemnyList.size() > 0)
				factoryMoney = BspOrderpricemnyList.get(0);

			// 商品清单
			BspOrderproductsExample bope = new BspOrderproductsExample();
			bope.or().andOidEqualTo(oid);
			List<BspOrderproducts> boplist = new ArrayList<BspOrderproducts>();
			boplist = orderproductsMapper.selectByExample(bope);
			BspRegions br = brm.selectByPrimaryKey(order.getRegionid());
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String pickWay = order.getShipsystemname();
			// 1，2 表示自提，查询自提证件信息
			List<BspOrderext> orderext = new ArrayList<BspOrderext>();
			if ("1".equals(pickWay) || "2".equals(pickWay)) {
				BspOrderextExample boee = new BspOrderextExample();
				boee.createCriteria().andOidEqualTo(oid);
				orderext = bspOrderextMapper.selectByExample(boee);
			}

			// 发票信息
			BspInvoice invoice = null;
			String needInvoice = order.getNeedinv();
			if ("1".equals(needInvoice)) {
				BspInvoiceExample boee = new BspInvoiceExample();
				boee.createCriteria().andOidEqualTo(oid);
				List<BspInvoice> invlist = bspInvoiceMapper
						.selectByExample(boee);
				if (invlist != null && !invlist.isEmpty()) {
					invoice = invlist.get(0);
				}
			}
			// 工程或者门店的信息
			Integer salerid = order.getSalerid();
			if (salerid != null) {
				BspSaleaddress saleraddr = saleaddressMapper
						.selectByPrimaryKey(salerid);
				map.put("saleraddr", saleraddr);
			}

			map.put("orderinfo", order);
			map.put("regions", br);
			map.put("boalist", boalist);// 跟踪信息
			map.put("sender", sender);// 配送信息
			map.put("payorder", payorder);// 支付信息
			map.put("reason", reason);// 订单取消原因
			map.put("cancelpicList", cancelpicList);// 订单取消图片
			map.put("factoryMoney", factoryMoney);// 工厂代收信息

			map.put("formate", formate);
			map.put("boplist", boplist);
			map.put("orderext", orderext);
			map.put("invoice", invoice);

		} else {
			map.put("state", "failed");
			map.put("msg", "验证失败！订单不存在！");
		}
		return map;
	}

	/**
	 * 发送订单信息和订单的验证码信息
	 * 
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public boolean sendOrderMsg(Integer orderid, String LOGINNAME)
			throws Exception {

		BspOrders order = ordersMapper.selectByPrimaryKey(orderid);
		if (order != null) {
			BspOrderproductsExample bore = new BspOrderproductsExample();
			bore.createCriteria().andOidEqualTo(orderid);
			List<BspOrderproducts> orderProducts = orderproductsMapper
					.selectByExample(bore);
			String details = "";
			if (orderProducts != null) {
				for (BspOrderproducts product : orderProducts) {
					int pid = product.getPid();
					List<BspAttributevalues> getProductAttrByID = bspProductsService
							.getProductAttrByID(pid);
					String attrName = "";
					String attrSpecification = "";
					for (BspAttributevalues bspAttributevalues : getProductAttrByID) {
						if (bspAttributevalues.getAttrid() == 52) {
							attrName += bspAttributevalues.getAttrvalue() + " ";
						}
						if (bspAttributevalues.getAttrid() == 49) {
							attrSpecification += bspAttributevalues
									.getAttrvalue() + " ";
						}
						System.out.println(attrName);
					}
					details += attrName + attrSpecification.trim() + "规格"
							+ product.getWeight() + "吨";
				}
			}
			// 生成发送短信的内容
			String verificationCode = OrderNoGenerator.getVerificationCode();
			String pikcDateStr = DateUtil.DateToString(order.getBesttime(),
					DateStyle.YYYY_MM_DD);
			String body = StringHandler.formateString(
					AdminuserDefVal.MSG_SAVE_ORDER,
					LOGINNAME,
					DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD),
					details,
					order.getOsn(),
					verificationCode,
					pikcDateStr + " " + order.getEnddate(),
					DateUtil.DateToString(order.getBesttime(),
							DateStyle.YYYY_MM_DD) + " " + order.getEnddate(),
					AdminuserDefVal.TEL_NUM_HS_ELESHOP);
			// 保存短信信息
			BspMessage bspMessage = new BspMessage();
			BsporderVerification bsporderVerification = new BsporderVerification();
			bspMessage.setMobile(order.getMobile());
			bspMessage.setMessage(body);
			bspMessage.setState("0");
			bspMessage.setValid(pikcDateStr.replace("-", "")
					+ order.getEnddate().replace(":", ""));
			bspMessage.setCode(verificationCode);
			bspMessage.setOrderNumber(order.getOsn());
			// 订单验证
			bsporderVerification.setState("0");
			bsporderVerification.setVerificationCode(verificationCode);
			bsporderVerification.setOrderNumber(order.getOsn());
			bsporderVerification.setValid(pikcDateStr.replace("-", "")
					+ order.getEnddate().replace(":", ""));
			// 向买家发送短息
			String rs = HttpRequest.sendMsm(order.getMobile(), body);
			// if (rs.equals("1")) {// 短信发送成功
			this.insertMessageAll(bspMessage);
			this.insertOrderVerification(bsporderVerification);
			return true;
			// } else {// 短信发送失败
			// return true;
			// }
		} else {
			return false;
		}

	}

	/**
	 * 查询门店/工厂的订单
	 * 
	 * @param storeid
	 *            工厂或者门店id
	 * @param roleid
	 *            1表示普通用户订单，2表示经销商订单
	 * @param ordernum
	 *            订单号
	 * @param productname
	 *            产品名称
	 * @param orderstate
	 *            订单状态
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 * @throws ParseException
	 */
	public List<BspOrders> queryOrder(BusinessRequestParam p)
			throws ParseException {

		List<Integer> orderidList = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(p.getProductname())) {
			BspOrderproductsExample example = new BspOrderproductsExample();
			example.or().andNameLike("%" + p.getProductname() + "%");
			List<BspOrderproducts> orderProductList = orderproductsMapper
					.selectByExample(example);

			for (BspOrderproducts b : orderProductList)
				orderidList.add(b.getOid());

		}
		// 分页查询要放在合适的位置
		PageHelper.startPage(p.getPage().getPageNum(), p.getPage()
				.getPageSize());
		BspOrdersExample e = new BspOrdersExample();
		Criteria c = e.createCriteria();
		c.andNcstateEqualTo("0");

		// // 工厂订单
		// if (p.getStoreType().equals(2)) {
		// // 工厂B2C
		// if (p.getRoleid().equals(1))
		// c.andSaleridEqualTo(p.getStoreid()).andUserroleEqualTo(
		// p.getRoleid());
		// // 工厂B2B
		// else if (p.getRoleid().equals(2))
		// c.andUserroleEqualTo(p.getRoleid());
		// }
		// // 经销商订单
		// else if (p.getStoreType().equals(1))
		// c.andSaleridEqualTo(p.getStoreid()).andUserroleEqualTo(
		// p.getRoleid());

		// 经销商买入的订单，不用管关联工厂门店id，而是关联用户id
		if (p.getStoreType().equals(1) && p.getRoleid().equals(2))
			c.andUserroleEqualTo(p.getRoleid()).andUidEqualTo(p.getUserid());
		else
			c.andSaleridEqualTo(p.getStoreid()).andUserroleEqualTo(
					p.getRoleid());

		if (StringUtils.isNotEmpty(p.getOrdernum()))
			c.andOsnLike("%" + p.getOrdernum() + "%");
		if (StringUtils.isNotEmpty(p.getProductname())) {
			if (orderidList.size() == 0)
				orderidList.add(-1);
			c.andOidIn(orderidList);
		}

		if (p.getOrderstate() != null && p.getOrderstate() != -1) {
			// 订单的待收款，待收款状态
			Byte state1 = (byte) OrderStateEnum.PreReceive.ordinal();
			Byte state2 = (byte) OrderStateEnum.PrePayCheck.ordinal();
			if (p.getOrderstate() == state1 || p.getOrderstate() == state2)
				c.andPaystateEqualTo("0");
			else
				c.andOrderstateEqualTo(p.getOrderstate());
		}

		SimpleDateFormat DateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 2015-08-25 00:00:00
		if (StringUtils.isNotEmpty(p.getBeginTime()))
			c.andAddtimeGreaterThanOrEqualTo(DateFormat.parse(p.getBeginTime()));

		if (StringUtils.isNotEmpty(p.getEndTime()))
			c.andAddtimeLessThanOrEqualTo(DateFormat.parse(p.getEndTime()));

		e.setOrderByClause("oid desc ");

		List<BspOrders> list = ordersMapper.selectByExample(e);
		return list;
	}

	/**
	 * 查询个人用户的订单
	 * 
	 * @param ordernum
	 *            订单号
	 * @param productname
	 *            产品名称
	 * @param orderstate
	 *            订单状态
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 * @throws ParseException
	 */
	public List<BspOrders> queryOrder(ClientRequestParam p)
			throws ParseException {

		List<Integer> orderidList = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(p.getProductname())) {
			BspOrderproductsExample example = new BspOrderproductsExample();
			example.or().andNameLike("%" + p.getProductname() + "%");
			List<BspOrderproducts> orderProductList = orderproductsMapper
					.selectByExample(example);

			for (BspOrderproducts b : orderProductList)
				orderidList.add(b.getOid());

		}
		// 分页查询要放在合适的位置
		PageHelper.startPage(p.getPage().getPageNum(), p.getPage()
				.getPageSize());
		BspOrdersExample e = new BspOrdersExample();
		Criteria c = e.createCriteria();
		c.andNcstateEqualTo("0");

		if (StringUtils.isNotEmpty(p.getOrdernum()))
			c.andOsnLike("%" + p.getOrdernum() + "%");
		if (StringUtils.isNotEmpty(p.getProductname())) {
			if (orderidList.size() == 0)
				orderidList.add(-1);
			c.andOidIn(orderidList);
		}

		if (p.getOrderstate() != null && p.getOrderstate() != -1) {
			// 订单的待收款，待收款状态
			Byte state1 = (byte) OrderStateEnum.PreReceive.ordinal();
			Byte state2 = (byte) OrderStateEnum.PrePayCheck.ordinal();
			if (p.getOrderstate() == state1 || p.getOrderstate() == state2)
				c.andPaystateEqualTo("0");
			else
				c.andOrderstateEqualTo(p.getOrderstate());
		}

		SimpleDateFormat DateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 2015-08-25 00:00:00
		if (StringUtils.isNotEmpty(p.getBeginTime()))
			c.andAddtimeGreaterThanOrEqualTo(DateFormat.parse(p.getBeginTime()));

		if (StringUtils.isNotEmpty(p.getEndTime()))
			c.andAddtimeLessThanOrEqualTo(DateFormat.parse(p.getEndTime()));

		e.setOrderByClause("oid desc ");

		List<BspOrders> list = ordersMapper.selectByExample(e);
		return list;
	}

	/**
	 * 门店/工厂订单总数量
	 * 
	 * @param params
	 * @return
	 */
	public int getOrderCountByStore(Map<String, Object> params) {
		return ordersMapper.getOrderCount(params);
	}

}
