package com.runlion.shop.entity.quartz;

//根据产品价格的设置的计划生效时间，判断当前时间是否与之一致，一致则执行生效操作。

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.entity.BspJobLog;
import com.runlion.shop.entity.BspProductsregionspriceLog;
import com.runlion.shop.service.PriceUpdateJobService;
import com.runlion.shop.service.region.RegionsPriceService;

public class EffectPriceJob {
	@Autowired
	private PriceUpdateJobService priceUpdateJobService;

	@Autowired
	private RegionsPriceService regionsPriceService;

	private SimpleDateFormat DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings({ "unchecked" })
	public void execute() throws JobExecutionException {
		// 1.从缓存取任务列表
		List<BspJobLog> jobList = (List<BspJobLog>) EHCacheUtil.get("jobList");
		if (jobList == null || jobList.size() == 0) {
			jobList = priceUpdateJobService.getJobList();
			if (jobList == null || jobList.size() == 0)
				return;
			else
				EHCacheUtil.put("jobList", jobList);
		}
		for (BspJobLog job : jobList) {
			int priceLogId = job.getPricelogid();
			BspProductsregionspriceLog log = priceUpdateJobService
					.getProductsregionspriceLog(priceLogId);
			if (log == null)
				continue;
			Date planeffecttime = log.getPlaneffecttime();
			// 2.判断现在时刻和计划生效时间
			Date now = new Date();
			if (planeffecttime.getTime() - now.getTime() <= 0) {
				// 3.更新价格日志的状态为有效，同时更新价格表
				log.setIseffect("1");
				log.setEffecttime(now);
				int pricetype = Integer.valueOf(log.getPricetype());
				if (pricetype <= 20) {// 更新价格信息表
					priceUpdateJobService.updateProductsregionspriceLog(log);
				} else {// 更新扩展价格信息表
					priceUpdateJobService.updateProductsregionsextpriceLog(log);
				}

				// 4.同时从列表删除已经完成的任务,并从缓存清除
				priceUpdateJobService.deleteJob(job);
				jobList = priceUpdateJobService.getJobList();
				EHCacheUtil.put("jobList", jobList);

				System.out.println("job id： " + job.getId() + " 计划生效时间： "
						+ DateFormat.format(planeffecttime));
				System.out.println("更新操作时间： " + DateFormat.format(now));

			}
		}

	}// end of execute()
}
