package lavsam.gb.libs.poplibs_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RepositoryFragment(val repository : GithubRepository?) : MvpAppCompatFragment(),RepositoryView,
    BackButtonListener {


    companion object {
        fun newInstance(repository : GithubRepository?) = RepositoryFragment(repository)
    }
    @InjectPresenter
    lateinit var presenter: RepositoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }
    @ProvidePresenter
    fun providePresenterRepository() =
        RepositoryPresenter(GithubRepository(repository!!.login), App.instance.getRouter())

    override fun renderData(login: String) {
        tv_login.text = login
    }

    override fun backClicked() = presenter.backClicked()
}