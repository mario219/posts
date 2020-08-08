package com.example.posts.screens.posts.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.ASK_INTERNET
import com.example.posts.R
import com.example.posts.screens.posts.adapters.PostAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_list_fragment.recycler_all
import javax.inject.Inject

class HomeListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeListViewModel> { viewModelFactory }
    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.visibility = View.VISIBLE
        return inflater.inflate(R.layout.home_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.askForInternet.observe(viewLifecycleOwner, Observer(::showNoConnectionToast))
        viewModel.postList.observe(viewLifecycleOwner, Observer(adapter::submitList))
        recycler_all.adapter = adapter
    }

    private fun showNoConnectionToast(internet: Boolean) {
        if (internet.not())
            Toast.makeText(context, ASK_INTERNET, Toast.LENGTH_SHORT).show()
    }
}