package com.lilmir.atomtesttask.feature.cities.ui

sealed class CityListScreenState

data object CityListLoadingState : CityListScreenState()

data class CityListContentState(val items: List<CityUiItem>) : CityListScreenState()