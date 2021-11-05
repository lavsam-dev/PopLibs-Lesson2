package lavsam.gb.libs.poplibs_lesson2

interface IRepositoryListPresenter {
    var itemClickListener: ((UserItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: UserItemView)
}