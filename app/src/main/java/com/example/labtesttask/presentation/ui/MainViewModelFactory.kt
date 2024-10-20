package com.example.labtesttask.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labtesttask.data.NoteRepository
import com.example.labtesttask.domain.AddNoteUseCase
import com.example.labtesttask.domain.DeleteNoteUseCase
import com.example.labtesttask.domain.EditNoteUseCase
import com.example.labtesttask.domain.NoteUseCase

class MainViewModelFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            DeleteNoteUseCase(),
            AddNoteUseCase(),
            EditNoteUseCase(),
            NoteUseCase(NoteRepository())
            ) as T
    }
}