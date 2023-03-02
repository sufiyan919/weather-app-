package com.example.weatherapp12

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var cityNameTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var cityEditText: EditText
    private lateinit var getWeatherButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityNameTextView = findViewById(R.id.cityNameTextView)
        temperatureTextView = findViewById(R.id.temperatureTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        cityEditText = findViewById(R.id.cityEditText)
        getWeatherButton = findViewById(R.id.getWeatherButton)

        getWeatherButton.setOnClickListener {
            val city = cityEditText.text.toString()
            getWeatherData(city)
        }
    }

    private fun getWeatherData(city: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        call.enqueue(object : Callback<WeatherResponse> {
            fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    if (weather != null) {
                        val temperature = weather.main.temp.toInt()
                        val description = weather.weather[0].description
                        val cityName = weather.name

                        cityNameTextView.text = cityName
                        temperatureTextView.text = "$temperature°C"
                        descriptionTextView.text = description
                    }
                }
            }

            fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle API call failure
            }
        })
    }
}
