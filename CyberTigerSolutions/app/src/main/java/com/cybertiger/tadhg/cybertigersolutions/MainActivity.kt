package com.cybertiger.tadhg.cybertigersolutions

import android.content.Context
import android.content.Intent
import android.drm.DrmStore
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cybertiger.tadhg.cybertigersolutions.ExpActivity
import com.cybertiger.tadhg.cybertigersolutions.Experience
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.project_card.view.*

class MainActivity : AppCompatActivity() {

    /**
     *
     * TODO: Add more cards
     * TODO: Clean up ExpActivity Details
     * TODO: Categories - Work/Education/Projects
     */

    private var listOfExp = ArrayList<Experience>()
    private var adapter:ExpAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Load Experience Cards

        listOfExp.add(Experience(R.drawable.dingicon,
                "Ding.com",
                "Online Operations Agent",
                "Nov 2017 - Present",
                "\nGet mobile recharge for cellphones worldwide with Ding. "+
                "Ding allows you to top-up your own phone or someone else's, no matter where in the world you are.",
                R.drawable.dingbackground,
                1,
                "\nTop-Up Mobile Phones Worldwide. \n",
                "Customer Care, Fraud, Investigating Cases, Refunding Customers, Responding promptly\n",
                "It was very very very fun work\n"))

        listOfExp.add(Experience(R.drawable.shawicon, "Shaw Academy",
                "Coding Educator",
                "Feb 2016 - April 2017",
                "\nShaw Academy is the largest live online educator on the planet, "+
                        "with over 2 million graduates, practical education is becoming accessible to all."+
                        " Shaw Academy's  focus is on LIVE education because it is interactive, engaging, "+
                        "always evolving and most importantly, better for learning. Perfect, whether you're"+
                        " looking to advance your career, upskill yourself or take up a new hobby.",
                R.drawable.shawbackground, 1,
                "\nEducating Everyone, Everywhere. \n",
                "Teaching, Researching, Customer Care, Sales, Technical Support, Reveiwing Material\n",
                "It was very very very very stressful.\n"))

        listOfExp.add(Experience(R.drawable.coderdojoicon,
                "CoderDojo Dundalk",
                "Volunteer Mentor",
                "Apr 2015 - May 2016",
                "\nVolunteers all around the world help young people build a positive future through"+
                        " coding and community. Every child has the opportunity to learn to code and be "+
                        "creative in a safe and social environment.",
                R.drawable.coderdojo,
                3,
                "\nA true global movement and phenomenon\n",
                "Mentoring Children learning to use App Inventor, Scratch, Python & HTML5",
                "It was very much the best way to teach and feel good about learning Computer Science."))

        listOfExp.add(Experience(R.drawable.maplinicon,
                "Maplin Electronics",
                "Sales Team Leader",
                "Oct 2011 - Feb 2016",
                "\nWith more than 200 stores across the UK and more than 30,000 products to choose"+
                        " from in store and online, Maplin is the UK's number one specialist technology retailer.",
                R.drawable.maplinbackground,
                1,
                "\nThe Electronic Specialists.\n",
                "Store Management, Training Part-Timers, Banking Responsibilities, Stock-take",
                "Some of the best years of my working life"))

        listOfExp.add(Experience(R.drawable.dkiticon,
                "Dundalk IT",
                "Games Development",
                "Sep 2010 - June 2014",
                "\nThe Computer Games Industry is vibrant and has enormous future growth potential "+
                        "far beyond entertainment only. This programme aims to produce graduate software "+
                        "developers who have a particular focus on computer games applications development.",
                R.drawable.dkitbackground,
                2,
                "\nDundalk Institute of Technology\n",
                "Games Dev, Games Design, Technology, Web Dev",
                "A Roley-poley time of learning, having fun and experimenting with teams."))

        listOfExp.add(Experience(R.drawable.ggulivrricon,
                "GGULIVRR 2013",
                "Game Design Lead",
                "Apr 2013 - May 2013",
                "\nProject GGULIVRR is a multidisciplinary project. The main target is to combine "+
                        "common scientific works, lecturers and students originally from Portugal, Ireland,"+
                        " Belgium, Finland an Poland.",
                R.drawable.ggulivrrbackground,
                3,
                "\nA wonderful experience.\n",
                "As part of a multidisciplinary team, we had a lot of work to do",
                "It was fantastic!"))

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
            myView.cardLayout.setOnClickListener {

                //...to open an activity containing this items specific attributes
                val intent = Intent(context, ExpActivity::class.java)
                intent.putExtra("name", expItem.n)
                intent.putExtra("subname", expItem.sn)
                intent.putExtra("date", expItem.d)
                intent.putExtra("desc", expItem.ds)
                intent.putExtra("image", expItem.i!!)
                intent.putExtra("icon", expItem.ic!!)
                intent.putExtra("type", expItem.t)
                intent.putExtra("tagLine", expItem.tL)
                intent.putExtra("roleDesc", expItem.rd)
                intent.putExtra("roleExp", expItem.re)
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

