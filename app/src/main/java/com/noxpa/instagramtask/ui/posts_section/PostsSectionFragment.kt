package com.noxpa.instagramtask.ui.posts_section

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast

import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.noxpa.instagramtask.R

import com.noxpa.instagramtask.adapters.PostsAdapter
import com.noxpa.instagramtask.data_classes.Post
import com.noxpa.instagramtask.databinding.FragmentPostsSectionBinding

class PostsSectionFragment : Fragment(), View.OnClickListener, PostsAdapter.OnPostClickListener {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val binding : FragmentPostsSectionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_posts_section,
            container,
            false
        )

        binding.imageViewStories.setOnClickListener(this)
        binding.imageViewDirect.setOnClickListener(this)

        val postsAdapter = PostsAdapter()
        postsAdapter.onPostClickListener = this

        binding.recyclerViewFeed.adapter = postsAdapter
        binding.recyclerViewFeed.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFeed.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val postsSectionViewModel = ViewModelProvider(this).get(PostsSectionViewModel::class.java)
        postsSectionViewModel.getData().observe(viewLifecycleOwner, Observer {
            postsAdapter.setPosts(it)
        })

        return binding.root
    }

    override fun onClick(view : View?) {
        when (view?.id) {
            R.id.image_view_stories -> showToast("Сториз")
            R.id.image_view_direct -> showToast("Директ")
        }
    }

    private fun showToast(text : String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onUserPictureClick(post : Post) {
        showToast("Фотография юзера ${post.userName}")
    }

    override fun onUserNameClick(post : Post) {
        showToast("Имя юзера ${post.userName}")
    }

    override fun onPostGeolocationClick(post : Post) {
        showToast("Геолокация поста юзера ${post.userName}")
    }

    override fun onOptionsClick(post : Post) {
        showToast("Кнопка опции в посте юзера ${post.userName}")
    }

    override fun onLikeClick(post : Post) {
        showToast("Кнопка лайк в посте юзера ${post.userName}")
    }

    override fun onCommentsClick(post : Post) {
        showToast("Кнопка комментарии в посте юзера ${post.userName}")
    }

    override fun onShareClick(post : Post) {
        showToast("Кнопка поделиться в посте юзера ${post.userName}")
    }

    override fun onSaveClick(post : Post) {
        showToast("Кнопка сохранить в посте юзера ${post.userName}")
    }

    override fun onLikedByClick(post : Post, likedByIndex : Int) {
        showToast("Юзер ${post.likes?.likedByList!![likedByIndex]} лайкнувший пост юзера ${post.userName}")
    }

    override fun onOthersLikedClick(post : Post) {
        showToast("Список всех кому понравился пост юзера ${post.userName}")
    }

    override fun onMentionedClick(post : Post, mentionedIndex : Int) {
        showToast("Юзер ${post.mentionedList!![mentionedIndex]} упомянутый в посте юзера ${post.userName}")
    }

    override fun onHashTagClick(post : Post, hashTagIndex : Int) {
        showToast("Хештег ${post.hashTagsList!![hashTagIndex]} указанный в посте юзера ${post.userName}")
    }
}