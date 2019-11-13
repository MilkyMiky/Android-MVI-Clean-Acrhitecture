package com.example.testproject.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.testproject.repository.domain.service.ImageLoader

class ImageBindingAdapter constructor(private val imageLoader: ImageLoader) {

  lateinit var packageName: String

  @BindingAdapter(
    value = [
      "imgUrl",
      "imgResId",
      "placeholderResId",
      "transformCenterCrop",
      "transformCircle",
      "transformRoundedCorners",
      "fade"],
    requireAll = false
  )
  fun loadImage(
    imageView: ImageView,
    imgUrl: String? = null,
    imgResId: Int = 0,
    placeholderResId: Int = 0,
    transformCenterCrop: Boolean = false,
    transformCircle: Boolean = false,
    transformRoundedCorners: Int = 0,
    fade: Boolean = false
  ) {
    if (!::packageName.isInitialized)
      packageName = imageView.context.packageName

    val imageUrl = if (imgResId == 0) imgUrl
    else "android.resource://$packageName/$imgResId"

    imageLoader.loadImg(
      imageView,
      imageUrl,
      ImageLoader.Args(
        placeholderResId,
        transformCenterCrop,
        transformCircle,
        transformRoundedCorners
      )
    )
  }
}