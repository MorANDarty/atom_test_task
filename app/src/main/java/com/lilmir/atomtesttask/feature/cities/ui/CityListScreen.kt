package com.lilmir.atomtesttask.feature.cities.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CityListScreen(navController: NavController) {
    val viewModel: CityListViewModel =
        viewModel(factory = CityListViewModel.CityListViewModelFactory())
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    CityListScreen(state) {
        navController.navigate("cityDetails/$it")
    }
}

@Composable
private fun CityListScreen(cityListScreenState: CityListScreenState, onClick: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (cityListScreenState) {
            is CityListLoadingState -> {
                LoadingStateScreen()
            }

            is CityListContentState -> {
                (cityListScreenState as? CityListContentState)?.let {
                    ContentStateScreen(
                        contentState = it,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ContentStateScreen(contentState: CityListContentState, onClick: (String) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Выберите город",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            contentState.items.forEach {
                item(key = it.name) {
                    CityListItem(name = it.name, onClick)
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }
    }
}

@Composable
private fun LoadingStateScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CityListItem(name: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .clickable { onClick(name) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = name, fontSize = 17.sp, modifier = Modifier.padding(horizontal = 20.dp))
    }
}
