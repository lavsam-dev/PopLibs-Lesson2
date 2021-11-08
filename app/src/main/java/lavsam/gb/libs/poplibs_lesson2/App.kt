package lavsam.gb.libs.poplibs_lesson2

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import lavsam.gb.libs.poplibs_lesson2.database.AppDatabase

const val DB_NAME = "github-users.db"

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

//    fun getNavigatorHolder() = cicerone.getNavigatorHolder()

    val router get() = cicerone.router

    val repository = GithubUsersRepo()
    private lateinit var db: AppDatabase

    fun getDB(): AppDatabase {
        return db
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            DB_NAME
        )
            .build()
    }
}