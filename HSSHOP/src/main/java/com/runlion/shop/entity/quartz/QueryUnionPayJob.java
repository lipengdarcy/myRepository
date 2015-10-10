package com.runlion.shop.entity.quartz;

//查询订单的状态，定时向银联发送查询请求，确认订单的支付状态

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.runlion.shop.common.Constant;
import com.runlion.shop.entity.BspOrders;
import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.pay.UnionPayService;

public class QueryUnionPayJob {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UnionPayService unionPayService;

	private SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	public void execute() throws JobExecutionException {

		// 1.从数据库获取订单
		Date start, end;
		Calendar cal = Calendar.getInstance();
		end = cal.getTime();
		cal.add(Calendar.HOUR, -1);
		start = cal.getTime();
		List<BspOrders> list = orderService.getUncertainOrderList(start, end);

		// 2.查询银联接口
		if (list == null || list.size() == 0)
			return;
		for (BspOrders job : list) {
			Map<String, String> result = unionPayService.queryOrder(job
					.getOsn());
			// 成功支付，更新订单状态
			if (result.get("respCode").equals("00")) {
				orderService.updateOrderState(job.getOid(),
						(byte) Constant.OrderState_finished);
				orderService.updateOrderPayState(job.getOid(), ""
						+ Constant.OrderPayState_finished);
			}

		}

	}// end of execute()
}
