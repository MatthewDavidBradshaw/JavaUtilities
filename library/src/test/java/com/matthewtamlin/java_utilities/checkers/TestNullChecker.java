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
	
	private Collection<String> containingNull = new ArrayList<>();
	
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
	 * Test to verify that the {@link NullChecker#checkNonNull(Object)} method functions correctly
	 * when null is passed for the {@code object} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNull_1_nullPassed() {
		NullChecker.checkNonNull(null);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object)} method functions correctly
	 * when a non-null String is passed for the {@code object} argument. The test will only pass if
	 * the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNonNull_1_nonNullPassed() {
		final String result = NullChecker.checkNonNull(TEST_STRING);
		assertThat("incorrect object returned", result == TEST_STRING);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object, String)} method functions
	 * correctly when null is passed for the {@code object} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNull_2_nullPassed() {
		NullChecker.checkNonNull(null, "error message");
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object, String)} method functions
	 * correctly when a non-null String is passed for the {@code object} argument. The test will
	 * only pass if the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNonNull_2_nonNullPassed() {
		final String result = NullChecker.checkNonNull(TEST_STRING, "error message");
		assertThat("incorrect object returned", result == TEST_STRING);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNonNull(Collection)} method
	 * functions correctly when a collection containing at least one null element is supplied.
	 * The test will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNonNull_1_collectionContainingNull() {
		NullChecker.checkEachElementIsNonNull(containingNull);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNonNull(Collection)} method
	 * functions correctly when a collection containing no null elements is supplied. The test
	 * will only pass if the collection is returned.
	 */
	@Test
	public void testCheckEachElementIsNonNull_1_collectionNotContainingNull() {
		final Collection<String> result = NullChecker.checkEachElementIsNonNull(notContainingNull);
		assertThat("incorrect collection returned", result == notContainingNull);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNonNull(Collection)} method
	 * functions correctly when null is supplied. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNonNull_1_nullSupplied() {
		NullChecker.checkEachElementIsNonNull(null);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNonNull(Collection, String)}
	 * method functions correctly when a collection containing at least one null element is
	 * supplied for the {@code collection} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNonNull_2_collectionContainingNull() {
		NullChecker.checkEachElementIsNonNull(containingNull, "error message");
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNonNull(Collection, String)}
	 * method functions correctly when a collection containing no null elements is supplied. The
	 * test will only pass if the collection is returned.
	 */
	@Test
	public void testCheckEachElementIsNonNull_2_collectionNotContainingNull() {
		final Collection<String> result = NullChecker.checkEachElementIsNonNull(notContainingNull,
				"error message");
		assertThat("incorrect collection returned", result == notContainingNull);
	}
	
	/**
	 * Test to verify that the {@link NullChecker#checkEachElementIsNonNull(Collection)} method
	 * functions correctly when null is supplied for the {@code collection} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEachElementIsNonNull_2_nullSupplied() {
		NullChecker.checkEachElementIsNonNull(null, null);
	}
}