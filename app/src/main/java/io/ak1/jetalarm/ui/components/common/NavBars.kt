package io.ak1.jetalarm.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import io.ak1.jetalarm.R
import io.ak1.jetalarm.ui.screens.Destinations

/**
 * Created by akshay on 02/11/21
 * https://ak1.io
 */


@Composable
fun BottomBar(
    parentNavController: NavController,
    navController: NavHostController
) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(4.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f, true))
            Image(
                painter = painterResource(R.drawable.ic_clock),
                contentDescription = stringResource(id = R.string.image_desc),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                modifier = Modifier
                    .clickable {
                        navController.navigate(Destinations.CLOCK_ROUTE) {
                            popUpTo(Destinations.CLOCK_ROUTE) { inclusive = true }
                        }
                    }
                    .padding(18.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_bell),
                contentDescription = stringResource(id = R.string.image_desc),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                modifier = Modifier
                    .clickable {
                        navController.navigate(Destinations.ALARM_ROUTE) {
                            popUpTo(Destinations.CLOCK_ROUTE)
                        }
                    }
                    .padding(18.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_settings),
                contentDescription = stringResource(id = R.string.image_desc),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                modifier = Modifier
                    .clickable {
                        parentNavController.navigate(Destinations.SETTINGS_ROUTE)
                    }
                    .padding(18.dp)
            )

        }
}