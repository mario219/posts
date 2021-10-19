package com.example.posts.screens.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.interactor.FavPostsUseCase
import com.example.domain.interactor.GetCommentsUseCase
import com.example.domain.interactor.GetPostDetailUseCase
import com.example.domain.interactor.GetPostOwnerUseCase
import com.example.domain.interactor.MarkAsReadUseCase
import com.example.domain.model.Comments
import com.example.domain.model.InfoWrapper
import com.example.domain.model.Posts
import com.example.domain.model.User
import com.example.posts.MainCoroutineRule
import com.example.posts.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK private lateinit var getPostOwner: GetPostOwnerUseCase
    @MockK private lateinit var getPostComments: GetCommentsUseCase
    @MockK private lateinit var getPostDetail: GetPostDetailUseCase
    @MockK private lateinit var markAsRead: MarkAsReadUseCase
    @MockK private lateinit var markAsFav: FavPostsUseCase

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = DetailViewModel(
            getPostOwner,
            getPostComments,
            getPostDetail,
            markAsRead,
            markAsFav
        )
    }

    @Test
    fun `test when viewModel is initialized, it should start wrapping the info and exposing it through LiveData`() {
        mainCoroutineRule.runBlockingTest {
            val mockInfo = InfoWrapper(mockPost, mockComments, mockUser)

            coEvery { getPostDetail(anyString()) } returns mockPost
            coEvery { getPostComments(anyString()) } returns mockComments
            coEvery { getPostOwner(anyString()) } returns mockUser

            viewModel.init(anyString(), anyString())

            assertEquals(viewModel.info.getOrAwaitValue(), mockInfo)
        }
    }

    @Test
    fun `test on fav clicked and post is favorite, viewModel should call unMark as favorite`() = mainCoroutineRule.runBlockingTest {
        viewModel.post = mockPost

        viewModel.onFavClicked()

        coVerify { markAsFav.unMark(any()) }
    }

    @Test
    fun `test on fav clicked and post is not favorite, viewModel should call mark as favorite`() = mainCoroutineRule.runBlockingTest {
        viewModel.post = mockPostNoFav

        viewModel.onFavClicked()

        coVerify { markAsFav.mark(any()) }
    }

    private val mockPost = Posts(1, 1, "post mock", "body mock", favorite = true, read = true)
    private val mockPostNoFav = Posts(2, 3, "post mock no fav", "body mock", favorite = false, read = true)
    private val mockComments = mutableListOf<Comments>().apply {
        add(Comments(1, 12, "mock comment", "mock mail", "this is a mock comment"))
    }
    private val mockUser = User(100, "mock name", "mock mail", "mock phone", "mock site")
}
