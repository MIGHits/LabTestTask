package com.example.labtesttask.domain

import com.example.labtesttask.presentation.model.Note

class AddNoteUseCase {
    operator fun invoke(note: Note, list:List<Note>):List<Note>{
        return list+note
    }
}