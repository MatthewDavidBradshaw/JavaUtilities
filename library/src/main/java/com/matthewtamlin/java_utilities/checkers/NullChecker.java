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
	private static final String DEFAULT_MESSAGE = "null check failed";
	
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
	public static <T> T checkNotNull(final T object) {
		return checkNotNull(object, (String) null);
	}
	
	/**
	 * Checks that the supplied object is not null. If the check passes then the object is
	 * returned, otherwise an IllegalArgumentException is thrown.
	 *
	 * @param object
	 * 		the object to check
	 * @param message
	 * 		the message to add to the exception, null allowed
	 *
	 * @return {@code object}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code object} is null
	 */
	public static <T> T checkNotNull(final T object, final String message) {
		final String exceptionMessage = message == null ? DEFAULT_MESSAGE : message;
		
		return checkNotNull(object, new IllegalArgumentException(exceptionMessage));
	}
	
	/**
	 * Checks that the supplied object is not null. If the check passes then the object is
	 * returned, otherwise the supplied exception is thrown.
	 *
	 * @param object
	 * 		the object to check
	 * @param exception
	 * 		the exception to throw if {@code object} is null, not null
	 *
	 * @return {@code object}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code exception} is null
	 * @throws S
	 * 		if {@code object} is null
	 */
	@SuppressWarnings("ThrowableResultOfMethodCallIgnored") // Irrelevant in the context
	public static <T, S extends Exception> T checkNotNull(final T object, final S exception)
			throws S {
		if (exception == null) {
			throw new IllegalArgumentException("exception cannot be null");
		}
		
		if (object != null) {
			return object;
		} else {
			throw exception;
		}
	}
	
	/**
	 * Checks that each element in the supplied Collection is not null. If the check passes then
	 * the collection is returned, otherwise an IllegalArgumentException is thrown. The
	 * collection must not be concurrently modified while this method executes or else a
	 * ConcurrentModificationException will be thrown.
	 *
	 * @param collection
	 * 		the collection to check, not null
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
	public static <T> Collection<T> checkEachElementIsNotNull(final Collection<T> collection) {
		return checkEachElementIsNotNull(collection, (String) null);
	}
	
	/**
	 * Checks that each element in the supplied Collection is not null. If the check passes then
	 * the collection is returned, otherwise an IllegalArgumentException is thrown. The
	 * collection must not be concurrently modified while this method executes or else a
	 * ConcurrentModificationException will be thrown.
	 *
	 * @param collection
	 * 		the collection to check, not null
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
	public static <T> Collection<T> checkEachElementIsNotNull(final Collection<T> collection,
			final String message) {
		final String exceptionMessage = message == null ? DEFAULT_MESSAGE : message;
		
		return checkEachElementIsNotNull(collection,
				new IllegalArgumentException(exceptionMessage));
	}
	
	/**
	 * Checks that each element in the supplied Collection is not null. If the check passes then
	 * the collection is returned, otherwise the supplied exception is thrown. The collection
	 * must not be concurrently modified while this method executes or else a
	 * ConcurrentModificationException will be thrown.
	 *
	 * @param collection
	 * 		the collection to check, not null
	 * @param exception
	 * 		the exception to throw, not null
	 *
	 * @return {@code object}
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code collection} is null
	 * @throws S
	 * 		if {@code collection} contains at least one null element
	 * @throws ConcurrentModificationException
	 * 		if {@code collection} is concurrently modified while this method executes
	 */
	@SuppressWarnings("WhileLoopReplaceableByForEach")
	public static <T, S extends Exception> Collection<T> checkEachElementIsNotNull(
			final Collection<T> collection, final S exception) throws S {
		checkNotNull(collection, "collection cannot be null");
		
		// Use an iterator so that an exception occurs if the collection is modified concurrently
		final Iterator<T> iterator = collection.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next() == null) {
				throw exception;
			}
		}
		
		// No element triggered the exception, therefore the collection must be entirely non-null
		return collection;
	}
}