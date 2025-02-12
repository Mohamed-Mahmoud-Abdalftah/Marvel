package com.marvel.home.presentation.uievent

import com.marvel.domain.models.CharacterEntity


sealed class CharacterUIEvent {
    data  object LoadInitialHome : CharacterUIEvent()
    data class OnCharacterClicked(val characterEntity: CharacterEntity) : CharacterUIEvent()
    data object Dismiss : CharacterUIEvent()
}