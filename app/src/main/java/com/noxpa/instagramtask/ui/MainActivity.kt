package com.noxpa.instagramtask.ui

import android.graphics.Color

import android.os.Build
import android.os.Bundle

import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.noxpa.instagramtask.R
import com.noxpa.instagramtask.databinding.ActivityMainBinding

import com.noxpa.instagramtask.ui.account_section.AccountSectionFragment
import com.noxpa.instagramtask.ui.add_section.AddSectionFragment
import com.noxpa.instagramtask.ui.posts_section.PostsSectionFragment
import com.noxpa.instagramtask.ui.news_section.NewsSectionFragment
import com.noxpa.instagramtask.ui.search_section.SearchSectionFragment

import com.noxpa.instagramtask.utils.add
import com.noxpa.instagramtask.utils.replace

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.WHITE
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (savedInstanceState == null) {
            add(supportFragmentManager, R.id.child_fragment, PostsSectionFragment())
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        binding.bottomNavigationView.itemIconTintList = null
    }

    override fun onNavigationItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            R.id.feed -> replace(supportFragmentManager, R.id.child_fragment, PostsSectionFragment())
            R.id.search -> replace(supportFragmentManager, R.id.child_fragment, SearchSectionFragment())
            R.id.add -> replace(supportFragmentManager, R.id.child_fragment, AddSectionFragment())
            R.id.news -> replace(supportFragmentManager, R.id.child_fragment, NewsSectionFragment())
            R.id.account -> replace(supportFragmentManager, R.id.child_fragment, AccountSectionFragment())
        }
        return true
    }
}
