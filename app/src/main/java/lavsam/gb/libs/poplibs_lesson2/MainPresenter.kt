package lavsam.gb.libs.poplibs_lesson2

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        viewState.init()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}