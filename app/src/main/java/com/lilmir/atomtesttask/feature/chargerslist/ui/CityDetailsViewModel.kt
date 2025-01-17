package com.lilmir.atomtesttask.feature.chargerslist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lilmir.atomtesttask.common.di.Inject
import com.lilmir.atomtesttask.feature.chargerslist.data.ChargerMapper
import com.lilmir.atomtesttask.feature.chargerslist.data.CityDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityDetailsViewModel(
    name: String,
    private val repository: CityDetailsRepository,
    private val chargerMapper: ChargerMapper
) : ViewModel() {

    private val _screenState: MutableStateFlow<CityDetailsState> =
        MutableStateFlow(CityDetailsLoadingState)
    val screenState: StateFlow<CityDetailsState> = _screenState

    init {
        fetchDetails(name)
    }

    private fun fetchDetails(name: String) {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) {
                flow<Unit> { delay(1000) }
                    .firstOrNull().let {
                        repository.getChargersByCity(name).map { chargerMapper.mapToUi(it) }
                    }
            }
            _screenState.value = CityDetailsContentState(items = items)
        }
    }

    class CityDetailsViewModelFactory(private val name: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CityDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CityDetailsViewModel(
                    name = name,
                    repository = Inject.cityDetailsRepository,
                    chargerMapper = Inject.provideChargerMapper()
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}