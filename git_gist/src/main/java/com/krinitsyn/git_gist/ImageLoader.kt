package com.krinitsyn.git_gist

import android.widget.ImageView

interface ImageLoader {

    fun loadUserAvatar(imageView: ImageView, avatarUrl: String)

}