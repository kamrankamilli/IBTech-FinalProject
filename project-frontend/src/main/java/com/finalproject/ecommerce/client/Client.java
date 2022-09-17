package com.finalproject.ecommerce.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.Cart;
import com.finalproject.ecommerce.entity.CartProduct;
import com.finalproject.ecommerce.entity.Category;
import com.finalproject.ecommerce.entity.Product;
import com.finalproject.ecommerce.utils.StreamUtils;
import com.finalproject.ecommerce.utils.XmlHelper;

public class Client {

	public static List<Category> getCategories(String address) {
		List<Category> categoryList = new ArrayList<>();
		try {
			InputStream in = StreamUtils.get(address);
			Document document = XmlHelper.parse(in);
			Element root = document.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("Category");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				long categoryId = XmlHelper.getAttribute(element, "id", 0);
				String categoryName = XmlHelper.getSingleElementText(element, "CategoryName", "");
				Category category = new Category(categoryId, categoryName);
				categoryList.add(category);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	public static List<Product> getProducts(String address) {
		List<Product> productList = new ArrayList<>();
		try {
			InputStream in = StreamUtils.get(address);
			Document document = XmlHelper.parse(in);
			Element root = document.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("Product");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element productElement = (Element) nodeList.item(i);
				long productId = XmlHelper.getAttribute(productElement, "id", 0);
				String productName = XmlHelper.getSingleElementText(productElement, "ProductName", "");
				String description = XmlHelper.getSingleElementText(productElement, "Description", "");
				double salePrice = XmlHelper.getSingleElementText(productElement, "SalesPrice", 0.0);
				String image = XmlHelper.getSingleElementText(productElement, "ImagePath", "");
				Category category = new Category();
				String categoryName = XmlHelper.getSingleElementText(productElement, "Category", "");
				NodeList categotyList = productElement.getElementsByTagName("Category");
				for(int j=0; j<categotyList.getLength();j++) {
					Element categoryElement = (Element)categotyList.item(j);
					long categoryId = XmlHelper.getAttribute(categoryElement, "id", 0);
					category.setCategoryId(categoryId);
					category.setCategoryName(categoryName);
				}
				
				Product product = new Product(productId, productName, description, salePrice, image);
				product.setCategory(category);
				productList.add(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public static Product getProduct(String address) {
		Product product = null;
		try {
			InputStream in = StreamUtils.get(address);
			Document document = XmlHelper.parse(in);
			Element root = document.getDocumentElement();
			long productId = XmlHelper.getAttribute(root, "id", 0);
			String productName = XmlHelper.getSingleElementText(root, "ProductName", "");
			String description = XmlHelper.getSingleElementText(root, "Description", "");
			double salesPrice = XmlHelper.getSingleElementText(root, "SalesPrice", 0.0);
			String image = XmlHelper.getSingleElementText(root, "ImagePath", "");
			String categoryName = XmlHelper.getSingleElementText(root, "Category", "");
			NodeList categotyList = root.getElementsByTagName("Category");
			Category category = new Category();
			for(int j=0; j<categotyList.getLength();j++) {
				Element categoryElement = (Element)categotyList.item(j);
				long categoryId = XmlHelper.getAttribute(categoryElement, "id", 0);
				category.setCategoryId(categoryId);
				category.setCategoryName(categoryName);
			}
			
			product = new Product(productId, productName, description, salesPrice, image);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		System.out.println(product);
		return product;
	}

	public static InputStream getImage(String address) {
		InputStream in = null;
		try {
			in = StreamUtils.get(address);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}
	
	public static Document getCart(String address,long cartId) {
		InputStream in;
		Document document = null;
		try {
			in = StreamUtils.get("http://localhost:8080/project-backend/api/cart/view?cartId="+cartId);
			document = XmlHelper.parse(in);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return document;

	}
	
	public static Cart parseCartXml(Document document) {
		Element root = document.getDocumentElement();
		long cartId = XmlHelper.getAttribute(root, "id", 0);
		String customerName = XmlHelper.getSingleElementText(root, "CustomerName", "");
		double totalAmount = XmlHelper.getSingleElementText(root, "TotalAmount", 0.0);
		return new Cart(cartId,totalAmount,customerName);
	}
	
	public static List<CartProduct> parseCartProductXml(Document document) {
		List<CartProduct> cartProductList = new ArrayList<>();
		Element root = document.getDocumentElement();
		long cartId = XmlHelper.getAttribute(root, "id", 0);
		NodeList cartProducts = root.getElementsByTagName("Product");
		for(int i=0; i<cartProducts.getLength(); i++) {
			Element product= (Element) cartProducts.item(i);
			long productId = XmlHelper.getAttribute(product, "id", 0);
			int quantity = XmlHelper.getSingleElementText(product, "Quantity", 0);
			cartProductList.add(new CartProduct(0, quantity, cartId, productId));
			
		}
		return cartProductList;
	}
	
	public static Cart postCart(String address, String customerName) {
		InputStream in;
		Cart cart = null;
		try {
			in = StreamUtils.post(address, "<CustomerName>" + customerName + "</CustomerName>");
			Document document = XmlHelper.parse(in);
			Element root = document.getDocumentElement();
			long cartId = XmlHelper.getAttribute(root, "id", 0);
			double totalAmount = XmlHelper.getSingleElementText(root, "TotalAmount", 0.0);
			cart = new Cart(cartId, totalAmount, customerName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cart;
	}
}
