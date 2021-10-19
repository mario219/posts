package com.example.domain.interactor

import com.example.domain.repository.PostsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RetrievePostsFromRemoteUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK lateinit var repository: PostsRepository

    private lateinit var useCase: RetrievePostsFromRemoteUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        useCase = RetrievePostsFromRemoteUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test if repository couldnt fetch from remote, emit a ui user error response`() {
        runBlockingTest {
            coEvery { repository.getPostsFromRemote() } returns false
            assertEquals(useCase(), "Something happened when fetching new content")
        }
    }

    @Test
    fun `test if repository fetch is success, returns an empty string or no user error result`() {
        runBlockingTest {
            coEvery { repository.getPostsFromRemote() } returns true
            assertEquals(useCase(), "")
        }
    }
}