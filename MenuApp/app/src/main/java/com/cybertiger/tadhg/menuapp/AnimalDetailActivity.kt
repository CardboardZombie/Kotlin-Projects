package com.cybertiger.tadhg.menuapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal_detail.*

class AnimalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)
        val bundle = intent.extras

        ivAnimalImage.setImageResource(bundle.getInt("icon"))
        tvName.text = bundle.getString("name")
        tvDetails.text = bundle.getString("desc")
    }
}
