package com.krinitsyn.git_gist.impl

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.krinitsyn.git_gist.ImageLoader
import com.krinitsyn.git_gist.R
import com.squareup.picasso.Picasso

internal class PicassoImageLoader constructor(
    private val context: Context,
    private val picasso: Picasso = Picasso.get()
) : ImageLoader {

    override fun loadUserAvatar40dp(imageView: ImageView, avatarUrl: String) {
        loadUserAvatar(imageView, avatarUrl , R.drawable.ic_rectangle_placeholder_40dp)
    }

    override fun loadUserAvatar60dp(imageView: ImageView, avatarUrl: String) {
        loadUserAvatar(imageView, avatarUrl , R.drawable.ic_rectangle_placeholder_60dp)
    }

    private fun loadUserAvatar(
        imageView: ImageView,
        avatarUrl: String,
        @DrawableRes placeholderRes: Int
    ) {
        picasso.load(avatarUrl)
            .placeholder(placeholderRes)
            .centerCrop()
            .into(imageView)
    }

}