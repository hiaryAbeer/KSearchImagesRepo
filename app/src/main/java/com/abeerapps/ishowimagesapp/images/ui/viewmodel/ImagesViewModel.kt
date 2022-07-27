package com.abeerapps.ishowimagesapp.images.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abeerapps.ishowimagesapp.images.domain.Resources
import com.abeerapps.ishowimagesapp.images.domain.models.SearchResponse
import com.abeerapps.ishowimagesapp.images.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    val mSearchQuery = MutableStateFlow("")
    val mShowError = MutableStateFlow(false)
    val mScope = viewModelScope

    private val mSearchResultSender = Channel<Actions> { }
    val mSearchResult = mSearchResultSender.receiveAsFlow()

    val mImagesList = MutableStateFlow(SearchResponse())
    val mErrorMessage = MutableSharedFlow<String>()

    /**
     * handle search queries
     */
    fun onSearchClicked() {
        if (mSearchQuery.value.isEmpty())
            mShowError.value = true
        else {
            viewModelScope.launch {
                searchUseCase.getSearchResult(mSearchQuery.value).collect {
                    when (it) {
                        is Resources.Success -> {
                            mImagesList.value = it.data!!
                            mSearchResultSender.send(Actions.NavToSearchList)

                        }
                        is Resources.Error -> {
                            mErrorMessage.emit("Something is not correct, please try later!")
                        }
                    }
                }
            }
        }
    }

    /**
     * open photographer portfolio when his name clicked
     * @param link String
     */
    fun openPhotographerPortfolio(link: String) {
        viewModelScope.launch {
            mSearchResultSender.send(Actions.NavToPhotographerPortfolio(link))

        }

    }

    /**
     * simple class to handle actions when buttons clicked
     */
    sealed class Actions {
        object NavToSearchList : Actions()
        class NavToPhotographerPortfolio(val link: String) : Actions()

    }
}