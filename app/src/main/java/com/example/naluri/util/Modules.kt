package com.example.naluri.util

import com.example.naluri.viewmodel.PiValueViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//koin injector
val viewModelModule = module {
    viewModel { PiValueViewModelImpl() }
}

