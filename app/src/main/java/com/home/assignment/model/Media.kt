package com.home.assignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Media(
    var approved_for_syndication: Int,
    var caption: String,
    var copyright: String,
    @SerializedName("media-metadata")
    @Expose
    var mediaMetadata: List<MediaMetadata>,
    var subtype: String,
    var type: String
)