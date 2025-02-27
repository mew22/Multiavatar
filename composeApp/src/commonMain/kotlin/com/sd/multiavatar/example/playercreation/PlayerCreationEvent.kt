package io.github.mew22.example.playercreation

sealed class PlayerCreationEvent {
    data object AvatarClicked : PlayerCreationEvent()
    data object AvatarBackgroundClicked : PlayerCreationEvent()
    data object AvatarClothesClicked : PlayerCreationEvent()
    data object AvatarHeadClicked : PlayerCreationEvent()
    data object AvatarMouthClicked : PlayerCreationEvent()
    data object AvatarEyesClicked : PlayerCreationEvent()
    data object AvatarTopClicked : PlayerCreationEvent()

    data class NameChanged(val name: String) : PlayerCreationEvent()
}
