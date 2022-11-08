package com.ravinishad.githubissuetracker.data.remote

import com.ravinishad.githubissuetracker.data.remote.dto.IssueResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubIssueService {

    @GET(ApiUrl.ISSUES_SEARCH)
    suspend fun getIssues(
        @Query("q", encoded = true) repo: String,
        @Query("page") page: Int,
        @Query("count") count: Int
    ): IssueResponseDto

}