package com.microfocus.techinterview.onlinestore.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class {@code SmartPhone} is the SmartPhone product definition.
 * 
 * @author Yohannes
 * @since 1.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SmartPhone extends Product {

	private static final String template = "%s " + "(%s), " + "numberOfCPUs: %s, " + "color: %s, ramSize: %s, screenResolution: %s";

	/**
	 * Name of smartPhone manufacturer.
	 */
	private String manufacturer;

	/**
	 * Color of the smartPhone.
	 */
	private String color;

	/**
	 * Number of CPUs installed in smartPhone.
	 */
	private int numberOfCPUs;

	/**
	 * Amount of RAM installed in smartPhone.
	 */
	private double ramSize;

	/**
	 * Smartphone's screen resolution.	
	 */
	private String screenResolution;

	public SmartPhone(String guid, String name) {
		super(guid, name);
	}

	@Override
	public String toString() {
		return String.format(
				template,
				getName(),
				getGuid(),
				getNumberOfCPUs(),
				getColor(),
				getRamSize(),
				getScreenResolution());
	}
}
