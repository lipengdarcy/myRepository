package com.runlion.shop;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.runlion.shop.entity.unionpay.UnionPayResult;

public class Test {
	private static SimpleDateFormat DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd-HH:mm:ss");

	public static void main(String[] args) {
		
		mysqlConnection();
		// TODO Auto-generated method stub
		String keywords = "hongshi 红狮    兰亮";
		String[] keys = keywords.split(" ");
		for (String key : keys)
			System.out.println(key);

		Date start, end;
		Calendar cal = Calendar.getInstance();
		end = cal.getTime();
		cal.add(Calendar.HOUR, -1);
		start = cal.getTime();

		System.out.println(DateFormat.format(start));
		System.out.println(DateFormat.format(end));
		System.out.println(DateFormat.format(new Date()));

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		System.out.printf("%d年%d月%d日", c.get(Calendar.YEAR),
				c.get(Calendar.MONDAY) + 1, c.get(Calendar.DATE));

		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date d = sdf.parse("20150820144412");
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// map转对象测试
		UnionPayResult a = new UnionPayResult();
		Map<String, String> map = new HashMap<String, String>();
		map.put("queryId", "201508211738437617288");
		map.put("settleAmt", "100");
		a = (UnionPayResult) a.mapToObject(a, map);
		System.out.println(a.getQueryId() + "--" + a.getSettleAmt());
	}
	
	public static void mysqlConnection(){
		try {
            java.sql.Connection con = null; //定义一个MYSQL链接对象
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsshop_dev?Unicode=true&amp;characterEncoding=utf8", "root", "123456"); //链接本地MYSQL
            System.out.print("Connection success");
            java.sql.Statement stmt; //创建声明
            stmt = con.createStatement();

            //新增一条数据
            //stmt.executeUpdate("INSERT INTO user (username, password) VALUES ('init', '123456')");
            ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
            int ret_id;
            if (res.next()) {
                ret_id = res.getInt(1);
                System.out.print(ret_id);
            }

        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }

	}
}
