package com.example.streamchatdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatUser(
    val firstName: String,
    val username: String,
    val avatar: String,
    val image: String
) : Parcelable