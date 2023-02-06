package com.batuy.crypytoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.batuy.crypytoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    lateinit var binding: ActivityCoinDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel.getDetailInfo(fromSymbol!!).observe(this) {
            with(binding) {
                Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoinDetail)
                tvPriceDetail.text = String.format(getString(R.string.price),it.price.toString())
                tvMinForDayDetail.text = String.format(getString(R.string.lov_price), it.lowday.toString())
                tvMaxForDayDetail.text = String.format(getString(R.string.max_price), it.highday.toString())
                tvRenewDetail.text = String.format(getString(R.string.last_update), it.getFormattedTime())
                tvLastMarketDetail.text = String.format(getString(R.string.last_market), it.lastmarket)
                tvSymbolDetail.text= String.format(it.fromsymbol,R.string.symbol_template,it.tosymbol)
            }
            Log.d("test", it.toString())
        }

    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }


}