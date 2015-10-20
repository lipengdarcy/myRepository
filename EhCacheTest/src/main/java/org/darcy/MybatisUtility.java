package org.darcy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.darcy.entity.BspProducts;
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

	/**
	 * generatorConfiguration文件中增加table节点
	 * 
	 * @return 表名的列表
	 * 
	 * */
	public void addTableToMybatisConfigFile(File inputXml) {
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

	/**
	 * 获取数据库的所有表
	 * 
	 * @return 表名的列表
	 * 
	 * */
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

	/**
	 * 根据对象生成create table sql语句
	 * 
	 * @param entity
	 *            对象
	 * */
	@SuppressWarnings("unchecked")
	public String createTable(Object entity) {

		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE ");
		List<Map<String, Object>> list = getFiledsInfo(entity);
		sql.append(list.get(0).get("obj_name").toString() + "(\n");
		for (int i = 0; i < list.size(); i++) {
			sql.append(list.get(i).get("f_name").toString() + " ");
			sql.append(list.get(i).get("f_type").toString() + " ");
			sql.append("NULL COMMENT '',\n");

		}
		sql.append("PRIMARY KEY (id)  COMMENT '')");
		sql.append("ENGINE = MyISAM DEFAULT CHARACTER SET = UTF8;");

		return sql.toString();
	}

	/**
	 * 根据对象生成insert sql语句
	 * 
	 * @param entity
	 *            对象
	 * */
	public String createInsert(Object entity) {

		String sql = "Insert into ";
		String column = ""; // 列
		String c_values = ""; // 列值
		List<Map<String, Object>> list = getFiledsInfo(entity);
		sql += list.get(0).get("obj_name").toString() + " ";
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).get("f_name").toString() == "id")
				i++;
			if (list.get(i).get("f_value") != null) {

				// System.out.println("属性数据类型：" + list.get(i).get("f_type"));
				column += list.get(i).get("f_name") + ",";
				c_values += "'" + list.get(i).get("f_value") + "',";
			}

		}
		sql += "(" + column.substring(0, column.length() - 1) + ") values ("
				+ c_values.substring(0, c_values.length() - 1) + ");";

		return sql;
	}

	/**
	 * 根据属性名获取属性值
	 * 
	 * @param fieldName
	 *            属性名
	 * @param o
	 *            对象
	 * */
	protected Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			// log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 类名(obj_name) 获取属性类型(f_type)， 属性名(f_name)， 属性值(f_value)的map组成的list
	 * */
	@SuppressWarnings("unused")
	protected List getFiledsInfo(Object o) {

		String obj_name = o.getClass().getSimpleName().toString();
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List<Map> list = new ArrayList();
		Map<String, Object> infoMap;

		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap<String, Object>();

			infoMap.put("obj_name", obj_name);
			infoMap.put("f_type", getMysqlType(fields[i]));

			infoMap.put("f_name", fields[i].getName());
			infoMap.put("f_value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

	// java属性类型转换为mysql类型
	protected String getMysqlType(Field f) {
		String fieldName = f.getType().getName();
		if (fieldName.equals("java.lang.Integer"))
			return "INT";
		if (fieldName.equals("java.lang.Short"))
			return "INT";
		if (fieldName.equals("java.math.BigDecimal"))
			return "DECIMAL";
		if (fieldName.equals("java.lang.Byte"))
			return "TINYINT";
		if (fieldName.equals("java.util.Date"))
			return "DATE";
		if (fieldName.equals("java.lang.String"))
			return "VARCHAR";

		return "error";
	}


	public static void main(String[] argv) {
		MybatisUtility dom4jParser = new MybatisUtility();
		// File f = new File("src/main/resources/generatorConfig.xml");
		// System.out.println(f.getName() + ":" + f.getAbsolutePath());
		// dom4jParser.addTableToMybatisConfigFile(f);

		String sql = dom4jParser.createTable(new BspProducts());
		System.out.println(sql);
	}

}
