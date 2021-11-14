package io.ak1.jetalarm.ui.screens.clock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.ak1.jetalarm.R
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import io.ak1.jetalarm.ui.components.DefaultAppBar
import io.ak1.jetalarm.ui.components.TimeZoneListRowSmallView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/**
 * Created by akshay on 02/11/21
 * https://ak1.io
 */

@Composable
fun TimeZoneScreen(navController: NavController) {
    val viewModel by inject<ClockViewModel>(ClockViewModel::class.java)
    val selectedTimeZones = viewModel.selectedTimeZoneList().collectAsState(initial = emptyList())
    val allTimeZones = viewModel.timeZoneList().collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = null, block = {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.prePopulateDataBase()
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            Modifier.fillMaxSize(),
            topBar = {
                DefaultAppBar(titleId = R.string.time_zone_title, navController = navController)
            }
        ) {

            LazyColumn(content = {

                if (selectedTimeZones.value.isNotEmpty()) {
                    item {
                        Text(
                            text = "Selected TimeZones", style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(0.dp, 4.dp)
                        )
                    }
                    items(selectedTimeZones.value) { item ->
                        TimeZoneListRowSmallView(item) {
                            viewModel.updateTimeZone(item) {}
                        }
                    }
                }
                item {
                    Text(
                        text = "All TimeZones", style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(0.dp, 4.dp)
                    )
                }
                items(allTimeZones.value) { item ->
                    TimeZoneListRowSmallView(item) {
                        viewModel.updateTimeZone(item) {
                            coroutineScope.launch {
                                if (it) {
                                    listState.scrollToItem(

                                        listState.firstVisibleItemIndex + 1,
                                        listState.firstVisibleItemScrollOffset
                                    )
                                } else {
                                    if (listState.firstVisibleItemIndex > 0) {
                                        listState.scrollToItem(
                                            listState.firstVisibleItemIndex - 1,
                                            listState.firstVisibleItemScrollOffset
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }, modifier = Modifier.padding(16.dp, 0.dp), state = listState)
        }
    }
}