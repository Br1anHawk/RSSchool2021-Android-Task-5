package com.example.rsschool2021_android_task_5

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rsschool2021_android_task_5.network.CatsApiService
import com.example.rsschool2021_android_task_5.network.CatsProperty

class PagingSource(
    private val catsApiService: CatsApiService
): PagingSource<Int, CatsProperty>() {
    override fun getRefreshKey(state: PagingState<Int, CatsProperty>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatsProperty> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize
            val properties = catsApiService.getPropertiesWithPagination(pageSize, pageNumber, HAS_BREEDS_API_PARAMETER_REQUEST)

            val nextPageNumber = if (properties.isEmpty()) null else pageNumber + 1
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null

            return if (properties.isNotEmpty()) {
                LoadResult.Page(properties, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}