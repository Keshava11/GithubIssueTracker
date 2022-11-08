package com.ravinishad.githubissuetracker.common.model

import com.google.gson.JsonObject

data class CommonResponse(
    val data: JsonObject?,
    val message: String,
    val statusCode: Int
)

