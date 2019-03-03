package com.krinitsyn.gitgist.ui.gist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.krinitsyn.git_gist.GithubGistImageLoaderFactory
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.dependencies
import com.krinitsyn.gitgist.presentation.gist.GistPresenter
import com.krinitsyn.gitgist.presentation.gist.GistView
import com.krinitsyn.gitgist.presentation.gist.GistViewState
import com.krinitsyn.gitgist.ui.AbstractFragment
import com.krinitsyn.utils.resource.Resource
import kotlinx.android.synthetic.main.fragment_gist.*

internal class GistFragment : AbstractFragment(), GistView {

    companion object {

        const val Tag = "GistFragment"

        private const val ExtraGistId = "gist_id"

        fun newInstance(gistId: String): GistFragment = GistFragment().apply {
            arguments = bundleOf(ExtraGistId to gistId)
        }

    }

    @InjectPresenter
    lateinit var presenter: GistPresenter

    @ProvidePresenter
    fun provideGistPresenter(): GistPresenter {
        val (repository, schedulers, logger) = requireContext().dependencies()
        val gistId = arguments?.getString(ExtraGistId)
            ?: throw IllegalStateException("Gist id should be not null")
        return GistPresenter(repository, schedulers, logger, gistId)
    }

    private lateinit var gistAdapter: GistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_gist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gistAdapter = GistAdapter()
        with(fragmentGistContentRecyclerView) {
            adapter = gistAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }
    }

    override fun onViewStateChanged(viewState: GistViewState) {
        when (val infoResource = viewState.info) {
            is Resource.Loading -> showInfoLoading()
            is Resource.Data -> showInfo(infoResource.data)
            is Resource.Error -> showInfoError(infoResource.throwable)
        }
        when (val itemsResource = viewState.items) {
            is Resource.Loading -> showItemsLoading()
            is Resource.Data -> showItems(itemsResource.data)
            is Resource.Error -> showItemsError(itemsResource.throwable)
        }
    }

    private fun showInfoLoading() {
        fragmentGistProgressBar.isVisible = true

        fragmentGistContentLayout.isVisible = false

        fragmentGistErrorView.isVisible = false
    }

    private fun showInfo(info: GistViewState.Info) {
        fragmentGistProgressBar.isVisible = false

        fragmentGistContentLayout.isVisible = true
        GithubGistImageLoaderFactory.withFragment(this)
            .loadUserAvatar60dp(fragmentGistAvatarView, info.avatarUrl)
        fragmentGistNameView.text = requireContext().getString(R.string.fragment_gist_gist_name_format,
            info.login, info.gistName)
        val gistDescription = info.gistDescription
        if (gistDescription != null) {
            fragmentGistDescriptionView.text = gistDescription
            fragmentGistDescriptionView.isVisible = true
        } else {
            fragmentGistDescriptionView.text = null
            fragmentGistDescriptionView.isVisible = false
        }

        fragmentGistErrorView.isVisible = false
    }

    private fun showInfoError(throwable: Throwable) {
        fragmentGistProgressBar.isVisible = false

        fragmentGistContentLayout.isVisible = false
        fragmentGistDescriptionView.isVisible = false

        fragmentGistErrorView.isVisible = true
    }

    private fun showItemsLoading() {
        gistAdapter.items = emptyList()
    }

    private fun showItems(items: List<GistViewState.Item>) {
        gistAdapter.items = items
    }

    private fun showItemsError(throwable: Throwable) {
        gistAdapter.items = emptyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentGistContentRecyclerView.adapter = null
    }

}