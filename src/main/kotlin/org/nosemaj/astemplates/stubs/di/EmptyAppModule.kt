package org.nosemaj.astemplates.stubs.di


import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyAppModule(
    packageName: String,
    itemName: String,
) = """
package ${escapeKotlinIdentifier(packageName)}.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ${escapeKotlinIdentifier(packageName)}.data.net.${itemName.capitalize()}Service

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provides${itemName.capitalize()}Service(): ${itemName.capitalize()}Service {
        return ${itemName.capitalize()}Service.create()
    }

    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application
    }
}
"""
