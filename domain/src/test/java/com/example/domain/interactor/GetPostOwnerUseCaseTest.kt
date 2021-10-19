package com.example.domain.interactor

import com.example.domain.model.ResultFromRemote.Error
import com.example.domain.model.ResultFromRemote.Success
import com.example.domain.model.User
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
class GetPostOwnerUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var repository: PostsRepository

    private lateinit var useCase: GetPostOwnerUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        useCase = GetPostOwnerUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test should return user if request was successful`() {
        runBlockingTest {
            coEvery { repository.getPostOwner(anyString()) } returns Success(mockUser)
            assertEquals(useCase(anyString())?.id, mockUser.id)
        }
    }

    @Test
    fun `test should return null if request returns an exception`() {
        runBlockingTest {
            coEvery { repository.getPostOwner(anyString()) } returns Error()
            assertEquals(useCase(anyString()), null)
        }
    }

    private val mockUser = User(1, "mock name", "mock mail")
}