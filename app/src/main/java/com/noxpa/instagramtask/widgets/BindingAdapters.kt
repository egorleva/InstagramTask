package com.noxpa.instagramtask.widgets

import android.graphics.Color
import android.graphics.Typeface

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan

import android.view.View

import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.BindingAdapter

import androidx.viewpager.widget.ViewPager

import com.noxpa.instagramtask.adapters.ImageAdapter
import com.noxpa.instagramtask.R
import com.noxpa.instagramtask.adapters.PostsAdapter
import com.noxpa.instagramtask.data_classes.Post
import com.noxpa.instagramtask.utils.isValidHashTag
import com.noxpa.instagramtask.utils.isValidMentioned

import com.squareup.picasso.Picasso

@BindingAdapter("userPictureUrl")
fun setUserPicture(imageView : ImageView, userPictureUrl : String?) {
    userPictureUrl?.let {
        try {
            Picasso
                .get()
                .load(it)
                .error(R.drawable.user_picture_default)
                .into(imageView)
        } catch (e : IllegalArgumentException) {
            Picasso
                .get()
                .load("file:///android_asset/user_picture_default.png")
                .into(imageView)
        }
    }
}

@BindingAdapter("post", "postGeolocationListener")
fun setPostGeolocation(textView : TextView, post : Post, listener : PostsAdapter.OnPostClickListener) {
    if (post.postGeolocation == null) {
        textView.visibility = View.GONE
    } else {
        textView.text = post.postGeolocation
        textView.setOnClickListener {
            listener.onPostGeolocationClick(post)
        }
    }
}

@BindingAdapter("post", "likedListener")
fun setLikedSection(textView : TextView, post : Post, listener : PostsAdapter.OnPostClickListener) {
    if (post.likes == null) {
        textView.visibility = View.GONE
    } else {
        val ssb = SpannableStringBuilder()

        setLikedBy(textView, post, listener, ssb)
        setOthersLiked(textView, post, listener, ssb)

        textView.text = ssb
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

@BindingAdapter("post", "commentMentionedHashTagsListener")
fun setCommentMentionedHashTagsSection(textView : TextView, post : Post, listener : PostsAdapter.OnPostClickListener) {
    if (post.comment == null && post.mentionedList == null && post.hashTagsList == null) {
        textView.visibility = View.GONE
    } else {
        val ssb = SpannableStringBuilder()

        setUserName(textView, post, listener, ssb)
        setComment(post, ssb)
        setMentioned(post, listener, ssb)
        setHashTags(post, listener, ssb)

        textView.text = ssb
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

@BindingAdapter("postPhotosUrlsList")
fun setPostPhotos(viewPager : ViewPager, postPhotosUrlsList : List<String>) {
    viewPager.adapter = ImageAdapter(viewPager.context, postPhotosUrlsList)
}

fun setLikedBy(textView : TextView, post : Post, listener : PostsAdapter.OnPostClickListener, ssb : SpannableStringBuilder) {
    post.likes?.likedByList?.let {
        ssb.append("Liked by ")
        for (index in it.indices) {
            val likedBy = it[index]
            ssb.append(likedBy)
            ssb.setSpan(object : ClickableSpan() {
                override fun onClick(view : View) {
                    listener.onLikedByClick(post, index)
                }
                override fun updateDrawState(textPaint : TextPaint) {
                    super.updateDrawState(textPaint)
                    textPaint.isUnderlineText = false
                    textPaint.color = textView.currentTextColor
                    textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }
            }, ssb.length - likedBy.length, ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            if (index != it.size - 1) ssb.append(", ")
        }
    }
}

fun setOthersLiked(textView : TextView, post : Post, listener : PostsAdapter.OnPostClickListener, ssb : SpannableStringBuilder) {
    post.likes?.othersLiked?.let {
        ssb.append(" and ")
        val othersLiked = "$it others"
        ssb.append(othersLiked)
        ssb.setSpan(object : ClickableSpan() {
            override fun onClick(view : View) {
                listener.onOthersLikedClick(post)
            }
            override fun updateDrawState(textPaint : TextPaint) {
                super.updateDrawState(textPaint)
                textPaint.isUnderlineText = false
                textPaint.color = textView.currentTextColor
                textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
        }, ssb.length - othersLiked.length, ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

fun setUserName(textView : TextView, post : Post, listener : PostsAdapter.OnPostClickListener, ssb : SpannableStringBuilder) {
    val userName = post.userName
    ssb.append(userName)
    ssb.setSpan(object : ClickableSpan() {
        override fun onClick(view : View) {
            listener.onUserNameClick(post)
        }
        override fun updateDrawState(textPaint : TextPaint) {
            super.updateDrawState(textPaint)
            textPaint.isUnderlineText = false
            textPaint.color = textView.currentTextColor
            textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
    }, 0, userName.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    ssb.append(" ")
}

fun setComment(post : Post, ssb : SpannableStringBuilder) {
    post.comment?.let {
        ssb.append(it)
        ssb.append(" ")
    }
}

fun setMentioned(post : Post, listener : PostsAdapter.OnPostClickListener, ssb : SpannableStringBuilder) {
    post.mentionedList?.let {
        for (index in it.indices) {
            val mentioned = it[index]
            if (isValidMentioned(mentioned)) ssb.append(mentioned)
            ssb.setSpan(object : ClickableSpan() {
                override fun onClick(view : View) {
                    listener.onMentionedClick(post, index)
                }
                override fun updateDrawState(textPaint : TextPaint) {
                    super.updateDrawState(textPaint)
                    textPaint.isUnderlineText = false
                    textPaint.color = Color.parseColor("#0000bf")
                }
            }, ssb.length - mentioned.length, ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ssb.append(" ")
        }
    }
}

fun setHashTags(post : Post, listener : PostsAdapter.OnPostClickListener, ssb : SpannableStringBuilder) {
    post.hashTagsList?.let {
        for (index in it.indices) {
            val hashTag = it[index]
            if (isValidHashTag(hashTag)) ssb.append(hashTag)
            ssb.setSpan(object : ClickableSpan() {
                override fun onClick(view : View) {
                    listener.onHashTagClick(post, index)
                }
                override fun updateDrawState(textPaint : TextPaint) {
                    super.updateDrawState(textPaint)
                    textPaint.isUnderlineText = false
                    textPaint.color = Color.parseColor("#0000bf")
                }
            }, ssb.length - hashTag.length, ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            if (index != it.size - 1) ssb.append(" ")
        }
    }
}