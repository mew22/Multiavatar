package io.github.mew22.example.playercreation

import io.github.mew22.AvatarData
import io.github.mew22.AvatarDataPart
import io.github.mew22.example.MviViewModel
import kotlinx.coroutines.flow.update

class PlayerCreationViewModel :
    MviViewModel<PlayerCreationEvent, PlayerCreationState>(PlayerCreationState()) {

    init {
        on<PlayerCreationEvent.NameChanged> { event ->
            internalState.update { state -> state.copy(playerName = event.name) }
        }

        onClick<PlayerCreationEvent.AvatarClicked> { event ->
            internalState.update { state -> state.copy(playerIcon = AvatarData.generateRandom()) }
        }

        onClick<PlayerCreationEvent.AvatarBackgroundClicked> {
            internalState.update { state -> state.copy(playerIcon = state.playerIcon.copy(background = AvatarDataPart.getRandomPart())) }
        }

        onClick<PlayerCreationEvent.AvatarClothesClicked> {
            internalState.update { state -> state.copy(playerIcon = state.playerIcon.copy(clothes = AvatarDataPart.getRandomPart())) }
        }

        onClick<PlayerCreationEvent.AvatarHeadClicked> {
            internalState.update { state -> state.copy(playerIcon = state.playerIcon.copy(head = AvatarDataPart.getRandomPart())) }
        }

        onClick<PlayerCreationEvent.AvatarMouthClicked> {
            internalState.update { state -> state.copy(playerIcon = state.playerIcon.copy(mouth = AvatarDataPart.getRandomPart())) }
        }

        onClick<PlayerCreationEvent.AvatarEyesClicked> {
            internalState.update { state -> state.copy(playerIcon = state.playerIcon.copy(eyes = AvatarDataPart.getRandomPart())) }
        }

        onClick<PlayerCreationEvent.AvatarTopClicked> {
            internalState.update { state -> state.copy(playerIcon = state.playerIcon.copy(top = AvatarDataPart.getRandomPart())) }
        }
    }
}
