package org.darcy.quartz;

//根据产品价格的设置的计划生效时间，判断当前时间是否与之一致，一致则执行生效操作。

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionException;

public class EffectPriceJob {
	public static String FAVORITE_COLOR;
	public static String EXECUTION_COUNT;

	private SimpleDateFormat DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public void execute() throws JobExecutionException {

		Calendar cal = Calendar.getInstance();
		cal.set(2015, 7, 6, 14, 06, 0); // 月份是从0开始的！
		Date planeffecttime = cal.getTime();

		// 判断现在时刻和计划生效时间
		Date now = new Date();
		if (planeffecttime.getTime() - now.getTime() <= 0) {

			System.out.println("计划生效时间： " + DateFormat.format(planeffecttime));
			System.out.println("更新操作时间： " + DateFormat.format(now));

			// 删除任务表中已执行的记录 to do

		} else {
			System.out.println("生效时间未到，请稍后。 ");
		}

	}
}
