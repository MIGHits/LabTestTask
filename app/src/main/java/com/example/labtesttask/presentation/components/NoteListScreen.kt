package com.example.labtesttask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.labtesttask.R
import com.example.labtesttask.presentation.components.FontFamily.customFontFamily
import com.example.labtesttask.presentation.model.Note
import com.example.labtesttask.presentation.theme.mainBackground
import com.example.labtesttask.presentation.theme.softOrange
import com.example.labtesttask.presentation.theme.textSoft
import com.example.labtesttask.presentation.ui.MainViewModel

@Composable
fun NoteListScreen(navController: NavController, viewModel: MainViewModel){
    val noteListState by viewModel.noteList.collectAsState()
    var showStub by remember { mutableStateOf(true) }
    when(noteListState.size){
        0->showStub = true
        else->showStub = false
    }
    Column(modifier = Modifier.fillMaxSize().background(mainBackground)) {
        if (showStub) {
            Column(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(
                    top = 150.dp,
                    start = 50.dp,
                    end = 50.dp
                ).align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = null
                )
                Text(
                    text = "У вас пока нет заметок",
                    textAlign = TextAlign.Center,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .padding(
                        top = 50.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
            ) {
                items(noteListState) { item ->
                    NoteItem(navController, Note(item.tag, item.text, item.id), viewModel)
                }
            }
        }
        Button(
            onClick = {
                val noteNull = ""
                navController.navigate("noteScreen/$noteNull")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = softOrange,
                contentColor = textSoft
            ), modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .align(Alignment.End)
                .padding(
                    top = 15.dp,
                    end = 15.dp
                )
        ) {
            Text(
                text = "+",
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        }
    }
}