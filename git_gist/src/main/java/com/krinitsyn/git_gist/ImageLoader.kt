package com.krinitsyn.git_gist

import android.widget.ImageView
import com.krinitsyn.git_gist.data.User

interface ImageLoader {

    fun loadUserAvatar(imageView: ImageView, avatarUrl: String)

}