package com.hosseini.feature.auth.presenation.di

import com.hosseini.feature.auth.presenation.register.RegisterViewModel
import com.hosseini.feature.auth.presenation.register_success.RegisterSuccessViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
}