package com.example.labtesttask.presentation.components

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.labtesttask.R
import com.example.labtesttask.presentation.components.FontFamily.customFontFamily
import com.example.labtesttask.presentation.model.Note
import com.example.labtesttask.presentation.theme.mainBackground
import com.example.labtesttask.presentation.theme.softOrange
import com.example.labtesttask.presentation.theme.tagTextColor
import com.example.labtesttask.presentation.theme.textSoft
import com.example.labtesttask.presentation.theme.unfocusedBorder
import com.example.labtesttask.presentation.ui.MainViewModel
import com.google.gson.Gson
import java.util.UUID

@Composable
fun NoteScreen(navController: NavController,
               viewModel: MainViewModel,
               noteJson: String){
    var note = Note("","","initial")
    if (noteJson.isNotEmpty()) {
        note = Gson().fromJson(noteJson, Note::class.java)
    }

    var tag by remember{ mutableStateOf(note.tag) }
    var text by remember{ mutableStateOf(note.text) }

    Box(modifier = Modifier.fillMaxSize()
        .background(color = mainBackground),
        contentAlignment = Alignment.Center){
        Icon(painter = painterResource(
            id = R.drawable.back_icon),
            contentDescription = null,
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .offset(x = 24.dp,y = 63.dp)
                .align(Alignment.TopStart)
                .clickable{ navController.navigate("noteListScreen")},
        )

        Column(modifier = Modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight(0.5f)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = tagTextColor,
                    unfocusedBorderColor = unfocusedBorder,
                    unfocusedContainerColor = textSoft,
                    focusedContainerColor = textSoft,
                    focusedBorderColor = softOrange,
                    focusedLabelColor = softOrange
                ),
                value = tag,
                onValueChange = { tag = it },
                label = { Text("Добавьте заголовок")}
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = tagTextColor,
                    unfocusedBorderColor = unfocusedBorder,
                    unfocusedContainerColor = textSoft,
                    focusedContainerColor = textSoft,
                    focusedBorderColor = softOrange,
                    focusedLabelColor = softOrange
                ),
                value = text,
                onValueChange = { text = it },
                label = { Text("Введите текст") }
            )
            Button(onClick = {
                when(note.id){
                    "initial"->viewModel
                        .addNewNote(
                            Note(
                            tag,
                            text,
                            UUID.randomUUID().toString())
                        )
                    else->viewModel
                        .editNote(note,tag,text)
                }
                navController.navigate("noteListScreen")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = softOrange,
                    contentColor = textSoft
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                Text(text = "Сохранить",
                    fontSize = 20.sp,
                    fontFamily = customFontFamily)
            }
        }
    }
}