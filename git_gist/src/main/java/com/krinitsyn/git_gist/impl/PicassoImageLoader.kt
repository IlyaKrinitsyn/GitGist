package com.krinitsyn.git_gist.impl

import android.content.Context
import android.widget.ImageView
import com.krinitsyn.git_gist.ImageLoader
import com.krinitsyn.git_gist.R
import com.squareup.picasso.Picasso

internal class PicassoImageLoader constructor(
    private val context: Context,
    private val picasso: Picasso = Picasso.get()
) : ImageLoader {

    override fun loadUserAvatar(imageView: ImageView, avatarUrl: String) {
        picasso.load(avatarUrl)
            .placeholder(R.drawable.ic_circle_placeholder_40dp)
            .fit()
            .centerCrop()
            .into(imageView)
    }

}