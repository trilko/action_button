package com.ironsource.action_button.di.module

import com.ironsource.domain.repository.ActionRepository
import com.ironsource.domain.usecase.GetActionUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetActionUseCase(
        actionRepository: ActionRepository
    ): GetActionUseCase {
        return GetActionUseCase(actionRepository)
    }
}