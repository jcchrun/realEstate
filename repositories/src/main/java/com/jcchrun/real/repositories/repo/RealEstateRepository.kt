package com.jcchrun.real.repositories.repo

import com.jcchrun.real.commons.app.helpers.NetworkHelper
import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateList
import com.jcchrun.real.remote.api.ApiServices
import com.jcchrun.real.repositories.converters.RealEstateListConverter

interface RealEstateRepository {

    suspend fun getRealEstateList(): Output<RealEstateList>
}

class RealEstateRepositoryImpl constructor(
    private val apiServices: ApiServices,
    private val converter: RealEstateListConverter,
    private val networkHelper: NetworkHelper
): RealEstateRepository {

    override suspend fun getRealEstateList(): Output<RealEstateList> {

        return try {
            val realEstateListDto = apiServices.fetchRealEstateList()
            val realEstateList = converter.convertDtoToModel(realEstateListDto)
            Output.Success(realEstateList)
        } catch (e: Exception) {
            if (!networkHelper.isConnected()) {
                Output.Error(
                    Output.Error.ERROR_CODE_NO_NETWORK,
                    "",
                    e
                )
            } else {
                Output.Error(
                    Output.Error.ERROR_CODE_UNKNOWN,
                    "",
                    e
                )
            }
        }
    }
}