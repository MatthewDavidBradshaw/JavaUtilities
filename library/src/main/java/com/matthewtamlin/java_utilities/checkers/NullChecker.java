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

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

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
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code object} is null
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
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code object} is null
	 */
	public static <T> T checkNonNull(final T object, final String message) {
		if (object != null) {
			return object;
		} else {
			throw new IllegalArgumentException(message == null ? "null check failed" : message);
		}
	}
	
	/**
	 * Checks that each element in the supplied Collection is not null. If the check passes then
	 * the collection is returned, otherwise an IllegalArgumentException is thrown. The
	 * collection must not be concurrently modified while this method executes or else a
	 * ConcurrentModificationException will occur.
	 *
	 * @param collection
	 * 		the object to check, not null
	 *
	 * @return {@code object}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code collection} is null
	 * @throws IllegalArgumentException
	 * 		if {@code collection} contains at least one null element
	 * @throws ConcurrentModificationException
	 * 		if {@code collection} is concurrently modified while this method executes
	 */
	public static <T> Collection<T> checkEachElementIsNonNull(final Collection<T> collection) {
		return checkEachElementIsNonNull(collection, null);
	}
	
	/**
	 * Checks that each element in the supplied Collection is not null. If the check passes then
	 * the collection is returned, otherwise an IllegalArgumentException is thrown. The
	 * collection must not be concurrently modified while this method executes or else a
	 * ConcurrentModificationException will occur.
	 *
	 * @param collection
	 * 		the object to check, not null
	 * @param message
	 * 		the exception message, may be null
	 *
	 * @return {@code object}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code collection} is null
	 * @throws IllegalArgumentException
	 * 		if {@code collection} contains at least one null element
	 * @throws ConcurrentModificationException
	 * 		if {@code collection} is concurrently modified while this method executes
	 */
	@SuppressWarnings("WhileLoopReplaceableByForEach") // Use while loop for concurrency protection
	public static <T> Collection<T> checkEachElementIsNonNull(final Collection<T> collection,
			final String message) {
		checkNonNull(collection, "collection cannot be null");
		
		// Use an iterator so that an exception occurs if the collection is modified concurrently
		final Iterator<T> iterator = collection.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next() == null) {
				throw new IllegalArgumentException(message == null ? "null check failed" :
						message);
			}
		}
		
		// No elements triggered exception, therefore the collection must be entirely non-null
		return collection;
	}
}