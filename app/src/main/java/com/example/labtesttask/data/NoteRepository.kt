package com.example.labtesttask.data

import android.content.Context
import android.content.SharedPreferences
import com.example.labtesttask.MyApplication
import com.example.labtesttask.data.PrefsConstants.NOTES_KEY
import com.example.labtesttask.data.PrefsConstants.PREFS_NAME
import com.example.labtesttask.presentation.model.Note
import com.example.labtesttask.presentation.ui.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NoteRepository {
    private val context = MyApplication.instance
    private val pref: SharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()
    private val gson = Gson()

    fun saveNote(notes: List<Note>) {
        val jsonNotes = gson.toJson(notes)
        editor.putString(NOTES_KEY,jsonNotes)
        editor.apply()
    }

    fun loadNote():List<Note> {
        val jsonNotes = pref.getString(NOTES_KEY,null)
        return if (jsonNotes!=null){
            val type = object : TypeToken<List<Note>>() {}.type
             gson.fromJson(jsonNotes,type)
        }else{
            emptyList()
        }
    }
}
