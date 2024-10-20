package com.example.labtesttask.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.labtesttask.domain.AddNoteUseCase
import com.example.labtesttask.domain.DeleteNoteUseCase
import com.example.labtesttask.domain.EditNoteUseCase
import com.example.labtesttask.domain.NoteUseCase
import com.example.labtesttask.presentation.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val noteUseCase: NoteUseCase
):ViewModel() {
    val noteList: StateFlow<List<Note>> get() =  _noteList
    private var _noteList: MutableStateFlow<List<Note>> =MutableStateFlow(emptyList())

    fun addNewNote(note:Note){
        _noteList.value = addNoteUseCase(note,_noteList.value)
        noteUseCase.saveNote(_noteList.value)
    }

    fun removeNote(note:Note){
        _noteList.value = deleteNoteUseCase(note,_noteList.value)
        noteUseCase.saveNote(_noteList.value)
    }

    fun editNote(note:Note,tag:String,text:String){
        _noteList.value = editNoteUseCase(note,_noteList.value,tag,text)
        noteUseCase.saveNote(_noteList.value)
    }

    fun setNoteList(){
        _noteList.value = noteUseCase.loadNote()
    }

}