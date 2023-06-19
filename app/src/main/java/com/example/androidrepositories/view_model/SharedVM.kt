package com.example.androidrepositories.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedVM @Inject constructor() : ViewModel() {
    var sharedData: String = ""
    var currentViewItemPosition = 0
}