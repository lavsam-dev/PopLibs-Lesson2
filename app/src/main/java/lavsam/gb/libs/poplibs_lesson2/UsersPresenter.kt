package lavsam.gb.libs.poplibs_lesson2

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter

const val POP_LIBS = "POP_LIBS"

@InjectViewState
class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {

        val users = (1..20).map { GithubUser("login $it", "", 0) }.toMutableList()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setTitle(user.login)
        }
    }

    val usersListPresenter = RepositoryListPresenter()

    private val disposable = usersRepo.subscribeOnGithubUsersData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ repos ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(repos)
            viewState.updateList()
        }, {
            Log.e(POP_LIBS, it.message, it)
        })

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        usersRepo.loadUserData()

        usersListPresenter.itemClickListener = { itemView ->
            val repository = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.githubUser(repository))
        }
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
