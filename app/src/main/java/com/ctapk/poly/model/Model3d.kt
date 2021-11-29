package com.ctapk.poly.model

data class Model3d(
    val name: String,
    val description: String,
    val uid: String,
    val vertexCount: Int,
    val thumbnails: Model3dImageList
) {
    fun getBiggestThumbnailUrl(): String? {
        if (thumbnails.model3dImages.isNotEmpty()) {
            thumbnails.model3dImages.sortedByDescending { it.size }
            return thumbnails.model3dImages[0].url
        }
        return null
    }
}