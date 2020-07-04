package com.noxpa.instagramtask.ui.posts_section

import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.google.gson.Gson

import com.noxpa.instagramtask.data_classes.Feed
import com.noxpa.instagramtask.data_classes.Post

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class PostsSectionRepository(application : Application) : Repository {

    private val data : MutableLiveData<List<Post>?> = MutableLiveData()

    init {
        val feedJson = StringBuilder()

        try {
            val assetManager = application.assets
            val inputStreamReader = InputStreamReader(assetManager.open("feed.txt"))
            val bufferReader = BufferedReader(inputStreamReader)
            feedJson.append(bufferReader.readText())
            bufferReader.close()
        } catch (e : FileNotFoundException) {
            e.printStackTrace()
        } catch (e : IOException) {
            e.printStackTrace()
        }

        val feed = Gson().fromJson(feedJson.toString(), Feed::class.java)
        data.value = feed.posts
    }

    override fun getData() : LiveData<List<Post>?> = data
}