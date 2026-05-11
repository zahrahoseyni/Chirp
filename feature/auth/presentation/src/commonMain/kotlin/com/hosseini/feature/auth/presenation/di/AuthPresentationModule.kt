package com.hosseini.feature.auth.presenation.di

import com.hosseini.feature.auth.presenation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
}