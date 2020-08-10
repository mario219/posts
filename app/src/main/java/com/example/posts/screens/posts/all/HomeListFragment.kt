package com.example.posts.screens.posts.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.domain.ASK_INTERNET
import com.example.domain.CONTENT_IS_RELOADING
import com.example.posts.R
import com.example.posts.screens.posts.adapters.PostAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_list_fragment.recycler_all
import javax.inject.Inject

class HomeListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeListViewModel> { viewModelFactory }
    private val adapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_fetch -> {
                showReloadToast()
                viewModel.fetchDataAgain()
                true
            }
            R.id.menu_fav -> {
                viewModel.postList.removeObservers(viewLifecycleOwner)
                recycler_all.adapter = adapter
                recycler_all.addItemDecoration(DividerItemDecoration(context, VERTICAL))
                viewModel.favList.observe(viewLifecycleOwner, Observer(
                    adapter::submitList
                ))
                true
            }
            R.id.menu_all_posts -> {
                viewModel.favList.removeObservers(viewLifecycleOwner)
                recycler_all.adapter = adapter
                recycler_all.addItemDecoration(DividerItemDecoration(context, VERTICAL))
                viewModel.postList.observe(viewLifecycleOwner, Observer(
                    adapter::submitList
                ))
                true
            }
            else -> false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.postList.observe(viewLifecycleOwner, Observer(
            adapter::submitList
        ))
        recycler_all.adapter = adapter
        recycler_all.addItemDecoration(DividerItemDecoration(context, VERTICAL))
    }

    private fun showReloadToast() {
        Toast.makeText(context, CONTENT_IS_RELOADING, Toast.LENGTH_SHORT).show()
    }
}