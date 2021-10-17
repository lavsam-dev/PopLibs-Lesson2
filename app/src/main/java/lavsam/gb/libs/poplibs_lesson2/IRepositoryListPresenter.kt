package lavsam.gb.libs.poplibs_lesson2

interface IRepositoryListPresenter {
    var itemClickListener: ((RepositoryItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: RepositoryItemView)
}