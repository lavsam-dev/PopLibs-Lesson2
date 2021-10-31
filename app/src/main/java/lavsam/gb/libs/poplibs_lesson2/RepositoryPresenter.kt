package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class RepositoryPresenter(val repository: GithubUser, val router: Router) :
    MvpPresenter<RepositoryView>(), IRepositoryRenderData {
    override fun renderData() {
        viewState.renderData(repository.login, repository.avatar_url)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        renderData()
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}