package com.teco.apparchitecure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.teco.apparchitecure.ui.screens.home.HomeScreen
import com.teco.apparchitecure.ui.theme.AppArchitecureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppArchitecureTheme {
                HomeScreen()
            }
        }
    }
}