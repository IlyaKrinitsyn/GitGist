package com.krinitsyn.gitgist.ui.gists

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.krinitsyn.git_gist.GithubGistImageLoaderFactory
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.dependencies
import com.krinitsyn.gitgist.presentation.gists.GistsPresenter
import com.krinitsyn.gitgist.presentation.gists.GistsView
import com.krinitsyn.gitgist.presentation.gists.GistsViewState
import com.krinitsyn.gitgist.ui.AbstractFragment
import com.krinitsyn.utils.resource.Resource
import kotlinx.android.synthetic.main.fragment_gists.*

internal class GistsFragment : AbstractFragment(), GistsView {

    companion object {

        const val Tag = "GistsFragment"

        fun newInstance(): GistsFragment = GistsFragment()

    }

    @InjectPresenter
    lateinit var presenter: GistsPresenter

    @ProvidePresenter
    fun provideGistsPresenter(): GistsPresenter {
        val (repository, schedulers, logger) = requireContext().dependencies()
        return GistsPresenter(repository, schedulers, logger)
    }

    private lateinit var gistsAdapter: GistsAdapter
    private lateinit var gistsItemDecoration: GistsItemDecorataion

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_gists, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentGistsSwipeRefreshLayout.setOnRefreshListener { presenter.refreshGists() }

        val imageLoader = GithubGistImageLoaderFactory.withFragment(this)
        gistsAdapter = GistsAdapter(imageLoader)

        gistsItemDecoration = GistsItemDecorataion()

        val context = requireContext()
        with(fragmentGistsRecyclerView) {
            adapter = gistsAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(gistsItemDecoration)
        }
    }

    override fun onViewStateChanged(viewState: GistsViewState) = when (val resource = viewState.gists) {
        is Resource.Loading -> showLoading()
        is Resource.Data -> showGists(resource.data)
        is Resource.Error -> showError(resource.throwable)
    }

    private fun showLoading() {
        fragmentGistsSwipeRefreshLayout.isRefreshing = true
        fragmentGistsSwipeRefreshLayout.isEnabled = false

        gistsAdapter.items = emptyList()

        fragmentGistsErrorView.isVisible = false
    }

    private fun showGists(gists: List<GistsViewState.Gist>) {
        fragmentGistsSwipeRefreshLayout.isRefreshing = false
        fragmentGistsSwipeRefreshLayout.isEnabled = true

        gistsAdapter.items = gists

        fragmentGistsErrorView.isVisible = false
    }

    private fun showError(throwable: Throwable) {
        fragmentGistsSwipeRefreshLayout.isRefreshing = false
        fragmentGistsSwipeRefreshLayout.isEnabled = true

        gistsAdapter.items = emptyList()

        fragmentGistsErrorView.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentGistsSwipeRefreshLayout.setOnRefreshListener(null)
        fragmentGistsRecyclerView.adapter = null
    }

}