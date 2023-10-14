package com.itsc.tuwoda

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.itsc.tuwoda.ui.theme.MyBottomSheetScaffold
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.MapViewModel
import com.itsc.tuwoda.ui.theme.VTBTheme
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MainActivity : ComponentActivity() {

    private val model = MyViewModel()
    private var mapViewModel: MapViewModel? = null

    private lateinit var context: Context
    private lateinit var locationManager: LocationManager
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private var currentBank:Int? = null

    //region Permission func
    private fun registerPermissionListener(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                //mapViewModel?.goToMyLocation()
            }
            else{

            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun doItAndCheckPermissions(
        action:() -> Unit
    ){
        when{
            (ContextCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
                    &&
                    ContextCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                    &&
                    ContextCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.INTERNET
                    ) == PackageManager.PERMISSION_GRANTED )-> {
                action()
            }
            else -> {
                pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                pLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                pLauncher.launch(Manifest.permission.INTERNET)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("f3d7b1d9-51c9-46c4-9c3c-c3e25d30f0d8")
        MapKitFactory.initialize(this)
        locationManager = MapKitFactory.getInstance().createLocationManager()
        //region locationManager Setting

        locationManager.requestSingleUpdate(
            object: LocationListener {
                override fun onLocationUpdated(p0: Location) {
                    mapViewModel?.setMyLocation(
                        p0.position
                    )
                }

                override fun onLocationStatusUpdated(p0: LocationStatus) {
                    Toast(
                        context,

                        )
                }
            }
        )

        locationManager.subscribeForLocationUpdates(
            50.0,
            10,
            50.0,
            false,
            FilteringMode.ON,
            object: LocationListener {
                override fun onLocationUpdated(p0: Location) {
                    mapViewModel?.setMyLocation(
                        p0.position
                    )
                    mapViewModel?.initRoads(p0.position)
                }

                override fun onLocationStatusUpdated(p0: LocationStatus) {
                    //Toast.makeText(context,"123", Toast.LENGTH_LONG).show()
                }

            }
        )
        //endregion
        setContent {
            VTBTheme {
                val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
                context = LocalContext.current
                mapViewModel = viewModel<MapViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun<T: ViewModel> create(modelClass: Class<T>): T{
                            return MapViewModel(
                                mapView = MapView(context).apply{
                                    this.map.move(
                                        CameraPosition(
                                            Point(
                                                56.452387,
                                                84.972267
                                            ),
                                            10.0f,
                                            0.0f,
                                            0.0f),
                                        Animation(Animation.Type.SMOOTH, 0f),
                                        null
                                    )
                                },
                                context = context
                            ) as T
                        }
                    }
                )

                mapViewModel?.initBankPlacemarks(
                    id = listOf(1,2,3,4,5),
                    l = listOf(
                        Pair(56.454424, 84.935289),
                        Pair(56.537350, 84.953260),
                        Pair(56.503949, 85.021851),
                        Pair(56.474359, 85.002254),
                        Pair(56.481329, 84.967838)
                    ),
                ){ id->
                    currentBank = id
                }

                Scaffold(
                    content = {
                        WindowInsetsControllerCompat(window, window.decorView).apply {
                            hide(WindowInsetsCompat.Type.navigationBars())
                            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        }
                        MyBottomSheetScaffold(
                            paddingValues = it,
                            scaffoldState = bottomSheetScaffoldState,
                            state = model.stateTextTitleSailing,
                            onState = { state ->
                                model.stateTextTitleSailing = state
                            },
                            content = {
                                YandexMap()
                            },
                            floatingActionButton = {
                                MyFloatingActionButton(
                                    background = R.drawable.ellipsefull,
                                    icon = R.drawable.geo,
                                    modifier = Modifier
                                        .offset(y = (-35).dp),
                                    onState = {
                                        doItAndCheckPermissions {
                                            mapViewModel?.goToMyLocation()
                                        }
                                    }
                                )
                            },
                            model = model
                        )
                    }
                )
            }
        }
        registerPermissionListener()
    }
    override fun onStop() {
        mapViewModel?.mapView?.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        mapViewModel?.mapView?.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    @Composable
    fun YandexMap(
        modifier: Modifier = Modifier
    ) {
        AndroidView(
            modifier = modifier.fillMaxSize(),
            factory = {
                mapViewModel!!.mapView
            },
            update = {
                mapViewModel!!.mapView
            }
        )
    }
    override fun onResume() {
        super.onResume()
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}