package com.finalproject.ecommerce.entity.content;

public class Article {
	private long articleId;
	private String articleTitle;
	private String articleBody;
	
	private long menuId;

	public Article(long articleId, String articleTitle, String articleBody, long menuId) {
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleBody = articleBody;
		this.menuId = menuId;
	}

	public Article() {
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleBody() {
		return articleBody;
	}

	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
}
