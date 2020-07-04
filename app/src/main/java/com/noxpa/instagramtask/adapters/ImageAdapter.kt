package com.noxpa.instagramtask.adapters

import android.content.Context

import android.view.View
import android.view.ViewGroup

import android.widget.ImageView

import androidx.viewpager.widget.PagerAdapter

import com.noxpa.instagramtask.R

import com.squareup.picasso.Picasso

class ImageAdapter(private val context : Context, private val postPhotosUrlsList : List<String>) : PagerAdapter() {

    override fun isViewFromObject(view : View, `object` : Any) : Boolean {
        return view == `object`
    }

    override fun getCount() : Int {
        return postPhotosUrlsList.size
    }

    override fun instantiateItem(container : ViewGroup, position : Int) : Any {
        val imageView = ImageView(context)

        try {
            Picasso
                .get()
                .load(postPhotosUrlsList[position])
                .error(R.drawable.post_photo_default)
                .fit()
                .centerCrop()
                .into(imageView)
        } catch (e : IllegalArgumentException) {
            Picasso
                .get()
                .load("file:///android_asset/post_photo_default.png")
                .fit()
                .centerCrop()
                .into(imageView)
        }

        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container : ViewGroup, position : Int, `object` : Any) {
        container.removeView(`object` as ImageView)
    }
}