package com.example.posts.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.posts.R
import com.example.posts.databinding.PostDetailFragmentBinding
import com.example.posts.screens.detail.adapter.CommentsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: PostDetailFragmentBinding
    private lateinit var starredItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val infoArgs: DetailFragmentArgs by navArgs()
        viewModel.init(infoArgs.post, infoArgs.userId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu_bar, menu)
        starredItem = menu.findItem(R.id.menu_star)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_star -> {
                viewModel.onFavClicked()
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
        binding = PostDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        handleOnFavClicked()
    }

    override fun onStop() {
        super.onStop()
        viewModel.info.removeObservers(viewLifecycleOwner)
        viewModel.fav.removeObservers(viewLifecycleOwner)
    }

    private fun loadData() {
        viewModel.info.observe(viewLifecycleOwner, Observer {
            it.post?.favorite?.run {
                if (this) {
                    if (::starredItem.isInitialized)
                        starredItem.icon = context?.resources?.getDrawable(R.drawable.ic_menu_star_)
                }
            }
            binding.post = it.post
            binding.user = it.user
            it.comments?.run {
                binding.recyclerComments.adapter = CommentsAdapter(this)
                binding.recyclerComments.addItemDecoration(DividerItemDecoration(context, VERTICAL))
            }
            binding.executePendingBindings()
        })
    }

    private fun handleOnFavClicked() {
        viewModel.fav.observe(viewLifecycleOwner, Observer { fav ->
            if (fav) {
                starredItem.icon = context?.resources?.getDrawable(R.drawable.ic_menu_star_)
            } else {
                starredItem.icon = context?.resources?.getDrawable(R.drawable.ic_menu_star_border)
            }
        })
    }
}