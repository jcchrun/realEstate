package com.jcchrun.real.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.presentation.R
import com.jcchrun.real.presentation.databinding.ItemRealEstateBinding

class RealEstateListAdapter(
    private val listener: Listener
) : ListAdapter<RealEstateListViewModel.Result, RealEstateListAdapter.ViewHolder>(
    ContentDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRealEstateBinding =
            ItemRealEstateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            itemRealEstateBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemRealEstateBinding.city.text = item.realEstateItem.city
        holder.itemRealEstateBinding.price.text = "${item.realEstateItem.price.toInt()} €"
        holder.itemRealEstateBinding.image.load(item.realEstateItem.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder_image)
            listener(
                onStart = {},
                onCancel = {},
                onSuccess = { _, metadata -> },
                onError = { _, metadata -> holder.itemRealEstateBinding.image.load(R.drawable.ic_placeholder_image) }
            )
        }
        holder.itemRealEstateBinding.root.setOnClickListener {
            listener.onItemClick(
                item.realEstateItem,
                holder.itemRealEstateBinding.image
            )
        }
        holder.itemRealEstateBinding.favourite.background =
            ContextCompat.getDrawable(holder.itemRealEstateBinding.root.context,
            if (item.favourite) R.drawable.ic_heart_full
            else R.drawable.ic_heart_empty)

        holder.itemRealEstateBinding.roomCount.text = item.realEstateItem.rooms.toString()
        holder.itemRealEstateBinding.bedroomCount.text = item.realEstateItem.bedrooms.toString()
    }

    private class ContentDiffCallback : DiffUtil.ItemCallback<RealEstateListViewModel.Result>() {
        override fun areItemsTheSame(
            oldItem: RealEstateListViewModel.Result,
            newItem: RealEstateListViewModel.Result
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: RealEstateListViewModel.Result,
            newItem: RealEstateListViewModel.Result
        ): Boolean = oldItem == newItem
    }

    class ViewHolder(val itemRealEstateBinding: ItemRealEstateBinding) :
        RecyclerView.ViewHolder(itemRealEstateBinding.root) {

    }

    interface Listener {
        fun onItemClick(realEstateItem: RealEstateItem, imageView: ImageView)
    }
}