package com.example.searchrecyclerviewexample

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchrecyclerviewexample.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerViewAdapter
    lateinit var countryrv: RecyclerView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchIcon = binding.countrySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)


        val cancelIcon = binding.countrySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)

        val textView = binding.countrySearch.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
        // If you want to change the color of the cursor, change the 'colorAccent' in colors.xml


        countryrv = findViewById(R.id.country_rv)
        countryrv.layoutManager = LinearLayoutManager(countryrv.context)
        countryrv.setHasFixedSize(true)

        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        getListOfCountries()
    }

    private fun getListOfCountries() {
        val isoCountryCodes = Locale.getISOCountries()
        val countryListWithEmojis = ArrayList<String>()
        for (countryCode in isoCountryCodes) {
            val locale = Locale("", countryCode)
            val countryName = locale.displayCountry
            val flagOffset = 0x1F1E6
            val asciiOffset = 0x41
            val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
            val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
            val flag =
                (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
            countryListWithEmojis.add("$countryName $flag")
        }
        adapter = RecyclerViewAdapter(countryListWithEmojis)
        countryrv.adapter = adapter
    }


}
