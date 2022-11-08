package com.ravinishad.githubissuetracker.domain.repository

import com.ravinishad.githubissuetracker.data.remote.dto.IssueResponseDto

interface IssueRepository {
    suspend fun getIssueResponse(
        repoName: String = "square/retrofit",
        issueType: String = "closed",
        text: String,
        page: Int,
        count: Int = 30
    ): IssueResponseDto
}