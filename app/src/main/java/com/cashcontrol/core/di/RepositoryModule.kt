package com.cashcontrol.core.di

import com.cashcontrol.domain.repositories.categories.CategoriesRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider

val RepositoryModule = DI.Module("Repository") {
    bindProvider { CategoriesRepositoryImpl() }
}