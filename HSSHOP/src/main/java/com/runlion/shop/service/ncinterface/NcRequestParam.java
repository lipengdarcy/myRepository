package com.runlion.shop.service.ncinterface;

//nc交互请求参数

/*
 "vreceiptcode":"SO1509110016" --NC订单号,如果要取消订单，务必传订单号
 "custcode": "2010100263",  --客户编号
 "pk_corp": "1002",		--公司编码 
 "dbilldate": "2015-09-11",   --订单日期
 "cvehicle": "111111",		--车号
 "vnote": "测试水泥电商的数据", --备注
 "invcode": "060201001",  -存货编码 
 "so_qty": "3",  -数量
 "dj": "295",  --单价
 "je": "885"   --金额
 "stats":"0"   --状态   ，0表示是新的订单，1表示关闭该订单

 "dbilldate": "2015-09-11",   --订单日期
 "vnote": "测试水泥电商的数据", --备注
 "invcode": "060201001",  -存货编码 
 "so_qty": "3",  -数量
 "dj": "295",  --单价
 "stats":"0"   --状态   ，0表示是新的订单，1表示关闭该订单


 {"state":"success","msg":"NC订单编号（水泥电商需要记录）"}

 //如果信用检查不通过，NC作废订单，返回提示信息

 {"state":"failure","msg":""信用余额不足，尚缺金额："+xyye+"元""}


 String methodPort(String djlx, String dataStr) 

 这个方法，djlx你传30就是销售订单，我会按照销售订单的处理

 */
public class NcRequestParam {

	// D2
	private String bankacc_fk; // 付款银行账号 其中银行帐号和银行名称可为空，可不传。
	private String dzsj; // 到账时间
	private String je; // 金额
	private String pk_corp; // 公司（水泥电商对应的工厂）
	private String fkyhmc; // 付款银行名称 其中银行帐号和银行名称可为空，可不传。
	private String custcode; // 客户编号

	// 4C
	private String vreceiptcode; // "SO1509140001" //销售单号
	private String sjfhsl; // "2", //出库单实发数量
	private String dbilltime;// "2015-09-14 12:00:12" //出库时间
	private String nmny; // "240" //出库总额
	private String nprice; // "120" ////出库价格
	private String cvehicle;// "1111" //车号

	// 30
	private String dbilldate;// ": "2015-09-11", --订单日期
	private String vnote;// 测试水泥电商的数据", --备注
	private String invcode;// 060201001", -存货编码
	private String so_qty;// "3", -数量
	private String rownum = "1";// 行号，默认1
	private String dj; // "295", --单价
	private String stats;// "0" --状态 ，0表示是新的订单，1表示关闭该订单

	public NcRequestParam() {

	}

	// 销售收款单，单据类型： D2
	public NcRequestParam(String time, String amount, String client) {
		this.dzsj = time;
		this.je = amount;
		this.custcode = client;
	}

	// 销售出库单，单据类型： 4C
	public NcRequestParam(String ordernum, String custcode,
			String sendQuantity, String sendtime, String sendAmount,
			String sendPrice, String carno) {
		this.vreceiptcode = ordernum;
		this.custcode = custcode;
		this.sjfhsl = sendQuantity;
		this.dbilltime = sendtime;
		this.nmny = sendAmount;
		this.nprice = sendPrice;
		this.cvehicle = carno;
	}

	// 销售订单，单据类型： 30
	public NcRequestParam(String ncOrdernum, String client, String orderdate,
			String carno, String remark, String invcode, String quantity,
			String price, String amount, String state) {
		this.vreceiptcode = ncOrdernum;
		this.custcode = client;
		this.dbilldate = orderdate;
		this.cvehicle = carno;
		this.vnote = remark;
		this.invcode = invcode;
		this.so_qty = quantity;
		this.dj = price;
		this.je = amount;
		this.stats = state;
	}

	/**
	 * 订单推送参数对象构造函数
	 * 
	 * @param vreceiptcode
	 *            订单号，-NC订单号,如果要取消订单，务必传订单号
	 * @param custcode
	 *            --客户编号
	 * @param pk_corp
	 *            --公司编码
	 * @param dbilldate
	 *            --订单日期
	 * @param cvehicle
	 *            --车号
	 * @param vnote
	 *            --备注
	 * @param invcode
	 *            -存货编码
	 * @param rownum
	 *            行号 永远为1
	 * @param so_qty
	 *            -数量
	 * @param dj
	 *            --单价
	 * @param je
	 *            --金额
	 * @param stats
	 *            --状态 ，0表示是新的订单，1表示关闭该订单
	 */
	public NcRequestParam(String vreceiptcode, String custcode, String pk_corp,
			String dbilldate, String cvehicle, String vnote, String invcode,
			String rownum, String so_qty, String dj, String je, String stats) {
		this.vreceiptcode = vreceiptcode;
		this.custcode = custcode;
		this.pk_corp = pk_corp;
		this.dbilldate = dbilldate;
		this.cvehicle = cvehicle;
		this.vnote = vnote;
		this.invcode = invcode;
		this.so_qty = so_qty;
		this.dj = dj;
		this.je = je;
		this.stats = stats;
		this.rownum = rownum;

	}

	public String getBankacc_fk() {
		return bankacc_fk;
	}

	public void setBankacc_fk(String bankacc_fk) {
		this.bankacc_fk = bankacc_fk;
	}

	public String getDzsj() {
		return dzsj;
	}

	public void setDzsj(String dzsj) {
		this.dzsj = dzsj;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getPk_corp() {
		return pk_corp;
	}

	public void setPk_corp(String pk_corp) {
		this.pk_corp = pk_corp;
	}

	public String getFkyhmc() {
		return fkyhmc;
	}

	public void setFkyhmc(String fkyhmc) {
		this.fkyhmc = fkyhmc;
	}

	public String getCustcode() {
		return custcode;
	}

	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	public String getVreceiptcode() {
		return vreceiptcode;
	}

	public void setVreceiptcode(String vreceiptcode) {
		this.vreceiptcode = vreceiptcode;
	}

	public String getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}

	public String getCvehicle() {
		return cvehicle;
	}

	public void setCvehicle(String cvehicle) {
		this.cvehicle = cvehicle;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
	}

	public String getInvcode() {
		return invcode;
	}

	public void setInvcode(String invcode) {
		this.invcode = invcode;
	}

	public String getSo_qty() {
		return so_qty;
	}

	public void setSo_qty(String so_qty) {
		this.so_qty = so_qty;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getSjfhsl() {
		return sjfhsl;
	}

	public void setSjfhsl(String sjfhsl) {
		this.sjfhsl = sjfhsl;
	}

	public String getDbilltime() {
		return dbilltime;
	}

	public void setDbilltime(String dbilltime) {
		this.dbilltime = dbilltime;
	}

	public String getNmny() {
		return nmny;
	}

	public void setNmny(String nmny) {
		this.nmny = nmny;
	}

	public String getNprice() {
		return nprice;
	}

	public void setNprice(String nprice) {
		this.nprice = nprice;
	}

}
