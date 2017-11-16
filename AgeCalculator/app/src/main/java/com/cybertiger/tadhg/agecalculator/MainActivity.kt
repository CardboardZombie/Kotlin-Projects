package com.cybertiger.tadhg.agecalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Fired when button with ageBtn ID is clicked
        //ageBtn.setOnClickListener {...}
    }
    fun findAgeEvent(view: View){
        //val year:Int=Calendar.getInstance().get(Calendar.YEAR)
        /* val yearOfBirth:Int = ageInput.text.toString().toInt()
         if((yearOfBirth < 1900) || (yearOfBirth > year)){
             ageDisplay.text="Invalid input"
             return
         }
         val myAge =year - yearOfBirth
         ageDisplay.text = "You are $myAge years old"
         */
        val currentYear:Calendar= Calendar.getInstance()
        var age:Int?
        val dobStr:String = ageInput.text.toString()
        val dobDate:SimpleDateFormat= SimpleDateFormat("dd/MM/yyyy")
        dobDate.parse(dobStr)
        val dob:Calendar = dobDate.calendar
        age = currentYear.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if(dob.get(Calendar.MONTH) > currentYear.get(Calendar.MONTH))
            age--
        else if(dob.get(Calendar.MONTH) == currentYear.get(Calendar.MONTH)){
            if(dob.get(Calendar.DATE) > currentYear.get(Calendar.DATE)){
                age--
            }
            if(dob.get(Calendar.DATE) == currentYear.get(Calendar.DATE)){
                ageDisplay.text = "You are $age today. Happy Birthday!"
                return
            }

        }
        ageDisplay.text = "You are $age years old"



    }
}
