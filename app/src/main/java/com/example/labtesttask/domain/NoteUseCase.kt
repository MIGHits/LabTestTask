package com.example.labtesttask.domain

import com.example.labtesttask.data.NoteRepository
import com.example.labtesttask.presentation.model.Note

class NoteUseCase(private val noteRepository: NoteRepository){
    fun saveNote(notes:List<Note>) {
        noteRepository.saveNote(notes)
    }
    fun loadNote():List<Note>{
       return noteRepository.loadNote()
    }
}