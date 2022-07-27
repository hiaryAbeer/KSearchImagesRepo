package com.abeerapps.ishowimagesapp.images.domain

import com.abeerapps.ishowimagesapp.images.domain.models.SearchResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {

     fun getSearchResult(searchWord: String): Flow<Resources<SearchResponse>>
}