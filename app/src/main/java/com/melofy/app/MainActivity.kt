package com.melofy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.melofy.app.ui.AuthScreen
import com.melofy.app.ui.MainScreen
import com.melofy.app.ui.MainViewModel
import com.melofy.app.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: MainViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsState()

                if (uiState.isLoggedIn) {
                    MainScreen(
                        viewModel = viewModel,
                        onLogout = { viewModel.logout() }
                    )
                } else {
                    AuthScreen(
                        onLogin = { username -> viewModel.login(username) }
                    )
                }
            }
        }
    }
}
