package com.noxpa.instagramtask.ui.posts_section

import androidx.lifecycle.LiveData

import com.noxpa.instagramtask.data_classes.Post

interface Repository {
    fun getData() : LiveData<List<Post>?>
}