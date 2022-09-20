package com.ironsource.action_button.di.binds

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface AppBinds {

    @Binds
    fun bindContext_to_ApplicationContext(application: Application): Context
}