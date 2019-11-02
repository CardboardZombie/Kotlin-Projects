package com.example.tdm.noteapp

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.lang.Exception

class AddNotes : AppCompatActivity() {
    val dbTable="Notes"
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        try{
            var bundle:Bundle=intent.extras
            id=bundle.getInt("ID", 0)
            editTitle.setText(bundle.getString("Title"))
            editDesc.setText(bundle.getString("Description"))
        }catch(ex:Exception){

        }
    }
    fun btn_add(view:View){
        var dbManager = DbManager(this)
        var values= ContentValues()
        values.put("Title", editTitle.text.toString())
        values.put("Description", editDesc.text.toString())
        if(id==0) {
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, "Note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Cannot Add Note", Toast.LENGTH_LONG).show()
            }
        }else{
            var selectionArgs=arrayOf(id.toString())
            val ID = dbManager.Update(values, "ID=?", selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, "Note is updated", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Cannot Add Note", Toast.LENGTH_LONG).show()
            }
        }
    }

}
