package com.finalproject.ecommerce.entity.shopping;

public class Province {
	private long provinceId;
	private String provinceName;
	
	
	public Province(long provinceId, String provinceName) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}
	
	public Province() {
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
