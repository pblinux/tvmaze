package me.pblinux.tvmaze.utils

import android.text.Html
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

fun String.clean(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString();
    } else {
        Html.fromHtml(this).toString()
    }.trim()
}

@ExperimentalFoundationApi
fun <T : Any> LazyGridScope.itemsIndexed(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(index: Int, item: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(index, lazyPagingItems.getAsState(index).value)
    }
}