package com.doachgosum.listsampleapp.di

import javax.inject.Qualifier

class CoroutineQualifiers {
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class DefaultDispatcher

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class IoDispatcher
}