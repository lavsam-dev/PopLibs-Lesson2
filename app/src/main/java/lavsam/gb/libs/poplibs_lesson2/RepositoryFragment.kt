package lavsam.gb.libs.poplibs_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RepositoryFragment(val repository: GithubUser?) : MvpAppCompatFragment(),
    RepositoryView, BackButtonListener {

    companion object {
        fun newInstance(repository: GithubUser?) = RepositoryFragment(repository)
    }

//    @InjectPresenter
//    lateinit var presenter: RepositoryPresenter

    val presenter by moxyPresenter {
        RepositoryPresenter(
            GithubUser(
                repository!!.login,
                repository!!.avatar_url,
                repository!!.id
            )
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

//@ProvidePresenter
//fun providePresenterRepository() =
//    RepositoryPresenter(
//        GithubUser(repository!!.login, repository!!.avatar_url, repository!!.id),
//        App.instance.router
//    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun renderData(login: String, avatar: String) {
        tv_login.text = login
        iv_Avatar?.run {
            Glide.with(this)
                .load(avatar)
                .error(R.drawable.empty01e)
                .placeholder(R.drawable.progger00_400)
                .into(this)
        }
    }

    override fun backClicked() = presenter.backClicked()
}