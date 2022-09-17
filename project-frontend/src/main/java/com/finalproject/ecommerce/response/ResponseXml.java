package com.finalproject.ecommerce.response;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.utils.XmlHelper;

public class ResponseXml {
	public static Response parseResponse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		Document document = XmlHelper.parse(in);
		Element root = document.getDocumentElement();
		int statusCode = XmlHelper.getSingleElementText(root, "Status", 0);
		String message = XmlHelper.getSingleElementText(root, "Message","");
		Response response = new Response(statusCode,message);
		return response;
	}

}
