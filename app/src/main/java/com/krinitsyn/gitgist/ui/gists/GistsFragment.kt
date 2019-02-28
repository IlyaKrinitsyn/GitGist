package com.krinitsyn.gitgist.ui.gists

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.dependencies
import com.krinitsyn.gitgist.presentation.gists.GistsPresenter
import com.krinitsyn.gitgist.presentation.gists.GistsView
import com.krinitsyn.gitgist.presentation.gists.GistsViewState
import com.krinitsyn.gitgist.ui.AbstractFragment

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_gists, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateChanged(viewState: GistsViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}