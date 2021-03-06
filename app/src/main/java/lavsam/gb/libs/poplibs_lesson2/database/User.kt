package lavsam.gb.libs.poplibs_lesson2.database

import androidx.room.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "age") val age: Int = 0,
)

@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getAll(): List<User>

    @Insert
    fun insertAll(vararg user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}

@Database(
    entities = [
        User::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
