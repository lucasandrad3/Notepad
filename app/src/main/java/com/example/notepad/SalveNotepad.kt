package com.example.notepad

import android.content.Context
import android.content.SharedPreferences

class SalveNotepad(private val context:Context) {

    private val FILE = "notepad.preferences"
    private val CHAVE = "text"
    private val preferences: SharedPreferences // recupera
    private val editor: SharedPreferences.Editor // salva

    fun salveNotepad(texto:String?){
        editor.putString(CHAVE,texto)
        editor.commit()
    }
    fun loadNotepad():String?{
        return preferences.getString(CHAVE,"")
    }

    init {
        preferences = context.getSharedPreferences(FILE,0)
        editor = preferences.edit()
    }
}