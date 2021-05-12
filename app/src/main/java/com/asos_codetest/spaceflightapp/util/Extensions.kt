package com.asos_codetest.spaceflightapp.util

import androidx.fragment.app.Fragment
import com.asos_codetest.spaceflightapp.view.dialog.FilterDialog

fun Fragment.showFilterDialog() : FilterDialog {
    return FilterDialog.instance.also { dialog->
        parentFragmentManager.let { manager->
            dialog.show(manager,FilterDialog::class.java.name)
        }
    }
}