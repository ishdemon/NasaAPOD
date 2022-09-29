package com.example.nasagallery

import io.mockk.MockKStubScope

sealed class ReturnValue<T> {

    class WithoutCrash<T>(val value: T) : ReturnValue<T>()

    class Crash<T>(val throwable: Throwable) : ReturnValue<T>()

}

val <T : Any?> T.withoutCrash
    get() = ReturnValue.WithoutCrash(this)

inline fun <reified T> Throwable.asCrash(): ReturnValue.Crash<T> {
    return ReturnValue.Crash(this)
}

infix fun <T> MockKStubScope<T, *>.returnOrCrash(returnValue: ReturnValue<T>) {
    when (returnValue) {
        is ReturnValue.WithoutCrash -> {
            returns(returnValue.value)
        }
        is ReturnValue.Crash -> {
            throws(returnValue.throwable)
        }
    }
}