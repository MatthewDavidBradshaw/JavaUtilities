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

package com.matthewtamlin.java_utilities.file;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link FileFinder} class.
 */
public class TestFileFinder {
	/**
	 * The directory to search.
	 */
	private File rootTestDirectory;
	
	/**
	 * The expected search results {@code rootTestDirectory}.
	 */
	private Set<File> expectedFiles;
	
	/**
	 * Initialises the testing environment.
	 * <p>
	 * A File tree is constructed as follows:<br> - Tier 1 directory<br> --- Tier 2 directory (1)
	 * <br> ----- Tier 3 directory<br> ------- Tier 4 file (1)<br> ------- Tier 4 file (2)<br>
	 * ------- Tier 4 file (3)<br> ----- Tier 3 file<br> --- Tier 2 directory (2)<br>
	 * <p>
	 * The file tree is designed to allow testing to verify that the FileFinder can: <ul>
	 * <li>Search down more than 2 levels</li> <li>Find multiple files in a single directory</li>
	 * <li>Find multiple directories in a single directory</li> <li>Find files and directories in
	 * the same directory</li> <li>Deal with empty directories</li> </ul>
	 */
	@Before
	public void setup() {
		// Mock all files and directories
		File tier1Dir = mock(File.class);
		File tier2Dir1 = mock(File.class);
		File tier2Dir2 = mock(File.class);
		File tier3Dir = mock(File.class);
		File tier3File = mock(File.class);
		File tier4File1 = mock(File.class);
		File tier4File2 = mock(File.class);
		File tier4File3 = mock(File.class);
		
		// Mock the listFiles() and isDirectory() methods of the directories to define the file
		// tree
		when(tier1Dir.listFiles()).thenReturn(new File[]{tier2Dir1, tier2Dir2});
		when(tier2Dir1.listFiles()).thenReturn(new File[]{tier3Dir, tier3File});
		when(tier3Dir.listFiles()).thenReturn(new File[]{tier4File1, tier4File2, tier4File3});
		when(tier2Dir2.listFiles()).thenReturn(new File[0]);
		when(tier1Dir.isDirectory()).thenReturn(true);
		when(tier2Dir1.isDirectory()).thenReturn(true);
		when(tier3Dir.isDirectory()).thenReturn(true);
		when(tier2Dir2.isDirectory()).thenReturn(true);
		
		// Mock the listFiles() and isDirectory() methods of the files to indicate they aren't dirs
		when(tier3File.listFiles()).thenReturn(null);
		when(tier4File1.listFiles()).thenReturn(null);
		when(tier4File2.listFiles()).thenReturn(null);
		when(tier4File3.listFiles()).thenReturn(null);
		when(tier3File.isDirectory()).thenReturn(false);
		when(tier4File1.isDirectory()).thenReturn(false);
		when(tier4File2.isDirectory()).thenReturn(false);
		when(tier4File3.isDirectory()).thenReturn(false);
		
		// Save a reference to the root file for use during testing
		rootTestDirectory = tier1Dir;
		
		// Save references to the files for use during testing
		expectedFiles = new HashSet<>();
		expectedFiles.add(tier3File);
		expectedFiles.add(tier4File1);
		expectedFiles.add(tier4File2);
		expectedFiles.add(tier4File3);
	}
	
	/**
	 * Test to verify that the correct exception is thrown when the {@code dir} argument of {@link
	 * FileFinder#searchDownTreeFrom(File)} is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testSearchDownTreeFrom_invalidArg_nullDir() {
		FileFinder.searchDownTreeFrom(null);
	}
	
	/**
	 * Test to verify that the {@link FileFinder#searchDownTreeFrom(File)} method functions
	 * correctly when provided with valid arguments. The test will only pass if all the expected
	 * Files are returned.
	 */
	@Test
	public void testSearchDownTreeFrom_validArgs() {
		final Set<File> files = FileFinder.searchDownTreeFrom(rootTestDirectory);
		assertThat(files, is(expectedFiles));
	}
}