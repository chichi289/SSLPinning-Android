package com.chichi289.sslpining.network

import com.chichi289.sslpining.network.models.GithubUser
import com.chichi289.sslpining.network.models.Todo
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

// Base Urls
private const val BASE_URL_GITHUB = "https://api.github.com/"
private const val BASE_URL_JSON_PLACE_HOLDER = "https://jsonplaceholder.typicode.com/"

// Certificate Pinner

private val certificatePinnerGithub = CertificatePinner.Builder()
    .add("api.github.com", "sha256/Jg78dOE+fydIGk19swWwiypUSR6HWZybfnJG/8G7pyM=")
    .build()

private val certificatePinnerJsonPlaceHolder = CertificatePinner.Builder()
    .add("jsonplaceholder.typicode.com", "sha256/47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=")
    .build()

// Okhttp Client
private val okHttpClientGithub = OkHttpClient.Builder()
    .certificatePinner(certificatePinnerGithub)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()

private val okHttpClientJsonPlaceHolder = OkHttpClient.Builder()
    .certificatePinner(certificatePinnerJsonPlaceHolder)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()

// Retrofit
private val retrofitGithub = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_GITHUB)
    .client(okHttpClientGithub)
    .build()

private val retrofitJsonPlaceHolder = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_JSON_PLACE_HOLDER)
    .client(okHttpClientJsonPlaceHolder)
    .build()

// Service
interface GithubApiService {
    @GET("/users/{profile}")
    fun getUserData(@Path("profile") profile: String): Call<GithubUser>
}

interface JsonPlaceHolderApiService {
    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Call<Todo>
}

// Api
object GithubApi {
    val githubApiService: GithubApiService by lazy {
        retrofitGithub.create(GithubApiService::class.java)
    }
}

object JsonPlaceHolderApi {
    val jsonPlaceHolderApi: JsonPlaceHolderApiService by lazy {
        retrofitJsonPlaceHolder.create(JsonPlaceHolderApiService::class.java)
    }
}