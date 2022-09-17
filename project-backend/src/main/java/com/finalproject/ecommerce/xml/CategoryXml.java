package com.finalproject.ecommerce.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.inventory.Category;
import com.finalproject.ecommerce.utils.XmlHelper;

public class CategoryXml {
	public static Document format(Category category) throws ParserConfigurationException {
		Document document = XmlHelper.create("Category");
		Element root = document.getDocumentElement();
		root.setAttribute("id", Long.toString(category.getCategoryId()));
		XmlHelper.addSingleElementText(document, root, "CategoryName", category.getCategoryName());
		return document;
	}

	public static Document format(List<Category> categoryList) throws ParserConfigurationException {
		Document document = XmlHelper.create("Categories");
		if (categoryList.size() > 0) {
			for (Category category : categoryList) {
				Element root = document.getDocumentElement();
				Element categoryElement = XmlHelper.addSingleElementText(document, root, "Category", "");
				categoryElement.setAttribute("id", Long.toString(category.getCategoryId()));
				XmlHelper.addSingleElementText(document, categoryElement, "CategoryName", category.getCategoryName());
			}
		}
		return document;
	}

	public static Category parseXml(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		Document document = XmlHelper.parse(in);
		Element root = document.getDocumentElement();
		long categoryId = XmlHelper.getAttribute(root, "id", 0);
		String categoryName = XmlHelper.getSingleElementText(root, "CategoryName", "");
		Category category = new Category(categoryId, categoryName);
		return category;
	}

}
