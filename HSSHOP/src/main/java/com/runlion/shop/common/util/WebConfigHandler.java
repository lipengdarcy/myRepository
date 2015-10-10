package com.runlion.shop.common.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 用来解析网站配置文件的工具类
 * 
 * @author lwtao
 *
 */
public class WebConfigHandler {

	// 读取传入的路径，返回一个document对象
	public static Document loadInit(String filePath) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filePath));
			document.normalize();
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 修改制定的xml
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean updateXML(String filePath, String tagName,
			String newValue) {
		// 读取传入的路径，返回一个document对象
		Document document = loadInit(filePath);
		try {
			// 获取叶节点
			Element element = document.getDocumentElement();
			NodeList nodeList = element.getElementsByTagName(tagName);
			if (nodeList != null && nodeList.getLength() > 0) {
				Node node = nodeList.item(0);
				if (node != null) {
					if (node.getFirstChild() == null) {
						Text eleText = document.createTextNode(newValue);
						node.appendChild(eleText);
					} else {
						node.getFirstChild().setNodeValue(newValue);
					}

					saveXML(document, filePath);
				}
			} else {
				Element newEle = document.createElement(tagName);
				Text eleText = document.createTextNode(newValue);
				newEle.appendChild(eleText);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 通过传入的对象修改制定的xml
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean updateXMLByObj(String filePath, Object obj) {
		// 读取传入的路径，返回一个document对象
		Document document = loadInit(filePath);
		try {
			// 获取叶节点
			Field fields[] = obj.getClass().getDeclaredFields();
			for (int fi = 0; fi < fields.length; fi++) {
				String tagName = fields[fi].getName();
				String methodName = "get" + StringHandler.upFirstChar(tagName);
				Method setMethod = obj.getClass().getDeclaredMethod(methodName);
				String newValue = (String) setMethod.invoke(obj);
				if (newValue != null) {
					Element element = document.getDocumentElement();
					NodeList nodeList = element.getElementsByTagName(tagName);
					if (nodeList != null && nodeList.getLength() > 0) {
						Node node = nodeList.item(0);
						if (node != null) {
							if (node.getFirstChild() == null) {
								Text eleText = document
										.createTextNode(newValue);
								node.appendChild(eleText);
							} else {
								node.getFirstChild().setNodeValue(newValue);
							}

							saveXML(document, filePath);
						}
					} else {
						Element newEle = document.createElement(tagName);
						Text eleText = document.createTextNode(newValue);
						newEle.appendChild(eleText);
					}
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 把修改后的document写进源文件（更新源文件）
	 * 
	 * @param document
	 * @param filePath
	 * @return
	 */
	public static boolean saveXML(Document document, String filePath) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 通过反射机制从XML文件读取数据并填写到给定的obj中
	 * 
	 * @param obj
	 * @param filePath
	 * @return
	 */
	public static Object getConfigInfor(Object obj, String filePath) {

		Document document = loadInit(filePath);
		try {
			Field fields[] = obj.getClass().getDeclaredFields();
			for (int fi = 0; fi < fields.length; fi++) {
				String tagName = fields[fi].getName();
				Element element = document.getDocumentElement();
				NodeList nodeList = element.getElementsByTagName(tagName);
				if (nodeList != null && nodeList.getLength() > 0) {
					Node node = nodeList.item(0);
					if (node != null) {
						if (node.getFirstChild() != null) {
							String tagVal = node.getFirstChild().getNodeValue();
							String methodName = "set"
									+ StringHandler.upFirstChar(tagName);
							Method setMethod = obj
									.getClass()
									.getDeclaredMethod(methodName, String.class);
							setMethod.invoke(obj, tagVal);
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

}
