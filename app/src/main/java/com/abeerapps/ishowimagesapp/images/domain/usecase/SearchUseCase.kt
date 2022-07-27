package com.abeerapps.ishowimagesapp.images.domain.usecase

import com.abeerapps.ishowimagesapp.images.domain.AppRepository
import com.abeerapps.ishowimagesapp.images.domain.Resources
import com.abeerapps.ishowimagesapp.images.domain.models.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: AppRepository) {

    fun getSearchResult(searchWord: String): Flow<Resources<SearchResponse>> = repository.getSearchResult(searchWord)


}