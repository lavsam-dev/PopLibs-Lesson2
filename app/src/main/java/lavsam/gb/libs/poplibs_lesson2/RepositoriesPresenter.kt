package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class RepositoriesPresenter(
    val repositoriesRepo: GithubUsersRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<RepositoriesView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubUser>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setTitle(repository.login)
        }
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()

        repositoryListPresenter.itemClickListener = { itemView ->
            val repository = repositoryListPresenter.repositories[itemView.pos]

            router.navigateTo(screens.RepositoryScreen(repository))
            //Практическое задание
//            router.replaceScreen(Screens.RepositoryScreen(repository))
        }
    }

    private fun loadRepos() {
        repositoriesRepo.getUsers().let { repos ->
            repositoryListPresenter.repositories.clear()
            repositoryListPresenter.repositories.addAll(repos)
            viewState.updateList()
        }
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}
