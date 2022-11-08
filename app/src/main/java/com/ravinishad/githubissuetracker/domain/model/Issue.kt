package com.ravinishad.githubissuetracker.domain.model

data class Issue(
    val title: String, val createdDate: String, val closedDate: String, val userName: String, val userImage: String
)