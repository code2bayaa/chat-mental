package com.example.streamchatdemo.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.streamchatdemo.R
import com.example.streamchatdemo.model.ChatUser
import com.example.streamchatdemo.ui.login.LoginFragmentDirections
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.name

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val client = ChatClient.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        navController = findNavController(R.id.navHostFragment)

        val pref = getSharedPreferences("Chat", Context.MODE_PRIVATE)
        val username = pref.getString("username", null).toString()
        val name = pref.getString("name", null).toString().split(",")[0]
        val image = pref.getString("image", null).toString()
        val avatar = pref.getString("avatar", null).toString()

        val user = ChatUser(name, username, avatar, image )
        val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
            navController.navigate(action)

    }
}