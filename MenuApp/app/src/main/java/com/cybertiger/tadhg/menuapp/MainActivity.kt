package com.cybertiger.tadhg.menuapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_animal_detail.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.icon_card.view.*

class MainActivity : AppCompatActivity() {

    var adapter:AnimalAdapter?=null
    var listOfIcons =  ArrayList<Animal>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load icons
        listOfIcons.add(Animal("Toad","Toads are Amphibians!", R.drawable.amphibians_icon))
        listOfIcons.add(Animal("Parrot","Parrots are a bird commonly known to hang out with Pirates! Raaargh!",R.drawable.birds_icon))
        listOfIcons.add(Animal("Butterfly", "Butterflies are a form of invertebrates. Also known as Creepy-crawlies!", R.drawable.invertebrates_icon))
        listOfIcons.add(Animal("Giraffe", "Giraffes are a form of Mammal.", R.drawable.mammal_icon))
        listOfIcons.add(Animal("Turtle", "Even turtles count as reptiles!", R.drawable.reptiles_icon))

        adapter= AnimalAdapter(this, listOfIcons)
        gvListIcon.adapter=adapter
    }

    class AnimalAdapter:BaseAdapter{
        var iconList = ArrayList<Animal>()
        var context: Context?=null
        constructor(context:Context, iconList:ArrayList<Animal>):super(){
            this.context = context
            this.iconList = iconList
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = iconList[p0]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var iconView = inflater.inflate(R.layout.icon_card, null)
            iconView.ivIcon.setImageResource(animal.icon!!)
            iconView.ivIcon.setOnClickListener{
                val intent  = Intent(context, AnimalDetailActivity::class.java)
                intent.putExtra("name", animal.name!!)
                intent.putExtra("desc", animal.desc!!)
                intent.putExtra("icon", animal.icon!!)
                context!!.startActivity(intent)
            }
            iconView.textView.text = animal.name!!
            return iconView
        }

        override fun getItem(p0: Int): Any {
            return iconList
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return iconList.size
        }
    }
}
