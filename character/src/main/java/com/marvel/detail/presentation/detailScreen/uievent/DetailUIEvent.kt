package com.marvel.detail.presentation.detailScreen.uievent


sealed class DetailUIEvent {
    data  class LoadInitialComics(val id: Int) : DetailUIEvent()
    data  class LoadInitialSeries(val id: Int) : DetailUIEvent()
    data  class LoadInitialStories(val id: Int) : DetailUIEvent()
    data  class LoadInitialEvents(val id: Int) : DetailUIEvent()
    data object Dismiss : DetailUIEvent()
}
