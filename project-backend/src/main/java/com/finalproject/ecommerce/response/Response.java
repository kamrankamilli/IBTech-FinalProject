package com.finalproject.ecommerce.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.finalproject.ecommerce.utils.XmlHelper;

public class Response {
	public static void sendResponse(HttpServletResponse response, int statusCode, String message) {
		try {
			Document document = XmlHelper.create("Response");
			Element root = document.getDocumentElement();
			response.setStatus(statusCode);
			XmlHelper.addSingleElementText(document, root, "Status", statusCode);
			XmlHelper.addSingleElementText(document, root, "Message", message);
			XmlHelper.dump(document, response.getOutputStream());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
