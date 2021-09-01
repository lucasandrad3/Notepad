package com.example.notepad

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.notepad.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val bt_salvar = binding.btSalvar



        val salvenotepad = SalveNotepad(applicationContext)

        val loadNotepad = salvenotepad.loadNotepad()

        if(loadNotepad !=""){
            binding.editText.editNotepad.setText(loadNotepad)
        }

        binding.editText.editNotepad.setSelection(binding.editText.editNotepad.getText().length);





        bt_salvar.setOnClickListener {
            val editNotepad = binding.editText.editNotepad.text.toString()
            if(editNotepad == ""){
                Toast.makeText(this ,"Digite Algo..." ,Toast.LENGTH_SHORT).show()
            }else{
                salvenotepad.salveNotepad(editNotepad)
                Toast.makeText(this ,"Salvo com sucesso" ,Toast.LENGTH_SHORT).show()

            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.action_settings-> {
            confirmDelete()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDelete(){


        val view = View.inflate(this@MainActivity,R.layout.confirm_del_dialog, null)
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val bt_confirm = view.findViewById<Button>(R.id.bt_confim)
        val bt_cancel = view.findViewById<Button>(R.id.bt_cancel)


        bt_confirm.setOnClickListener {
            val salvenotepad = SalveNotepad(applicationContext)
            binding.editText.editNotepad.setText("")
            salvenotepad.salveNotepad("")
            dialog.dismiss()
        }

        bt_cancel.setOnClickListener {
            dialog.dismiss()
        }


    }


}