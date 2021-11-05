package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    val router: Router,
    val screens: IScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}