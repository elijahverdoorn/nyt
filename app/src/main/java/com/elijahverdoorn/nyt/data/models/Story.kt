package com.elijahverdoorn.nyt.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val title: String,
    val section: String,
    val subsection: String,
    val abstract: String,
    val byline: String,
    val url: String,
    val multimedia: List<Multimedia>? // The API sometimes does not return any multimedia. To handle this case we allow the multimedia value to be nullable.
)