package lavsam.gb.libs.poplibs_lesson2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val avatar_url: String,
    val id: Int
) :Parcelable