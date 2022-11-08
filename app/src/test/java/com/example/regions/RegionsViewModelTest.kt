package com.example.regions

import com.example.regions.models.RegionListData
import com.example.regions.models.RepositoryResult
import com.example.regions.viewmodels.RegionsApiResponseState
import com.example.regions.viewmodels.RegionsViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

class RegionsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify get load regions invoked`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        val repository = mockk<RegionsRepository>(relaxed = true)
        val viewModel = RegionsViewModel(repository)

        val mockRegionListDataItem = mockk<RegionListData>()

        every { repository.retrieveListOfRegions() } returns flow {
            emit(RepositoryResult.Success(mockRegionListDataItem))
        }
        try {
            viewModel.loadRegions()
            assertEquals(
                viewModel.listState.value, RegionsApiResponseState.Success(mockRegionListDataItem)
            )
        } finally {
            Dispatchers.resetMain()
        }
    }
}