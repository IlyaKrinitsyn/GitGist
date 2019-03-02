package com.krinitsyn.git_gist

import androidx.fragment.app.Fragment
import com.krinitsyn.git_gist.impl.PicassoImageLoader

object GithubGistImageLoaderFactory {

    fun withFragment(fragment: Fragment): ImageLoader = PicassoImageLoader(fragment.requireContext())

}