package com.krinitsyn.git_gist

import androidx.fragment.app.Fragment
import com.krinitsyn.git_gist.impl.picasso.PicassoGithubGistImageLoader

object GithubGistImageLoaderFactory {

    fun withFragment(fragment: Fragment): GithubGistImageLoader = PicassoGithubGistImageLoader(fragment.requireContext())

}