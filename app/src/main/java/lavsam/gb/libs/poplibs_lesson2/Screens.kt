package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens : IScreens {

    override fun RepositoriesScreen() = FragmentScreen { RepositoriesFragment.newInstance() }

    override fun RepositoryScreen(repository: GithubUser) =
        FragmentScreen { RepositoryFragment.newInstance(repository) }
}

interface IScreens {
    fun RepositoriesScreen(): Screen
    fun RepositoryScreen(user: GithubUser): Screen
}