package com.lilmir.atomtesttask.feature.chargerslist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CityDetailsScreen(name: String, navController: NavController) {
    val viewModel: CityDetailsViewModel =
        viewModel(factory = CityDetailsViewModel.CityDetailsViewModelFactory(name))
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    CityDetailsScreen(state = state) {
        navController.popBackStack()
    }
}

@Composable
private fun CityDetailsScreen(state: CityDetailsState, onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 20.dp)
    ) {
        IconButton(onClick = onBackPressed) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            when (state) {
                is CityDetailsLoadingState -> {
                    LoadingStateScreen()
                }

                is CityDetailsContentState -> {
                    (state as? CityDetailsContentState)?.let {
                        ChargerListScreen(state = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun ChargerListScreen(state: CityDetailsContentState) {
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn {
            state.items.forEach { item ->
                item(key = item.address) {
                    ChargerItem(item = item)
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }
    }
}

@Composable
private fun ChargerItem(item: ChargerUiItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = item.busyColor, shape = RoundedCornerShape(16.dp)),
    ) {
        Column(
            Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
                .padding(12.dp)
        ) {
            Text(text = item.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.address, fontSize = 17.sp)
        }
        Box(
            modifier = Modifier
                .padding(end = 12.dp)
                .align(Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color = item.busyColor, shape = CircleShape)
            )
        }
    }
}

@Composable
private fun LoadingStateScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
