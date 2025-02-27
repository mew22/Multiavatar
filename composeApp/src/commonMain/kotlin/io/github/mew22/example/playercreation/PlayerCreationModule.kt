package io.github.mew22.example.playercreation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playerCreationModule = module {
    viewModelOf(::PlayerCreationViewModel)
}
