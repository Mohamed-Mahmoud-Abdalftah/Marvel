package com.marvel.home.presentation.uievent


sealed class CharacterUIEvent {
    data  object LoadInitialHome : CharacterUIEvent()
    data class OnCharacterClicked(val id: Long) : CharacterUIEvent()
    data object Dismiss : CharacterUIEvent()
}