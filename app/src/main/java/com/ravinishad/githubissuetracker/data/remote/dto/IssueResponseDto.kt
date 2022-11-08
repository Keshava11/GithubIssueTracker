package com.ravinishad.githubissuetracker.data.remote.dto

data class IssueResponseDto(
    val incomplete_results: Boolean,
    val items: List<IssueItem>,
    val total_count: Int
)