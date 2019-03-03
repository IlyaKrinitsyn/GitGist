package com.krinitsyn.git_gist.impl.picasso

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.krinitsyn.git_gist.GithubGistImageLoader
import com.krinitsyn.git_gist.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

internal class PicassoGithubGistImageLoader constructor(
    private val context: Context,
    private val picasso: Picasso = Picasso.get()
) : GithubGistImageLoader {

    override fun loadUserAvatar40dp(imageView: ImageView, avatarUrl: String) {
        loadUserAvatar(imageView, avatarUrl, R.drawable.ic_rectangle_placeholder_40dp)
    }

    override fun loadUserAvatarCircle50dp(imageView: ImageView, avatarUrl: String) {
        loadUserAvatar(imageView, avatarUrl, R.drawable.ic_circle_placeholder_50dp, CircleTransformation)
    }

    override fun loadUserAvatar60dp(imageView: ImageView, avatarUrl: String) {
        loadUserAvatar(imageView, avatarUrl, R.drawable.ic_rectangle_placeholder_60dp)
    }

    private fun loadUserAvatar(
        imageView: ImageView,
        avatarUrl: String,
        @DrawableRes placeholderRes: Int,
        vararg transformation: Transformation
    ) {
        picasso.load(avatarUrl)
            .placeholder(placeholderRes)
            .error(placeholderRes)
            .centerCrop()
            .fit()
            .transform(listOf(*transformation))
            .into(imageView)
    }

}