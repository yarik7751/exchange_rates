package by.yaroslav.exchangerates.view.adapter

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.yaroslav.exchangerates.R
import by.yaroslav.exchangerates.api.pojo.Currency
import com.wonshinhyo.dragrecyclerview.DragAdapter
import com.wonshinhyo.dragrecyclerview.DragHolder
import by.yaroslav.exchangerates.databinding.ItemCurrencyBinding
import com.wonshinhyo.dragrecyclerview.DragRecyclerView

class CurrenciesAdapter(context: Context, var currencies: ArrayList<Currency>) : DragAdapter(context, currencies) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding: ItemCurrencyBinding = DataBindingUtil.inflate(inflater, R.layout.item_currency, parent, false)
        return CurrenciesViewHolder(binding)
    }

    override fun onBindViewHolder(hol: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(hol, position)
        (hol as CurrenciesViewHolder).bind(currencies[position])
    }

    override fun getItemId(position: Int): Long {
        return currencies.size.toLong()
    }

    fun update(newCurrencies: List<Currency>) {
        currencies.clear()
        currencies.addAll(newCurrencies)
        notifyDataSetChanged()
    }

    companion object {
        @BindingAdapter("android:setAdapter")
        @JvmStatic
        fun setAdapter(dragRecyclerView: DragRecyclerView, currencies: List<Currency>) {
            (dragRecyclerView.adapter as CurrenciesAdapter).update(currencies)
        }
    }

    class CurrenciesViewHolder(var binding: ItemCurrencyBinding) : DragHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currency = currency
            binding.executePendingBindings()
        }
    }
}