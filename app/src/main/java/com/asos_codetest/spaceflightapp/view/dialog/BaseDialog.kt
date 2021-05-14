package com.asos_codetest.spaceflightapp.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.asos_codetest.spaceflightapp.R

open class BaseDialog : DialogFragment() {
    init {
        enterTransition = Slide().apply {
            slideEdge = Gravity.TOP
        }

        exitTransition = Slide().apply {
            slideEdge = Gravity.BOTTOM
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.run {
                setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.customDialog)
    }
}