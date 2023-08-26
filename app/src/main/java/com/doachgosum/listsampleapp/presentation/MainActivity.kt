package com.doachgosum.listsampleapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.doachgosum.listsampleapp.Logger
import com.doachgosum.listsampleapp.R
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var photoRepository: PhotoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            photoRepository.getPhotos().also {
                Logger.d(it.toString())
            }
        }
    }
}