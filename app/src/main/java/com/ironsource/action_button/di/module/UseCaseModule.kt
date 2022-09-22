package com.ironsource.action_button.di.module

import com.ironsource.domain.repository.ActionCoolDownRepository
import com.ironsource.domain.repository.ActionRepository
import com.ironsource.domain.usecase.GetActionUseCase
import com.ironsource.domain.verifiers.VerifiersFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetActionUseCase(
        actionRepository: ActionRepository,
        verifiersFactory: VerifiersFactory,
        actionCoolDownRepository: ActionCoolDownRepository
    ): GetActionUseCase {
        return GetActionUseCase(
            actionRepository,
            verifiersFactory,
            actionCoolDownRepository
        )
    }
}