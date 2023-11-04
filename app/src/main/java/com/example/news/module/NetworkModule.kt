package com.example.news.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun providesRetrofit(): Retrofit.Builder {
//        val gson = GsonBuilder().setLenient().create()
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(MoshiConverterFactory.create().asLenient())
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .addInterceptor(authInterceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun providesProfileAPI(
//        retrofitBuilder: Retrofit.Builder,
//        okHttpClient: OkHttpClient
//    ): ProfileApi {
//        return retrofitBuilder.client(okHttpClient).build()
//            .create(ProfileApi::class.java)
//    }
}