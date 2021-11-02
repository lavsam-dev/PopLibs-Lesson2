package lavsam.gb.libs.poplibs_lesson2

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class RepositoriesPresenter(
    val repositoriesRepo: GithubUsersRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<RepositoriesView>() {

//    private var disposable: Disposable? = null
//        set(value) {
//            field?.takeIf { !it.isDisposed }?.dispose()
//            field = value
//        }



    class RepositoryListPresenter : IRepositoryListPresenter {

        val repositories = (1..20).map { GithubUser( "login $it", "", 0) }.toMutableList()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setTitle(repository.login)
        }
    }

    val repositoryListPresenter = RepositoryListPresenter()

    private val disposable = repositoriesRepo.subscribeOnGithubUsersData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ repos ->
            repositoryListPresenter.repositories.clear()
            repositoryListPresenter.repositories.addAll(repos)
            viewState.updateList()
        }, {
            Log.e("GB_LIBS", it.message, it)
        })

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
//        loadRepos()
        repositoriesRepo.loadUserData()

        repositoryListPresenter.itemClickListener = { itemView ->
            val repository = repositoryListPresenter.repositories[itemView.pos]
            router.navigateTo(screens.RepositoryScreen(repository))
        }
    }

//    private fun loadRepos() {
//        disposable = repositoriesRepo.getUsers2()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ repos ->
//                repositoryListPresenter.repositories.clear()
//                repositoryListPresenter.repositories.addAll(repos)
//                viewState.updateList()
//            })
//    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
//        disposable = null
        disposable.dispose()
    }
}
