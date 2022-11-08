package com.ravinishad.githubissuetracker.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ravinishad.githubissuetracker.data.remote.dto.IssueItem
import com.ravinishad.githubissuetracker.domain.repository.IssueRepository

class IssuePagingSource(private val issueRepository: IssueRepository) : PagingSource<Int, IssueItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IssueItem> {
        return try {
            val position = params.key ?: 1
            val response = issueRepository.getIssueResponse(text = "", page = position)

            return LoadResult.Page(
                data = response.items,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == (response.total_count / 30)) null else position + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, IssueItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}