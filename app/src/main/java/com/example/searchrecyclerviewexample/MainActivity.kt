package com.example.searchrecyclerviewexample

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerViewAdapter
    private lateinit var countryRv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countrySearch = findViewById<SearchView>(R.id.country_search)
        val searchIcon =
            countrySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)

        val cancelIcon =
            countrySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)

        val textView = countrySearch.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
        // If you want to change the color of the cursor, change the 'colorAccent' in colors.xml


        countryRv = findViewById(R.id.country_rv)
        countryRv.layoutManager = LinearLayoutManager(countryRv.context)
        countryRv.setHasFixedSize(true)

        countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        countryRv.adapter = adapter
    }


}
