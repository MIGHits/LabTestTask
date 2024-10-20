package com.example.labtesttask.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.labtesttask.R
import com.example.labtesttask.presentation.components.FontFamily.customFontFamily
import com.example.labtesttask.presentation.model.Note
import com.example.labtesttask.presentation.theme.PurpleGrey40
import com.example.labtesttask.presentation.theme.softOrange
import com.example.labtesttask.presentation.theme.tagTextColor
import com.example.labtesttask.presentation.theme.unfocusedBorder
import com.example.labtesttask.presentation.ui.MainViewModel
import com.google.gson.Gson
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun NoteItem(navController:NavController,note: Note,viewModel: MainViewModel){
    val delete = SwipeAction(
        onSwipe = {
          viewModel.removeNote(note)
        },
        icon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.bin),
                contentDescription = null,
                tint = Color.White)
        },
        background = softOrange
    )

    val edit = SwipeAction(
        onSwipe = {
            val jsonNote = Gson().toJson(note)
            navController.navigate("noteScreen/$jsonNote")
        },
        icon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.pencil),
                contentDescription = null,
                tint = Color.White)
        },
        background = unfocusedBorder
    )

    SwipeableActionsBox (
        startActions = listOf(delete),
        endActions = listOf(edit),
        backgroundUntilSwipeThreshold = Color.Transparent){
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
            .background(color = Color.White,
                shape = RoundedCornerShape(15.dp))
        ){
            Column(modifier = Modifier.fillMaxWidth()
                .background(color = Color.White)){
                Text(text = note.tag,
                    color = tagTextColor,
                    fontSize = 16.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(10.dp))
                Text(text = note.text,
                    color = tagTextColor,
                    fontSize = 14.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp))
            }
        }
    }

}
