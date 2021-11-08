package lavsam.gb.libs.poplibs_lesson2.di

import dagger.Module
import dagger.Provides
import lavsam.gb.libs.poplibs_lesson2.App

@Module
class AppModule (val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}