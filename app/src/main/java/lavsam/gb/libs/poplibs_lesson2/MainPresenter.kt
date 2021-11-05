package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    val router: Router,
    val screen: IScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}