package lavsam.gb.libs.poplibs_lesson2

import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class RepositoriesScreen() : SupportAppScreen() {
    }

    class RepositoryScreen(val repository: GithubRepository) : SupportAppScreen() {
    }
}