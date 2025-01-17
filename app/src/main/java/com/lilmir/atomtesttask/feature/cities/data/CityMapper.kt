package com.lilmir.atomtesttask.feature.cities.data

import com.lilmir.atomtesttask.feature.cities.domain.City
import com.lilmir.atomtesttask.feature.cities.ui.CityUiItem

class CityMapper {

    fun mapToUi(item: City): CityUiItem = CityUiItem(item.name)
}