package com.example.searchrecyclerviewexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.searchrecyclerviewexample.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.detailCountryText.text = intent.extras!!.getString("passselectedcountry")!!

    }
}
