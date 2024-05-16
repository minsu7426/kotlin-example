package com.example.backend.domain.test.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeApi {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/test")
    fun test(): String {

        var a: Int
        a = sum(1, 3)

        var b: Int?
        b = 1
        println(b)

        b = null
        println(b)

        var c: String
        c = sum(1, 2).toString()
        print(c);

        return a.toString();
    }

    @GetMapping("/test1")
    fun test1(a:String, b:Int): String {
        println("javaClass = ${javaClass}")
        println("a = [${a}], b = [${b}]")

        return addTest(a.toInt(), b).toString()
    }

    fun addTest(a: Int, b: Int) = a + b

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // Define a data class.
    data class User(val name: String, val age: Int)

    // Define a companion object.
    /*
    object MyClass {
        fun doSomething() { ... }
    }

    // Define a higher-order function.
    fun MyClass.myFunction(a: Int, b: Int, f: (Int, Int) -> Int): Int {
        return f(a, b)
    }

    // Use a lambda expression.
    val result = MyClass.myFunction(1, 2) { a, b -> a + b }

    // Define an extension function.
    fun String.toUpperCase(): String {
        return this.toUpperCase()
    }
    시작하기
     */
}