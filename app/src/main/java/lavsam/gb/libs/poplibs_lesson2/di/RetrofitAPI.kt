package lavsam.gb.libs.poplibs_lesson2.di

import io.reactivex.rxjava3.core.Single
import lavsam.gb.libs.poplibs_lesson2.GithubUser
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("/users")
    fun loadUsers() : Single<List<GithubUser>>

//    @GET
//    fun loadRepositories(@Url url: String?) : Single<List<GithubRepository>>
}