package com.microfocus.techinterview.onlinestore.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.microfocus.techinterview.onlinestore.domain.Product;
import com.microfocus.techinterview.onlinestore.exception.ProductListException;

public class ProductListUtil {

	public static String listToString(Product product) throws ProductListException {
		try {
			Method method = product.getClass().getMethod("toString");
			return (String) method.invoke(product);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new ProductListException(e.getMessage());
		}
	}
}
