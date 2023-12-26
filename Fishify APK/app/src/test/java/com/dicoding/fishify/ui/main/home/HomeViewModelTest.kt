package com.dicoding.fishify.ui.main.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.dicoding.fishify.DataDummy
import com.dicoding.fishify.MainDispatcherRule
import com.dicoding.fishify.getOrAwaitValue
import com.dicoding.fishify.model.StoryResponseItem
import com.dicoding.fishify.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var quoteRepository: Repository

    @Test
    fun `when Get Quote Should Not Null and Return Data`() = runTest {
        val dummyQuote = DataDummy.generateDummyStories()
        val data: PagingData<StoryResponseItem> = QuotePagingSource.snapshot(dummyQuote)
        val expectedQuote = MutableLiveData<PagingData<StoryResponseItem>>()
        expectedQuote.value = data
        Mockito.`when`(quoteRepository.getStoriesList(toString())).thenReturn(expectedQuote)

        val homeViewModel = MenuViewModel(quoteRepository)
        val actualQuote: PagingData<StoryResponseItem> = homeViewModel.getStoriesList(toString()).getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = FishifyAdapter.mDiffCallback,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualQuote)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyQuote.size, differ.snapshot().size)
        Assert.assertEquals(dummyQuote[0], differ.snapshot()[0])
    }

    @Test
    fun `when Get Quote Empty Should Return No Data`() = runTest {
        val data: PagingData<StoryResponseItem> = PagingData.from(emptyList())
        val expectedQuote = MutableLiveData<PagingData<StoryResponseItem>>()
        expectedQuote.value = data
        Mockito.`when`(quoteRepository.getStoriesList(toString())).thenReturn(expectedQuote)
        val mainViewModel = MenuViewModel(quoteRepository)
        val actualQuote: PagingData<StoryResponseItem> = mainViewModel.getStoriesList(toString()).getOrAwaitValue()
        val differ = AsyncPagingDataDiffer(
            diffCallback = FishifyAdapter.mDiffCallback,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualQuote)
        Assert.assertEquals(0, differ.snapshot().size)
    }
}

class QuotePagingSource : PagingSource<Int, LiveData<List<StoryResponseItem>>>() {
    companion object {
        fun snapshot(items: List<StoryResponseItem>): PagingData<StoryResponseItem> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<StoryResponseItem>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<StoryResponseItem>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}