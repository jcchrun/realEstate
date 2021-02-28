package com.jcchrun.real.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.transition.TransitionInflater
import coil.load
import com.jcchrun.real.commons.app.ui.AbstractFragment
import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.presentation.R
import com.jcchrun.real.presentation.databinding.FragmentRealEstateDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RealEstateDetailFragment : AbstractFragment(R.layout.fragment_real_estate_detail) {

    private val realEstateDetailViewModel: RealEstateDetailViewModel by viewModel()
    private var favouriteLastValue = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentRealEstateDetailBinding = FragmentRealEstateDetailBinding.bind(view)

        arguments?.let {
            val safeArgs = RealEstateDetailFragmentArgs.fromBundle(it)
            val realEstateItem = safeArgs.realEstateItem

            fragmentRealEstateDetailBinding.image.load(realEstateItem.imageUrl) {
                listener(
                    onStart = {},
                    onCancel = {},
                    onSuccess = { _, metadata -> startPostponedEnterTransition() },
                    onError = { _, metadata -> startPostponedEnterTransition() }
                )
            }

            setupFavouriteButton(fragmentRealEstateDetailBinding, realEstateItem)
            setupUi(fragmentRealEstateDetailBinding, realEstateItem)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    private fun setupFavouriteButton(
        fragmentRealEstateDetailBinding: FragmentRealEstateDetailBinding,
        realEstateItem: RealEstateItem
    ) {
        realEstateDetailViewModel.favouriteLiveData.observe(this, {
            favouriteLastValue = it
            fragmentRealEstateDetailBinding.favourite.background =
                ContextCompat.getDrawable(
                    fragmentRealEstateDetailBinding.root.context,
                    if (it) R.drawable.ic_heart_full
                    else R.drawable.ic_heart_empty
                )
        })

        fragmentRealEstateDetailBinding.favourite.setOnClickListener {
            if (favouriteLastValue) {
                realEstateDetailViewModel.deleteFavourite(realEstateItem)
            }
            else {
                realEstateDetailViewModel.saveFavourite(realEstateItem)
            }
        }

        realEstateDetailViewModel.getFavourite(realEstateItem)
    }

    private fun setupUi(
        fragmentRealEstateDetailBinding: FragmentRealEstateDetailBinding,
        realEstateItem: RealEstateItem
    ) {
        fragmentRealEstateDetailBinding.city.text = realEstateItem.city
        fragmentRealEstateDetailBinding.price.text = "${realEstateItem.price.toInt()} €"
        fragmentRealEstateDetailBinding.roomCount.text = realEstateItem.rooms.toString()
        fragmentRealEstateDetailBinding.bedroomCount.text = realEstateItem.bedrooms.toString()
        fragmentRealEstateDetailBinding.area.text = "${realEstateItem.area.toInt()} m²"
        fragmentRealEstateDetailBinding.propertyType.text = realEstateItem.propertyType
        fragmentRealEstateDetailBinding.professional.text = realEstateItem.professional
    }
}