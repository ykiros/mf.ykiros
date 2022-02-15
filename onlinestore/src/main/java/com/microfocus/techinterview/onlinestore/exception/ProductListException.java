package com.microfocus.techinterview.onlinestore.exception;

/**
 * Class {@code ProductListException} is a custom Exception for onlinestore.
 * 
 * @author Yohannes
 * @since 1.0
 */

public class ProductListException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductListException(String msg) {
		super(msg);
	}
}
