package com.darkoverlordofdata.entitas.test

import com.darkoverlordofdata.bunny.Bunny
import com.darkoverlordofdata.entitas.Matcher

fun main(args: Array<String>) {
    // Prints "Hello, World" to the terminal window.
    println("Hello, World")
    MyTest().run()
}


class MyTest : Bunny() {
    init {

        describe("this is a test")

        test("Smoke Test Bunny::expect")
        {
            expect(10).to.equal(5+5)
        }

        test("Smoke Test Bunny::assert")
        {
            assert.equal(0, 1 - 1)
        }

        test("ECS Test Matcher")
        {
            var m = Matcher.allOf(1, 2, 3)
            //expect(m.toString()).to.equal("AllOf(Position,Movement,Resource)")
            expect(m.toString()).to.equal("AllOf(1,2,3)")
        }

    }
}

