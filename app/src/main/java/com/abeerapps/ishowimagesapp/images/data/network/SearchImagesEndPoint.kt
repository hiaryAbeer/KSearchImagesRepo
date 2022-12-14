package com.abeerapps.ishowimagesapp.images.data.network

import com.abeerapps.ishowimagesapp.UNSPLASH_WEBSITE_ACCESS_KEY
import com.abeerapps.ishowimagesapp.images.domain.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchImagesEndPoint {

    @GET("search/photos")
    suspend fun getSearchResult(
        @Query("query") itemName: String,
        @Query("client_id") accessKey: String = UNSPLASH_WEBSITE_ACCESS_KEY,
    ): SearchResponse
}