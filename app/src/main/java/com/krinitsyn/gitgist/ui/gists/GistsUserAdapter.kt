package com.krinitsyn.gitgist.ui.gists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.git_gist.GithubGistImageLoader
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.presentation.gists.GistsViewState
import com.krinitsyn.gitgist.ui.viewholder.UserViewHolder
import kotlin.properties.Delegates

internal class GistsUserAdapter(
    private val githubGistImageLoader: GithubGistImageLoader
) : RecyclerView.Adapter<UserViewHolder>() {

    var onClickListener: ((GistsViewState.User) -> Unit)? = null

    var items: List<GistsViewState.User> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder.inflate(layoutInflater, parent)
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        val user = items[position]
        with(viewHolder) {
            setAvatar { view -> githubGistImageLoader.loadUserAvatarCircle50dp(view, user.avatarUrl) }
            gists = itemView.context.getString(R.string.simple_placeholder, user.gists)
            setOnClickListener { viewHolder, _ -> onGistClick(viewHolder as UserViewHolder) }
        }
    }

    override fun getItemCount(): Int = items.size

    private fun onGistClick(viewHolder: UserViewHolder) {
        val position = viewHolder.adapterPosition
        if (position in 0..(itemCount - 1)) {
            val gist = items[position]
            onClickListener?.invoke(gist)
        }
    }

}