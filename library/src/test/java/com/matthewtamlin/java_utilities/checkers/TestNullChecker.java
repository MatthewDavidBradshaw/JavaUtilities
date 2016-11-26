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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collection;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkEachElementIsNotNull;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link NullChecker} class.
 */
@RunWith(JUnit4.class)
public class TestNullChecker {
	/**
	 * A string for use in testing.
	 */
	private static final String TEST_STRING = "test";
	
	/**
	 * A collection guaranteed to contain at least one null element.
	 */
	private Collection<String> containingNull = new ArrayList<>();
	
	/**
	 * A collection guaranteed to contain exactly zero	 null elements.
	 */
	private Collection<String> notContainingNull = new ArrayList<>();
	
	@Before
	public void setup() {
		containingNull.clear();
		containingNull.add(null);
		containingNull.add(TEST_STRING);
		
		notContainingNull.clear();
		notContainingNull.add(TEST_STRING);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object)} method functions correctly
	 * when null is passed for the {@code object} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotNull_1_nullPassed() {
		checkNotNull(null);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object)} method functions correctly
	 * when a non-null String is passed for the {@code object} argument. The test will only pass if
	 * the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNotNull_1_nonNullPassed() {
		final String result = checkNotNull(TEST_STRING);
		assertThat("incorrect object returned", result == TEST_STRING);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object, String)} method functions
	 * correctly when null is passed for the {@code object} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotNull_2_nullPassed() {
		checkNotNull(null, "error message");
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object, String)} method functions
	 * correctly when a non-null String is passed for the {@code object} argument. The test will
	 * only pass if the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNotNull_2_nonNullPassed() {
		final String result = checkNotNull(TEST_STRING, "error message");
		assertThat("incorrect object returned", result == TEST_STRING);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object, Exception)} method
	 * functions correctly when null is passed for the {@code object} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalStateException.class)
	public void testCheckNotNull_3_nullObjectPassed() {
		checkNotNull(null, new IllegalStateException());
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object, Exception)} method
	 * functions correctly when a non-null String is passed for the {@code object} argument. The
	 * test will only pass if the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNotNull_3_nonNullObjectPassed() {
		final String result = checkNotNull(TEST_STRING, new IllegalStateException());
		assertThat("incorrect object returned", result == TEST_STRING);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNotNull(Object, Exception)} method
	 * fails correctly when null is passed for the {@code exception} argument. The test will only
	 * pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotNull_3_nullExceptionPassed() throws Exception {
		checkNotNull(TEST_STRING, (Exception) null);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection)} method
	 * functions correctly when a collection containing at least one null element is supplied.
	 * The test will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNotNull_1_collectionContainingNull() {
		checkEachElementIsNotNull(containingNull);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection)} method
	 * functions correctly when a collection containing no null elements is supplied. The test
	 * will only pass if the collection is returned.
	 */
	@Test
	public void testCheckEachElementIsNotNull_1_collectionNotContainingNull() {
		final Collection<String> result = checkEachElementIsNotNull(notContainingNull);
		assertThat("incorrect collection returned", result == notContainingNull);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection)} method
	 * fails correctly when null is supplied. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNotNull_1_nullSupplied() {
		checkEachElementIsNotNull(null);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection, String)}
	 * method functions correctly when a collection containing at least one null element is
	 * supplied
	 * for the {@code collection} argument. The test will only pass if an IllegalArgumentException
	 * is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNotNull_2_collectionContainingNull() {
		checkEachElementIsNotNull(containingNull, "error message");
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection, String)}
	 * method functions correctly when a collection containing no null elements is supplied. The
	 * test will only pass if the collection is returned.
	 */
	@Test
	public void testCheckEachElementIsNotNull_2_collectionNotContainingNull() {
		final Collection<String> result = checkEachElementIsNotNull(notContainingNull,
				"error message");
		assertThat("incorrect collection returned", result == notContainingNull);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection)} method
	 * fails correctly when null is supplied for the {@code collection} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNotNull_2_nullSupplied() {
		checkEachElementIsNotNull(null, (String) null);
	}
	
	/**
	 * Test to verify that the
	 * {@link NullChecker#checkEachElementIsNotNull(Collection, Exception)} method
	 * functions correctly when a collection containing at least one null element is supplied for
	 * the {@code collection} argument. The test will only pass if an IllegalStateException is
	 * thrown.
	 */
	@Test(expected = IllegalStateException.class)
	public void testCheckEachElementIsNotNull_3_collectionContainingNull() {
		checkEachElementIsNotNull(containingNull, new IllegalStateException());
	}
	
	/**
	 * Test to verify that the
	 * {@link NullChecker#checkEachElementIsNotNull(Collection, Exception)} method
	 * functions correctly when a collection containing no null elements is
	 * supplied. The test will only pass if the collection is returned.
	 */
	@Test
	public void testCheckEachElementIsNotNull_3_collectionNotContainingNull() {
		final Collection<String> result = checkEachElementIsNotNull(notContainingNull,
				new IllegalStateException());
		assertThat("incorrect collection returned", result == notContainingNull);
	}
	
	/**
	 * Test to verify that the
	 * {@link NullChecker#checkEachElementIsNotNull(Collection, Exception)} method
	 * fails correctly when null is supplied for the {@code collection} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNotNull_3_nullCollectionSupplied() {
		checkEachElementIsNotNull(null, new IllegalStateException());
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNotNull(Collection)} method
	 * fails correctly when null is supplied for the {@code exception} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNotNull_3_nullExceptionSupplied() throws Exception {
		checkEachElementIsNotNull(null, (Exception) null);
	}
}