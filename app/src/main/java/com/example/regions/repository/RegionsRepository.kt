package com.example.regions

import android.util.Log
import com.example.regions.models.RegionListDataItem
import com.example.regions.models.RepositoryResult
import com.example.regions.network.RegionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository that provides required Regions data
 */
interface RegionsRepository {

    /**
     * Getter function that returns a flow that emits the Regions data.
     */
    fun retrieveListOfRegions(): Flow<RepositoryResult<List<RegionListDataItem>>>

}

class RegionsRepositoryImpl @Inject constructor(private val regionsApi: RegionsApi) :
    RegionsRepository {
    override fun retrieveListOfRegions(): Flow<RepositoryResult<List<RegionListDataItem>>> =
        flow {

            Log.d(
                RegionsRepository::class.simpleName,
                "RegionList: fetching regions preview data"
            )
            val response = regionsApi.getRegionList()

            response.takeIf {
                it.isSuccessful && (it.body() != null)
            }?.let {
                emit(RepositoryResult.Success(it.body()!!))
                Log.d(
                    RegionsRepository::class.simpleName,
                    "RegionList: Success!!! fetching regions preview data"
                )

            } ?: kotlin.run {
                emit(RepositoryResult.Error(response.errorBody().toString()))
            }

        }.flowOn(Dispatchers.IO)
}
