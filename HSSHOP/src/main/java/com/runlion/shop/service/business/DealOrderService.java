package com.runlion.shop.service.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.common.Constant;
import com.runlion.shop.common.defaultval.AdminuserDefVal;
import com.runlion.shop.dao.BspAreaManagerMapper;
import com.runlion.shop.dao.BspInvoiceMapper;
import com.runlion.shop.dao.BspNcenterinforMapper;
import com.runlion.shop.dao.BspOrderactionsMapper;
import com.runlion.shop.dao.BspOrderextMapper;
import com.runlion.shop.dao.BspOrderpricemnyMapper;
import com.runlion.shop.dao.BspOrderproductsMapper;
import com.runlion.shop.dao.BspOrdersMapper;
import com.runlion.shop.dao.BspProductsMapper;
import com.runlion.shop.dao.BspProductskusMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspNcenterinforExample;
import com.runlion.shop.entity.BspOrderactions;
import com.runlion.shop.entity.BspOrderext;
import com.runlion.shop.entity.BspOrderpricemny;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductskus;
import com.runlion.shop.entity.BspProductskusExample;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.enums.OrderStateEnum;
import com.runlion.shop.service.BspSaleaddressService;
import com.runlion.shop.service.OrderNoGenerator;
import com.runlion.shop.service.ncinterface.NcRequestParam;
import com.runlion.shop.vo.DealOrderVO;

@Service
public class DealOrderService {
	private static Logger logger = Logger.getLogger(DealOrderService.class);

	@Autowired
	private BspSaleaddressMapper saleaddressMapper;
	@Autowired
	private BspOrdersMapper ordersMapper;
	@Autowired
	private BspProductsMapper bspProductsMapper;
	@Autowired
	private BspNcenterinforMapper bspNcenterinforMapper;
	@Autowired
	private BspOrderproductsMapper bspOrderproductsMapper;
	@Autowired
	private BspOrderextMapper bspOrderextMapper;
	@Autowired
	private BspInvoiceMapper bspInvoiceMapper;
	@Autowired
	private BspOrderactionsMapper orderactionsMapper;
	@Autowired
	private BspProductskusMapper bspProductskusMapper;
	@Autowired
	private BspOrderpricemnyMapper bspOrderpricemnyMapper;

	@Autowired
	private BusinessService businessService;
	@Autowired
	private BspAreaManagerMapper bspAreaManagerMapper;
	@Autowired
	private BspSaleaddressService bspSaleaddressService;

	// ///

	/**
	 * 保存经销商订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Map<String, Object> saveDealOrder(DealOrderVO delOrder,
			HttpSession session) throws Exception {
		Map<String, Object> rsMap = new HashMap();
		// 预处理订单信息
		this.handDealOrder(delOrder);
		// 向商城数据库保存订单信息，但是订单的nc状态为1
		boolean isok = this.saveOrderToDb(delOrder);
		if (isok) {
			rsMap.put("state", "waiting");
			rsMap.put("content", "正在生成订单，请耐心等待。。。");
			session.setAttribute(DealOrderServiceHelper.ORDER_NOW_STATE
					+ delOrder.getOrder().getOid(), rsMap);

			// 接着向推动订单,实际是多次推送
			this.orderToNc(delOrder, session);
		} else {
			rsMap.put("state", "error");
			rsMap.put("content", "水泥商城订单保存失败。");
		}

		return rsMap;
	}

	@Transactional
	public boolean saveOrderToDb(DealOrderVO order) {
		boolean isOk = true;
		// 1 保存订单基本信息
		BspOrders forder = order.getOrder();
		if (this.saveFOrder(forder) == null) {
			isOk = false;
		}
		// 订单基本信息保存成功，继续操作
		if (isOk) {
			int oid = forder.getOid();
			// 2 处理订单的物品信息
			List<BspOrderproducts> proList = order.getProductList();
			for (int i = 0; i < proList.size(); i++) {
				BspOrderproducts proInfor = proList.get(i);
				proInfor.setOid(oid);
				bspOrderproductsMapper.insertSelective(proInfor);
			}
			// 3 保存订单的扩展信息
			List<BspOrderext> extList = order.getOrderExtList();
			for (int i = 0; i < extList.size(); i++) {
				BspOrderext extInfor = extList.get(i);
				extInfor.setOid(oid);
				bspOrderextMapper.insertSelective(extInfor);
			}
			// 4 保存订单的应付总额及平均单价信息
			BspOrderpricemny pricemny = order.getOrderPricemny();
			if (order.getOrder().getPaymode() != 0 && pricemny != null) {
				pricemny.setOid(oid);
				pricemny.setUptime(new Date());
				bspOrderpricemnyMapper.insertSelective(pricemny);
			}
			// 5 订单处理动作信息
			addOrderAction(forder.getUid(), "用户", (short) 0, (byte) 0, "非管理员",
					"提交订单，请等待系统确认", oid);

		}
		return isOk;
	}

	/**
	 * 预处理定单的信息
	 * 
	 * @param order
	 * @return
	 */
	public boolean handDealOrder(DealOrderVO order) {
		BspOrders forder = order.getOrder();
		// 补齐定点的基本信息
		forder.setUserrole(Constant.UserType_store);// 设置购买时的角色为经销商
		forder.setNcstate("1");
		//
		BigDecimal totalProMny = BigDecimal.ZERO;
		BigDecimal totalOrderMny = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;

		// 获取用户关联的门店的信息
		int userId = forder.getUid();
		BspSaleaddress salerinfor = businessService.getStoreInfor(userId,
				Constant.UserType_store);

		// 处理订单的物品信息
		List<BspOrderproducts> proList = order.getProductList();
		for (int i = 0; i < proList.size(); i++) {
			BspOrderproducts proInfor = proList.get(i);
			// 获取物品的单价
			int pid = proInfor.getPid();
			BspProducts pro = this.getProInfor(pid);
			BigDecimal price = this.getProNCPrice(pro.getNcpronum(),
					salerinfor.getPkcorp());
			proInfor.setMarketprice(price);
			proInfor.setCostprice(price);
			proInfor.setShopprice(price);
			proInfor.setUid(userId);
			proInfor.setAddtime(new Date());
			proInfor.setWeight(pro.getWeight());
			int brandid = this.getProBrandid(pid);
			proInfor.setBrandid(brandid);
			// 计算该产品总价格
			BigDecimal itemMny = BigDecimal.ZERO;
			BigDecimal buycount = new BigDecimal(proInfor.getBuycount());
			// BigDecimal perWeight = new BigDecimal(pro.getWeight());
			itemMny = price.multiply(buycount);
			BigDecimal itemAmount = new BigDecimal(proInfor.getBuycount());
			proInfor.setItemtotalmny(itemMny);
			//
			totalProMny = totalProMny.add(itemMny);
			totalOrderMny = totalOrderMny.add(itemMny);
			totalAmount = totalAmount.add(itemAmount);
			//
		}
		forder.setSurplusmoney(totalOrderMny);
		forder.setOrderamount(totalOrderMny);
		forder.setProductamount(totalProMny);
		Byte payMode = forder.getPaymode();
		if (payMode > 0 && payMode <= AdminuserDefVal.payNames.length) {
			forder.setPayfriendname(AdminuserDefVal.payNames[payMode - 1]);
		}
		forder.setPaysystemname(payMode + "");

		return true;
	}

	/**
	 * 保存订单本身
	 * 
	 * @param forder
	 * @return
	 */
	@Transactional
	public BspOrders saveFOrder(BspOrders forder) {
		// 生成并设置订单号
		String orderNum = OrderNoGenerator.getOrderNo();
		forder.setOsn(orderNum);
		// 补全订单的基本信息
		forder.setOrderstate((byte) OrderStateEnum.PreConfirm.ordinal());
		forder.setIsreview((byte) 0);
		forder.setAddtime(new Date());
		//

		//
		int rsi = ordersMapper.insertSelective(forder);
		if (rsi > 0) {
			return forder;
		} else {
			return null;
		}
	}

	/**
	 * 保存订单子表信息
	 * 
	 * @param productList
	 * @return
	 */
	@Transactional
	public boolean saveSubOrder(List<BspOrderproducts> productList) {
		return true;
	}

	/**
	 * 
	 * @param orderExtList
	 * @return
	 */
	@Transactional
	public boolean savaOrderExt(List<BspOrderext> orderExtList) {
		return true;
	}

	/**
	 * 获取产品的详细信息
	 * 
	 * @param pid
	 * @return
	 */
	public BspProducts getProInfor(int pid) {
		BspProducts proInfor = null;
		proInfor = bspProductsMapper.selectByPrimaryKey(pid);
		return proInfor;
	}

	/**
	 * 获取提供给经销商的产品价格
	 * 
	 * @param pid
	 * @return
	 */
	public BigDecimal getProNCPrice(String proNum, String pkcorp) {
		BigDecimal price = BigDecimal.ZERO;
		BspNcenterinforExample example = new BspNcenterinforExample();
		example.createCriteria().andPidEqualTo(proNum).andStateEqualTo(0)
				.andEidEqualTo(pkcorp);
		List<BspNcenterinfor> elist = bspNcenterinforMapper
				.selectByExample(example);
		if (elist != null && elist.size() > 0) {
			BspNcenterinfor einfor = elist.get(0);
			try {
				BigDecimal tprice = new BigDecimal(einfor.getPrice());
				price = tprice;
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
		return price;
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

	private int getProBrandid(int pid) {
		int brandid = 0;
		//
		BspProductskusExample example = new BspProductskusExample();
		example.createCriteria().andPidEqualTo(pid)
				.andAttridEqualTo(Short.valueOf(Constant.brandId + ""));
		List<BspProductskus> idList = bspProductskusMapper
				.selectByExample(example);
		if (idList != null && !idList.isEmpty()) {
			BspProductskus skuinfor = idList.get(0);
			brandid = skuinfor.getAttrvalueid();
		}
		//
		return brandid;
	}

	public BspSaleaddressMapper getSaleaddressMapper() {
		return saleaddressMapper;
	}

	public void setSaleaddressMapper(BspSaleaddressMapper saleaddressMapper) {
		this.saleaddressMapper = saleaddressMapper;
	}

	public BspOrdersMapper getOrdersMapper() {
		return ordersMapper;
	}

	public void setOrdersMapper(BspOrdersMapper ordersMapper) {
		this.ordersMapper = ordersMapper;
	}

	/**
	 * 
	 * @param delOrder
	 * @param salerinfor
	 * @return
	 * @throws Exception
	 */
	public void orderToNc(DealOrderVO delOrder, HttpSession session)
			throws Exception {
		Date intDate = new Date();
		List<BspOrderext> extlist = delOrder.getOrderExtList();
		String carNum = delOrder.getOrder().getRemark();
		if (extlist != null) {
			for (int ei = 0; ei < extlist.size(); ei++) {
				BspOrderext extinfor = extlist.get(ei);
				if ("1".equals(extinfor.getOexttype())) {
					carNum = extinfor.getOextvalue();
					break;
				}
			}
		}
		String proNcnum = "";
		Double buycount = (double) 0;
		BigDecimal price = BigDecimal.ZERO;
		BigDecimal sumMny = BigDecimal.ZERO;
		List<BspOrderproducts> proList = delOrder.getProductList();
		if (proList != null && !proList.isEmpty()) {
			BspOrderproducts proinfor = proList.get(0);
			proNcnum = proinfor.getPsn();
			buycount = proinfor.getBuycount();
			price = proinfor.getCostprice();
			sumMny = proinfor.getItemtotalmny();
		}
		//
		int userId = delOrder.getOrder().getUid();
		BspSaleaddress salerinfor = businessService.getStoreInfor(userId,
				Constant.UserType_store);
		//
		Integer facid = delOrder.getOrder().getSalerid();
		BspSaleaddress bspSaleaddress = bspSaleaddressService
				.getBspSaleaddressById(facid);
		String facCorp = "";
		if (bspSaleaddress != null) {
			facCorp = bspSaleaddress.getPkcorp();
		}
		// 首先向nc提交订单;参数依次为
		// 订单的NC编号，经销商NC编号，PkCorp,订单初始化日期,车号，备注，存货编码，数量，单价，金额，状态
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		final NcRequestParam param = new NcRequestParam("",
				salerinfor.getPkcorp(), facCorp, sdf.format(intDate), carNum,
				delOrder.getOrder().getBuyerremark(), proNcnum, "1", buycount
						+ "", price.toString(), sumMny.toString(), "0");
		// 声明一个定时器
		ScheduledExecutorService timeService = Executors
				.newSingleThreadScheduledExecutor();
		// 定时器要执行的任务
		DealOrderServiceHelper runnable = new DealOrderServiceHelper(session,
				delOrder, param, 5, timeService, ordersMapper);
		// 第二个参数为首次执行的延时时间
		timeService.scheduleWithFixedDelay(runnable, 0,
				DealOrderServiceHelper.SECONDS_TO_NC, TimeUnit.SECONDS);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getProductFactoryIds(String productIds) {
		Map<String, Object> rsMap = new HashMap();
		if (productIds != null) {
			String[] pids = productIds.split(",");
			String pid = "0";
			for (int pi = 0; pi < pids.length; pi++) {
				String tpid = pids[pi];
				if (!tpid.equals("0") && !tpid.trim().equals("")) {
					pid = tpid;
					break;
				}
			}
			Integer ipit = Integer.valueOf(pid);
			Integer brandid = this.getProBrandid(ipit);
			// BRANDID
			Map<String, Object> par = new HashMap();
			par.put("BRANDID", brandid);
			List<BspSaleaddress> saleAddrList = bspAreaManagerMapper
					.selSaleAddressByBrandid(par);
			if (saleAddrList != null && !saleAddrList.isEmpty()) {
				BspSaleaddress addr = saleAddrList.get(0);
				rsMap.put("factory", addr);
				rsMap.put("state", "success");
			}
		}

		if (rsMap.get("state") == null) {
			rsMap.put("state", "errinfor");
			rsMap.put("msg", "没有找到任何相关的工厂信息。");
		}

		return rsMap;
	}

}
