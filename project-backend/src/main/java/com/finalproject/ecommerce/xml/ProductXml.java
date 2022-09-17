package com.finalproject.ecommerce.xml;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.finalproject.ecommerce.entity.inventory.Product;
import com.finalproject.ecommerce.utils.XmlHelper;

public class ProductXml {
	public static Document format(Product product) throws ParserConfigurationException {
		Document document = XmlHelper.create("Product");
		Element root = document.getDocumentElement();
		root.setAttribute("id", Long.toString(product.getProductId()));
		XmlHelper.addSingleElementText(document, root, "ProductName", product.getProductName());
		XmlHelper.addSingleElementText(document, root, "Description", product.getDescription());
		XmlHelper.addSingleElementText(document, root, "SalesPrice", product.getSalesPrice());
		XmlHelper.addSingleElementText(document, root, "ImagePath", product.getImagePath());
		Element categoryElement=XmlHelper.addSingleElementText(document, root, "Category", product.getCategory().getCategoryName());
		categoryElement.setAttribute("id", Long.toString(product.getCategory().getCategoryId()));
		return document;
	}

	public static Document format(List<Product> productList) throws ParserConfigurationException {
		Document document = XmlHelper.create("Products");
		if (productList.size() > 0) {
			for (Product product : productList) {
				Element root = document.getDocumentElement();
				Element productElement = XmlHelper.addSingleElementText(document, root, "Product", "");
				productElement.setAttribute("id", Long.toString(product.getProductId()));
				XmlHelper.addSingleElementText(document, productElement, "ProductName", product.getProductName());
				XmlHelper.addSingleElementText(document, productElement, "Description", product.getDescription());
				XmlHelper.addSingleElementText(document, productElement, "SalesPrice", product.getSalesPrice());
				XmlHelper.addSingleElementText(document, productElement, "ImagePath", product.getImagePath());
				Element categoryElement=XmlHelper.addSingleElementText(document, root, "Category", product.getCategory().getCategoryName());
				categoryElement.setAttribute("id", Long.toString(product.getCategory().getCategoryId()));
			}
		}
		return document;
	}
	
}
