package com.krinitsyn.gitgist.ui.gist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_gist_file.view.*

internal class GistFileViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            GistFileViewHolder(layoutInflater.inflate(R.layout.list_item_gist_file, parent, false))

    }

    init {
        itemView.listItemGistFileHideView.setOnClickListener { onHideClick() }
        itemView.listItemGistFileHideView.isClickable = true
    }

    var name: CharSequence?
        get() = itemView.listItemGistFileNameView.text
        set(value) {
            itemView.listItemGistFileNameView.text = value
        }

    // Я не смог быстро найти способ отображения разных форматов данных,
    // т.к. есть куча разных форматов и так далее, поэтому отображается только текст((9(
    var content: CharSequence?
        get() = itemView.listItemGistFileContentView.text
        set(value) {
            itemView.listItemGistFileContentView.text = value
        }

    private fun onHideClick() {
        if (itemView.listItemGistFileContentView.isVisible) {
            itemView.listItemGistFileContentView.isVisible = false
            itemView.listItemGistFileHideView.setText(R.string.list_item_gist_file_show_content)
        } else {
            itemView.listItemGistFileContentView.isVisible = true
            itemView.listItemGistFileHideView.setText(R.string.list_item_gist_file_hide_content)
        }
    }

}
