package com.example.waie

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.waie.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var binding : ActivityMainBinding
    lateinit var pref :SharedPreferences


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)


        checkNetworkAndHitApi(false)



        viewModel.data.observe(this, Observer {

            viewModel.savePlanetaryData(it.date, it.title, it.explanation)
            binding.tvTitle.setText(it.title)
            viewModel.setBitmap(it.url)
            binding.tvExplanation.setText(it.explanation)

        })

        viewModel.getBitmapData.observe(this, Observer {


            binding.ivImage.visibility = View.VISIBLE

        })
    }


    fun checkNetworkAndHitApi(storedData : Boolean) {

       if (viewModel.checkForInternet(this)) {
            viewModel.getData()
        } else{
            Toast.makeText(
                this,
                "We are not connected to the internet, Please connect internet and try again",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}