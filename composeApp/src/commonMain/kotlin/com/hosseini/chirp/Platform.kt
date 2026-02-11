package com.hosseini.chirp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform