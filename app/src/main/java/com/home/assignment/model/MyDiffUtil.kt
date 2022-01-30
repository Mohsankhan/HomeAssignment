package com.home.assignment.model

import androidx.recyclerview.widget.DiffUtil

//DiffUtil is a utility class that can calculate the difference between two lists and
// output a list of update operations that converts the first list into the second one.

//It can be used to calculate updates for a RecyclerView Adapter.

open class MyDiffUtil(
    private val oldResults: List<LatestArticle.Result>,
    private val newResults: List<LatestArticle.Result>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldResults.size
    }

    override fun getNewListSize(): Int {
        return newResults.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // In the real world you need to compare something unique like id
        return oldResults[oldItemPosition] == newResults[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // This is called if areItemsTheSame() == true;
        return oldResults[oldItemPosition] == newResults[newItemPosition]
    }
}
//end