package com.krinitsyn.gitgist.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_file.view.*

internal class FileViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
                FileViewHolder(layoutInflater.inflate(R.layout.list_item_file, parent, false))

    }

    init {
        itemView.listItemFileHideView.setOnClickListener { onHideClick() }
        itemView.listItemFileHideView.isClickable = true
    }

    var name: CharSequence?
        get() = itemView.listItemFileNameView.text
        set(value) {
            itemView.listItemFileNameView.text = value
        }

    // Я не смог быстро найти способ отображения разных форматов данных,
    // т.к. есть куча разных форматов и так далее, поэтому отображается только текст((9(
    var content: CharSequence?
        get() = itemView.listItemFileContentView.text
        set(value) {
            itemView.listItemFileContentView.text = value
        }

    private fun onHideClick() {
        if (itemView.listItemFileContentView.isVisible) {
            itemView.listItemFileContentView.isVisible = false
            itemView.listItemFileHideView.setText(R.string.list_item_file_show_content)
        } else {
            itemView.listItemFileContentView.isVisible = true
            itemView.listItemFileHideView.setText(R.string.list_item_file_hide_content)
        }
    }

}
