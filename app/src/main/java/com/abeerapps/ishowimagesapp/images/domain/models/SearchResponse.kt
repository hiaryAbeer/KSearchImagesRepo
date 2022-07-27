package com.abeerapps.ishowimagesapp.images.domain.models

data class SearchResponse(
    val results: List<Result> = listOf(),
    val total: Int = 0,
    val total_pages: Int = 0
)