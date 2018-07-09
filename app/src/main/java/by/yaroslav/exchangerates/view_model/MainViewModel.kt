package by.yaroslav.exchangerates.view_model

import android.content.Context
import android.databinding.Bindable
import by.yaroslav.exchangerates.BR
import by.yarik.walmart.model.api.Api
import by.yaroslav.exchangerates.R
import by.yaroslav.exchangerates.api.pojo.Currency
import by.yaroslav.exchangerates.api.pojo.ExchangeRates
import by.yaroslav.exchangerates.view_model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(override val context: Context) : BaseViewModel(context) {

    private var exchangingRates = ExchangeRates();

    fun getExchangingRates() {
        if(!isNetworkOnline()) {
            setMessage(R.string.no_connection)
            return
        }
        Api.create().getExchangeRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {isLoadingVisible = true}
                .doOnTerminate {isLoadingVisible = false}
                .subscribe({
                    isMessageVisible = false;
                    exchangingRates = it
                    notifyPropertyChanged(BR.date)
                    notifyPropertyChanged(BR.currencies)
                }, {
                    setMessage(R.string.server_error)
                })
    }

    private fun setMessage(res: Int) {
        isLoadingVisible = false
        isMessageVisible = true;
        message = context.getString(res)
    }

    var currencies: List<Currency> = ArrayList()
        @Bindable get() {
            return exchangingRates.currencies
        }

    var date: String = ""
        @Bindable get() {
            return exchangingRates.date
        }

    var isLoadingVisible: Boolean = false
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingVisible)
        }

    var isMessageVisible: Boolean = false
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.messageVisible)
        }

    var message: String = ""
        @Bindable  get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.message)
        }
}