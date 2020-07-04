package com.noxpa.instagramtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView

import com.noxpa.instagramtask.R
import com.noxpa.instagramtask.data_classes.Post
import com.noxpa.instagramtask.databinding.RecyclerViewPostItemBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var posts : List<Post>? = null
    lateinit var onPostClickListener : OnPostClickListener
    private lateinit var binding : RecyclerViewPostItemBinding

    fun setPosts(posts : List<Post>?) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : PostsViewHolder {
        binding  = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_post_item,
            parent,
            false
        )
        binding.listener = onPostClickListener
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder : PostsViewHolder, position : Int) {
        posts?.let {
            holder.bind(it[position])
            if (it[position].postPhotosUrlsList.size > 1) {
                binding.stepperLayout.setupWithViewPager(binding.viewPagerPostPhotos)
            }
        }
    }

    override fun getItemCount() = posts?.size ?: 0

    class PostsViewHolder(private val binding : RecyclerViewPostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post : Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

    interface OnPostClickListener {
        fun onUserPictureClick(post : Post)
        fun onUserNameClick(post : Post)
        fun onPostGeolocationClick(post : Post)
        fun onOptionsClick(post : Post)
        fun onLikeClick(post : Post)
        fun onCommentsClick(post : Post)
        fun onShareClick(post : Post)
        fun onSaveClick(post : Post)
        fun onLikedByClick(post : Post, likedByIndex : Int)
        fun onOthersLikedClick(post : Post)
        fun onMentionedClick(post : Post, mentionedIndex : Int)
        fun onHashTagClick(post : Post, hashTagIndex : Int)
    }
}