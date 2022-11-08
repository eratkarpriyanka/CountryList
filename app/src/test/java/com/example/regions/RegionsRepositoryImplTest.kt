package com.example.regions

import android.util.Log
import com.example.regions.models.RegionListDataItem
import com.example.regions.models.RepositoryResult
import com.example.regions.network.RegionsApi
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RegionsRepositoryImplTest {

    @Test
    fun `verify all regions repository call`() = TestScope().runTest {

        mockkStatic(Log::class)

        val mockRegionsApi = mockk<RegionsApi>(relaxed = true)
        val regionRepository = RegionsRepositoryImpl(mockRegionsApi)
        val data = mockk<List<RegionListDataItem>>(relaxed = true)

        coEvery { mockRegionsApi.getRegionList().body() } returns data

        regionRepository.retrieveListOfRegions().collect { result ->
            if (result is RepositoryResult.Success) {
                assertEquals(result.data, RepositoryResult.Success(data))
            }
        }
    }
}