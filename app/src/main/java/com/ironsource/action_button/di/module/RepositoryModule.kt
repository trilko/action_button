package com.ironsource.action_button.di.module

import android.content.Context
import com.ironsource.data.ActionCoolDownRepositoryImpl
import com.ironsource.data.ActionsDataSource
import com.ironsource.data.cool_down.ActionsCoolDown
import com.ironsource.domain.repository.ActionCoolDownRepository
import com.ironsource.domain.repository.ActionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideActionRepository(context: Context): ActionRepository {
        return ActionsDataSource(context)
    }

    @Provides
    fun provideActionCoolDownRepository(
        actionsCoolDown: ActionsCoolDown
    ): ActionCoolDownRepository {
        return ActionCoolDownRepositoryImpl(actionsCoolDown)
    }
}