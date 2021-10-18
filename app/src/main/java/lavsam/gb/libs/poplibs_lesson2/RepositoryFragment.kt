package lavsam.gb.libs.poplibs_lesson2

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RepositoryFragment(val repository: GithubRepository?) : MvpAppCompatFragment(),
    RepositoryView,
    BackButtonListener {


    companion object {
        fun newInstance(repository: GithubRepository?) = RepositoryFragment(repository)
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
        iv_Avatar.setImageBitmap(getAvatar())
    }

    private fun getAvatar(): Bitmap {
        val bitmap: Bitmap =
            when ((1..8).random()) {
                1 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger01, null)
                        as BitmapDrawable).bitmap
                2 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger02, null)
                        as BitmapDrawable).bitmap
                3 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger03, null)
                        as BitmapDrawable).bitmap
                4 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger04, null)
                        as BitmapDrawable).bitmap
                5 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger05, null)
                        as BitmapDrawable).bitmap
                6 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger06, null)
                        as BitmapDrawable).bitmap
                7 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger07, null)
                        as BitmapDrawable).bitmap
                8 -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.progger08, null)
                        as BitmapDrawable).bitmap

                else -> (ResourcesCompat.getDrawable(
                    resources, R.drawable.empty01e, null)
                        as BitmapDrawable).bitmap
            }
        return bitmap
    }

    override fun backClicked() = presenter.backClicked()
}