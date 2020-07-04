package com.noxpa.instagramtask.data_classes

import com.google.gson.annotations.SerializedName

data class Likes (
	@SerializedName("likedByList") val likedByList : List<String>?,
	@SerializedName("othersLiked") val othersLiked : String?
)