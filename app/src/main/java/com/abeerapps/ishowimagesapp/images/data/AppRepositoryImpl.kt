package com.abeerapps.ishowimagesapp.images.data

import com.abeerapps.ishowimagesapp.images.data.network.SearchImagesEndPoint
import com.abeerapps.ishowimagesapp.images.domain.AppRepository
import com.abeerapps.ishowimagesapp.images.domain.Resources
import com.abeerapps.ishowimagesapp.images.domain.models.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val searchImagesEndPoint: SearchImagesEndPoint) : AppRepository{
    override  fun getSearchResult(searchWord: String): Flow<Resources<SearchResponse>> = flow {
        try {
            val response = searchImagesEndPoint.getSearchResult(searchWord)
            emit(Resources.Success(response))

        } catch (e: Exception) {
            emit(Resources.Error(null, e))
        }
    }
}