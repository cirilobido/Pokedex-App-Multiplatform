package presentation.home

import kotlinx.serialization.Serializable

@Serializable
data class BirdImageModel(
    val author: String,
    val category: String,
    val path: String
)