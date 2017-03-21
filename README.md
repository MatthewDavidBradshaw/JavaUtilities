# JavaUtilities
This library contains simple helpers and utilities which save time and eliminate boilerplate code when developing in Java.

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:java-utilities:1.3.3'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/JavaUtilities/view).

## Components
There are five components in this library:
- CallbackExecutor
- FileFinder
- IntChecker
- NullChecker
- Tested

### CallbackExecutor
Executes a queue of Runnable tasks and delivers a callback when all tasks complete.

### FileFinder
Recursively searches a file tree to find all files below a particular directory.

### IntChecker
Checks that integers meet certain numeric conditions without boilerplate code.

### NullChecker
Checks that arguments are non-null without boilerplate code. Also provides a utility for checking the contents of a collection in one line.

### @Tested
Useful for marking classes which have been tested.

## Licensing
This library is licenced under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Compatibility
This library is was compiled using Java 1.7.
