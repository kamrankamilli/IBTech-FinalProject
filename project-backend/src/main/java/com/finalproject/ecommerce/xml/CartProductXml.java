package com.finalproject.ecommerce.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.shopping.Cart;
import com.finalproject.ecommerce.entity.shopping.CartProduct;
import com.finalproject.ecommerce.utils.XmlHelper;

public class CartProductXml {

	public static CartProduct parseXml(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		CartProduct cartProduct = null;
		Document document = XmlHelper.parse(in);
		Element root = document.getDocumentElement();
		long cartId = XmlHelper.getAttribute(root, "id", 0);
		NodeList productList = root.getElementsByTagName("Product");
		for (int i = 0; i < productList.getLength(); i++) {
			Element productElement = (Element) productList.item(i);
			int quantity = (int) XmlHelper.getSingleElementText(productElement, "Quantity", 0);
			long productId = XmlHelper.getAttribute(productElement, "id", 0);
			cartProduct = new CartProduct(0, quantity, cartId, productId);
		}

		return cartProduct;
	}

	public static Document format(Cart cart,List<CartProduct> cartProductList) throws ParserConfigurationException {
		Document document = XmlHelper.create("Cart");
		Element cartElement = document.getDocumentElement();
		cartElement.setAttribute("id", Long.toString(cart.getCartId()));
		for (CartProduct cartProduct : cartProductList) {
			Element productElement = XmlHelper.addSingleElementText(document, cartElement, "Product", "");
			productElement.setAttribute("id", Long.toString(cartProduct.getProducId()));
			XmlHelper.addSingleElementText(document, productElement, "Quantity", cartProduct.getSaleQuantity());
		}
		XmlHelper.addSingleElementText(document,cartElement,"TotalAmount", cart.getTotalAmount());
		return document;
	}
}
