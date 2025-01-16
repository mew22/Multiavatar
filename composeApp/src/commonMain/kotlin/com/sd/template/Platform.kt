package com.sd.template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
