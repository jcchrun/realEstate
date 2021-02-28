package com.jcchrun.real.domain.di

import com.jcchrun.real.domain.favourite.DeleteFavouriteUseCase
import com.jcchrun.real.domain.favourite.HasFavouriteUseCase
import com.jcchrun.real.domain.favourite.InsertFavouriteUseCase
import com.jcchrun.real.domain.realEstate.FetchRealEstateListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { FetchRealEstateListUseCase(get()) }

    factory { HasFavouriteUseCase(get()) }
    factory { InsertFavouriteUseCase(get()) }
    factory { DeleteFavouriteUseCase(get()) }
}