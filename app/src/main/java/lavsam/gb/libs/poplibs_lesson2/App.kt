package lavsam.gb.libs.poplibs_lesson2

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import lavsam.gb.libs.poplibs_lesson2.database.AppDatabase

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

//    fun getNavigatorHolder() = cicerone.getNavigatorHolder()
    val navigatorHolder get() = cicerone.getNavigatorHolder()
//    fun getRouter() = cicerone.router
    val router get() = cicerone.router
    val repository = GithubUsersRepo()
    private lateinit var db: AppDatabase

    fun getDB(): AppDatabase {
        return db
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}