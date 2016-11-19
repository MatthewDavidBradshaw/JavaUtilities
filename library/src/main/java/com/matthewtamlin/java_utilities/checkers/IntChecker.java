/*
 * Copyright 2016 Matthew Tamlin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matthewtamlin.java_utilities.checkers;

/**
 * Utility for checking if integers satisfy numeric conditions without boilerplate code.
 */
@SuppressWarnings("SameParameterValue") // Not important, class is part of public API
public class IntChecker {
	/**
	 * The exception message to use if no message is provided.
	 */
	private static final String DEFAULT_MESSAGE = "integer check failed";
	
	/**
	 * Checks that x < y. If the check passes then x is returned, otherwise an exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must be less than
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} >= {@code y}
	 */
	public static int checkLessThan(final int x, final int y) {
		return checkLessThan(x, y, DEFAULT_MESSAGE);
	}
	
	/**
	 * Checks that x < y. If the check passes then x is returned, otherwise an exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must be less than
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} >= {@code y}
	 */
	public static int checkLessThan(final int x, final int y, final String message) {
		if (x >= y) {
			throw new IllegalArgumentException(message);
		} else {
			return x;
		}
	}
	
	/**
	 * Checks that x > y. If the check passes then x is returned, otherwise an exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must be greater than
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} <= {@code y}
	 */
	public static int checkGreaterThan(final int x, final int y) {
		return checkGreaterThan(x, y, DEFAULT_MESSAGE);
	}
	
	/**
	 * Checks that x > y. If the check passes then x is returned, otherwise an exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must be greater than
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} <= {@code y}
	 */
	public static int checkGreaterThan(final int x, final int y, final String message) {
		if (x <= y) {
			throw new IllegalArgumentException(message);
		} else {
			return x;
		}
	}
	
	/**
	 * Checks that x equals y. If the check passes then x is returned, otherwise an exception is
	 * thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must be equal to
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} != {@code y}
	 */
	public static int checkEqualTo(final int x, final int y) {
		return checkEqualTo(x, y, DEFAULT_MESSAGE);
	}
	
	/**
	 * Checks that x equals y. If the check passes then x is returned, otherwise an exception is
	 * thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must be equal to
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} != {@code y}
	 */
	public static int checkEqualTo(final int x, final int y, final String message) {
		if (x != y) {
			throw new IllegalArgumentException(message);
		} else {
			return x;
		}
	}
	
	/**
	 * Checks that x is not equal y. If the check passes then x is returned, otherwise an
	 * exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must not be equal to
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} == {@code y}
	 */
	public static int checkNotEqualTo(final int x, final int y) {
		return checkNotEqualTo(x, y, DEFAULT_MESSAGE);
	}
	
	/**
	 * Checks that x is not equal y. If the check passes then x is returned, otherwise an
	 * exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param y
	 * 		the number {@code x} must not be equal to
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} == {@code y}
	 */
	public static int checkNotEqualTo(final int x, final int y, final String message) {
		if (x == y) {
			throw new IllegalArgumentException(message);
		} else {
			return x;
		}
	}
	
	/**
	 * Checks that x is in an interval inclusive of the bounds. If the check passes then x is
	 * returned, otherwise an exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param lower
	 * 		the lower bound of the interval
	 * @param upper
	 * 		the upper bound of the interval
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} is not in the interval between {@code lower} and {@code upper} (inclusive)
	 */
	public static int checkBetween(final int x, final int lower, final int upper) {
		return checkBetween(x, lower, upper, DEFAULT_MESSAGE);
	}
	
	/**
	 * Checks that x is in an interval inclusive of the bounds. If the check passes then x is
	 * returned, otherwise an exception is thrown.
	 *
	 * @param x
	 * 		the number to check
	 * @param lower
	 * 		the lower bound of the interval
	 * @param upper
	 * 		the upper bound of the interval
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code x}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code x} is not in the interval between {@code lower} and {@code upper} (inclusive)
	 */
	public static int checkBetween(final int x, final int lower, final int upper,
			final String message) {
		if (x < lower || x > upper) {
			throw new IllegalArgumentException(message);
		} else {
			return x;
		}
	}
}