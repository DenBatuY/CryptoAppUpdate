package com.batuy.crypytoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batuy.crypytoapp.R
import com.batuy.crypytoapp.databinding.ItemCoinInfoBinding
import com.batuy.crypytoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var onCoinClickListener: OnCoinClickListener? = null

    var coinInfoList: List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CoinInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCoinInfoBinding.bind(view)

        val ivLogCoin = binding.ivLogoCoin
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvLastUpdate = binding.tvLustUpdate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        val symbol = context.resources.getString(R.string.symbol_template)
        val lustUpdate = context.resources.getString(R.string.last_update)
        with(holder) {
            tvSymbols.text = String.format(symbol, coin.fromsymbol, coin.tosymbol)
            tvPrice.text = coin.price.toString()
            tvLastUpdate.text = String.format(lustUpdate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogCoin)

            itemView.setOnClickListener {
                onCoinClickListener?.onClickCoin(coin)
            }
        }
    }


    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    interface OnCoinClickListener {
        fun onClickCoin(coinPriceInfo: CoinPriceInfo)
    }
}