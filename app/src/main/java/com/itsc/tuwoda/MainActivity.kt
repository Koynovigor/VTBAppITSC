package com.itsc.tuwoda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itsc.tuwoda.ui.theme.MyBottomSheetScaffold
import com.itsc.tuwoda.ui.theme.VTBTheme

class MainActivity : ComponentActivity() {

    private val model = MyViewModel()


    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VTBTheme {
                val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()


                Scaffold(
                    floatingActionButton = {
                        if (model.stateMap) {
                            Column(
                                modifier = Modifier.offset(y = 25.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                Box(
                                    contentAlignment = Alignment.TopEnd
                                ) {

                                }
                                MyFloatingActionButton(
                                    background = R.drawable.ellipsefull,
                                    icon = R.drawable.geo,
                                    padding = 5.dp,
                                    onState = {
                                        val intent =
                                            packageManager.getLaunchIntentForPackage("ru.yandex.taxi")
                                        startActivity(intent)
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    content = {
                        //YandexMap()

                        MyBottomSheetScaffold(
                            scaffoldState = bottomSheetScaffoldState,
                            state = model.stateTextTitleSailing,
                            onState = { state ->
                                model.stateTextTitleSailing = state
                            }
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            contentAlignment = Alignment.TopEnd,
                        ) {
                            FloatingActionButton(
                                onClick = { },
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .size(50.dp)
                                    .paint(
                                        painter = painterResource(id = R.drawable.more),
                                        contentScale = ContentScale.Crop
                                    ),
                                containerColor = Color.Transparent,
                                contentColor = Color.Transparent,
                                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.morepoint),
                                    contentDescription = "moreVert",
                                    modifier = Modifier
                                        .size(50.dp * 0.6f)
                                        .background(Color.Transparent),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                )
            }

        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
    }
}

/*
* if (model.stateRouting){
                                    MyFABWithText(
                                        text = "Построить новый маршрут",
                                        x = (-15).dp,
                                        y = (-45).dp,
                                        paddingCardHorizontal = 20.dp,
                                        paddingTextHorizontal = 10.dp,
                                        sizeButton = 45.dp,
                                        backgroundButton = R.drawable.ellipsefull,
                                        iconButton = R.drawable.plus,
                                        colorBackgroundButton = R.color.blue_light_main,
                                        colorBackgroundText = R.color.blue_light_main_alfa,
                                        model = model
                                    )
                                    if(model.stateMapDialog){
                                        AlertDialog(
                                            containerColor = colorResource(id = R.color.blue_main_alfa),
                                            onDismissRequest = {
                                                model.stateMapDialog = false
                                                model.stateTextTitle = ""
                                                model.stateTextName = ""
                                            },
                                            confirmButton = {
                                                Button(
                                                    onClick = { TODO },
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color.White,
                                                        contentColor = Color.Black
                                                    )
                                                ) {
                                                    Text(text = "Построить")
                                                }
                                            },
                                            dismissButton = {
                                                Button(
                                                    onClick = {
                                                        model.stateMapDialog = false
                                                        model.stateTextTitle = ""
                                                        model.stateTextName = ""
                                                    },
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color.White,
                                                        contentColor = Color.Black
                                                    )
                                                ) {
                                                    Text(text = "Назад")
                                                }
                                            },
                                            text = {
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    ItemDialog(
                                                        name = "Начальная точка",
                                                        colorName = Color.White,
                                                        state = model.stateTextName,
                                                        onState = {state ->
                                                            model.stateTextName = state
                                                        }
                                                    )
                                                    ItemDialog(
                                                        name = "Конечная точка",
                                                        colorName = Color.White,
                                                        state = model.stateTextTitle,
                                                        onState = {state ->
                                                            model.stateTextTitle = state
                                                        }
                                                    )
                                                    MyFloatingActionButton(
                                                        background = R.drawable.ellipsefull,
                                                        icon = R.drawable.plus,
                                                        scaleY = 15.dp,
                                                        colorBackground = R.color.white,
                                                        tint = colorResource(id = R.color.blue_main_alfa),
                                                    )
                                                }

                                            },
                                        )
                                    }
                                    MyFABWithText(
                                        backgroundButton = R.drawable.ellipsefull,
                                        iconButton = R.drawable.star,
                                        colorBackgroundButton = R.color.blue_light_main,
                                        colorBackgroundText = R.color.blue_light_main_alfa,
                                        text = "Повторить маршрут",
                                        sizeButton = 45.dp,
                                        x = (-65).dp,
                                        paddingCardHorizontal = 20.dp,
                                        paddingTextHorizontal = 10.dp,
                                    )
                                }
                                MyFloatingActionButton(
                                    background = R.drawable.ellipsefull,
                                    icon = R.drawable.routing,
                                    padding = 5.dp,
                                    state = model.stateRouting,
                                    onState = {state ->
                                        model.stateRouting = state
                                    }
                                )
*
*
* */