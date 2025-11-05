package com.example.slideshow.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Photo(
    @StringRes val captionResId: Int,
    @DrawableRes val imageResId: Int
)
