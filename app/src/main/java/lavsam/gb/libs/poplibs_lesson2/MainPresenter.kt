package lavsam.gb.libs.poplibs_lesson2

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.RepositoriesScreen())
    }

    fun backClicked() {
        router.exit()
    }

}