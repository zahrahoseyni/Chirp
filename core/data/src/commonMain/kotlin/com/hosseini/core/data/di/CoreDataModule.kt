package com.hosseini.core.data.di

import com.hosseini.core.data.auth.KtorAuthService
import com.hosseini.core.data.networking.HttpClientFactory
import com.hosseini.core.data.networking.KermitLogger
import com.hosseini.core.domain.auth.AuthService
import com.hosseini.core.domain.logging.ChirpLogger
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformCoreDataModule: Module

val coreDataModule = module {
    includes(platformCoreDataModule)
    single<ChirpLogger> { KermitLogger }
    single {
        HttpClientFactory(get()).create(get())
    }

    singleOf(::KtorAuthService) bind AuthService::class
}