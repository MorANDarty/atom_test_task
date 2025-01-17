package com.lilmir.atomtesttask.feature.cities.data

import com.lilmir.atomtesttask.common.data.MockApi
import com.lilmir.atomtesttask.feature.cities.domain.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CityRepository(
    val mockApi: MockApi,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCityList(): List<City> = withContext(dispatcher) {
        mockApi.getChargers()
            .let { data ->
                data.items.associateBy { dataItem ->
                    dataItem.city
                }.keys.map { City(it) }
            }
    }
}
