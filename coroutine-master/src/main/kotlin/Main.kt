package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

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

fun main() = runBlocking {
    val time = measureTimeMillis {
        println(loadProfile())
    }
    println("Took $time ms")
}


