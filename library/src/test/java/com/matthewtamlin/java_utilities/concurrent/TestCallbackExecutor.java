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

package com.matthewtamlin.java_utilities.concurrent;

import com.matthewtamlin.java_utilities.concurrent.CallbackExecutor.OnExecutionCompleteListener;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link CallbackExecutor} class. These tests are not unit tests, instead they test
 * to make sure that: <ul> <li>The correct exceptions are thrown when incorrect arguments are passed
 * </li> <li>Queued tasks are not executed before {@link CallbackExecutor#execute()} is called.</li>
 * <li>All queued tasks are executed.</li> <li>Callbacks are delivered.</li> </ul>
 */
public class TestCallbackExecutor {
	/**
	 * The length of time each task should run for, measured in milliseconds.
	 */
	private static final int TASK_EXECUTION_TIME_MS = 2000;
	
	/**
	 * A task for use in testing. The task runs for approximately 2000 ms.
	 */
	private Runnable task1;
	
	/**
	 * A task for use in testing. The task runs for approximately 2000 ms.
	 */
	private Runnable task2;
	
	/**
	 * A task for use in testing. The task runs for approximately 2000 ms.
	 */
	private Runnable task3;
	
	/**
	 * A mock of the OnExecutionCompleteListener interface.
	 */
	private OnExecutionCompleteListener mockCallback1;
	
	/**
	 * A mock of the OnExecutionCompleteListener interface.
	 */
	private OnExecutionCompleteListener mockCallback2;
	
	/**
	 * Flag to indicate whether or not task 1 has started. This flag must be set by task 1 when
	 * it starts.
	 */
	private AtomicBoolean task1Started = new AtomicBoolean();
	
	/**
	 * Flag to indicate whether or not task 2 has started. This flag must be set by task 2 when
	 * it starts.
	 */
	private AtomicBoolean task2Started = new AtomicBoolean();
	
	/**
	 * Flag to indicate whether or not task 3 has started. This flag must be set by task 3 when
	 * it starts.
	 */
	private AtomicBoolean task3Started = new AtomicBoolean();
	
	/**
	 * Flag to indicate whether or not task 1 has finished. This flag must be set by task 1 when
	 * it finishes.
	 */
	private AtomicBoolean task1Finished = new AtomicBoolean();
	
	/**
	 * Flag to indicate whether or not task 2 has finished. This flag must be set by task 2 when
	 * it finishes.
	 */
	private AtomicBoolean task2Finished = new AtomicBoolean();
	
	/**
	 * Flag to indicate whether or not task 3 has finished. This flag must be set by task 3 when
	 * it finishes.
	 */
	private AtomicBoolean task3Finished = new AtomicBoolean();
	
	/**
	 * Blocks the calling thread for the supplied amount of time.
	 *
	 * @param waitTime
	 * 		the length of the pause, measured in milliseconds
	 */
	private static void pause(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialises the testing environment.
	 */
	@Before
	public void setup() {
		// Initialise thread flags to show that no tasks have started or finished
		task1Started.getAndSet(false);
		task2Started.getAndSet(false);
		task3Started.getAndSet(false);
		task1Finished.getAndSet(false);
		task2Finished.getAndSet(false);
		task3Finished.getAndSet(false);
		
		task1 = new Runnable() {
			@Override
			public void run() {
				task1Started.getAndSet(true);
				pause(TASK_EXECUTION_TIME_MS);
				task1Finished.getAndSet(true);
			}
		};
		
		task2 = new Runnable() {
			@Override
			public void run() {
				task2Started.getAndSet(true);
				pause(TASK_EXECUTION_TIME_MS);
				task2Finished.getAndSet(true);
			}
		};
		
		task3 = new Runnable() {
			@Override
			public void run() {
				task3Started.getAndSet(true);
				pause(TASK_EXECUTION_TIME_MS);
				task3Finished.getAndSet(true);
			}
		};
		
		mockCallback1 = mock(OnExecutionCompleteListener.class);
		mockCallback2 = mock(OnExecutionCompleteListener.class);
	}
	
	/**
	 * Test to verify that the correct exception is thrown when the {@code task} argument of {@link
	 * CallbackExecutor#addToQueue(Runnable)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ensureCorrectExceptionIsThrown() {
		final CallbackExecutor executor = CallbackExecutor.usingSingleThreadExecutor();
		executor.addToQueue(null);
	}
	
	/**
	 * Test to verify that the CallbackExecutor class functions correctly when using a single
	 * thread executor. The test will only pass if: <ul> <li>Queued tasks do not execute until the
	 * {@link CallbackExecutor#execute()} method is called.</li> <li>All queued tasks are
	 * executed.</li> <li>Callbacks are delivered after all tasks finish.</li>.
	 */
	@Test
	public void testCallbackExecutor_usingSingleThreadExecutor() {
		// Create a new executor and register callbacks
		final CallbackExecutor executorUnderTest = CallbackExecutor.usingSingleThreadExecutor();
		executorUnderTest.registerOnExecutionCompleteListener(mockCallback1);
		executorUnderTest.registerOnExecutionCompleteListener(mockCallback2);
		
		// Queue all tasks but don't start execution
		executorUnderTest.addToQueue(task1);
		executorUnderTest.addToQueue(task2);
		executorUnderTest.addToQueue(task3);
		
		// Verify that no tasks started
		assertThat("task 1 started before execute()", task1Started.get(), is(false));
		assertThat("task 2 started before execute()", task2Started.get(), is(false));
		assertThat("task 3 started before execute()", task3Started.get(), is(false));
		
		// Trigger
		executorUnderTest.execute();
		
		// Allow enough time for all tasks to complete
		System.out.println("Waiting for concurrent tasks to complete...");
		pause(TASK_EXECUTION_TIME_MS * 4);
		
		// Verify that all tasks finished
		assertThat("task 1 did not finish", task1Finished.get(), is(true));
		assertThat("task 2 did not finish", task2Finished.get(), is(true));
		assertThat("task 3 did not finish", task3Finished.get(), is(true));
		
		// Verify that the callbacks were delivered
		verify(mockCallback1, times(1)).onExecutionComplete(executorUnderTest);
		verify(mockCallback2, times(1)).onExecutionComplete(executorUnderTest);
	}
	
	/**
	 * Test to verify that the CallbackExecutor class functions correctly when using a cached
	 * thread pool. The test will only pass if: <ul> <li>Queued tasks do not execute until the
	 * {@link CallbackExecutor#execute()} method is called.</li> <li>All queued tasks are
	 * executed.</li> <li>Callbacks are delivered after all tasks finish.</li>.
	 */
	@Test
	public void testCallbackExecutor_usingCachedThreadPool() {
		final CallbackExecutor executorUnderTest = CallbackExecutor.usingCachedThreadPool();
		
		final OnExecutionCompleteListener callback1 = mock(OnExecutionCompleteListener.class);
		final OnExecutionCompleteListener callback2 = mock(OnExecutionCompleteListener.class);
		
		executorUnderTest.registerOnExecutionCompleteListener(callback1);
		executorUnderTest.registerOnExecutionCompleteListener(callback2);
		
		// Queue all tasks but don't start execution
		executorUnderTest.addToQueue(task1);
		executorUnderTest.addToQueue(task2);
		executorUnderTest.addToQueue(task3);
		
		// Verify that no tasks started
		assertThat("task 1 started before execute()", task1Started.get(), is(false));
		assertThat("task 2 started before execute()", task2Started.get(), is(false));
		assertThat("task 3 started before execute()", task3Started.get(), is(false));
		
		// Trigger
		executorUnderTest.execute();
		
		// Allow enough time for all tasks to complete
		System.out.println("Waiting for concurrent tasks to complete...");
		pause(TASK_EXECUTION_TIME_MS * 2);
		
		// Verify that all tasks finished
		assertThat("task 1 did not finish", task1Finished.get(), is(true));
		assertThat("task 2 did not finish", task2Finished.get(), is(true));
		assertThat("task 3 did not finish", task3Finished.get(), is(true));
		
		// Verify that the callbacks were delivered
		verify(callback1, times(1)).onExecutionComplete(executorUnderTest);
		verify(callback2, times(1)).onExecutionComplete(executorUnderTest);
	}
	
	/**
	 * Test to verify that the CallbackExecutor class functions correctly when using a fixed thread
	 * pool. The test will only pass if: <ul> <li>Queued tasks do not execute until the
	 * {@link CallbackExecutor#execute()} method is called.</li> <li>All queued tasks are
	 * executed.</li> <li>Callbacks are delivered after all tasks finish.</li>.
	 */
	@Test
	public void testCallbackExecutor_usingFixedThreadPool() {
		final CallbackExecutor executorUnderTest = CallbackExecutor.usingFixedThreadPool(3);
		
		// Create and register callbacks
		final OnExecutionCompleteListener callback1 = mock(OnExecutionCompleteListener.class);
		final OnExecutionCompleteListener callback2 = mock(OnExecutionCompleteListener.class);
		executorUnderTest.registerOnExecutionCompleteListener(callback1);
		executorUnderTest.registerOnExecutionCompleteListener(callback2);
		
		// Queue all tasks but don't start execution
		executorUnderTest.addToQueue(task1);
		executorUnderTest.addToQueue(task2);
		executorUnderTest.addToQueue(task3);
		
		// Verify that no tasks started
		assertThat("task 1 started before execute()", task1Started.get(), is(false));
		assertThat("task 2 started before execute()", task2Started.get(), is(false));
		assertThat("task 3 started before execute()", task3Started.get(), is(false));
		
		// Trigger
		executorUnderTest.execute();
		
		// Allow enough time for all tasks to complete
		System.out.println("Waiting for concurrent tasks to complete...");
		pause(TASK_EXECUTION_TIME_MS * 2);
		
		// Verify that all tasks finished
		assertThat("task 1 did not finish", task1Finished.get(), is(true));
		assertThat("task 2 did not finish", task2Finished.get(), is(true));
		assertThat("task 3 did not finish", task3Finished.get(), is(true));
		
		// Verify that the callbacks were delivered
		verify(callback1, times(1)).onExecutionComplete(executorUnderTest);
		verify(callback2, times(1)).onExecutionComplete(executorUnderTest);
	}
}