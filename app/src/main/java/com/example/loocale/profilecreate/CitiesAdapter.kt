package com.example.loocale.profilecreate

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.example.loocale.BaseAdapter
import com.example.loocale.BaseViewHolder
import com.example.loocale.R
import com.example.loocale.databinding.ItemConnectBinding


class CitiesAdapter (
    context: Context,
    items: List<ConnectResponse.ConnectId>?
): BaseAdapter<ConnectResponse.ConnectId, CitiesAdapter.ViewHolder>(context, items) {

    inner class ViewHolder(private val binding: ItemConnectBinding): BaseViewHolder<ConnectResponse.ConnectId>(binding.root){

        fun bind(item: ConnectResponse.ConnectId) {
            binding.apply {
                tvTitle.text = item.title
                ivBackground.let {
                    Glide.with(it)
                        .load(item.background)
                        .into(it)
                }


            }
        }
    }

    override fun getLayoutId(): Int = R.layout.item_connect

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(ItemConnectBinding.bind(view))

    override fun bindView(holder: ViewHolder, item: ConnectResponse.ConnectId, position: Int) {
        holder.bind(item)
    }
}