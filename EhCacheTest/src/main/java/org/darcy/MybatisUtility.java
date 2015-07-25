package org.darcy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 说明: 此文件主要是生成mybatis-generator-config中的table属性 运行此文件后，执行mvn
 * mybatis-generator:generate 即可生成mybatis的model、mapper、xml3个文件
 * 注意：会覆盖所在目录的文件，执行之前注意保存。
 * */

public class MybatisUtility {

	public void addTable(File inputXml) {
		try {
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(inputXml);
			// 取节点context
			@SuppressWarnings("unchecked")
			List<Element> list = doc
					.selectNodes("/generatorConfiguration/context");
			Element context = (Element) list.get(0);
			// delete
			@SuppressWarnings("unchecked")
			List<Element> conList = context.elements();
			for (Element e : conList)
				if (e.getName().equals("table"))
					context.remove(e);
			// add
			List<String> tableList = getTableList();
			for (String str : tableList)
				context.addElement("table").addAttribute("schema", "hsshop")
						.addAttribute("tableName", str);

			// 美化格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 缩减格式 OutputFormat format = OutputFormat.createCompactFormat();

			// 指定XML编码 format.setEncoding("GBK");

			XMLWriter output = new XMLWriter(new FileWriter(inputXml), format);
			output.write(doc);
			output.close();
			System.out.println("成功生成generatorConfig.xml的table节点！");
		}

		catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<String> getTableList() {

		List<String> list = new ArrayList<String>();
		String sql = "select table_name from information_schema.tables where table_schema='hsshop' and table_type='base table'";
		Connection conn = null;
		Statement stmt = null;

		String url = "jdbc:mysql://192.168.158.130:3306/hsshop?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("成功加载MySQL驱动程序");
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				list.add(result.getString(1));
			}
			System.out.println("成功获取数据库表名!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到MySQL驱动程序");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public static void main(String[] argv) {
		MybatisUtility dom4jParser = new MybatisUtility();
		File f = new File("src/main/resources/generatorConfig.xml");
		System.out.println(f.getName() + ":" + f.getAbsolutePath());
		dom4jParser.addTable(f);
	}

}
