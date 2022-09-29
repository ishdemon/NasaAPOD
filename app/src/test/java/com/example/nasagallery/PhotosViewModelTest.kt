package com.example.nasagallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasagallery.common.DataState
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.data.repository.PhotosRepository
import com.example.nasagallery.ui.home.PhotosViewModel
import com.google.gson.JsonParseException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotosViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @MockK
    private lateinit var repository: PhotosRepository

    private lateinit var subject: PhotosViewModel


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = PhotosViewModel(
            repository,
            TestCoroutineDispatcher()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `PhotosDetails flow emits Success when fetchPhotos succeeds`() = runTest {
        val data = listOf<PhotoDetails>(PhotoDetails(
            "","","","","",""
        ))
        coEvery { repository.getPhotos() } returnOrCrash data.withoutCrash

        subject.fetchPhotos()
        advanceUntilIdle()

        val photoDetailsState = subject.photoDetailsState.value
        Assert.assertTrue(photoDetailsState is DataState.Success)

        val actualValue = (photoDetailsState as DataState.Success).data
        Assert.assertEquals(data, actualValue)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `PhotosDetails flow emits Error when exception from repository`() = runTest {
        val exception = IllegalStateException("Photos null")
        coEvery { repository.getPhotos() } returnOrCrash exception.asCrash()

        subject.fetchPhotos()
        advanceUntilIdle()

        val photoDetailsState = subject.photoDetailsState.value
        Assert.assertTrue(photoDetailsState is DataState.Error)

        val actualValue = (photoDetailsState as DataState.Error).error
        Assert.assertEquals(exception, actualValue)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `PhotosDetails flow emits Error when JSON fails to parse`() = runTest {
        val exception = JsonParseException("Bad Json")
        coEvery { repository.getPhotos() } returnOrCrash exception.asCrash()

        subject.fetchPhotos()
        advanceUntilIdle()

        val photoDetailsState = subject.photoDetailsState.value
        Assert.assertTrue(photoDetailsState is DataState.Error)

        val actualValue = (photoDetailsState as DataState.Error).error
        Assert.assertEquals(exception, actualValue)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `PhotosDetails flow emits Loading until fetchPhotos is Successful`() = runTest {
        val data = listOf<PhotoDetails>(PhotoDetails(
            "","","","","",""
        ))
        coEvery { repository.getPhotos() } returnOrCrash data.withoutCrash

        subject.fetchPhotos()
        runCurrent()
        var photoDetailsState = subject.photoDetailsState.value
        Assert.assertTrue(photoDetailsState is DataState.Loading)

        advanceUntilIdle()

        photoDetailsState = subject.photoDetailsState.value
        Assert.assertTrue(photoDetailsState is DataState.Success)

    }



}