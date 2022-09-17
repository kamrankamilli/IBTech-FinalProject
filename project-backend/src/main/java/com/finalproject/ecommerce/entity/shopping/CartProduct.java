package com.finalproject.ecommerce.entity.shopping;

public class CartProduct {
	private long cartProductId;
	private int saleQuantity;
	private long producId;
	private long cartId;

	public CartProduct(long cartProductId, int saleQuantity, long cartId, long producId) {
		this.cartProductId = cartProductId;
		this.saleQuantity = saleQuantity;
		this.producId = producId;
		this.cartId = cartId;
	}
	

	public CartProduct() {
	}


	public long getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(long cartProductId) {
		this.cartProductId = cartProductId;
	}

	public int getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(int salesQuantity) {
		this.saleQuantity = salesQuantity;
	}

	public long getProducId() {
		return producId;
	}

	public void setProducId(long producId) {
		this.producId = producId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
}
