package com.example.naluri.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.naluri.R
import com.example.naluri.databinding.ActivityPiValueBinding
import com.example.naluri.viewmodel.PiValueViewModel
import com.example.naluri.viewmodel.PiValueViewModelImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class PiValueActivity : AppCompatActivity() {
    private val viewModel : PiValueViewModelImpl by viewModel()
    private lateinit var binding: ActivityPiValueBinding
    private var circumferenceAdapter : CircumferenceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        //bind view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pi_value)

        //register listener to listen from viewmodel once process is done
        viewModel.uiState.observe(this) { event ->
            when(event) {
                is PiValueViewModel.UiState.DisplayPiValue -> {
                    binding.tvPiValue.text = event.piValue
                }
                is PiValueViewModel.UiState.DisplaySunCircumference -> {
                    binding.tvSunCircumference.text = event.circumference
                }
                is PiValueViewModel.UiState.DisplayNewCircumference -> {
                    circumferenceAdapter?.let { adapter ->
                        adapter.refresh(event.circumferenceList)
                    } ?: run {
                        circumferenceAdapter = CircumferenceAdapter(this, circumferenceLists = event.circumferenceList)
                        binding.rvCircumference.adapter = circumferenceAdapter
                    }
                }
            }
        }

        binding.btnCalculate.setOnClickListener {
            //send data to vm to calculate circumference then clear edit text
            viewModel.createNewCircumference(binding.etName.text.toString(), binding.etRadius.text.toString())
            binding.etName.text.clear()
            binding.etRadius.text.clear()
        }

        viewModel.getPiValue()
    }
}