package com.finalproject.ecommerce.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.auth.User;
import com.finalproject.ecommerce.utils.Encryption;
import com.finalproject.ecommerce.utils.XmlHelper;

public class UserXml {
//	public static Document format(User user) throws ParserConfigurationException {
//		Document document = XmlHelper.create("User");
//		Element root = document.getDocumentElement();
//		root.setAttribute("id", Long.toString(cart.getCartId()));
//		XmlHelper.addSingleElementText(document, root, "CustomerName", cart.getCustomerName());
//		XmlHelper.addSingleElementText(document, root, "TotalAmount", 0);
//		return document;
//	}

	public static User parseXml(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		Document document = XmlHelper.parse(in);
		Element root = document.getDocumentElement();
		String userName = XmlHelper.getSingleElementText(root, "UserName", "");
		String userPassword = XmlHelper.getSingleElementText(root, "UserPassword", "");
		userPassword = Encryption.get_SHA_512_SecurePassword(userPassword);
		User user = new User(0,userName,userPassword);
		return user;
	}
}
