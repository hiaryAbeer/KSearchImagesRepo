package com.abeerapps.ishowimagesapp.images.extension

import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@BindingAdapter("app:setText", "app:scope", requireAll = true)
fun AppCompatEditText.setText(text: MutableStateFlow<String>, scope: CoroutineScope) {
    this.addTextChangedListener(doAfterTextChanged {
        scope.launch {
            text.emit(this@setText.text.toString())
        }
    })
}