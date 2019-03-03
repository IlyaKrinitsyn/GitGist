package com.krinitsyn.gitgist.presentation.gist

import androidx.annotation.StringRes
import com.krinitsyn.git_gist.data.ChangeStatus
import com.krinitsyn.utils.resource.Resource

internal data class GistViewState(
    val info: Resource<Info> = Resource.Loading(),
    val items: Resource<List<Item>> = Resource.Loading()
) {

    data class Info(
        val login: String,
        val avatarUrl: String,
        val userUrl: String,
        val gistName: String,
        val gistDescription: String?
    )

    sealed class Item {

        enum class Type(val value: Int) {
            Header(1),
            File(2),
            Commit(3)
        }

        abstract val type: Type

        data class Header(
            @StringRes val value: Int,
            override val type: Type = Type.Header
        ) : Item()

        data class File(
            val name: String,
            val content: String,
            val rawUrl: String,
            override val type: Type = Type.File
        ) : Item()

        data class Commit(
            val userName: String?,
            val version: String,
            val changeStatus: ChangeStatus,
            override val type: Type = Type.Commit
        ) : Item()

    }

}