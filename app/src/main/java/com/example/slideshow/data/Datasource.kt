package com.example.slideshow.data

import com.example.slideshow.R
import com.example.slideshow.model.Photo

class Datasource {
    fun loadPhotos(): List<Photo> = listOf(
        Photo(R.string.caption1, R.drawable.image1),
        Photo(R.string.caption2, R.drawable.image2),
        Photo(R.string.caption3, R.drawable.image3),
        Photo(R.string.caption4, R.drawable.image4),
        Photo(R.string.caption5, R.drawable.image5),
        Photo(R.string.caption6, R.drawable.image6),
        Photo(R.string.caption7, R.drawable.image7),
        Photo(R.string.caption8, R.drawable.image8),
        Photo(R.string.caption9, R.drawable.image9),
        Photo(R.string.caption10, R.drawable.image10)
    )
}
