package com.noxpa.instagramtask.data_classes

import com.google.gson.annotations.SerializedName

data class Feed (
	@SerializedName("posts") val posts : List<Post>?
)