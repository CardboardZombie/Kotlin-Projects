package com.cybertiger.tadhg.cybertigersolutions

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cybertiger.tadhg.cybertigersolutions.ExpActivity
import com.cybertiger.tadhg.cybertigersolutions.Experience
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.work_card.view.*

class MainActivity : AppCompatActivity() {

    private var listOfExp = ArrayList<Experience>()
    private var adapter:ExpAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Load Animals
        /**
         * TODO: Fix Image Sizes - Add Descriptions
         */

        listOfExp.add(Experience(R.drawable.shaw_academy_icon, "Shaw Academy", "Coding Educator", "Feb 2016 - April 2017", "Words that I could and would say about this place...",R.drawable.shaw_inside, 1))
        listOfExp.add(Experience(R.drawable.coderdojo_icon, "CoderDojo Dundalk","Volunteer Mentor","Apr 2015 - May 2016","A wonderful experience.", R.drawable.coderdojo, 3))
        listOfExp.add(Experience(R.drawable.maplin_icon, "Maplin Electronics","Sales Team Leader","Oct 2011 - Feb 2016","A wonderful experience.", R.drawable.maplin_outside, 1))
        listOfExp.add(Experience(R.drawable.dkit_icon, "Dundalk Institute of Technology","Computing in Games Development","Sep 2010 - June 2014","A wonderful experience.", R.drawable.games_dev, 2))
        listOfExp.add(Experience(R.drawable.ggulivrr, "GGULIVRR - Portugal","Team Lead","Apr 2013 - May 2013","A wonderful experience.", R.drawable.irish_team, 3))
        //listOfExp.add(Experience("Birds", "There are about 10,000 species ranging from very small to very large.", R.drawable.birds_icon, false))
        //listOfExp.add(Experience("Invertebrates", "Invertebrates are the most abundant creatures on the planet.", R.drawable.invertebrates_icon, true))
        //listOfExp.add(Experience("Reptiles","Reptiles are independent from day one. There are more than 6,500 reptile species", R.drawable.reptiles_icon , false))

        adapter = ExpAdapter(this, listOfExp)
        //Set the List Item to the correct adapter in layout
        listItem.adapter = adapter
    }
    //Functions could go here
    //Add,Delete,Duplicate

    //Inner Class for the Adapter - Initializing our own adapter
    inner class ExpAdapter: BaseAdapter {
        //Set the list to type needed
        private var expList=ArrayList<Experience>()
        //prepare the context needed later
        private var context: Context?=null
        //Override the constructor for an adapter of your content
        constructor(context:Context, listOfExp: ArrayList<Experience>):super(){
            this.expList=listOfExp
            this.context = context
        }
        //Get view runs the current List for each item in the list

        //(2)Then this one is called to implement the view for each item in the list
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            //The Current List Item being initialized as a view in the Adapter
            val expItem = expList[p0]


            //Set the view to the layout of the inflator - the_card_view.xml
            //If a special attribute is true....

            //...to open an activity containing this items specific attributes
            //Start the Activity
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView:View?=null
            //cardIcon, cardName, cardSubName

            when {
                expItem.t == 1 -> myView = inflater.inflate(R.layout.work_card,null)
            //Set the attributes of the items in the Card_View to the current item in the list
                expItem.t == 2 -> myView = inflater.inflate(R.layout.study_card,null)
            //Set the attributes of the items in the Card_View to the current item in the list
                expItem.t == 3 -> myView = inflater.inflate(R.layout.project_card,null)
            //Set the attributes of the items in the Card_View to the current item in the list
            }

            myView!!.cardIcon.setImageResource(expItem.ic!!)
            myView.cardName.text = expItem.n
            myView.cardSubName.text = expItem.sn

            //Set the onclicklistener...
            myView.cardIcon.setOnClickListener {

                //...to open an activity containing this items specific attributes
                val intent = Intent(context, ExpActivity::class.java)
                intent.putExtra("name", expItem.n)
                intent.putExtra("subname", expItem.sn)
                intent.putExtra("date", expItem.d)
                intent.putExtra("desc", expItem.ds)
                intent.putExtra("image", expItem.i!!)
                intent.putExtra("icon", expItem.ic!!)
                intent.putExtra("type", expItem.t)
                //Start the Activity
                context!!.startActivity(intent)

            }

            return myView
        }
        //Needed for implementation
        override fun getItem(p0: Int): Any {
            return expList[p0]
        }
        //Needed for implementation
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
        //Get Count asks how long the list is before creating the View

        //(1)This one is called to see how many Items are in the List
        override fun getCount(): Int {
            return expList.size
        }

    }
}

