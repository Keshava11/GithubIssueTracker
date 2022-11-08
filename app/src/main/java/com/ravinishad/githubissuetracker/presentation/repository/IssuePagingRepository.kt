package com.ravinishad.githubissuetracker.presentation.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ravinishad.githubissuetracker.domain.repository.IssueRepository
import com.ravinishad.githubissuetracker.presentation.paging.IssuePagingSource
import javax.inject.Inject

class IssuePagingRepository @Inject constructor(private val issueRepository: IssueRepository) {

    fun getClosedIssues() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { IssuePagingSource(issueRepository) }
    ).liveData

}