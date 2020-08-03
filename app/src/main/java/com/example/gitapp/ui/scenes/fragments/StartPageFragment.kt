package com.example.gitapp.ui.scenes.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.ui.scenes.adapter.UsersAdapter
import com.example.gitapp.ui.scenes.viewmodels.StartPageViewModel

class StartPageFragment : Fragment(), UsersAdapter.OnUserClickListener {

    private val startPageViewModel: StartPageViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )
    }
    private lateinit var navController: NavController
    private lateinit var adapter: UsersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.fragment_start_page, container, false)
        val rvUsersList = rootView.findViewById<RecyclerView>(R.id.rv_users_list)

        adapter = UsersAdapter(this)
        rvUsersList.layoutManager = LinearLayoutManager(rootView.context)
        rvUsersList.adapter = adapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startPageViewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setUsersList(it)
        })
    }

    override fun onClick(userId: Int) {
        val action = StartPageFragmentDirections.navigateToDetailsPage(userId)
        navController.navigate(action)
    }
}