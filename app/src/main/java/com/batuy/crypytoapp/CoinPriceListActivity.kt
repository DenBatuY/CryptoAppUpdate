package com.batuy.crypytoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.batuy.crypytoapp.adapter.CoinInfoAdapter
import com.batuy.crypytoapp.databinding.ActivityCoinPriceListBinding
import com.batuy.crypytoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter

        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onClickCoin(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromsymbol
                )
                startActivity(intent)
            }
        }


    }


}