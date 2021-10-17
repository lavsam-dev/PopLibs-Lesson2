package lavsam.gb.libs.poplibs_lesson2

class GithubRepositoriesRepo {

    private val repositories =
        (0..100).map { GithubRepository("login $it",) }

    fun getRepos() = repositories
}