package lavsam.gb.libs.poplibs_lesson2

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import lavsam.gb.libs.poplibs_lesson2.database.User
import lavsam.gb.libs.poplibs_lesson2.database.UserDao

const val GITHUB_URL = "https://api.github.com/"
const val GITHUB_URL_ADDON = "users"

class GithubUsersRepo {

    private val db: UserDao by lazy { App.instance.getDB().userDao() }
    private val client = RetrofitKeeper().api
    private val bs = BehaviorSubject.create<Unit>()

    fun getUsers2(): Observable<List<GithubUser>> = client.loadUsers().toObservable()


    fun subscribeOnGithubUsersData(): Observable<List<GithubUser>> {
        return bs
            .observeOn(Schedulers.io())
            .switchMap {
                Observable.combineLatest(
                    Observable.just(db.getAll().mapToUser()),
                    client.loadUsers().onErrorReturn { listOf() }.toObservable(),
                    { fromDatabase, fromNetwork ->
                        if (fromNetwork.isEmpty()) {
                            return@combineLatest fromDatabase
                        } else {
                            db.deleteAll()
                            saveUsersToDB(fromNetwork)
                            fromNetwork
                        }
                    }
                )
            }
    }

    fun loadUserData() {
        bs.onNext(Unit)
    }

    private fun List<User>.mapToUser(): List<GithubUser> {
        return this.map { GithubUser(login = it.firstName.orEmpty(), avatar_url = it.avatarUrl.orEmpty(), id = it.uid) }
    }

    private fun saveUsersToDB(list: List<GithubUser>) {
        db.insertAll(
            *(list.map {
                User(uid = it.id, firstName = it.login, avatarUrl = it.avatar_url)
            }.toTypedArray())
        )
    }
}

