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

import com.matthewtamlin.java_utilities.testing.Tested;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Searches a directory to find all contained files. The search is recursive and will continue
 * down the file tree until there are no more directories to explore.
 *
 * @deprecated this entire library has been deprecated.
 */
@SuppressWarnings("WeakerAccess") // This is a public API class
@Tested(testMethod = "automated")
@Deprecated
public class FileFinder {
	/**
	 * Used during debugging to identify this class.
	 */
	@SuppressWarnings("unused") private static final String TAG = "[RecursiveFileFiler]";
	
	/**
	 * Performs the search. If the search finds no Files, then an empty Set will be returned.
	 *
	 * @param dir
	 * 		the directory to search, not null
	 *
	 * @return all files contained within the supplied directory and its recursive sub-directories
	 */
	@SuppressWarnings("ConstantConditions") // Check for isDirectory() eliminates NPE risk
	public static Set<File> searchDownTreeFrom(final File dir) {
		final Set<File> files = new HashSet<>();
		
		if (dir.isDirectory()) {
			for (final File f : dir.listFiles()) {
				// If the file is a directory then recurse, otherwise add the file to the
				// collection
				if (f.isDirectory()) {
					files.addAll(searchDownTreeFrom(f));
				} else {
					files.add(f);
				}
			}
		}
		
		return files;
	}
}