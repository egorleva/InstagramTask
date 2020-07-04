package com.noxpa.instagramtask.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun replace(fragmentManager : FragmentManager?, container : Int, fragment : Fragment) {
    fragmentManager?.beginTransaction()?.replace(container, fragment)?.commit()
}

fun add(fragmentManager : FragmentManager?, container : Int, fragment : Fragment) {
    fragmentManager?.beginTransaction()?.add(container, fragment)?.commit()
}