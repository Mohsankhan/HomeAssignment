package com.home.assignment.common

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.home.assignment.R
import javax.inject.Inject

class CustomDialogPopup @Inject constructor() : DialogFragment() {
    protected var mActivity: Activity? = null
    private var layoutId = 0

    fun setLayoutId(layoutId: Int) {
        this.layoutId = layoutId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        run { setStyle(STYLE_NO_TITLE, R.style.progress_dialog) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(layoutId, container, false)
        initViews(v)
        isCancelable = false
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = if (context is Activity) context else null
    }

    fun initViews(v: View?) {
        dialog!!.setCanceledOnTouchOutside(false)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.setCancelable(false)
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}