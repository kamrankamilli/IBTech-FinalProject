package com.finalproject.ecommerce.entity.shopping;

public class Address {
	private long addressId;
	private long addressLine1;
	private long addressLine2;
	
	private Province province;

	public Address(long addressId, long addressLine1, long addressLine2) {
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(long addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public long getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(long addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}
