package com.noxpa.instagramtask.ui.account_section

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.noxpa.instagramtask.R

class AccountSectionFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_account_section, container, false)
    }
}