package club.home.assignment.network

import android.content.Context
import com.home.assignment.BuildConfig
import com.home.assignment.common.Constants.Companion.BASE_URL

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(context: Context): Retrofit? {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        httpClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder().addHeader("Connection", "close")
            requestBuilder.addHeader("Accept", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        })

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}
