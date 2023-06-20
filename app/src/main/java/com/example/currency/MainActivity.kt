package com.example.currency

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import com.example.currency.ApiConfig.api_key
import com.example.currency.ApiConfig.url
import com.example.currency.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.time.toDuration


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
             downloadTask()
             Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
        }

    }

    private fun setUpURL(){

        val from = binding.spFromCurrency.selectedItem.toString()
        val to = binding.spToCurrency.selectedItem.toString()
        val amount = binding.etFrom.text.toString()

        url = "https://api.getgeoapi.com/v2/currency/convert\n" +
                "?api_key=${ApiConfig.api_key}\n" +
                "&from=$from\n" +
                "&to=$to\n" +
                "&amount=$amount"
    }

    private fun downloadTask() {
        setUpURL()
        val queue = Volley.newRequestQueue(this)
        val requeue = StringRequest(url, { response ->
            val data = response.toString()
            val to = binding.spToCurrency.selectedItem.toString()
            val jobject = JSONObject(data).getJSONObject("rates").getJSONObject(to).get("rate_for_amount")
            Log.d("volley","That $data")
            Log.d("volley", "This ${jobject.toString()}")
            val fromCurrency = binding.etFrom.text.toString()
            val from = binding.spFromCurrency.selectedItem
               binding.tvResult.text = ("$fromCurrency $from =  ${jobject.toString()} $to").toString()
        },
            { volleyError: VolleyError ->
                Toast.makeText(
                    this@MainActivity,
                    volleyError.message,
                    Toast.LENGTH_SHORT
                ).show()
            })

        queue.add(requeue)

    }
}

