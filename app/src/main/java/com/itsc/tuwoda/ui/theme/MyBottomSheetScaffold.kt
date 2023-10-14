package com.itsc.tuwoda.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
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
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itsc.tuwoda.R
import com.itsc.tuwoda.offices
import java.nio.file.WatchEvent

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

    var state by remember {
        mutableStateOf(true)
    }
    var stateSerch by remember {
        mutableStateOf(false)
    }

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
                            top = paddingValues.calculateTopPadding() * 0 + 30.dp
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
                            var text1 by remember { mutableStateOf(TextFieldValue()) }
                            TextField(
                                value = text1,
                                onValueChange = { text1 = it },
                                placeholder = {
                                        Text(
                                            text = "Город, улица...",
                                            color = Color.Gray
                                        )
                                    },
                                modifier = Modifier
                                    .weight(4f)
                                    .height(50.dp)
                                    .padding(end = 5.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xFFF1F2F4),
                                    textColor = Color.Black,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                ),
                                shape = RoundedCornerShape(percent = 10)
                            )
                            IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFF1F2F4)),
                                onClick = {
                                    stateSerch = !stateSerch
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.MoreVert,
                                        contentDescription = "morevert",
                                        tint = Color.Black,
                                    )
                                }
                            )
                            IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFF1F2F4)),
                                onClick = {},
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = "map",
                                        tint = Color.Black,
                                    )
                                }
                            )
                        }

                        val rull = listOf<String>(
                            "Работает сейчас",
                            "Минимальная загруженность",
                            "Ближе всего",
                            "Наличие пандуса",
                            "Рядом с метро"
                        )

                        if (stateSerch){
                            LazyVerticalGrid(
                                modifier = Modifier.fillMaxWidth(),
                                columns = GridCells.Adaptive(130.dp),
                                content = {
                                    itemsIndexed(rull){ _, item ->
                                        OutlinedButton(
                                            onClick = { },
                                            modifier = Modifier
                                                .size(width = 80.dp, height = 57.dp)
                                                .padding(2.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.White,
                                                contentColor = Blue,
                                            ),
                                            shape = RoundedCornerShape(20.dp),
                                        ) {
                                            Text(
                                                text = item,
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
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
                                    state = !state
                                },
                            ) {
                                Text(
                                    text = "Отделения",
                                    color = if(buttonColor)
                                        Blue
                                    else
                                        Color.White,
                                    overflow = TextOverflow.Visible
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
                                onClick = {
                                    buttonColor = true
                                    state = !state
                                },
                            ) {
                                Text(
                                    text = "Банкоматы",
                                    color = if(buttonColor)
                                        Color.White
                                    else
                                        Blue,
                                    overflow = TextOverflow.Visible
                                )
                            }
                            /*IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Blue),
                                onClick = {},
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.filter_by_near_empty_bank),
                                        contentDescription = "map",
                                        tint = Color.White,
                                    )
                                }
                            )*/
                        }
                        Divider(
                            thickness = 1.dp,
                            color = Color.LightGray,
                            modifier = Modifier
                                .padding(10.dp, 0.dp)
                        )

                        val allOffices = listOf<offices>(
                            offices(
                                addres = "г. Солнечногорск, ул. Красная, д. 60",
                                distance = 62105
                            ),
                            offices(
                                addres = "г. Красногорск, ул. Ленина, д.38б",
                                distance = 21965
                            ),
                            offices(
                                addres = "г. Москва, Звенигородское шоссе, д. 18/20, корп. 1",
                                distance = 4181
                            ),
                            offices(
                                addres = "г. Москва, Бродников пер., д. 4",
                                distance = 1857
                            )
                        )
                        val allAtms = listOf<offices>(
                            offices(
                                addres = "ул. Богородский Вал, д. 6, корп. 1",
                                distance = 62105
                            ),
                            offices(
                                addres = "ул. Скобелевская, д. 23",
                                distance = 21965
                            ),
                            offices(
                                addres = "пр-кт Ленинский, д. 111, корп. 1",
                                distance = 4181
                            ),
                            offices(
                                addres = "ул. Свободы, д. 13/2, стр. 1А",
                                distance = 1857
                            )
                        )


                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            itemsIndexed(
                                if (state) allOffices
                                else allAtms
                            ) { _, item ->
                                Row(
                                    modifier = Modifier
                                        .padding(start = 15.dp, top = 15.dp, end = 5.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource( id =
                                            if (state) R.drawable.icoffices
                                            else R.drawable.icatm
                                        ),
                                        contentDescription = "icoffices",
                                        modifier = Modifier
                                            .weight(2f)
                                            .padding(end = 10.dp)
                                            .clip(RoundedCornerShape(45.dp))
                                    )
                                    Text(
                                        text = item.addres,
                                        modifier = Modifier
                                            .weight(7f)
                                    )
                                    Text(
                                        text = "${item.distance} м",
                                        modifier = Modifier
                                            .weight(3f)
                                            .padding(start = 10.dp)
                                    )
                                }
                            }
                        }

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