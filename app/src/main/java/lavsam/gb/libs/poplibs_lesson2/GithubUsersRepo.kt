package lavsam.gb.libs.poplibs_lesson2

import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val GITHUB_URL = "https://api.github.com/"
const val GITHUB_URL_ADDON = "users"

class GithubUsersRepo {

    fun getUsers2(): Observable<List<GithubUser>> = client.LoadUsers().toObservable()

    val client = RetrofitKeeper().api
}

class RetrofitKeeper {
    private val gson = Gson()
    val retrofit = Retrofit.Builder().baseUrl(GITHUB_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(GitHub::class.java)
}

interface GitHub {
    @GET(GITHUB_URL_ADDON)
    fun LoadUsers(): Single<List<GithubUser>>
}