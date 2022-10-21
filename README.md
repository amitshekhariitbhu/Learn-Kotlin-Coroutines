<img src=https://raw.githubusercontent.com/amitshekhariitbhu/Learn-Kotlin-Coroutines/main/assets/learn-kotlin-coroutines.png >

# Learn Kotlin Coroutines by real examples for Android

## About this project:

* This project is for someone who wants to get started with Kotlin Coroutines.
* I have tried to add the examples we implement in our Android project frequently.

## What are Coroutines?

The current framework which is available to handle multithreading leads to callback hells and
blocking states because we do not have any other simple way to guarantee thread-safe execution.

Coroutines, a very efficient and complete framework to manage concurrency in a more performant and
simple way.

Let's understand what exactly Coroutines are in a very simple way.

**Coroutines = Co + Routines**

Here, **Co** means **cooperation** and **Routines** means **functions**.

It means that when functions cooperate with each other, we call it as Coroutines.

<img src=https://raw.githubusercontent.com/amitshekhariitbhu/Learn-Kotlin-Coroutines/main/assets/cooperation-coroutines.png >

Let's understand this with an example. I have written the below code in a different way just for the
sake of understanding. Suppose we have two functions as `functionA` and `functionB`.

`functionA` as below:

```kotlin
fun functionA(case: Int) {
    when (case) {
        1 -> {
            taskA1()
            functionB(1)
        }
        2 -> {
            taskA2()
            functionB(2)
        }
        3 -> {
            taskA3()
            functionB(3)
        }
        4 -> {
            taskA4()
            functionB(4)
        }
    }
}
```

And `functionB` as below:

```kotlin
fun functionB(case: Int) {
    when (case) {
        1 -> {
            taskB1()
            functionA(2)
        }
        2 -> {
            taskB2()
            functionA(3)
        }
        3 -> {
            taskB3()
            functionA(4)
        }
        4 -> {
            taskB4()
        }
    }
}
```

Then, we can call the `functionA` as below:

```kotlin
functionA(1)
```

Here, `functionA` will do the `taskA1` and give control to the `functionB` to execute the `taskB1`.

Then, `functionB` will do the `taskB1` and give the control back to the `functionA` to execute
the `taskA2` and so on.

The important thing is that `functionA` and `functionB` are cooperating with each other.

With Kotlin Coroutines, the above cooperation can be done very easily which is without the use of
**when** or **switch** case which I have used in the above example for the sake of understanding.

Now that, we have understood what are coroutines when it comes to cooperation between the functions.
There are endless possibilities that open up because of the cooperative nature of functions.

A few of the possibilities are as follows:

- It can execute a few lines of functionA and then execute a few lines of functionB and then again a
  few lines of functionA and so on. **This will be helpful when a thread is sitting idle not doing
  anything, in that case, it can execute a few lines of another function. This way, it can take the
  full advantage of thread.** Ultimately the cooperation helps in multitasking.
- It will enable writing asynchronous code in a synchronous way. We will talk about this later in
  this article.

Overall, the Coroutines make the multitasking very easy.

So, we can say that **Coroutines** and the threads both are multitasking. But the difference is that
threads are managed by the OS and **coroutines by the users** as it can execute a few lines of
function by taking advantage of the cooperation.

It's an optimized framework written over the actual threading by taking advantage of the cooperative
nature of functions to make it light and yet powerful. So, we can say that **Coroutines** are
lightweight threads. A lightweight thread means it doesn't map on the native thread, so it doesn't
require context switching on the processor, so they are faster.

What does it mean when I say “it doesn't map on the native thread”?

Coroutines are available in many languages. Basically, there are two types of Coroutines:

- Stackless
- Stackful

Kotlin implements stackless coroutines — it means that the coroutines don’t have their own stack, so
they don’t map on the native thread.

Now, you can understand the below paragraph, what the official website of Kotlin says

> One can think of a coroutine as a light-weight thread. Like threads, coroutines can run in parallel, wait for each other and communicate. The biggest difference is that coroutines are very cheap, almost free: we can create thousands of them, and pay very little in terms of performance. True threads, on the other hand, are expensive to start and keep around. A thousand threads can be a serious challenge for a modern machine.

**Coroutines do not replace threads, it’s more like a framework to manage it.**

**The exact definition of Coroutines:** A framework to manage concurrency in a more performant and
simple way with its lightweight thread which is written on top of the actual threading framework to
get the most out of it by taking the advantage of cooperative nature of functions.

Now we have understood what exactly Coroutines are.

## Steps to learn Kotlin Coroutines from this project

* Learn all the concepts of Kotlin Coroutines from
  this **[blog](https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide)**
* Learn about the Dispatchers
  from **[here](https://amitshekhar.me/blog/dispatchers-in-kotlin-coroutines)**
* Learn about the `coroutineScope` vs `supervisorScope`
  from **[here](https://amitshekhar.me/blog/coroutinescope-vs-supervisorscope)**
* Blog [Callback to Coroutines in Kotlin](https://amitshekhar.me/blog/callback-to-coroutines-in-kotlin)  
* Blog [Retrofit with Kotlin Coroutines](https://amitshekhar.me/blog/retrofit-with-kotlin-coroutines)
* Then, just clone, build, run the project and start learning Kotlin Coroutines by examples.

## You will learn the following from this Learn Kotlin Coroutines project:

* Step by step guide on how to implement the Kotlin Coroutines in Android.
* Doing simple task in background using Kotlin Coroutines.
* Doing tasks in series using Kotlin Coroutines.
* Doing tasks in parallel using Kotlin Coroutines.
* Making two network calls in parallel using Kotlin Coroutines.
* What are scopes in Kotlin Coroutines?
* Canceling background task using Kotlin Coroutines.
* Exception handling in Kotlin Coroutines.
* Using Kotlin Coroutines with Retrofit.
  Blog: [Retrofit with Kotlin Coroutines](https://amitshekhar.me/blog/retrofit-with-kotlin-coroutines)
* Using Kotlin Coroutines with Room Database.
* Using Kotlin Coroutines with various 3rd party libraries.
* Adding timeout to a task using Kotlin Coroutines.
* Writing Unit-Test for ViewModel which uses Kotlin Coroutines.

## Kotlin Coroutines Examples for Android Development: Activity and ViewModel

* **Single Network Call:** Learn how to make a network call using Kotlin Coroutines. This is a very
  simple use-case in Android App Development.
  Blog: [Retrofit with Kotlin Coroutines](https://amitshekhar.me/blog/retrofit-with-kotlin-coroutines)
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/single/SingleNetworkCallActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/single/SingleNetworkCallViewModel.kt)

* **Series Network Calls:** Learn how to make network calls in series using Kotlin Coroutines. This
  is useful when you want to make a network call which is dependent on an another network call.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/series/SeriesNetworkCallsActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/series/SeriesNetworkCallsViewModel.kt)

* **Parallel Network Calls:** Learn how to make network calls in parallel using Kotlin Coroutines.
  This is useful when you want to make network calls in parallel which are independent of each
  other.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/parallel/ParallelNetworkCallsActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/parallel/ParallelNetworkCallsViewModel.kt)

* **Room Database Operation:** Learn how to fetch or insert entity in database using Kotlin
  Coroutines. This is useful when you are using Room Database in your Android Application.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/room/RoomDBActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/room/RoomDBViewModel.kt)

* **Long Running Task:** Learn how to run a long running task using Kotlin Coroutines. If you want
  to do any of your task in background thread using the Kotlin Coroutines, then this is useful.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/task/onetask/LongRunningTaskActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/task/onetask/LongRunningTaskViewModel.kt)

* **Two Long Running Tasks:** Learn how to run two long running tasks in parallel using Kotlin
  Coroutines.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/task/twotasks/TwoLongRunningTasksActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/task/twotasks/TwoLongRunningTasksViewModel.kt)

* **Timeout:** Learn how to add timeout to a task using Kotlin Coroutines. If you want to add a
  timeout to any of your background task in Android, this is going to super useful.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/timeout/TimeoutActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/timeout/TimeoutViewModel.kt)

* **Try-Catch Error Handling:** Learn how to handle error in Kotlin Coroutines using Try-Catch.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/errorhandling/trycatch/TryCatchActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/errorhandling/trycatch/TryCatchViewModel.kt)

* **CoroutineExceptionHandler:** Learn how to handle error in Kotlin Coroutines using
  CoroutineExceptionHandler.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/errorhandling/exceptionhandler/ExceptionHandlerActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/errorhandling/exceptionhandler/ExceptionHandlerViewModel.kt)

* **Ignore Error And Continue:** Learn how to
  use [`supervisorScope`](https://amitshekhar.me/blog/coroutinescope-vs-supervisorscope) to ignore
  error of a task and continue with other task. In other words, if more than two child jobs are
  running in parallel under a supervisor, one child job gets failed, we can continue with other.
    * [Activity Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/errorhandling/supervisor/IgnoreErrorAndContinueActivity.kt)
    * [ViewModel Code](app/src/main/java/me/amitshekhar/learn/kotlin/coroutines/ui/errorhandling/supervisor/IgnoreErrorAndContinueViewModel.kt)

* **Unit Test:** Learn how write unit-test for ViewModel which uses Kotlin Coroutines.
    * [ViewModelTest Code](app/src/test/java/me/amitshekhar/learn/kotlin/coroutines/ui/retrofit/single/SingleNetworkCallViewModelTest.kt)

## If this project helps you in anyway, show your love :heart: by putting a :star: on this project :v:

### License

```
   Copyright (C) 2022 Amit Shekhar

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to Learn Kotlin Coroutines

Just make pull request. You are in!


Thanks

[**Amit Shekhar**](https://amitshekhar.me)

You can connect with me on:

- [Twitter](https://twitter.com/amitiitbhu)
- [LinkedIn](https://www.linkedin.com/in/amit-shekhar-iitbhu)
- [GitHub](https://github.com/amitshekhariitbhu)
- [Facebook](https://www.facebook.com/amit.shekhar.iitbhu)

[**Read all of my blogs here.**](https://amitshekhar.me/blog)
