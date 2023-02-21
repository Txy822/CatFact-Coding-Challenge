package com.tes.apps.development.catfact.di

import android.app.Application
import androidx.room.Room
import com.tes.apps.development.catfact.data.local.database.CatFactDatabase
import com.tes.apps.development.catfact.data.remote.api.CatFactApi
import com.tes.apps.development.catfact.data.repository.CatFactRepositoryImpl
import com.tes.apps.development.catfact.domain.repository.CatFactRepository
import com.tes.apps.development.catfact.domain.use_case.CatFactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(CatFactApi.BASE_URL)
        .build()

    @Provides
    fun providesShowsApi(retrofit: Retrofit): CatFactApi = retrofit.create(CatFactApi::class.java)

    @Provides
    fun providesDatabase(app: Application): CatFactDatabase {
        return Room.databaseBuilder(
            app,
            CatFactDatabase::class.java,
            "cat_fact_db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun providesRepository(
        api: CatFactApi,
        db:CatFactDatabase
    ): CatFactRepository {
        return CatFactRepositoryImpl(api,db)
    }

    @Provides
    fun provideCatFactUseCase(repository: CatFactRepository): CatFactUseCase {
        return CatFactUseCase(repository)
    }

    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}