package com.elijahverdoorn.nyt.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val section: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Story>
)