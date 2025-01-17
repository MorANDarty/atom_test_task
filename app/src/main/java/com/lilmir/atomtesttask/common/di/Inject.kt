package com.lilmir.atomtesttask.common.di

import com.lilmir.atomtesttask.common.data.MockApi
import com.lilmir.atomtesttask.feature.chargerslist.data.ChargerMapper
import com.lilmir.atomtesttask.feature.chargerslist.data.CityDetailsRepository
import com.lilmir.atomtesttask.feature.cities.data.CityMapper
import com.lilmir.atomtesttask.feature.cities.data.CityRepository

object Inject {

    val cityRepository: CityRepository by lazy { provideCityRepository() }
    val cityDetailsRepository: CityDetailsRepository by lazy { provideCityDetailsRepository() }

    fun provideCityMapper() = CityMapper()

    fun provideChargerMapper() = ChargerMapper()

    private fun provideCityRepository() = CityRepository(
        mockApi = MockApi(),
    )

    private fun provideCityDetailsRepository() = CityDetailsRepository(
        mockApi = MockApi()
    )
}