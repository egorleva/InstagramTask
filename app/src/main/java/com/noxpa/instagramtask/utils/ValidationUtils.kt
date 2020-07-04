package com.noxpa.instagramtask.utils

fun isValidMentioned(mentioned : String) : Boolean {
    if (!mentioned.startsWith('@') || mentioned.contains(' ')) {
        return false
    }
    return true
}

fun isValidHashTag(hashTag : String) : Boolean {
    if (!hashTag.startsWith('#') || hashTag.contains(' ')) {
        return false
    }
    return true
}