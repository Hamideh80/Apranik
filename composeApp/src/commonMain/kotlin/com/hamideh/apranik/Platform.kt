package com.hamideh.apranik

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform