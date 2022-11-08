package com.ravinishad.githubissuetracker.data.repository

import com.ravinishad.githubissuetracker.data.remote.GitHubIssueService
import com.ravinishad.githubissuetracker.data.remote.dto.IssueResponseDto
import com.ravinishad.githubissuetracker.domain.repository.IssueRepository
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(private val gitHubIssueService: GitHubIssueService) : IssueRepository {

    override suspend fun getIssueResponse(repoName: String, issueType: String, text: String, page: Int, count: Int):
            IssueResponseDto {
        val query = "repo:${repoName}+type:issue+state:${issueType}${text}"
        return gitHubIssueService.getIssues(query, page, count)
    }

}