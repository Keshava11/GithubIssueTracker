package com.ravinishad.githubissuetracker.data.remote.dto

import com.ravinishad.githubissuetracker.domain.model.Issue

data class IssueItem(
    val active_lock_reason: Any,
    val assignee: Any,
    val assignees: List<Any>,
    val author_association: String,
    val body: String,
    val closed_at: String,
    val comments: Int,
    val comments_url: String,
    val created_at: String,
    val events_url: String,
    val html_url: String,
    val id: Int,
    val labels: List<Label>,
    val labels_url: String,
    val locked: Boolean,
    val milestone: Any,
    val node_id: String,
    val number: Int,
    val performed_via_github_app: Any,
    val reactions: Reactions,
    val repository_url: String,
    val score: Double,
    val state: String,
    val state_reason: String,
    val timeline_url: String,
    val title: String,
    val updated_at: String,
    val url: String,
    val user: User
)


fun IssueItem.toIssue(): Issue {
    return Issue(
        title = title,
        createdDate = created_at,
        closedDate = closed_at,
        userName = user.login,
        userImage = user.avatar_url
    )
}

