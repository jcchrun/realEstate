package com.jcchrun.real.presentation.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jcchrun.real.commons.app.Constants
import com.jcchrun.real.commons.app.extensions.isPortrait
import com.jcchrun.real.commons.app.ui.AbstractFragment
import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.presentation.R
import com.jcchrun.real.presentation.databinding.FragmentRealEstateListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RealEstateListFragment: AbstractFragment(R.layout.fragment_real_estate_list), RealEstateListAdapter.Listener{

    private val LOG_TAG = RealEstateListFragment::class.java.name
    private val realEstateListViewModel: RealEstateListViewModel by viewModel()
    private var fragmentRealEstateListBinding: FragmentRealEstateListBinding? = null
    private val realEstateListAdapter = RealEstateListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realEstateListViewModel.dataLiveData.observe(this, {
            when (it) {
                is Output.Error -> showError(it)
                is Output.Success -> showData(it.result)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentRealEstateListBinding = FragmentRealEstateListBinding.bind(view)
        val gridLayoutColumns = getGridLayoutColumns(view.context)
        val gridLayoutManager = GridLayoutManager(context, gridLayoutColumns)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        fragmentRealEstateListBinding?.list?.apply {
            adapter = realEstateListAdapter
            layoutManager = gridLayoutManager
            addItemDecoration(
                MarginItemDecoration(
                    gridLayoutColumns,
                    resources.getDimension(R.dimen.marge_16).toInt()
                )
            )
        }
    }

    private fun getGridLayoutColumns(context: Context): Int {
        return if (context.isPortrait) Constants.GRID_LAYOUT_COLUMN_PORTRAIT else Constants.GRID_LAYOUT_COLUMN_LANDSCAPE
    }

    override fun onStart() {
        super.onStart()
        fetchData()
    }

    private fun fetchData() {
        showShimmer()
        realEstateListViewModel.fetchData()
    }

    private fun showShimmer() {
        fragmentRealEstateListBinding?.let {
            it.shimmerRoot.shimmerView.visibility = View.VISIBLE
            it.shimmerRoot.shimmerFrameLayout.startShimmer()
            it.errorRoot.errorImage.visibility = View.GONE
            it.errorRoot.errorText.visibility = View.GONE
            it.errorRoot.errorButton.visibility = View.GONE
            it.list.visibility = View.GONE
        }
    }

    private fun hideShimmer() {
        fragmentRealEstateListBinding?.let {
            it.shimmerRoot.shimmerView.visibility = View.GONE
            it.shimmerRoot.shimmerFrameLayout.stopShimmer()
        }
    }

    private fun showError(outputError: Output.Error) {
        Log.e(LOG_TAG, outputError.errorResponse, outputError.exception)
        hideShimmer()
        if (outputError.errorCode == Output.Error.ERROR_CODE_NO_NETWORK) {
            showErrorLayout(
                R.drawable.ic_connection_off,
                R.string.error_no_connection
            )
        } else {
            showErrorLayout(
                R.drawable.ic_error_unknown,
                R.string.error_unknown
            )
        }
    }

    private fun showErrorLayout(
        errorImageDrawableId: Int,
        errorStringId: Int
    ) {
        fragmentRealEstateListBinding?.let {
            it.errorRoot.errorImage.visibility = View.VISIBLE
            it.errorRoot.errorText.visibility = View.VISIBLE
            it.errorRoot.errorButton.visibility = View.VISIBLE
            it.list.visibility = View.GONE
            it.errorRoot.errorImage.setBackgroundResource(errorImageDrawableId)
            it.errorRoot.errorText.text = getString(errorStringId)
            it.errorRoot.errorButton.setOnClickListener { fetchData() }
        }

    }

    private fun showData(data: List<RealEstateListViewModel.Result>) {
        hideShimmer()

        if (data.isNotEmpty()) {
            fragmentRealEstateListBinding?.let {
                it.errorRoot.errorImage.visibility = View.GONE
                it.errorRoot.errorText.visibility = View.GONE
                it.errorRoot.errorButton.visibility = View.GONE
                it.list.visibility = View.VISIBLE
                realEstateListAdapter.submitList(data)
            }

        } else {
            showErrorLayout(R.drawable.ic_empty, R.string.empty_data)
        }
    }

    override fun onItemClick(realEstateItem: RealEstateItem, imageView: ImageView) {
        ViewCompat.setTransitionName(imageView, getString(R.string.shared_transition_image))
        val extras = FragmentNavigatorExtras(
            imageView to "thumbnail"
        )
        findNavController().navigate(
            RealEstateListFragmentDirections.nextAction(
                realEstateItem
            ), extras
        )
    }
}