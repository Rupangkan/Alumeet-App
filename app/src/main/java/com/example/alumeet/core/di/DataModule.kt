package com.example.alumeet.core.di

import android.content.Context
import androidx.room.Room
import com.example.alumeet.core.data.local.room.UserDatabase
import com.example.alumeet.core.data.remote.api.ApiDataSource
import com.example.alumeet.core.data.remote.api.ApiService
import com.example.alumeet.utils.constants.Constants.USER_DB_NAME
import com.example.alumeet.utils.constants.Constants.Urls.Companion.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val requestInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Requested-With", "XMLHttpRequest")
//                .addHeader(
//                    "Authorization", "Bearer " + SharedPreferenceUtil.accessToken
//                )
            return@Interceptor chain.proceed(request.build())
        }

        return OkHttpClient.Builder()
//            .cache(Cache(App.context.cacheDir, 10 * 1024 * 1024))
            .connectTimeout(10, TimeUnit.MINUTES)
            .addNetworkInterceptor(requestInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiDataSource(apiService: ApiService) = ApiDataSource(apiService)

    @Singleton
    @Provides
    fun providesUserDatabase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(applicationContext, UserDatabase::class.java, USER_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesUserDao(userDatabase: UserDatabase) = userDatabase.getUsersDao()

}