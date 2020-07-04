package com.noxpa.instagramtask.ui.posts_section

import android.app.Application

import androidx.lifecycle.AndroidViewModel

class PostsSectionViewModel(application : Application) : AndroidViewModel(application) {

    private val repository : Repository = PostsSectionRepository(application)

    fun getData() = repository.getData()
}