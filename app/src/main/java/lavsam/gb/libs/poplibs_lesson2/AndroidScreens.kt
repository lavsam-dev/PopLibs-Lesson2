package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun githubUser(repository: GithubUser) =
        FragmentScreen { RepositoryFragment.newInstance(repository) }
}
