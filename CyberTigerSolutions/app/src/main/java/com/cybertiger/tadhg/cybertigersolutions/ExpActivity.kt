package com.cybertiger.tadhg.cybertigersolutions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_exp.*

class ExpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp)

        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val b: Bundle = intent.extras
        val name = b.getString("name")
        val subname = b.getString("subname")
        val dateText = b.getString("date")
        val desc = b.getString("desc")
        val image = b.getInt("image")
        val icon = b.getInt("icon")
        coverImage.setImageResource(image)
        logoIcon.setImageResource(icon)
        //heading.text = name
        subheading.text = subname
        date.text = dateText
        description.text = desc
        val tagLine = b.getString("tagLine")
        val roledesc = b.getString("roleDesc")
        val roleexp = b.getString("roleExp")

        role_desc.text = roledesc
        role_exp.text = roleexp
        tag_line.text = tagLine

        if(b.getInt("type") == 1)
            setTitle(R.string.work)
        else if(b.getInt("type") == 2)
            setTitle(R.string.study)
        else
            setTitle(R.string.project)
    }
}
