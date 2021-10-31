package lavsam.gb.libs.poplibs_lesson2

import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.gson.Gson
import com.google.gson.JsonParser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.*
import java.io.IOException
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class GithubUsersRepo {

    private val repositories =
        (0..100).map { GithubUser("login $it") }

    private val bs = BehaviorSubject.create<List<GithubUser>>()

    fun getUsers2(): Observable<List<GithubUser>> = bs

    fun loadNextChunkWithUsers() {
        client
            .newCall(Request.Builder().url("https://api.github.com/users").build())
            .enqueue(object  : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    println("Fail")
                }

                override fun onResponse(call: Call, response: Response) {
                    val s = response.body?.string()
                    val list = JsonParser.parseString(s).asJsonArray.toList()
                        .map { it.asJsonObject }
                        .map { it["login"]}
                        .map { GithubUser(it.asString)}
                    bs.onNext(list)
                }
            })
    }

    //    fun getUsers() = repositories
    fun getUsers(): List<GithubUser> {

        return repositories
    }

    val client = OkHttpClient.Builder().build()
    val gson = Gson()
}

class RetrofitKeeper {
    private val gson = Gson()
    val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(GitHub::class.java)
}

interface GitHub {
    @GET("users")
    fun LoadUsers(): Single<List<GithubUser>>
}