package com.home.assignment.home

import com.home.assignment.common.BaseFragment
import com.home.assignment.viewmodel.ArticleViewModel
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.webkit.WebSettings
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.home.assignment.R
import com.home.assignment.adapter.LatestArticleItemAdapter
import com.home.assignment.common.Constants.Companion.BUNDLE_KEY_DETAIL_SCREEN
import com.home.assignment.common.findNavController
import kotlinx.android.synthetic.main.fragment_detail_screen.*
import kotlinx.android.synthetic.main.fragment_home.*


class DetailScreenFragment : BaseFragment() {
    lateinit var articleViewModel: ArticleViewModel
    lateinit var latestArticleItemAdapter: LatestArticleItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(BUNDLE_KEY_DETAIL_SCREEN)
            val webSettings: WebSettings = web_view.settings
            webSettings.javaScriptEnabled = true
        url?.let { web_view.loadUrl(it) }
        left_arrow.setOnClickListener({
            findNavController().popBackStack()
        })
        }


}