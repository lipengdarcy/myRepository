package com.runlion.shop.vo;


public class ItemAreaInfor {
	private Integer id;
	private String name;
	private Integer layer;
	private Integer ProvinceId;
	private Integer CityId;
	private Integer CountyId;
	private Integer StreetId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public Integer getProvinceId() {
		return ProvinceId;
	}

	public void setProvinceId(Integer provinceId) {
		ProvinceId = provinceId;
	}

	public Integer getCityId() {
		return CityId;
	}

	public void setCityId(Integer cityId) {
		CityId = cityId;
	}

	public Integer getCountyId() {
		return CountyId;
	}

	public void setCountyId(Integer countyId) {
		CountyId = countyId;
	}

	public Integer getStreetId() {
		return StreetId;
	}

	public void setStreetId(Integer streetId) {
		StreetId = streetId;
	}
}
