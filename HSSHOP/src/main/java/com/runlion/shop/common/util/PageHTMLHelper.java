package com.runlion.shop.common.util;

import java.util.List;

/**
 * PageHelper工具类
 * 
 * @author 赵威
 * 
 */
public class PageHTMLHelper {
	// 总共的数据量
	private long total;

	// 每页显示多少条
	private int pageSize;

	// 共有多少页
	private int totalPage;

	// 当前是第几页
	private int index;

	// 数据
	private List<?> data;

	// 连接路径
	private String path = "";

	/**
	 * 页码HTML信息
	 */
	@SuppressWarnings("unused")
	private String pageHTML;

	private int startPage; // 开始页面

	private int endPage; // 结束页面

	private int displayNum = 5; // 显示的页数

	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 设置路径前缀，页面第一页index为1
	 * 
	 * @param path
	 *            此path含有参数形式，如/aa/article?page=,或者/bb/article/list/
	 * 
	 *            java 调用代码 PageHTMLHelper p = new PageHTMLHelper();
	 *            p.setTotal(productService.getCount(cataId, -1));//此处设置记录的总数
	 *            p.setPageSize(Constant.ADMIN_PAGE_SIZE);//此处设置第一页显示多少条记录
	 *            p.setIndex
	 *            (page);//此处设置当前页面值，通常是由request.getParameter("page")取得的。
	 *            p.setPath
	 *            ("/aa/product?cataid=55&order_type=1&page=");//此处设置路径前缀
	 *            ，可在前缀中构造其它参数 request.setAttribute("p",p);
	 * 
	 *            jsp 页面显示代码 <!--velocity写法--> <span
	 *            class="pager">$p.pageHTML</span> <!--普通jsp写法，el表达式--> <span
	 *            class="pager">${p.pageHTML}</span> <!--普通jsp写法--> <span
	 *            class="pager"
	 *            ><%=((PageHTMLHelper)request.getAttribute("p")).getPageHTML
	 *            ()%></span>
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return (int) ((this.total + this.pageSize - 1) / this.pageSize);
	}

	public int getIndex() {
		return index;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	public String getPageHTML() {
		totalPage = getTotalPage();
		if (index > totalPage) {
			index = totalPage + 1;
		}
		StringBuffer displayInfo = new StringBuffer();
		if (totalPage != 0 && pageSize != 0) {
			displayInfo.append("<div class='page clearfix'>"
					+ "<div class='page-info clearfix'>");
			displayInfo
					.append("<span>共" + totalPage + "页 "/* + total + "条记录" */);
			displayInfo
					.append("到第</span><input id='go-page-num' type='text'  />"
							+ "<span>页 " + "<a href='" + path
							+ "' onclick='goPageFunc(this)' >GO</a>"
							+ "</span></div>");

			displayInfo.append("<div class='page-button clearfix'>");
			if (index <= 1) {
				// displayInfo.append("<a>首页</a>");
				// displayInfo.append("<a>上一页</a>");
			} else {
				displayInfo.append("<a href='" + path + "1'>首页</a>");
				displayInfo.append("<a href='" + path + (index - 1)
						+ "'>上一页</a>");
			}

			countPages();

			for (int i = startPage; i <= endPage; i++) {
				if (i == index) {
					displayInfo.append("<a class='curr'>" + i + "</a>");
				} else {
					displayInfo.append("<a href='" + path + i + "'>" + i
							+ "</a>");
				}
			}

			if (index >= totalPage) {
				// displayInfo.append("下一页");
				// displayInfo.append("尾页");
			} else {
				displayInfo.append("<a href='" + path + (index + 1)
						+ "'>下一页</a>");
				displayInfo.append("<a href='" + path + totalPage + "'>尾页</a>");
			}
			displayInfo.append("</div>");
			displayInfo.append("</div>");
			// go按钮的脚本
			String goPageFun = "<script>" + "function goPageFunc(that){"
					+ "var page=$('#go-page-num').val();" + "that.href+=page;"
					+ "}" + "</script>";
			displayInfo.append(goPageFun);
		}
		return displayInfo.toString();
	}

	public String getPageHTML_bak() {
		totalPage = getTotalPage();
		StringBuffer displayInfo = new StringBuffer();
		if (totalPage != 0 && pageSize != 0) {
			displayInfo.append("<div class='page'>");
			displayInfo.append("<span class='item nolink'>共<span class='num'>"
					+ totalPage + "</span>页/<span class='num'>" + total
					+ "</span>条记录</span>");
			// displayInfo.append("<span class='item nolink'>/<span class='num'>"
			// + total + "</span>条记录</span>");
			// 判断如果当前是第一页 则“首页”和“第一页”失去链接
			if (index <= 1) {
				displayInfo.append("<span class='item nolink'>首页</span>");
				displayInfo.append("<span class='item nolink'>上一页</span>");
			} else {
				displayInfo.append("<span class='item'><a href='" + path
						+ "1'>首页</a></span>");
				displayInfo.append("<span class='item'><a href='" + path
						+ (index - 1) + "'>上一页</a></span>");
			}

			countPages();
			displayInfo.append("<span class='item nums'>");
			for (int i = startPage; i <= endPage; i++) {
				if (i == index) {
					displayInfo.append("<span class='nolink'>" + i + "</span>");
				} else {
					displayInfo.append("<a href='" + path + i + "'>" + i
							+ "</a>");
				}
			}
			displayInfo.append("</span>");

			if (index >= totalPage) {
				displayInfo.append("<span class='item nolink'>下一页</span>");
				displayInfo.append("<span class='item nolink'>尾页</span>");
			} else {
				displayInfo.append("<span class='item'><a href='" + path
						+ (index + 1) + "'>下一页</a></span>");
				displayInfo.append("<span class='item'><a href='" + path
						+ totalPage + "'>尾页</a></span>");
			}
			displayInfo.append("</div>");
		}
		return displayInfo.toString();
	}

	/**
	 * 计算起始页和结束页
	 */
	public void countPages() {

		if (index - displayNum / 2 < 1) {
			startPage = 1;
			endPage = displayNum > totalPage ? totalPage : displayNum;
		} else if (index + displayNum / 2 > totalPage) {
			int n = totalPage - displayNum + 1;
			startPage = n > 0 ? n : 1;
			endPage = totalPage;
		} else {
			startPage = index - displayNum / 2;
			endPage = startPage + displayNum - 1;
		}
	}

	/**
	 * @param pageHTML
	 *            the pageHTML to set
	 */
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public static void main(String[] args) {
		PageHTMLHelper p = new PageHTMLHelper();
		// p.totalPage = p.getTotalPage();
		p.setTotal(1002);
		p.setPageSize(20);
		p.setPath("/bb/article/list/");
		// for (int i = -80; i < 80; i++)
		// {
		// p.setIndex(i);
		// p.countPages();
		// System.out.println(i+"----"+p.getStartPage() + "-----" +
		// p.getEndPage());
		// }
		p.setIndex(11);
		System.out.println(p.getPageHTML());
	}
}
