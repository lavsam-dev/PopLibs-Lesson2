package lavsam.gb.libs.poplibs_lesson2

class GithubUsersRepo {

    private val repositories =
        (0..100).map { GithubUser("login $it",) }

//    fun getUsers() = repositories
    fun getUsers(): List<GithubUser> {
        return repositories
    }
}