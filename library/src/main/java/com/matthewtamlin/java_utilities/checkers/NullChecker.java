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

import com.matthewtamlin.java_utilities.testing.Tested;

/**
 * Utility for checking if arguments are null without boilerplate code.
 */
@Tested(testMethod = "automated")
public class NullChecker {
	/**
	 * Checks that the supplied object is not null. If the check passes then the object is
	 * returned, otherwise an IllegalArgumentException is thrown.
	 *
	 * @param object
	 * 		the object to check
	 *
	 * @return {@code object}
	 */
	public static <T> T checkNonNull(T object) {
		return checkNonNull(object, null);
	}
	
	/**
	 * Checks that the supplied object is not null. If the check passes then the object is
	 * returned, otherwise an IllegalArgumentException is thrown.
	 *
	 * @param object
	 * 		the object to check
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code object}
	 */
	public static <T> T checkNonNull(final T object, final String message) {
		if (object != null) {
			return object;
		} else {
			throw new IllegalArgumentException(message == null ? "" : message);
		}
	}
}