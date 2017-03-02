#JavaUtilities
A small library of Java utilities which I've found to be useful in Java development. Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:java-utilities:1.3.3'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/JavaUtilities/view).

## Utilities
There are four utility classes in this library:
- `CallbackExecutor`: Executes a queue of Runnable tasks and delivers a callback when all tasks complete.
- `FileFinder`: Recursively searches a file tree to find all files below a particular directory.
- `IntChecker`: Checks that integers meet certain numeric conditions without boilerplate code.
- `NullChecker`: Checks that arguments are non-null without boilerplate code. Also provides a
utility for checking the contents of a collection in one line.

The library also contains the @Tested annotation which is useful for keeping track of which classes have been tested.

## Licensing
This library is licenced under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Compatibility
This library is was compiled using Java 1.7.
