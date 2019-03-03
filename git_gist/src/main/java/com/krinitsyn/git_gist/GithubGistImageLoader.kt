package com.krinitsyn.git_gist

import android.widget.ImageView

interface GithubGistImageLoader {

    fun loadUserAvatar40dp(imageView: ImageView, avatarUrl: String)

    fun loadUserAvatarCircle50dp(imageView: ImageView, avatarUrl: String)

    fun loadUserAvatar60dp(imageView: ImageView, avatarUrl: String)

}