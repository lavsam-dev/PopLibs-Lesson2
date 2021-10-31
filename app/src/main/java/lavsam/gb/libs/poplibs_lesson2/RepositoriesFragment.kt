package lavsam.gb.libs.poplibs_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repositories.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RepositoriesFragment : MvpAppCompatFragment(), RepositoriesView, BackButtonListener {

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    @InjectPresenter
    lateinit var presenter: RepositoriesPresenter

    var adapter: RepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        View.inflate(context, R.layout.fragment_repositories, null)

    @ProvidePresenter
    fun providePresenter() =
        RepositoriesPresenter(GithubUsersRepo(), App.instance.getRouter(), Screens())

    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.repositoryListPresenter)
        rv_repos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backClicked() = presenter.backClicked()
}