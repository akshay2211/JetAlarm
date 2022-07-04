package io.ak1.jetalarm.ui.screens.home.clock

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.ak1.jetalarm.R
import io.ak1.jetalarm.data.viewmodels.ClockViewModel
import io.ak1.jetalarm.ui.components.TimeZoneListRowSmallView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/**
 * Created by akshay on 02/11/21
 * https://ak1.io
 */

@Composable
fun TimeZoneScreen(navigateUp: () -> Unit) {
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
    var showEffect by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(listState.firstVisibleItemIndex == 0) {
        showEffect = listState.firstVisibleItemIndex == 0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(elevation = if (showEffect) 4.dp else 0.dp) {
                    if (showEffect) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_left),
                            contentDescription = stringResource(id = R.string.navigate_back),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                            modifier = Modifier
                                .clickable {
                                    navigateUp.invoke()
                                }
                                .padding(12.dp)
                        )
                    } else {
                        Spacer(modifier = Modifier.width(48.dp))
                    }
                    Text(
                        text = stringResource(id = R.string.time_zone_title),
                        style = MaterialTheme.typography.h6, modifier = Modifier.padding(0.dp, 9.dp)
                    )

                }
            }
        ) {
            LazyColumn(content = {

                /*if (selectedTimeZones.value.isNotEmpty()) {
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
                }*/
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