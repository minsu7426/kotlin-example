package com.example.backend.domain.test.entity

fun main() {

    var a = Prs(123)

    println("a = ${a}")
}

data class Prs(var birth:Int, var name:String){

    constructor(birth:Int) : this(birth, "default") {
        this.birth = birth
        this.name = "defaul11t"
    }
}

fun exwhen(a: Any) {
    var b =
    when (a) {
        1 -> a
        2 -> {
            println("22")
            2
        }
        else -> {
            println("elseee")
            "else"
        }
    }

    println(b)
}

class Test {

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // Define a data class.
    data class User(val name: String, val age: Int)

    // Define a companion object.
    object MyClass {

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

}