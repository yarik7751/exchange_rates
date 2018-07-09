package by.yarik.walmart.model.api

import by.yaroslav.exchangerates.api.pojo.ExchangeRates
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Api {

    @GET("/Services/XmlExRates.aspx")
    fun getExchangeRates(): Observable<ExchangeRates>

    companion object {
        private val REQUEST_TIMEOUT = 60L
        private val BASE_URL = "http://www.nbrb.by"

        fun create(): Api {
            var retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
                    .client(initOkHttpClient())
                    .build();

            return retrofit.create(Api::class.java)
        }

        fun initOkHttpClient(): OkHttpClient {
            var okHttpClientBuilder = OkHttpClient.Builder()
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

            var interceptor = HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.interceptors().add(interceptor)

            return okHttpClientBuilder.build()
        }
    }
}