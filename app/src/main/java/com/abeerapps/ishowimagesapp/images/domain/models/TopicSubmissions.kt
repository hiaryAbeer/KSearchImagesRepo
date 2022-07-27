package com.abeerapps.ishowimagesapp.images.domain.models

import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    val monochrome: Monochrome,
    @SerializedName("textures-patterns")
    val texturesPatterns: TexturesPatterns,
    val wallpapers: Wallpapers
)