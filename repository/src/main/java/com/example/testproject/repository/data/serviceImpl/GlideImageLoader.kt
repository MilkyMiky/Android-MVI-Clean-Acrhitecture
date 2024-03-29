package com.example.testproject.repository.data.serviceImpl

import android.content.res.Resources
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.testproject.repository.domain.service.ImageLoader
import java.util.ArrayList

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

object GlideImageLoader : ImageLoader {

  override fun loadImg(
    iv: ImageView,
    url: String?,
    args: ImageLoader.Args
  ) {
    val (placeholderResId, transformCenterCrop, transformCircle, roundedCornersRadiusDp) = args

    require(!transformCircle || roundedCornersRadiusDp == 0) {
      "Cannot apply transformCircle and roundedCornersRadiusDp attrs at the same time"
    }

    val transformations = ArrayList<Transformation<Bitmap>>().apply {
      if (transformCenterCrop) add(CenterCrop())
      if (transformCircle) add(CircleCrop())
      if (roundedCornersRadiusDp > 0) add(RoundedCorners(roundedCornersRadiusDp.px))
    }
    // is transform requested?
    val requestTransformOptions = if (!transformations.isEmpty()) {
      RequestOptions.bitmapTransform(MultiTransformation(transformations))
    } else RequestOptions()

    if (placeholderResId != 0)
      requestTransformOptions.placeholder(placeholderResId)

    Glide.with(iv)
      .load(url)
      .apply(requestTransformOptions)
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(iv)
  }
}