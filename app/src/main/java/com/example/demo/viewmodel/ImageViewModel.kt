package com.example.demo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.ImageBitmap

class ImageViewModel : ViewModel() {
    val imageBitmap = mutableStateOf<ImageBitmap?>(null)
}