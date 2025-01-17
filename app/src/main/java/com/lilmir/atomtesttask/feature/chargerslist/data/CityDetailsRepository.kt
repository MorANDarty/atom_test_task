package com.lilmir.atomtesttask.feature.chargerslist.data

import com.lilmir.atomtesttask.common.data.MockApi
import com.lilmir.atomtesttask.feature.chargerslist.domain.Charger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CityDetailsRepository(
    private val mockApi: MockApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getChargersByCity(name: String) = withContext(dispatcher) {
        mockApi.getChargers().items
            .filter { it.city == name }
            .map {
                val chargerData = it.charger
                Charger(chargerData.name, chargerData.busy, chargerData.address)
            }
            .sortedBy { it.name }
    }
}
