package com.fabway.smartquerybuilder.sql;

import com.fabway.smartquerybuilder.Function;

/**
 * Define the SQL Functions for Smart Querying.
 * 
 */
public final class SQLFunctions {

	private SQLFunctions() {
	}

	/**
	 * Encapsulate the value in the LIKE expression "%value%".
	 */
	public static final Function<String, String> Like = new Function<String, String>() {
		@Override
		public String call(String value) {
			if (value == null) {
				return null;
			}
			return "%" + value + "%";
		}
	};

	/**
	 * Encapsulate the value in the LIKE expression "value%".
	 */
	public static final Function<String, String> LikeStart = new Function<String, String>() {

		@Override
		public String call(String value) {
			return "%" + value;
		}
	};

	/**
	 * Encapsulate the value in the LIKE expression "%value".
	 */
	public static final Function<String, String> LikeEnd = new Function<String, String>() {

		@Override
		public String call(String value) {
			return value + "%";
		}
	};
}