package com.example.searchrecyclerviewexample

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class RecyclerViewAdapter(private var countryList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.CountryViewHolder>(), Filterable {

    var countryFilterList = ArrayList<String>()

    private lateinit var mContext: Context

    inner class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view)

    init {
        countryFilterList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        val sch = CountryViewHolder(v)
        mContext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val selectCountryContainer =
            holder.itemView.findViewById<RelativeLayout>(R.id.select_country_container)
        selectCountryContainer.setBackgroundColor(Color.TRANSPARENT)

        val selectCountryTextView =
            holder.itemView.findViewById<TextView>(R.id.select_country_text_view)
        selectCountryTextView.setTextColor(Color.WHITE)
        selectCountryTextView.text = countryFilterList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("passselectedcountry", countryFilterList[position])
            mContext.startActivity(intent)
            Log.d("Selected:", countryFilterList[position])
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = countryList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in countryList) {
                        if (row.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}