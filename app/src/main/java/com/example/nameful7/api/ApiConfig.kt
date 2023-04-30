package com.example.nameful7.api



import com.example.nameful7.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(baseUrl: String = BuildConfig.BASE_URL): ApiService {
            val interceptor =
                if(BuildConfig.DEBUG)
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                else
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
            return  retrofit.create(ApiService::class.java)
        }
    }
}