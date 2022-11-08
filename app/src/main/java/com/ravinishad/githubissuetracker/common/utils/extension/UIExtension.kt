package com.ravinishad.githubissuetracker.common.utils.extension

import java.text.SimpleDateFormat
import java.util.*

infix fun Any.appendAtToUser(user: String): String {
    return "@$user"
}

infix fun Any.serverDateToLocal(serverDate: String): String {
    val dFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    dFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = dFormat.parse(serverDate)
    val dateFormat = SimpleDateFormat("dd MMM, yyyy hh:mm a", Locale.US)
    return dateFormat.format(date!!)
}