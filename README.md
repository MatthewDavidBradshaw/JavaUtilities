# JavaUtilities
This library contains simple helpers and utilities which save time and eliminate boilerplate code when developing in Java.

This library was created as an exercise in producing an open source library and is now deprecated. This library is no longer being supported.

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
Executes a queue of Runnable tasks and delivers a callback when all tasks complete. This is especially useful when Java 1.8 isn't available.

Create an executor using one of the three available configurations.
```java
// Three configurations are provided
CallbackExecutor executor1 = CallbackExecutor.usingSingleThreadExecutor();
CallbackExecutor executor2 = CallbackExecutor.usingFixedThreadPool(numberOfThreads);
CallbackExecutor executor3 = CallbackExecutor.usingCachedThreadPool();
```

Add listeners and start execution.
```java
executor.registerOnExecutionCompleteListener(new OnExecutionCompleteListener() {
	@Override
	public void onExecutionComplete(CallbackExecutor callbackExecutor) {
		// Execution complete
	}
}

executor.execute();
```

### FileFinder
Recursively searches a file tree to find all files below a particular directory. Consider the following file tree:
```
- A
  - B
    - C
      - D
  - E
    - F
  - G
  - H
- I
  - J
- K
```

Searching the tree using `searchDownTreeFrom(new File("A"));` and returns a set containing `[A, B, C, D, E, F, G and H]` but not `[I, J, K]`.

### IntChecker
Checks that integers meet numeric conditions without boilerplate code.
```java
// Passes and returns 4
checkLessThan(4, 5);

// Fails and throws an IllegalArgumentException with a default message
checkLessThan(100, 5);

// Fails and throws an IllegalArgumentException with the message "check failed"
checkLessThat(100, 5, "check failed");
```

Checkers exist for:
- Less than
- Less than or equal to
- Greater then
- Greater than or equal to
- Equal to
- Not equal to

### NullChecker
Performs null checks without boilerplate code.
```java
// Passes and returns the Object
checkNotNull(new Object());

// Fails and throws an IllegalArgumentException with a default message
checkNotNull(null);

// Fails and throws an IllegalArgumentException with the message "check failed"
checkNotNull(null, "check failed");

// Fails and throws the supplied exception
checkNotNull(null, new MyCustomException());
```

### Tested
The Tested annotation has no functionality, it's just a good way to keep track of what has and hasn't been tested.
```java
@Tested(testMethod="automated")
public class SomethingUnitTested {}

@Tested(testMethod="manual")
public class SomethingManuallyTested {}

public class SomethingUntested {}
```

## Licensing
This library is licenced under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Compatibility
This library is compatible with Java 1.7 and up.
