package com.baker.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("예제 1")
    println("--------------------------")
    runWithOneThread()
    println("")
    println("")

    println("예제 2")
    println("--------------------------")
    runWithNThreads(10)
    println("")
    println("")

    println("예제 3")
    println("--------------------------")
    runNestedSuspendFunc()
    println("")
    println("")

    println("예제 4")
    println("--------------------------")

    val plans = mutableListOf<Plan>()

    for(num in 1..1000000) {
        plans.add(Plan("PLAN_${num}"))
    }
    val planSync = PlanSync(plans)
    runBlocking {
        val job = syncPlanDealList(planSync)
        job.join()
    }
}

fun runWithOneThread() {
    /**
     * launch { } 코루틴 빌더는 Dispatcher 를 재정의 하지 않았기 때문에 현재 스코프(runBlocking)의 Dispatcher 를 그대로 사용다.
     * runBlocking 코루틴 빌더는 내부적으로 GlobalScope을 사용하며 Dispatcher 는 BlockingEventLoop 을 사용하는데, 이는 큐를 이용한 이벤트 루프 형태의 Dispatcher 구현이다.
     * 그래서 아래 코드는, 실행(main) 스레드에서 이벤트 루프 기반으로 10번의 이벤트를 발생하여 실행한다.
     */
    runBlocking {
        val elapsedTime = measureTimeMillis {
            val jobs = ArrayList<Job>()
            (1..10).forEach { num ->
                jobs += launch {
                    longRunningTask(num, num + 1)
                }
            }

            jobs.forEach {
                it.join()
            }
        }

        print("소요 시간: ${elapsedTime}")
    }
}

fun runWithNThreads(threadCount: Int) {
    runBlocking {
        val elapsedTime = measureTimeMillis {
            val dispatcherThreadPool = newFixedThreadPoolContext(threadCount, "threadpool")
            val jobs = ArrayList<Job>()
            (1..10).forEach { num ->
                jobs += launch(dispatcherThreadPool) {
                    longRunningTask(num, num + 1)
                }
            }

            jobs.forEach {
                it.join()
            }
        }

        print("소요 시간: ${elapsedTime}")
    }
}

suspend fun longRunningTask(input1: Int, input2: Int): Int {
    log("시작 : input1 : $input1, input2 : $input2")
    delay(1000)
    val intermediateResult = input1 + input2
    log("중간 계산 : $intermediateResult")
    delay(1000)
    val finalResult = intermediateResult * 2
    log("최종 계산 후 종료 : $finalResult")
    return finalResult
}


fun runNestedSuspendFunc() {
    runBlocking {
        val elapsedTime = measureTimeMillis {
            val dispatcherThreadPool = newFixedThreadPoolContext(10, "threadpool")
            val jobs = ArrayList<Job>()
            (1..10).forEach { num ->
                jobs += launch(dispatcherThreadPool) {
                    nestedSuspendFunctionCall(num, num + 1)
                }
            }

            jobs.forEach {
                it.join()
            }
        }

        print("소요 시간: ${elapsedTime}")
    }
}

suspend fun nestedSuspendFunctionCall(input1: Int, input2: Int): Int {
    log("시작 : input1 : $input1, input2 : $input2")
    customDelay(1000)
    val finalResult = input1 + input2
    log("최종 계산 후 종료 : $finalResult")
    return finalResult
}

suspend fun customDelay(timeMillis: Long) {
    log("커스텀 Delay 시작")
    delay(timeMillis)
    log("커스텀 Delay 종료")
}

private fun log(message: String) {
    println("[${Thread.currentThread().name}] : $message")
}

class Plan(val planId: String)

class PlanSync(val plans: List<Plan>)


/**
 * 원본 Java 구현 코드
 * @Override
 * public void syncPlanDealList(PlanSync planSync) {
 *      CompletableFuture[] futures = Streams.ofNullable(planSync.getPlans())
 *          .map(Plan::getPlanId)
 *          .map(planningId -> CompletableFuture.runAsync(() -> syncPlanDealListPort.postSyncPlanDealListRequest(planningId)))
 *          .collect(Collectors.toList())
 *          .toArray(new CompletableFuture[]{});
 *
 *      try {
 *          CompletableFuture.allOf(futures).get();
 *      } catch (Exception e) {
 *          throw new RuntimeException(e);
 *      }
 * }
 */

fun syncPlanDealList(planSync:PlanSync): Job {
    val singleThreadPoolDispatcher = newSingleThreadContext("single thread")
    return CoroutineScope(singleThreadPoolDispatcher).launch {
        val elapsedTime = measureTimeMillis {
            planSync.plans.map { plan -> plan.planId }
                .map {
                    async {
                        postSyncPlanDealListRequest(it)
                    }
                }
                .forEach { println(it.await()) }
        }
        print("소요 시간: ${elapsedTime} ms")
    }
}

suspend fun postSyncPlanDealListRequest(planId: String): String {
    delay(1000)
    return "[${Thread.currentThread().name}] ${planId} 동기화 완료"
}