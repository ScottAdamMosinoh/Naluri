package com.example.naluri

import android.app.Application
import com.example.naluri.util.viewModelModule
import org.koin.core.context.startKoin

class NaluriApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //init koin
        startKoin{
            modules(viewModelModule)
        }
    }
}