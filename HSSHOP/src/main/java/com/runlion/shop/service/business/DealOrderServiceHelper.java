package com.runlion.shop.service.business;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.http.HttpSession;

import com.runlion.shop.dao.BspOrdersMapper;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.service.ncinterface.NcInterface;
import com.runlion.shop.service.ncinterface.NcRequestParam;
import com.runlion.shop.service.ncinterface.NcReturnResult;
import com.runlion.shop.vo.DealOrderVO;

public class DealOrderServiceHelper implements Runnable {

	public static final String ORDER_NOW_STATE = "orderNowState";
	public static final int SECONDS_TO_NC = 10;
	private HttpSession session;
	private DealOrderVO delOrder;
	private NcRequestParam param;
	private int maxtimes = 5;
	private int nowtimes = 1;
	private ScheduledExecutorService timeService;
	private BspOrdersMapper ordersMapper = null;

	public DealOrderServiceHelper() {

	}

	public DealOrderServiceHelper(HttpSession session, DealOrderVO delOrder,
			NcRequestParam param, int maxtimes,
			ScheduledExecutorService timeService, BspOrdersMapper ordersMapper) {
		this.session = session;
		this.delOrder = delOrder;
		this.maxtimes = maxtimes;
		this.param = param;
		this.timeService = timeService;
		this.ordersMapper = ordersMapper;

	}

	@Override
	public void run() {
		Map<String, Object> rsMap = new HashMap();
		try {
			rsMap.put("state", "waiting");
			rsMap.put("content", "正在生成订单，请耐心等待。。。");
			this.session.setAttribute(DealOrderServiceHelper.ORDER_NOW_STATE
					+ delOrder.getOrder().getOid(), rsMap);
			if (nowtimes < this.maxtimes) {
				nowtimes++;
				NcReturnResult ncrs = NcInterface.checkOrderSuccess(param);
				/*
				 * NcReturnResult ncrs = new NcReturnResult();
				 * ncrs.setState("success"); ncrs.setMsg("ceshiso123");
				 */
				// nc成功返回数据
				if (ncrs != null) {
					this.timeService.shutdown();
					// nc中订单保存成功了
					if (ncrs.getState().equals("success")) {
						String ncOrdernum = ncrs.getMsg();
						BspOrders order = new BspOrders();
						order.setOid(delOrder.getOrder().getOid());
						order.setNcordernum(ncOrdernum);
						order.setNcstate("0");
						ordersMapper.updateByPrimaryKeySelective(order);
						rsMap.put("state", "success");
					} else {
						// nc中订单保存失败了
						rsMap.put("state", "error");
						rsMap.put("content", "NC订单生成失败，错误消息如下:" + ncrs.getMsg());
					}
				}
			} else {
				// 超过请求次数不在向nc请求处理
				rsMap.put("state", "error");
				rsMap.put("content", "订单生成失败，错误消息:100099，请联系管理人员。");
				this.timeService.shutdown();
			}
			this.session.setAttribute(DealOrderServiceHelper.ORDER_NOW_STATE
					+ delOrder.getOrder().getOid(), rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public DealOrderVO getDelOrder() {
		return delOrder;
	}

	public void setDelOrder(DealOrderVO delOrder) {
		this.delOrder = delOrder;
	}

	public NcRequestParam getParam() {
		return param;
	}

	public void setParam(NcRequestParam param) {
		this.param = param;
	}

	public int getMaxtimes() {
		return maxtimes;
	}

	public void setMaxtimes(int maxtimes) {
		this.maxtimes = maxtimes;
	}

	public ScheduledExecutorService getTimeService() {
		return timeService;
	}

	public void setTimeService(ScheduledExecutorService timeService) {
		this.timeService = timeService;
	}
}
