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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Executes a queue of Runnables and delivers callbacks when all Runnables have finished.
 * Submitted tasks are not executed until the {@link #execute()} method is called,
 * and no new Runnables may be submitted once execution has begun. Runnables can be executed
 * using a single thread, a cached thread pool, or a fixed thread pool.
 */
@SuppressWarnings("WeakerAccess") // This is a public API class
public class CallbackExecutor {
	/**
	 * The ExecutorService which actually runs the tasks.
	 */
	private final ExecutorService executorService;
	
	/**
	 * The tasks to run.
	 */
	private final Queue<Runnable> tasks = new LinkedList<>();
	
	/**
	 * A count of the number of tasks which have completed. An atomic integer is needed since
	 * this variable is used by multiple threads to determine if callbacks should be delivered.
	 */
	private final AtomicInteger numberOfCompletedTasks = new AtomicInteger(0);
	
	/**
	 * The listeners to notify when execution completes.
	 */
	private final Set<OnExecutionCompleteListener> onExecutionCompleteListeners = new HashSet<>();
	
	/**
	 * A flag to indicate whether or not execution of the tests has started.
	 */
	private boolean executionHasBegun = false;
	
	/**
	 * Constructs a new CallbackExecutor which uses a single thread executor to execute the tasks.
	 * See {@link Executors#newSingleThreadExecutor()}.
	 *
	 * @return the new CallbackExecutor
	 */
	public static CallbackExecutor usingSingleThreadExecutor() {
		return new CallbackExecutor(Executors.newSingleThreadExecutor());
	}
	
	/**
	 * Constructs a new CallbackExecutor which uses a cached thread pool to execute the tasks.
	 * See {@link Executors#newCachedThreadPool()}.
	 *
	 * @return the new CallbackExecutor
	 */
	public static CallbackExecutor usingCachedThreadPool() {
		return new CallbackExecutor(Executors.newCachedThreadPool());
	}
	
	/**
	 * Constructs a new CallbackExecutor which uses a fixed thread pool to execute the tasks.
	 * See {@link Executors#newFixedThreadPool(int)}.
	 *
	 * @param n
	 * 		the number of threads in the pool
	 *
	 * @return the new CallbackExecutor
	 */
	public static CallbackExecutor usingFixedThreadPool(int n) {
		return new CallbackExecutor(Executors.newFixedThreadPool(n));
	}
	
	/**
	 * Constructs a new CallbackExecutor.
	 *
	 * @param executor
	 * 		the Executor to use for task execution, not null
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code executor} is null
	 */
	private CallbackExecutor(ExecutorService executor) {
		if (executor == null) {
			throw new IllegalArgumentException("executor cannot be null");
		}
		
		this.executorService = executor;
	}
	
	/**
	 * Adds the supplied task to the execution queue. Tasks cannot be added to the queue if
	 * execution has already started.
	 *
	 * @param task
	 * 		the task to execute, not null
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code runnable} is null
	 * @throws IllegalStateException
	 * 		if execution has already started
	 */
	public synchronized void addToQueue(Runnable task) {
		if (task == null) {
			throw new IllegalArgumentException("runnable cannot be null");
		}
		
		if (!executionHasBegun) {
			tasks.add(task);
		} else {
			throw new IllegalStateException("execution has already been started");
		}
	}
	
	/**
	 * Starts executing the queued tasks. Tasks are retrieved from the queue and started using a
	 * FIFO basis. Note that no new tasks may be added to the queue once this method is called.
	 */
	public synchronized void execute() {
		if (!executionHasBegun) {
			executionHasBegun = true;
			
			for (final Runnable r : tasks) {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						r.run();
						
						if (numberOfCompletedTasks.incrementAndGet() == tasks.size()) {
							deliverCallbacks();
						}
					}
				});
			}
		} else {
			throw new IllegalStateException("execution has already been started");
		}
	}
	
	/**
	 * Registers an OnExecutionCompleteListener. The listener will receive callbacks when all
	 * queued tasks finish executing. This method has no effect the supplied listener is null or
	 * is already registered.
	 *
	 * @param listener
	 * 		the listener to register
	 */
	public synchronized void registerOnExecutionCompleteListener(
			OnExecutionCompleteListener listener) {
		if (listener != null) {
			onExecutionCompleteListeners.add(listener);
		}
	}
	
	/**
	 * Unregisters an OnExecutionCompleteListener. The listener will not receive callbacks when
	 * all queued tasks finish executing. This method has no effect the supplied listener is
	 * null or
	 * is not currently registered.
	 *
	 * @param listener
	 * 		the listener to unregister
	 */
	public synchronized void unregisterOnExeutionCompleteListener(
			OnExecutionCompleteListener listener) {
		if (listener != null) {
			onExecutionCompleteListeners.remove(listener);
		}
	}
	
	/**
	 * Delivers callbacks to the registered listeners.
	 */
	private synchronized void deliverCallbacks() {
		for (OnExecutionCompleteListener listener : onExecutionCompleteListeners) {
			listener.onExecutionComplete(this);
		}
	}
	
	/**
	 * Interface definition for a callback to be delivered when all tasks finish.
	 */
	public interface OnExecutionCompleteListener {
		/**
		 * Called when all tasks finish
		 *
		 * @param callbackExecutor
		 * 		the CallbackExecutor which finished executing all tasks
		 */
		void onExecutionComplete(CallbackExecutor callbackExecutor);
	}
}