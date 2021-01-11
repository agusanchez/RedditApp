package com.reddit.app.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.reddit.app.domain.usecase.GetPostByIdUseCase
import com.reddit.app.domain.usecase.SavePictureUseCase
import com.reddit.app.mock.mockedPost
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString

@RunWith(JUnit4::class)
class PostDetailViewModelTest {
    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PostDetailViewModel

    private lateinit var savePictureUseCase: SavePictureUseCase
    private lateinit var getPostByIdUseCase: GetPostByIdUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        savePictureUseCase = mock()
        getPostByIdUseCase = mock()

        viewModel = PostDetailViewModel(savePictureUseCase, getPostByIdUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when an image is saved successfully then show congrats message`() = runBlockingTest {
        //WHEN
        viewModel.savePictureOnGallery(anyString())

        //THEN
        verify(savePictureUseCase).invoke(anyString())
        assert(viewModel.pictureSavedStatus.value == true)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when an image is not saved correctly then show error message`() = runBlockingTest {
        //GIVEN
        `when`(savePictureUseCase.invoke(anyString())).doAnswer{ throw Exception() }

        //WHEN
        viewModel.savePictureOnGallery(anyString())

        //THEN
        verify(savePictureUseCase).invoke(anyString())
        assert(viewModel.pictureSavedStatus.value == false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when a postId is given then return the entire Post`() = runBlockingTest {
        //GIVEN
        `when`(getPostByIdUseCase.invoke(mockedPost.id)).thenReturn(mockedPost)

        //WHEN
        viewModel.getDetail(mockedPost.id)

        //THEN
        verify(getPostByIdUseCase).invoke(mockedPost.id)
        assert(viewModel.postDetail.value == mockedPost)
    }
}