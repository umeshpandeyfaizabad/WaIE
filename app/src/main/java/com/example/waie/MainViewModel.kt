package com.example.waie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.waie.model.PlanetaryData

class MainViewModel(application: Application) : AndroidViewModel(application) {


 private val context = getApplication<Application>().applicationContext
 var data = MutableLiveData<PlanetaryData>()
}