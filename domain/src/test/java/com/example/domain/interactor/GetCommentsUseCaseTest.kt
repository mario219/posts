package com.example.domain.interactor

import com.example.domain.model.Comments
import com.example.domain.model.ResultFromRemote
import com.example.domain.model.ResultFromRemote.Error
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
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class GetCommentsUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var repository: PostsRepository

    private lateinit var useCase: GetCommentsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        useCase = GetCommentsUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test when fetch comments is successful, then use case should return the list of comments`() {
        runBlockingTest {
            coEvery { repository.getCommentsByPost(anyString()) } returns ResultFromRemote.Success(mockUsers.toList())
            assertEquals(useCase(anyString()).size, mockUsers.size)
        }
    }

    @Test
    fun `test when repository returns error, then use case should return an empty list`() {
        val errorFromRepo = Error(400, "unrecognizable error")
        runBlockingTest {
            coEvery { repository.getCommentsByPost(anyString()) } returns errorFromRepo
            assert(useCase(anyString()).isEmpty())
        }
    }

    private val mockUsers = mutableListOf<Comments>().apply {
        add(Comments())
        add(Comments())
    }
}