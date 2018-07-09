package by.yaroslav.exchangerates.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import by.yaroslav.exchangerates.R
import by.yaroslav.exchangerates.api.pojo.Currency
import by.yaroslav.exchangerates.databinding.ActivityMainBinding
import by.yaroslav.exchangerates.view.adapter.CurrenciesAdapter
import by.yaroslav.exchangerates.view_model.MainViewModel
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    var mainVM: MainViewModel  = MainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.mainVM = mainVM
        initRecyclerView()
        mainVM.getExchangingRates()
        binding.executePendingBindings()
    }

    private fun initRecyclerView() {
        binding.drvCurrencies.layoutManager = LinearLayoutManager(this)
        var adapter = CurrenciesAdapter(this, mainVM.currencies as ArrayList<Currency>)
        binding.drvCurrencies.adapter = adapter
        adapter.setHandleDragEnabled(true)
        adapter.setLongPressDragEnabled(true)
        adapter.setSwipeEnabled(false)

        binding.drvCurrencies.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .color(R.color.black)
                .size(1)
                .build())
    }
}

