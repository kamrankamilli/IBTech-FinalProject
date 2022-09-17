package com.finalproject.ecommerce.entity.content;

public class Menu {
	private long menuId;
	private String menuTitle;
	
	public Menu(long menuId, String menuTitle) {
		this.menuId = menuId;
		this.menuTitle = menuTitle;
	}

	public Menu() {
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	
}
