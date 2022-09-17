package com.finalproject.ecommerce.entity;

public class Product {
	private long productId;
	private String productName;
	private String description;
	private double salesPrice;
	private String imagePath;

	private Category category;

	public Product(long productId, String productName, String description, double salesPrice, String imagePath) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.salesPrice = salesPrice;
		this.imagePath = imagePath;
	}

	public Product() {

	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salePrice) {
		this.salesPrice = salePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
