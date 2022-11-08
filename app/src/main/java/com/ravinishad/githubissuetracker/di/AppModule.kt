package com.ravinishad.githubissuetracker.di

import com.ravinishad.githubissuetracker.common.Constant
import com.ravinishad.githubissuetracker.data.remote.GitHubIssueService
import com.ravinishad.githubissuetracker.data.repository.IssueRepositoryImpl
import com.ravinishad.githubissuetracker.domain.repository.IssueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): GitHubIssueService = retrofit.create(GitHubIssueService::class.java)


    @Singleton
    @Provides
    fun provideIssueRepository(gitHubIssueService: GitHubIssueService): IssueRepository =
        IssueRepositoryImpl(gitHubIssueService)


}