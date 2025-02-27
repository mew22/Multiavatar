package io.github.mew22.example

import io.github.mew22.example.playercreation.playerCreationModule
import org.koin.dsl.KoinAppDeclaration

val koinConfig: KoinAppDeclaration = {
    modules(playerCreationModule)
}
