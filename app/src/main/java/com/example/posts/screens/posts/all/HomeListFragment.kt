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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.domain.CONTENT_IS_RELOADING
import com.example.posts.R
import com.example.posts.screens.posts.adapters.PostAdapter
import com.example.posts.screens.posts.adapters.SwipeHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_list_fragment.recycler_all

@AndroidEntryPoint
class HomeListFragment : Fragment() {

    private val viewModel: HomeListViewModel by viewModels()
    private val adapter = PostAdapter { viewModel.removePostOnSwipe(it) }
    private val swipeHelper = SwipeHelper(adapter)
    private val itemTouchHelper = ItemTouchHelper(swipeHelper)

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
                initFavList()
                true
            }
            R.id.menu_all_posts -> {
                initRecyclerWithAllData()
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
        initRecyclerWithAllData()
        recycler_all.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        itemTouchHelper.attachToRecyclerView(recycler_all)
    }

    private fun showReloadToast() {
        Toast.makeText(context, CONTENT_IS_RELOADING, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerWithAllData() {
        viewModel.postList.observe(
            viewLifecycleOwner, Observer(
                adapter::submitList
            )
        )
        recycler_all.adapter = adapter
    }

    private fun initFavList() {
        viewModel.postList.removeObservers(viewLifecycleOwner)
        viewModel.favList.observe(
            viewLifecycleOwner, Observer(
                adapter::submitList
            )
        )
        recycler_all.adapter = adapter
    }
}