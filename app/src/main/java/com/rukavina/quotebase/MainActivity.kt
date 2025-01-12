package com.rukavina.quotebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.rukavina.quotebase.navigation.BottomNavigationBar
import com.rukavina.quotebase.navigation.NavigationGraph
import com.rukavina.quotebase.ui.theme.QuoteBaseTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        val isAuthenticated = FirebaseAuth.getInstance().currentUser != null

        setContent {
            QuoteBaseTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        if (isAuthenticated) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        isAuthenticated = isAuthenticated,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // Sign out the user when the app is stopped
        FirebaseAuth.getInstance().signOut()
    }
}