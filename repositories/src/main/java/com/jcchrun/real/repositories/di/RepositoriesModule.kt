package com.jcchrun.real.repositories.di

import com.jcchrun.real.repositories.converters.RealEstateItemConverter
import com.jcchrun.real.repositories.converters.RealEstateListConverter
import com.jcchrun.real.repositories.repo.FavouriteRepository
import com.jcchrun.real.repositories.repo.FavouriteRepositoryImpl
import com.jcchrun.real.repositories.repo.RealEstateRepository
import com.jcchrun.real.repositories.repo.RealEstateRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {

    // Repositories
    factory { RealEstateRepositoryImpl(get(), get(), get()) as RealEstateRepository }
    factory { FavouriteRepositoryImpl(get()) as FavouriteRepository }

    // Converters
    factory { RealEstateItemConverter() }
    factory { RealEstateListConverter(get()) }
}