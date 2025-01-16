package com.sd.template

class Greeting {
    private val platform = getPlatform()

    fun greet(): String =
        "Hello, ${platform.name}, flavor ${BuildKonfig.flavor}!"
}
