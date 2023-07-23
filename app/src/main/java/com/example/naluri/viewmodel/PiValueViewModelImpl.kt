package com.example.naluri.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naluri.model.CircumferenceModel
import com.example.naluri.model.PiModel
import com.example.naluri.util.Constant.CIRCUMFERENCE
import com.example.naluri.util.Constant.RADIUS
import com.example.naluri.util.Constant.SUN_DIAMETER
import com.example.naluri.util.Constant.TITLE
import com.example.naluri.util.PiService
import com.example.naluri.util.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PiValueViewModelImpl : ViewModel(), PiValueViewModel, LifecycleObserver {
    private val retrofitService = RetrofitService().getClient()?.create(PiService::class.java)
    override val uiState: LiveData<PiValueViewModel.UiState>
        get() = _uiState
    private val _uiState: MutableLiveData<PiValueViewModel.UiState> = MutableLiveData()

    private var pi: Double = 0.0

    private var circumferenceList: ArrayList<CircumferenceModel>? = null


    override fun getPiValue() {
        //call api to retrieve pi value
        val call: Call<PiModel>? = retrofitService?.doGetListResources()
        call?.enqueue(object : Callback<PiModel?> {
            override fun onResponse(call: Call<PiModel?>?, response: Response<PiModel?>) {
                response.body()?.let { model ->
                    model.content?.let { content ->
                        //add decimal point for value as it is return as whole string
                        val prefix = content.substring(0, 1)
                        val suffix = content.substring(1, content.length - 1)
                        val combined = "$prefix.$suffix"

                        pi = combined.toDouble()

                        //notify view to display pi value
                        _uiState.value = PiValueViewModel.UiState.DisplayPiValue(combined)

                        //calculate circuference of sun with known diameter of sun
                        val circumference = calculateCircumference(SUN_DIAMETER)
                        _uiState.value = PiValueViewModel.UiState.DisplaySunCircumference(circumference)

                    }
                }
            }

            override fun onFailure(call: Call<PiModel?>, t: Throwable?) {
                call.cancel()
            }
        })
    }

    private fun calculateCircumference(radius: Double): String {
        //circumference of anything formula is 2PiR
        return (2 * pi * radius).toString()
    }

    override fun createNewCircumference(title: String, radius: String) {
        //store title and radius given by user
        val newCircumference = CircumferenceModel(title = title, radius = radius)
        //calculate circumference and store
        newCircumference.circumference = calculateCircumference(radius.toDouble())

        //check if list != null add to list
        circumferenceList?.let { list ->
            list.add(newCircumference)
        } ?: run {

            //if list == null add title row then add new model from user
            circumferenceList = arrayListOf()
            circumferenceList?.add(CircumferenceModel(title = TITLE, radius = RADIUS, circumference = CIRCUMFERENCE))
            circumferenceList?.add(newCircumference)
        }

        //notify view to render
        _uiState.value = PiValueViewModel.UiState.DisplayNewCircumference(circumferenceList)
    }
}