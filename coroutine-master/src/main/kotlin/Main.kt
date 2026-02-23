package org.example

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


//suspend fun sayHello() {
//    println("I'm saying hello (thread: ${Thread.currentThread().name})")
//    delay(2000) // suspend 2s without blocking thread
//    println("I'm done after 2s")
//}
//
//fun main() = runBlocking { // runBlocking used to run suspend from main
//    println("Main start (thread: ${Thread.currentThread().name})")
//    sayHello()
//    println("Main end")
//}

suspend fun fetchUserFromDb(): String {
    delay(1500)
    return "User: Cuong"
}

suspend fun fetchPostsFromApi(): String {
    delay(2500)
    return "3 posts from api"
}

suspend fun loadProfile(): String {
    val user = fetchUserFromDb()
    val post = fetchPostsFromApi()
    return "$user\n$post"
}

suspend fun loadProfileConcurrently(): String = coroutineScope {
    val userDeferred = async { fetchUserFromDb() }
    val postDeferred = async { fetchPostsFromApi() }

    val user = userDeferred.await()
    val post = postDeferred.await()
    "$user\n$post"
}

fun main() = runBlocking {
//    val time = measureTimeMillis {
////        println(loadProfile())
//        println(loadProfileConcurrently())
//    }
//    println("Took $time ms")
//    println("Start main")
//
//    val job = launch {
//        println("Launch started")
//        delay(1000)
//        println("Launch finished")
//    }
//
//    println("After launch")
//
//    job.join()
//
//    println("End main")
    val job = launch {
        repeat(5) { i ->
            println("Working $i")
            delay(500)
        }
    }

    delay(1200)
    println("Cancel job")
    job.cancel()
    job.join()

    println("Main finished")
}


