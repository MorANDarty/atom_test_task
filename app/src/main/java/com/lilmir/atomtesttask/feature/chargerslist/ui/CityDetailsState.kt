package com.lilmir.atomtesttask.feature.chargerslist.ui

sealed class CityDetailsState

data object CityDetailsLoadingState : CityDetailsState()

data class CityDetailsContentState(
    val items: List<ChargerUiItem>
): CityDetailsState()
