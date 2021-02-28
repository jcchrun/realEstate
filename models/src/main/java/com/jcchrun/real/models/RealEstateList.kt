package com.jcchrun.real.models

data class RealEstateList(
    val items: List<RealEstateItem>,
    val count: Int
) {
}