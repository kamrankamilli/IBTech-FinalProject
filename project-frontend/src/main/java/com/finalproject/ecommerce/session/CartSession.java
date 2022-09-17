package com.finalproject.ecommerce.session;

import java.io.IOException;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.finalproject.ecommerce.utils.StreamUtils;

@WebListener
public class CartSession implements HttpSessionListener {

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if(session!=null) {
			String cartId = session.getAttribute("cartId").toString();
			try {
				StreamUtils.get("http://localhost:8080/project-backend/api/cart/delete?cartId="+cartId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
