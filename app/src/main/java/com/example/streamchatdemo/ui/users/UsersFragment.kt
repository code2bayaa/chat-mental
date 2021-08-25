package com.example.streamchatdemo.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mental.api.groupsAPI
import com.example.streamchatdemo.R
import com.example.streamchatdemo.adapter.UsersAdapter
import com.example.streamchatdemo.databinding.FragmentUsersBinding
import com.example.streamchatdemo.ui.channel.ChannelFragmentArgs
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val usersAdapter by lazy { UsersAdapter() }
    private val client = ChatClient.instance()
    private var avatar: String = ""
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val pref = this.requireActivity()
            .getSharedPreferences("Chat", Context.MODE_PRIVATE)
        avatar = pref.getString("avatar", null).toString()
        username = pref.getString("username", null).toString()

        setupToolbar()
        setupRecyclerView()
        setupGroup()
        queryAllUsers()


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.users_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query!!.isEmpty()) {
                    queryAllUsers()
                } else {
                    searchUser(query)
                }
                return true
            }
        })
        searchView?.setOnCloseListener {
            queryAllUsers()
            false
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecyclerView.adapter = usersAdapter
    }

    private fun searchUser(query: String) {
        val filters = Filters.and(
            Filters.autocomplete("id", query),
            Filters.ne("id", client.getCurrentUser()!!.id)
        )
        val request = QueryUsersRequest(
            filter = filters,
            offset = 0,
            limit = 100
        )
        client.queryUsers(request).enqueue { result ->
            if (result.isSuccess) {
                val users: List<User> = result.data()
                usersAdapter.setData(users)
            } else {
                Log.e("UsersFragment", result.error().message.toString())
            }
        }
    }

    private fun queryAllUsers() {

        val filters = Filters.and(
            Filters.ne("group", avatar),
            Filters.ne("id", client.getCurrentUser()!!.id)
        )

        val request = QueryUsersRequest(
            filter = filters,
            offset = 0,
            limit = 10
        )
        client.queryUsers(request).enqueue { result ->
            Toast.makeText(context, "Re$result", Toast.LENGTH_SHORT).show()

            if (result.isSuccess) {
                val users: List<User> = result.data()
                usersAdapter.setData(users)
            } else {
                Log.e("UsersFragment", result.error().message.toString())
            }
        }
    }

    private fun setupGroup() {

        var permit = "patient"
        if(avatar.equals("Doctor")){
            permit = "doctor"
        }

        val user = username

        val retrofit = Retrofit.Builder()
            .baseUrl(groupsAPI.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retrofit.create(groupsAPI::class.java)
        val call = api.getStrings(permit, user)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful) {

                    if (response.body() != null) {
                        try {
                            val data = JSONObject(response.body())
                            val message = data.getString("message")
                            if (message == "Success!") {
                                val patients = data.getJSONArray("data")
                                Toast.makeText(context, "Pe$patients", Toast.LENGTH_SHORT).show()

                                var users: MutableList<User> = mutableListOf()
                                for (i in 0 until patients.length()) {
                                    val jsonObject = patients.getJSONObject(i)
                                    val name = jsonObject.optString("name")
                                    val image = "http://192.168.0.22/mentalImgs/" +jsonObject.optString("image")
                                    val email = jsonObject.optString("email")
                                    val type = jsonObject.optString("type")

                                    users.add(
                                        User(
                                            id = email,
                                            extraData = mutableMapOf(
                                                "name" to name,
                                                "image" to image,
                                                "type" to type,
                                                "group" to avatar
                                            )
                                        )
                                    )
                                    client.connectGuestUser(
                                        userId  = email,
                                        username = name
                                    ).enqueue { result ->
                                        if (result.isSuccess) {
                                            Log.d("GuestUser", result.toString()+"Group")
                                        } else {
                                            Log.d("GuestUser", result.error().message.toString()+"Group")
                                        }
                                    }
                                }

                                usersAdapter.setData(users)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    } else {
                        Toast.makeText(context, "No response from the server", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(context, "Response not successful $response", Toast.LENGTH_SHORT)
                        .show()
                }

            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<String?>, t: Throwable) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Error occurred during upload", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}













