package com.example.labtesttask.domain

import android.text.TextUtils.indexOf
import com.example.labtesttask.presentation.model.Note


class EditNoteUseCase {
    operator fun invoke(note: Note,list: List<Note>,tag:String,text:String):List<Note>{
        var element = list.find { element->element == note}
        val index = list.indexOf(element)

        list[index].tag = tag
        list[index].text = text
        return  list
    }
}