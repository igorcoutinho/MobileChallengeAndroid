package br.com.igorcoutinho.mobilechallengeandroid.uiTest.adapters

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GithubDatabaseAdapterTest {
    val context = ApplicationProvider.getApplicationContext<Context>()

    private var githubDatabaseAdapter = GithubDatabaseAdapter(context)
    private var page = 30

    @Test
    fun getAllGithubRepositoriesFixedNumber() {

        val testList = githubDatabaseAdapter.getAllGithubRepositories(page)

        assertNotNull(testList)
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun getLimitDataNegative() {
        githubDatabaseAdapter.getLimitData(-1, 0)
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun getOffsetDataNegative() {
        githubDatabaseAdapter.getLimitData(1, -1)
    }

    @Test
    fun getLimitData() {

        val testList = githubDatabaseAdapter.getLimitData(30, 1)

        assertNotNull(testList)
    }

    @Test
    fun transformationResultToGithubRepositorySuccess() {

        val data = githubDatabaseAdapter.getLimitData(30, 1)

        val testList =
            githubDatabaseAdapter.transformationResultToGithubRepository(result = data)

        assertNotNull(testList)
    }

    @Test
    fun transformationResultToGithubRepositoryEmptyResult() {

        val data = null

        val testList =
            data?.let { githubDatabaseAdapter.transformationResultToGithubRepository(it) }

        assertNull(testList)
    }
}