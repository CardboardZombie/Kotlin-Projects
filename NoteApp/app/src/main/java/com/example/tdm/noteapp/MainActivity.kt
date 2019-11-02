package com.example.tdm.noteapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity: AppCompatActivity() {

    var listNotes = ArrayList<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()
        //Add dummy data
        //listNotes.add(Note(1, "Meet Professor", "From breaking news and entertainment to sports and politics, get the full story with all the live commentary."))
        //listNotes.add(Note(2, "Meet Doctor", "From breaking news and entertainment to sports and politics, get the full story with all the live commentary."))
        //listNotes.add(Note(3, "Meet Friend", "From breaking news and entertainment to sports and politics, get the full story with all the live commentary."))

        //Load from Database
        LoadQuery("%")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
        LoadQuery("%")
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    fun LoadQuery(title:String){
        var dbManager = DbManager(this)
        val projections = arrayOf("ID", "Title", "Description")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections, "Title LIKE ?", selectionArgs, "Title")
        listNotes.clear()
        if(cursor.moveToFirst()){
            do{
                val ID= cursor.getInt(cursor.getColumnIndex("ID"))
                val Title= cursor.getString(cursor.getColumnIndex("Title"))
                val Description= cursor.getString(cursor.getColumnIndex("Description"))
                        listNotes.add(Note(ID, Title, Description))
            }while(cursor.moveToNext())
        }
        var myNotesAdapter = MyNotesAdapter(this, listNotes)
        lvNotes.adapter=myNotesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search_view:SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val search_manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(search_manager.getSearchableInfo(componentName))
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                LoadQuery("%"+query+"%")
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.addNote->{
                    //Go to Add Page
                    var intent = Intent(this, AddNotes::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MyNotesAdapter:BaseAdapter{
        var listNoteAdapter=ArrayList<Note>()
        var context:Context?=null
        constructor(context:Context, listNoteAdapter:ArrayList<Note>):super(){
            this.listNoteAdapter=listNoteAdapter
            this.context=context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var myView=layoutInflater.inflate(R.layout.ticket,null)
            var myNote=listNoteAdapter[position]
            myView.title.text=myNote.noteName
            myView.desc.text=myNote.noteDes
            myView.delete.setOnClickListener(View.OnClickListener {
                var dbManager = DbManager(this.context!!)
                val selectionArgs = arrayOf(myNote.noteID.toString())
                dbManager.Delete("ID=?",selectionArgs )
                LoadQuery("%")
            })
            myView.edit.setOnClickListener(View.OnClickListener {
                GoToUpdate(myNote)
            })
            return myView

        }

        override fun getItem(position: Int): Any {
            return listNoteAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNoteAdapter.size
        }
    }
    fun GoToUpdate(note: Note){
        var intent = Intent(this, AddNotes::class.java)
        intent.putExtra("ID", note.noteID)
        intent.putExtra("Title", note.noteName)
        intent.putExtra("Description", note.noteDes)
        startActivity(intent)
    }

}
