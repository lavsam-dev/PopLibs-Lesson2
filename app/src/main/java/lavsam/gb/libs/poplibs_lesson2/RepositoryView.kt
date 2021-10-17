package lavsam.gb.libs.poplibs_lesson2

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView: MvpView {
    fun renderData(login: String)
}