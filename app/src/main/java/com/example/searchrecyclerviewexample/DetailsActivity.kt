package com.example.searchrecyclerviewexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val detailCountryText = findViewById<TextView>(R.id.detail_country_text_view)
        detailCountryText.text = intent.extras!!.getString("passselectedcountry")!!

    }
}
