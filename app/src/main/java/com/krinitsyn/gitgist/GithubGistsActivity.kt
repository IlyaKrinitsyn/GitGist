package com.krinitsyn.gitgist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.krinitsyn.gitgist.ui.gists.GistsFragment

internal class GithubGistsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_gists)
        if (savedInstanceState == null) {
            showGistsFragment()
        }
    }

    private fun showGistsFragment() {
        supportFragmentManager.transaction {
            replace(R.id.root, GistsFragment.newInstance(), GistsFragment.Tag)
        }
    }

}
