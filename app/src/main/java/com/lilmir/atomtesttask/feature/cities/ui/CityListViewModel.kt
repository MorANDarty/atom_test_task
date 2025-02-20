package com.lilmir.atomtesttask.feature.cities.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lilmir.atomtesttask.common.di.Inject
import com.lilmir.atomtesttask.feature.cities.data.CityMapper
import com.lilmir.atomtesttask.feature.cities.data.CityRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Suppress("OPT_IN_USAGE")
class CityListViewModel(
    private val cityRepository: CityRepository,
    private val mapper: CityMapper
) : ViewModel() {

    private val _screenState: MutableStateFlow<CityListScreenState> =
        MutableStateFlow(CityListLoadingState)
    val screenState: StateFlow<CityListScreenState> = _screenState

    init {
        viewModelScope.launch {
            try {
                delay(1000)
                val items = cityRepository.getCityList().map { mapper.mapToUi(it) }
                _screenState.value = CityListContentState(items)
            } catch (e: Exception) {
                //  _screenState.value = CityListErrorState(...)
            }
        }
    }

    class CityListViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CityListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CityListViewModel(Inject.cityRepository, Inject.provideCityMapper()) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
