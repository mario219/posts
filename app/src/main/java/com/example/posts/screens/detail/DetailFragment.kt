package com.example.posts.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.posts.R
import com.example.posts.databinding.PostDetailFragmentBinding
import com.example.posts.screens.detail.adapter.CommentsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }
    private lateinit var binding: PostDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val infoArgs: DetailFragmentArgs by navArgs()
        viewModel.init(infoArgs.post, infoArgs.userId)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.visibility = View.GONE
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
        viewModel.info.observe(viewLifecycleOwner, Observer {
            binding.post = it.post
            binding.user = it.user
            it.comments?.run {
                binding.recyclerComments.adapter = CommentsAdapter(this)
                binding.recyclerComments.addItemDecoration(DividerItemDecoration(context, VERTICAL))
            }
            binding.executePendingBindings()
        })
    }
}