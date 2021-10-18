package lavsam.gb.libs.poplibs_lesson2

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class RepositoryPresenter(val repository: GithubRepository,val router: Router) :
    MvpPresenter<RepositoryView>(),IRepositoryRenderData
{
    override fun renderData() {
        viewState.renderData(repository.login)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        renderData()
    }

    fun backClicked(): Boolean {
        router.replaceScreen(Screens.RepositoriesScreen())
        return true
    }
}