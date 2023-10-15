package com.itsc.tuwoda

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    model: MyViewModel,
    office: Office,
    navController: NavHostController
) {
    val officeInfo = listOf<String>(
        model.curOffice.address,
        model.curOffice.distance.toString(),
        model.curOffice.openHours.toString(),
        model.curOffice.openHoursIndividual.toString(),
        model.curOffice.salePointFormat.toString(),
        model.curOffice.officeType.toString(),
        model.curOffice.kep.toString(),
        model.curOffice.rko.toString(),
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Button(onClick = {}) {
                        Text(
                            text = model.curOffice.name.toString(),
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            itemsIndexed(officeInfo) { _, item ->
                Divider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(10.dp, 5.dp)
                )
                Text(
                    text = item,
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}