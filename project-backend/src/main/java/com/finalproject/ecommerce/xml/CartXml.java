package com.finalproject.ecommerce.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.shopping.Cart;
import com.finalproject.ecommerce.utils.XmlHelper;

public class CartXml {
	public static Document format(Cart cart) throws ParserConfigurationException {
		Document document = XmlHelper.create("Cart");
		Element root = document.getDocumentElement();
		root.setAttribute("id", Long.toString(cart.getCartId()));
		XmlHelper.addSingleElementText(document, root, "CustomerName", cart.getCustomerName());
		XmlHelper.addSingleElementText(document, root, "TotalAmount", cart.getTotalAmount());
		return document;
	}

	public static Cart parseXml(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		Document document = XmlHelper.parse(in);
		Element root = document.getDocumentElement();
		String customerName = root.getTextContent();
		Cart cart = new Cart(0, 0, customerName);
		return cart;
	}
}
