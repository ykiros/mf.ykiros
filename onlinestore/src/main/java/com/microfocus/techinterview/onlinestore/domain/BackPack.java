package com.microfocus.techinterview.onlinestore.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class {@code BackPack} is the Backpack product definition.
 * 
 * @author Yohannes
 * @since 1.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BackPack extends Product {

	private static final String template = "%s (%s), maxContentWeight: %s, color: %s, capacity: %s";

	/**
	 * Maximum weight of content that can be placed inside a backpack.
	 */
	private double maxContentWeight;

	/**
	 * Backpack color.
	 */
	private String color;

	/**
	 * Capacity (in liters) of all parts of back pack.
	 */
	private double capacity;

	public BackPack(String guid, String name) {
		super(guid, name);
	}

	@Override
	public String toString() {
		return String.format(
				template,
				getName(),
				getGuid(),
				getMaxContentWeight(),
				getColor(),
				getCapacity());
	}
}
