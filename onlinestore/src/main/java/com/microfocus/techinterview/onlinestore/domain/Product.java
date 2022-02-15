package com.microfocus.techinterview.onlinestore.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Class {@code Product} is the base class for all products that can be soled in
 * online store.
 * 
 * @author Yohannes
 * @since 1.0
 */

@Data
@AllArgsConstructor
public abstract class Product {

	/**
	 * GUID that uniquely identifies a product,
	 */
	@Setter(AccessLevel.NONE)
	private final String guid;

	/**
	 * Meaningful product name.
	 */
	@Setter(AccessLevel.NONE)
	private final String name;

}
