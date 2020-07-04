package com.noxpa.instagramtask.data_classes

import com.google.gson.annotations.SerializedName

data class Post (
	@SerializedName("userPictureUrl") val userPictureUrl : String?,
	@SerializedName("userName") val userName : String,
	@SerializedName("postGeolocation") val postGeolocation : String?,
	@SerializedName("postPhotosUrlsList") val postPhotosUrlsList : List<String>,
	@SerializedName("isLiked") val isLiked : Boolean,
	@SerializedName("isSaved") val isSaved : Boolean,
	@SerializedName("likes") val likes : Likes?,
	@SerializedName("comment") val comment : String?,
	@SerializedName("mentionedList") val mentionedList : List<String>?,
	@SerializedName("hashTagsList") val hashTagsList : List<String>?,
	@SerializedName("postDate") val postDate : String
)