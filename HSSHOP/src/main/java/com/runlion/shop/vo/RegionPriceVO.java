package com.runlion.shop.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspRegionpriecedefunits;

public class RegionPriceVO implements Cloneable {

	@Override
	public RegionPriceVO clone() {
		try {
			RegionPriceVO cloneObj = (RegionPriceVO) super.clone();
			List<BspProductsregionsprice> cloneList1 = new ArrayList<BspProductsregionsprice>();
			for (BspProductsregionsprice b : this.getPriceList())
				cloneList1.add(b.clone());
			List<BspRegionpriecedefunits> cloneList2 = new ArrayList<BspRegionpriecedefunits>();
			for (BspRegionpriecedefunits b : this.getDefUnitList())
				cloneList2.add(b.clone());
			cloneObj.setDefUnitList(cloneList2);
			cloneObj.setPriceList(cloneList1);
			return cloneObj;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	Integer priid;
	Integer pid;
	Integer regionId;
	String regionName;
	int effectnow = 1; // 是否立即执行，1：是， 0：否

	public int getEffectnow() {
		return effectnow;
	}

	public void setEffectnow(int effectnow) {
		this.effectnow = effectnow;
	}

	// @JSONField(format = "yyyyMMddHHmmss")
	String planeffecttime;

	public String getPlaneffecttime() {
		return planeffecttime;
	}

	public void setPlaneffecttime(String planeffecttime) {
		this.planeffecttime = planeffecttime;
	}

	BigDecimal starthand;
	BigDecimal startship;

	public BigDecimal getStarthand() {
		return starthand;
	}

	public void setStarthand(BigDecimal starthand) {
		this.starthand = starthand;
	}

	public BigDecimal getStartship() {
		return startship;
	}

	public void setStartship(BigDecimal startship) {
		this.startship = startship;
	}

	List<BspProductsregionsprice> priceList = new ArrayList();

	List<BspRegionpriecedefunits> defUnitList = new ArrayList();

	List<BspProregionextprice> extPriceList = new ArrayList();

	public List<BspProregionextprice> getExtPriceList() {
		return extPriceList;
	}

	public void setExtPriceList(List<BspProregionextprice> extPriceList) {
		this.extPriceList = extPriceList;
	}

	public List<BspRegionpriecedefunits> getDefUnitList() {
		return defUnitList;
	}

	public void setDefUnitList(List<BspRegionpriecedefunits> defUnitList) {
		this.defUnitList = defUnitList;
	}

	public Integer getPriid() {
		return priid;
	}

	public void setPriid(Integer priid) {
		this.priid = priid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<BspProductsregionsprice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<BspProductsregionsprice> priceList) {
		this.priceList = priceList;
	}

}
