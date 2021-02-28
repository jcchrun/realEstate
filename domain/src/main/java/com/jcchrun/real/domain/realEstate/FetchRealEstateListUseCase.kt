package com.jcchrun.real.domain.realEstate

import com.jcchrun.real.repositories.repo.RealEstateRepository

class FetchRealEstateListUseCase(
    private val realEstateRepository: RealEstateRepository
) {

    suspend fun execute() = realEstateRepository.getRealEstateList()
}