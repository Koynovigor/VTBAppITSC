package com.itsc.tuwoda.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.twotone.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itsc.tuwoda.R

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetScaffold(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    scaffoldState: BottomSheetScaffoldState,
    onState: (String) -> Unit,
    state: String,
    content: @Composable (PaddingValues) -> Unit = {},
    floatingActionButton: @Composable() (() -> Unit)? = {},
    ) {
    BottomSheetScaffold(
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 45.dp,
        sheetBackgroundColor = Black,
        floatingActionButton = floatingActionButton,
        sheetContent = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .offset(y = (-45).dp),
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = Color.Transparent,
                            navigationIconContentColor = Color.Transparent,
                            titleContentColor = Color.Transparent,
                            actionIconContentColor = Color.Transparent
                        ),
                        title = {
                            Icon(
                                painter = painterResource(id = R.drawable.divider),
                                contentDescription = "divider",
                                modifier = Modifier
                                    .width(70.dp),
                                tint = colorScheme.inverseOnSurface,
                            )
                        },
                    )
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(
                            top = paddingValues.calculateBottomPadding()
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            var text1 by remember { mutableStateOf("Hello") }

                            androidx.compose.material.TextField(
                                value = text1,
                                onValueChange = { text1 = it },
                                label = { Text("Label") },
                                modifier = Modifier
                                    .weight(5f)
                                    .height(50.dp)
                                    .padding(0.dp, 0.dp, 5.dp, 0.dp),
                                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color(0xFFF1F2F4),
                                    textColor = Color.Black
                                ),
                                shape = RoundedCornerShape(percent = 10)
                            )
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "map",
                                tint = Color.Black,
                                modifier = Modifier
                                    .clickable {
                                        // Todo -> handle click
                                    }
                                    .weight(1f)
                                    .size(50.dp)
                                    .background(Color(0xFFF1F2F4))
                            )
                        }
                        Row(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            var buttonColor by remember { mutableStateOf(false) }
                            OutlinedButton(
                                shape = RoundedCornerShape(20.dp),
                                colors = if(buttonColor)
                                    ButtonDefaults.buttonColors(Color.White)
                                else
                                    ButtonDefaults.buttonColors(Blue),
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(end = 5.dp)
                                    .height(50.dp),
                                onClick = {
                                    buttonColor = false
                                },
                            ) {
                                Text(
                                    text = "Отделения",
                                    color = if(buttonColor)
                                        Blue
                                    else
                                        Color.White,
                                    modifier = Modifier.padding(2.dp)
                                )
                            }
                            OutlinedButton(
                                shape = RoundedCornerShape(20.dp),
                                colors = if(buttonColor)
                                    ButtonDefaults.buttonColors(Blue)
                                else
                                    ButtonDefaults.buttonColors(Color.White),
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(end = 5.dp)
                                    .height(50.dp),
                                onClick = { buttonColor = true },
                            ) {
                                Text(
                                    text = "Банкоматы",
                                    color = if(buttonColor)
                                        Color.White
                                    else
                                        Blue,
                                    modifier = Modifier.padding(2.dp)
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.TwoTone.List,
                                contentDescription = "map",
                                tint = Color.Black,
                                modifier = Modifier
                                    .clickable {
                                        // Todo -> handle click
                                    }
                                    .weight(1f)
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Blue)
                            )
                        }
                        Divider(
                            thickness = 1.dp,
                            color = Color.LightGray,
                            modifier = Modifier
                                .padding(10.dp, 0.dp)
                        )

                    }
                }
            }
        },
        sheetShape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        content = content
    )
}