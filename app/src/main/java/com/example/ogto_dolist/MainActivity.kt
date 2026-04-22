package com.example.ogto_dolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ogto_dolist.repository.Repository
import com.example.ogto_dolist.room.ListDb
import com.example.ogto_dolist.screen.UiScreen
import com.example.ogto_dolist.screen.UpdateScreen
import com.example.ogto_dolist.viewmodel.ListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val listDb = ListDb.getDatabase(context = context)
            val repo = Repository(listDb = listDb)
            val view = ListViewModel(repository = repo)


            val navController = rememberNavController()
            NavHost(navController, startDestination = "UiScreen"){
                composable("UiScreen"){
                    UiScreen(view, navController)
                }
                composable("UpdateScreen/{listId}") {
                    UpdateScreen(view, it.arguments?.getString("listId"), navController)
                }
            }
        }
    }
}

