package com.ironsource.action_button.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ironsource.action_button.di.qualifier.CoolDownVerifier
import com.ironsource.action_button.di.qualifier.EnabledVerifier
import com.ironsource.action_button.di.qualifier.InternetVerifier
import com.ironsource.action_button.di.qualifier.ValidDaysVerifier
import com.ironsource.data.cool_down.ActionsCoolDown
import com.ironsource.data.cool_down.ActionsCoolDownPrefs
import com.ironsource.data.current_day.CurrentDay
import com.ironsource.data.current_day.CurrentDayAndroid
import com.ironsource.data.internet_availability.InternetAvailabilityAndroid
import com.ironsource.data.internet_availability.InternetAvailable
import com.ironsource.domain.verifiers.Verifier
import com.ironsource.domain.verifiers.VerifiersFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelpersModule {

    @Singleton
    @Provides
    fun provideVerifiersFactory(
        @CoolDownVerifier coolDownVerifier: Verifier,
        @EnabledVerifier enabledVerifier: Verifier,
        @InternetVerifier internetVerifier: Verifier,
        @ValidDaysVerifier validDaysVerifier: Verifier
    ): VerifiersFactory {
        val verifiers: List<Verifier> = listOf(
            coolDownVerifier,
            enabledVerifier,
            internetVerifier,
            validDaysVerifier
        )
        return VerifiersFactory.BaseVerifiersFactory(verifiers)
    }

    @Provides
    @Singleton
    @CoolDownVerifier
    fun provideCoolDownVerifier(
        actionsCoolDown: ActionsCoolDown
    ): Verifier {
        return com.ironsource.data.verifiers.CoolDownVerifier(actionsCoolDown)
    }

    @Provides
    @Singleton
    @EnabledVerifier
    fun provideEnabledVerifier(): Verifier {
        return com.ironsource.data.verifiers.EnabledVerifier()
    }

    @Provides
    @Singleton
    @InternetVerifier
    fun provideInternetVerifier(
        internetAvailable: InternetAvailable
    ): Verifier {
        return com.ironsource.data.verifiers.InternetVerifier(internetAvailable)
    }

    @Provides
    @Singleton
    @ValidDaysVerifier
    fun provideValidDaysVerifier(
        currentDay: CurrentDay
    ): Verifier {
        return com.ironsource.data.verifiers.ValidDaysVerifier(currentDay)
    }

    @Provides
    @Singleton
    fun provideActionsCoolDown(
        sharedPreferences: SharedPreferences
    ): ActionsCoolDown {
        return ActionsCoolDownPrefs(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        context: Context
    ): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideInternetAvailable(
        context: Context
    ): InternetAvailable {
        return InternetAvailabilityAndroid(context)
    }

    @Provides
    @Singleton
    fun provideCurrentDay(): CurrentDay {
        return CurrentDayAndroid()
    }
}