package io.github.mew22.example.playercreation

import androidx.compose.runtime.Immutable
import io.github.mew22.AvatarData

@Immutable
data class PlayerCreationState(
    val playerName: String = "",
    val playerIcon: AvatarData = AvatarData.generateWithSha256("azerty").getOrThrow(),
)
