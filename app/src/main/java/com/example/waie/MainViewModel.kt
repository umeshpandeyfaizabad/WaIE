package com.example.waie

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.waie.model.PlanetaryData
import com.example.waie.network.ApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject



class MainViewModel(application: Application) : AndroidViewModel(application) {


 private val context = getApplication<Application>().applicationContext
 var data = MutableLiveData<PlanetaryData>()

 fun getData() {
  viewModelScope.launch(Dispatchers.IO) {
   val response = ApiCall.request()
   withContext(Dispatchers.Main) {

    data.value = getResponse(JSONObject(response))

   }
  }
 }

 private fun getResponse(jsonObject: JSONObject) : PlanetaryData{
  val pojoObject = PlanetaryData()
  try {
   pojoObject.date =  jsonObject.getString("date")
   pojoObject.url = jsonObject.getString("url")
   pojoObject.title =    jsonObject.getString("title")
   pojoObject.explanation = jsonObject.getString("explanation")
  } catch (e: JSONException) {
   e.printStackTrace()
  }
  return pojoObject
 }


}