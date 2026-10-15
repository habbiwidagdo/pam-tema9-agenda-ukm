package com.agendaukm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform