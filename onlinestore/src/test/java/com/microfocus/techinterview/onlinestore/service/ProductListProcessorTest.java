package com.microfocus.techinterview.onlinestore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microfocus.techinterview.onlinestore.domain.BackPack;
import com.microfocus.techinterview.onlinestore.domain.Product;
import com.microfocus.techinterview.onlinestore.domain.SmartPhone;
import com.microfocus.techinterview.onlinestore.exception.ProductListException;

@ExtendWith(MockitoExtension.class)
public class ProductListProcessorTest {

	@InjectMocks
	private ProductListProcessor productListProcessor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		productListProcessor = new ProductListProcessor();
	}

	@Test
	public void validateIpAddressTest() throws ProductListException {
		String result = productListProcessor.productListToString(getProducts());

		assertEquals("Backpack (111-222-333), maxContentWeight: 0.0, color: red, capacity: 20.0\n"
				+ "Smart Phone (444-555-666), numberOfCPUs: 2, color: Black, ramSize: 12.0, screenResolution: 828 x 1792 Pixel\n", result);
	}

	private List<Product> getProducts() {
		BackPack p1 = new BackPack("111-222-333", "Backpack");
		p1.setCapacity(15);
		p1.setCapacity(20);
		p1.setColor("red");

		SmartPhone p2 = new SmartPhone("444-555-666", "Smart Phone");
		p2.setRamSize(12);
		p2.setColor("Black");
		p2.setNumberOfCPUs(2);
		p2.setScreenResolution("828 x 1792 Pixel");

		List<Product> products = new ArrayList<>();
		products.add(p1);
		products.add(p2);
		return products;
	}
}
