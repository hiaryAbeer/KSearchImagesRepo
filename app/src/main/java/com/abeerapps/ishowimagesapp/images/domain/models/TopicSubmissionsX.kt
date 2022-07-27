package com.abeerapps.ishowimagesapp.images.domain.models

import com.google.gson.annotations.SerializedName

data class TopicSubmissionsX(
    @SerializedName("architecture-interior")
    val architectureInterior: ArchitectureInterior,
    @SerializedName("business-work")
    val businessWork: BusinessWork,
    @SerializedName("covid-19")
    val covid: Covid19,
    val interiors: Interiors,
    val wallpapers: WallpapersX
)