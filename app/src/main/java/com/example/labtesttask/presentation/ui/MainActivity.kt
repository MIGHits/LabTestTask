package com.example.labtesttask.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.labtesttask.presentation.components.NoteItem
import com.example.labtesttask.presentation.components.NoteListScreen
import com.example.labtesttask.presentation.components.NoteScreen
import com.example.labtesttask.presentation.model.Note
import com.example.labtesttask.presentation.theme.mainBackground
import com.google.gson.Gson
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         val mainViewModel = ViewModelProvider(this,MainViewModelFactory())
            .get(MainViewModel::class.java)
        if (savedInstanceState==null) {
            mainViewModel.setNoteList()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppNavHost(rememberNavController(),mainViewModel)
        }
    }
}

@Composable
fun MyAppNavHost(navController: NavHostController,viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "noteListScreen") {
        composable("noteListScreen"){ NoteListScreen(navController,viewModel) }
        composable ("noteScreen/{note}",
            arguments = listOf(navArgument("note")
            {type = NavType.StringType})){backStackEntry-> val note =
                backStackEntry
                    .arguments
                    ?.getString("note")?:"{}"
            NoteScreen(
                navController,
                viewModel
                ,note)}
    }
}





