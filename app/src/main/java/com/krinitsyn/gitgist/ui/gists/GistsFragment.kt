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
import com.krinitsyn.utils.optional.ifPresentOrElse
import com.krinitsyn.utils.resource.Resource
import kotlinx.android.synthetic.main.fragment_gists.*

internal class GistsFragment : AbstractFragment(), GistsView {

    interface Callbacks {

        fun showGist(gistId: String)

    }

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

    private lateinit var usersAdapter: GistsUserAdapter
    private lateinit var usersItemDecoration: GistsUserItemDecoration

    private lateinit var gistsAdapter: GistsAdapter
    private lateinit var gistsItemDecoration: GistsItemDecoration

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_gists, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentGistsSwipeRefreshLayout.setOnRefreshListener { presenter.refreshGists() }

        val imageLoader = GithubGistImageLoaderFactory.withFragment(this)
        val context = requireContext()

        usersAdapter = GistsUserAdapter(imageLoader)
//        usersAdapter.onClickListener = ::onUserClick
        usersItemDecoration = GistsUserItemDecoration()
        with(fragmentGistsUsersRecyclerView) {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(usersItemDecoration)
            isNestedScrollingEnabled = false
        }

        gistsAdapter = GistsAdapter(imageLoader)
        gistsAdapter.onClickListener = ::onGistClick
        gistsItemDecoration = GistsItemDecoration()
        with(fragmentGistsRecyclerView) {
            adapter = gistsAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(gistsItemDecoration)
            isNestedScrollingEnabled = false
        }
    }

    private fun onGistClick(gist: GistsViewState.Gist) {
        callbacks?.showGist(gist.gistId)
    }

    override fun onViewStateChanged(viewState: GistsViewState) {
        viewState.users.ifPresentOrElse(::showTopUsers, ::hideTopUsers)

        when (val resource = viewState.gists) {
            is Resource.Loading -> showLoading()
            is Resource.Data -> showGists(resource.data)
            is Resource.Error -> showError(resource.throwable)
        }
    }

    private fun showTopUsers(users: List<GistsViewState.User>) {
        usersAdapter.items = users
        fragmentGistsUsersLayout.isVisible = true
    }

    private fun hideTopUsers() {
        usersAdapter.items = emptyList()
        fragmentGistsUsersLayout.isVisible = false
    }

    private fun showLoading() {
        fragmentGistsSwipeRefreshLayout.isRefreshing = true
        fragmentGistsSwipeRefreshLayout.isEnabled = false

        gistsAdapter.items = emptyList()

        fragmentGistsErrorView.text = null
        fragmentGistsErrorView.isVisible = false
    }

    private fun showGists(gists: List<GistsViewState.Gist>) {
        fragmentGistsSwipeRefreshLayout.isRefreshing = false
        fragmentGistsSwipeRefreshLayout.isEnabled = true

        gistsAdapter.items = gists

        fragmentGistsErrorView.text = null
        fragmentGistsErrorView.isVisible = false
    }

    private fun showError(throwable: Throwable) {
        fragmentGistsSwipeRefreshLayout.isRefreshing = false
        fragmentGistsSwipeRefreshLayout.isEnabled = true

        gistsAdapter.items = emptyList()

        fragmentGistsErrorView.text = throwable.toErrorMessage()
        fragmentGistsErrorView.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentGistsSwipeRefreshLayout.setOnRefreshListener(null)
        fragmentGistsRecyclerView.adapter = null
        fragmentGistsUsersRecyclerView.adapter = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}