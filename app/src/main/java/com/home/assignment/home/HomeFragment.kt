package com.home.assignment.home

import com.home.assignment.common.BaseFragment
import com.home.assignment.viewmodel.ArticleViewModel
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.home.assignment.R
import com.home.assignment.adapter.LatestArticleItemAdapter
import com.home.assignment.common.Constants.Companion.BUNDLE_KEY_DETAIL_SCREEN
import com.home.assignment.common.findNavController
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(), LatestArticleItemAdapter.ArticleItemClickListener {
    lateinit var articleViewModel: ArticleViewModel
    lateinit var latestArticleItemAdapter: LatestArticleItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        latestArticleItemAdapter = LatestArticleItemAdapter(context = requireContext(),listener = this )

        article_list?.adapter = latestArticleItemAdapter

        articleViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ArticleViewModel::class.java)

        articleViewModel.latestArticleLiveData?.observe(requireActivity(), Observer {
            if(it.results.isNullOrEmpty()){
                Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show()
            }
                it.results?.let { it1 ->
                    context?.let {
                        no_data_label?.visibility = View.GONE
                        latestArticleItemAdapter.updateArticleList(it1) }
                }

        })

    }

    override fun onArticleClick(url: String) {
        val bundle =Bundle()
        bundle.putString(BUNDLE_KEY_DETAIL_SCREEN,url)
        findNavController().navigate(R.id.detailScreenFragment,bundle)
    }
}