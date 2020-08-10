package com.example.posts.screens.posts.adapters

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface SwipeAdapter {
    fun onItemDismiss(position: Int)
}

class SwipeHelper(val swipeAdapter: SwipeAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlags =  ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,
            swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun isItemViewSwipeEnabled() = true

    override fun isLongPressDragEnabled() = false

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        swipeAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

}