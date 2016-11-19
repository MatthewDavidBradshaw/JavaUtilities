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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link IntChecker} class.
 */
@RunWith(JUnit4.class)
public class TestIntChecker {
	/**
	 * Failure message for use when the wrong integer is returned by the checker.
	 */
	private static final String WRONG_RETURN = "Checked passed but returned wrong value	";
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions
	 * correctly when the {@code x} argument is less than the {@code y} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckLessThan_xLessThanY() {
		final int result = IntChecker.checkLessThan(5, 6);
		assertThat(WRONG_RETURN, result == 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions
	 * correctly when the {@code x} argument is equal to the {@code y} argument. The test will only
	 * pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckLessThan_xEqualToY() {
		IntChecker.checkLessThan(5, 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions
	 * correctly when the {@code x} argument is greater than the {@code y} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckLessThan_xGreaterThanY() {
		IntChecker.checkLessThan(6, 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions
	 * correctly when {@code x} is less than {@code y}, and {@code y} is equal to
	 * {@link Integer#MAX_VALUE}. The test will only pass if the correct integer is returned by
	 * the check and no exception is thrown.
	 */
	public void testCheckLessThan_yIsMaxValue() {
		final int result = IntChecker.checkLessThan(6, Integer.MAX_VALUE);
		assertThat(WRONG_RETURN, result == 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThanOrEqualTo(int, int)} method functions
	 * correctly when the {@code x} argument is less than the {@code y} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckLessThanOrEqualTo_xLessThanY() {
		final int result = IntChecker.checkLessThanOrEqualTo(5, 6);
		assertThat(WRONG_RETURN, result == 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThanOrEqualTo(int, int)} method functions
	 * correctly when the {@code x} argument is equal to the {@code y} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckLessThanOrEqualTo_xEqualToY() {
		final int result = IntChecker.checkLessThanOrEqualTo(5, 5);
		assertThat(WRONG_RETURN, result == 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThanOrEqualTo(int, int)} method functions
	 * correctly when the {@code x} argument is greater than the {@code y} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckLessThanOrEqualTo_xGreaterThanY() {
		IntChecker.checkLessThanOrEqualTo(6, 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions
	 * correctly when {@code x} is less than {@code y}, and {@code y} is equal to
	 * {@link Integer#MAX_VALUE}. The test will only pass if the correct integer is returned by
	 * the check and no exception is thrown.
	 */
	public void testCheckLessThanOrEqualTo_yIsMaxValue() {
		final int result = IntChecker.checkLessThanOrEqualTo(6, Integer.MAX_VALUE);
		assertThat(WRONG_RETURN, result == 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when the {@code x} argument is less than the {@code y} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckGreaterThan_xLessThanY() {
		IntChecker.checkGreaterThan(5, 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when the {@code x} argument is equal to the {@code y} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckGreaterThan_xEqualToY() {
		IntChecker.checkGreaterThan(5, 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when the {@code x} argument is greater than the {@code y} argument. The test
	 * will only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckGreaterThan_xGreaterThanY() {
		final int result = IntChecker.checkGreaterThan(6, 5);
		assertThat(WRONG_RETURN, result == 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when {@code x} is less than {@code y}, and {@code x} is equal to
	 * {@link Integer#MIN_VALUE}. The test will only pass if the correct integer is returned by
	 * the check and no exception is thrown.
	 */
	public void testCheckLessThan_yIsMinValue() {
		final int result = IntChecker.checkGreaterThan(Integer.MIN_VALUE, 6);
		assertThat(WRONG_RETURN, result == Integer.MIN_VALUE);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThanOrEqualTo(int, int)} method
	 * functions correctly when the {@code x} argument is less than the {@code y} argument. The
	 * test will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckGreaterThanOrEqualTo_xLessThanY() {
		IntChecker.checkGreaterThanOrEqualTo(5, 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThanOrEqualTo(int, int)} method
	 * functions correctly when the {@code x} argument is equal to the {@code y} argument. The test
	 * will only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckGreaterThanOrEqualTo_xEqualToY() {
		final int result = IntChecker.checkGreaterThanOrEqualTo(5, 5);
		assertThat(WRONG_RETURN, result == 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThanOrEqualTo(int, int)} method
	 * functions correctly when the {@code x} argument is greater than the {@code y} argument.
	 * The test will only pass if the correct integer is returned by the check and no exception
	 * is thrown.
	 */
	@Test
	public void testCheckGreaterThanOrEqualTo_xGreaterThanY() {
		final int result = IntChecker.checkGreaterThanOrEqualTo(6, 5);
		assertThat(WRONG_RETURN, result == 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThanOrEqualTo(int, int)} method
	 * functions correctly when {@code x} is less than {@code y}, and {@code x} is equal to
	 * {@link Integer#MIN_VALUE}. The test will only pass if the correct integer is returned by
	 * the check and no exception is thrown.
	 */
	public void testCheckLessThanOrEqualTo_yIsMinValue() {
		final int result = IntChecker.checkGreaterThanOrEqualTo(Integer.MIN_VALUE, 6);
		assertThat(WRONG_RETURN, result == Integer.MIN_VALUE);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkEqualTo(int, int)} method functions correctly
	 * when the {@code x} argument is less than the {@code y} argument. The test will only pass
	 * if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEqualTo_xLessThanY() {
		IntChecker.checkEqualTo(5, 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkEqualTo(int, int, String)} method functions
	 * correctly when the {@code x} argument is equal to the {@code y} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckEqualTo_xEqualToY() {
		final int result = IntChecker.checkEqualTo(5, 5);
		assertThat(WRONG_RETURN, result == 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkEqualTo(int, int)} method functions correctly
	 * when the {@code x} argument is greater than the {@code y} argument. The test will only
	 * pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEqualTo_xGreaterThanY() {
		IntChecker.checkEqualTo(6, 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkNotEqualTo(int, int)} method functions
	 * correctly when the {@code x} argument is less than the {@code notEqualTo} argument. The
	 * test will only pass if the correct integer is returned by the check and no exception is
	 * thrown.
	 */
	@Test
	public void testCheckNotEqualTo_xLessThanY() {
		final int result = IntChecker.checkNotEqualTo(5, 6);
		assertThat(WRONG_RETURN, result == 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkNotEqualTo(int, int)} method functions
	 * correctly when the {@code x} argument is equal to the {@code notEqualTo} argument. The test
	 * will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEqualTo_xEqualToY() {
		IntChecker.checkNotEqualTo(5, 5);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkNotEqualTo(int, int)} method functions
	 * correctly when the {@code x} argument is greater than the {@code notEqualTo} argument. The
	 * test will only pass if the correct integer is returned by the check and no exception is
	 * thrown.
	 */
	@Test
	public void testCheckNotEqualTo_xGreaterThanY() {
		final int result = IntChecker.checkNotEqualTo(6, 5);
		assertThat(WRONG_RETURN, result == 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code x} argument is less than the {@code lower} argument. The test
	 * will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckBetween_xLessThanLower() {
		IntChecker.checkBetween(5, 6, 8);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code x} argument is equal to the {@code lower} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckBetween_xEqualToLower() {
		final int result = IntChecker.checkBetween(6, 6, 8);
		assertThat(WRONG_RETURN, result == 6);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code x} argument is between the {@code lower} argument and the {@code
	 * upper} argument. The test will only pass if the correct integer is returned by the check and
	 * no exception is thrown.
	 */
	@Test
	public void testCheckBetween_xBetweenLowerAndUpper() {
		final int result = IntChecker.checkBetween(7, 6, 8);
		assertThat(WRONG_RETURN, result == 7);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code x} argument is equal to the {@code upper} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckBetween_xEqualToUpper() {
		final int result = IntChecker.checkBetween(8, 6, 8);
		assertThat(WRONG_RETURN, result == 8);
	}
	
	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code x} argument is greater than the {@code upper} argument. The test
	 * will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckBetween_xGreaterThanUpper() {
		IntChecker.checkBetween(9, 6, 8);
	}
}