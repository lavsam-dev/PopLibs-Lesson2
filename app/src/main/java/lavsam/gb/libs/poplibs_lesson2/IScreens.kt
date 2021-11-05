package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun githubUser(user: GithubUser): Screen
}