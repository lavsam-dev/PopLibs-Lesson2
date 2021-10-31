package lavsam.gb.libs.poplibs_lesson2

import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkLoadingUsers() {

        val githubUsersRepo = GithubUsersRepo()
        githubUsersRepo
            .getUsers2()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())

            .doOnNext { println(it) }
            .subscribe{
                println(it)
            }

        Thread.sleep(20000)

    }
}