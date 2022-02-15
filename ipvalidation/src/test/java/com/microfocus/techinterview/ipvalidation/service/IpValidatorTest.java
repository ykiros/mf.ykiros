package com.microfocus.techinterview.ipvalidation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IpValidatorTest {

	@InjectMocks
	private IpValidator ipValidator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ipValidator = new IpValidator();
	}

	@Test
	public void validateIpAddressTest() {
		boolean result = ipValidator.validateIpAddress("194.168.1.1", "192.168.1.1/10");
		assertEquals(true, result);
	}

	@Test
	public void cidrRangeWithNoSlashTest() {
		boolean result = ipValidator.validateIpAddress("194.168.1.1", "192.168.1.1");
		assertEquals(false, result);
	}
	
	@Test
	public void invalidCidrRangeTest() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> ipValidator.validateIpAddress("194.168.1.1", "192.168.1.1/100"));
		assertThat(exception.getMessage()).isEqualTo("invalid cidrRange format");
	}

	@Test
	public void invalidIpAddressTest() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> ipValidator.validateIpAddress("194.168.1.1/", "192.168.1.1/10"));
		assertThat(exception.getMessage()).isEqualTo("invalid ipAddress format");
	}
}
