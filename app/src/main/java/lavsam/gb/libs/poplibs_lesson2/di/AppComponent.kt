package lavsam.gb.libs.poplibs_lesson2.di

import dagger.Component
import lavsam.gb.libs.poplibs_lesson2.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
//    fun inject(githubUsersRepo: GithubUsersRepo)
    fun inject(repositoryPresenter: RepositoryPresenter)
}
