package com.krinitsyn.git_gist

import android.widget.ImageView

interface ImageLoader {

    fun loadUserAvatar40dp(imageView: ImageView, avatarUrl: String)

    fun loadUserAvatar60dp(imageView: ImageView, avatarUrl: String)

}