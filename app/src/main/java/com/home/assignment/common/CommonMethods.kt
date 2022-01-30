package com.home.assignment.common

import androidx.fragment.app.FragmentActivity
import com.home.assignment.R
import javax.inject.Inject

class CommonMethods @Inject constructor(private val customDialog: CustomDialogPopup) {

    fun showProgressDialog(mActivity: FragmentActivity) {
        if (mActivity == null || customDialog.dialog != null && customDialog.dialog!!.isShowing
        ) return
        progressDialog = CustomDialogPopup()
        progressDialog?.setLayoutId(R.layout.custom_dialog)
        try {
            progressDialog!!.show(mActivity.supportFragmentManager, "")
        } catch (e: IllegalStateException) {
        }
    }

    fun hideProgressDialog() {
        if (progressDialog == null || progressDialog!!.dialog == null || !progressDialog!!.dialog!!
                .isShowing
        ) return
        progressDialog!!.dismissAllowingStateLoss()
        progressDialog = null
    }

    companion object {
        var progressDialog: CustomDialogPopup? = null
    }


}