package io.ak1.jetalarm.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.ak1.jetalarm.R
import io.ak1.jetalarm.ui.screens.Destinations

/**
 * Created by akshay on 28/10/21
 * https://ak1.io
 */

@Composable
fun DefaultAppBar(
    @DrawableRes iconId: Int = R.drawable.ic_arrow_left,
    @StringRes titleId: Int,
    navController: NavController
) {
    Row(modifier = Modifier.padding(4.dp)) {
        Image(
            painter = painterResource(iconId),
            contentDescription = stringResource(id = R.string.navigate_back),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = Modifier
                .clickable {
                    navController.navigateUp()
                }
                .padding(12.dp)
        )
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.h6, modifier = Modifier.padding(0.dp, 9.dp)
        )
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(4.dp)) {
        Spacer(modifier = Modifier.weight(1f, true))
        Image(
            painter = painterResource(R.drawable.ic_clock),
            contentDescription = stringResource(id = R.string.image_desc),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = Modifier
                .clickable {
                    navController.navigate(Destinations.HOME_ROUTE)
                }
                .padding(18.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_bell),
            contentDescription = stringResource(id = R.string.image_desc),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = Modifier
                .clickable {
                    navController.navigate(Destinations.HOME_ROUTE)
                }
                .padding(18.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_settings),
            contentDescription = stringResource(id = R.string.image_desc),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            modifier = Modifier
                .clickable {
                    navController.navigate(Destinations.SETTINGS_ROUTE)
                }
                .padding(18.dp)
        )

    }
}