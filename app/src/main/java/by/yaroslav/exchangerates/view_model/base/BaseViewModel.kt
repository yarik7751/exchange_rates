package by.yaroslav.exchangerates.view_model.base

import android.content.Context
import android.databinding.BaseObservable
import by.yaroslav.exchangerates.util.AndroidUtils

open class BaseViewModel(open val context: Context) : BaseObservable() {

    protected fun isNetworkOnline(): Boolean {
        return AndroidUtils.isNetworkOnline(context)
    }
}