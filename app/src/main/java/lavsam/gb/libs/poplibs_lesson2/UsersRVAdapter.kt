package lavsam.gb.libs.poplibs_lesson2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*

class UsersRVAdapter(val presenter: IRepositoryListPresenter) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, UserItemView {
        override var pos = -1

        override fun setTitle(text: String) = with(containerView) {
            when (text.substring(0, 1)) {
                "W" -> tv_room_web.setBackgroundColor(Color.parseColor("#43A047"))
                "R" -> tv_room_web.setBackgroundColor(Color.parseColor("#B10404"))
                "L" -> tv_room_web.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            tv_title.text = text.substring(1)
        }
    }
}