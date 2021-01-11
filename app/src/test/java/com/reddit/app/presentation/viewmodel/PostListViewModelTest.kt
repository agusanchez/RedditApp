package com.reddit.app.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.reddit.app.domain.usecase.DismissAllPostsUseCase
import com.reddit.app.domain.usecase.DismissedPostUseCase
import com.reddit.app.domain.usecase.GetPostsUseCase
import com.reddit.app.domain.usecase.MarkPostAsReadUseCase
import com.reddit.app.mock.mockedPostList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class PostListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PostListViewModel

    private lateinit var getPostsUseCase: GetPostsUseCase
    private lateinit var dismissPostUseCase: DismissedPostUseCase
    private lateinit var markPostAsReadUseCase: MarkPostAsReadUseCase
    private lateinit var dismissAllPostsUseCase: DismissAllPostsUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        getPostsUseCase = mock()
        dismissAllPostsUseCase = mock()
        dismissPostUseCase = mock()
        markPostAsReadUseCase = mock()

        viewModel = PostListViewModel(getPostsUseCase, dismissPostUseCase,
            markPostAsReadUseCase, dismissAllPostsUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `when the post list is required then return it`() = runBlockingTest {
        //GIVEN
        `when`(getPostsUseCase.invoke(0)).thenReturn(mockedPostList)

        //WHEN
        viewModel.getNextPosts()

        //THEN
        assert(viewModel.redditList.value == mockedPostList)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when dismiss all posts is called then remove all items and show empty view`() = runBlockingTest {
        //GIVEN
        `when`(dismissAllPostsUseCase.invoke(mockedPostList)).thenReturn(any())

        //WHEN
        viewModel.dismissAllPosts(mockedPostList)

        //THEN
        assert(viewModel.showEmptyView.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when a dismiss post is called execute use case`() = runBlockingTest {
        //GIVEN
        val postList = mockedPostList
        `when`(dismissPostUseCase.invoke(anyString())).thenReturn(postList)

        //WHEN
        viewModel.dismissPost(anyString())

        //THEN
        verify(dismissPostUseCase).invoke(anyString())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when pull to refresh is called then retrieve new posts`() = runBlockingTest {
        //GIVEN

        `when`(getPostsUseCase.invoke(0)).thenReturn(mockedPostList)

        //WHEN
        viewModel.refreshForNewPosts()

        //THEN
        assert(viewModel.redditList.value == mockedPostList)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when the post list is required with an error then show error view`() = runBlockingTest {
        //GIVEN
        `when`(getPostsUseCase.invoke(0)).doAnswer{ throw Exception() }

        //WHEN
        viewModel.getNextPosts()

        //THEN
        assert(viewModel.showNetworkError.value)
    }
}