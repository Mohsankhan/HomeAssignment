package com.home.assignment.splash

import android.app.AlertDialog
import com.home.assignment.common.BaseFragment
import com.home.assignment.viewmodel.ArticleViewModel
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.home.assignment.R
import com.home.assignment.common.ConnectionLiveData
import com.home.assignment.common.DialogAlert
import com.home.assignment.common.findNavController
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : BaseFragment() {
    lateinit var articleViewModel: ArticleViewModel
    private lateinit var networkInfo: ConnectionLiveData

    private val timer = object : CountDownTimer(2000, 100) {
        override fun onFinish() {
            if(networkInfo.isConnected(requireContext())){
                articleViewModel.getLatestArticles()
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }else showNetworkError()


        }

        override fun onTick(millisUntilFinished: Long) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkInfo = ConnectionLiveData(requireContext())

        timer.start()

        articleViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ArticleViewModel::class.java)
        //articleViewModel.getLatestArticles()
    }
    private fun showNetworkError() {
        val dialogAlert = DialogAlert()
        dialogAlert.showErrorDialog(
            requireActivity(),
            resources!!.getString(R.string.error_title),
            resources!!.getString(R.string.networkerror_meesgae),
            resources!!.getString(R.string.btn_ok)
        )
    }
    private fun registerNetworkListner() {
        networkInfo.observe(this, Observer {
            if (it!!) {
                showSnackbar(R.string.internet_available)
            } else showSnackbar(R.string.internet_not_available)
        })
    }
    private fun showSnackbar(msg: Int) {
        val snackbar = Snackbar
            .make(main_container, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}