package com.hosseini.chirp.di

import com.hosseini.core.data.di.coreDataModule
import com.hosseini.feature.auth.presenation.di.authPresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreDataModule,
            authPresentationModule,
        )
    }
}