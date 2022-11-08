package com.example.regions.network

import com.example.regions.models.RegionListDataItem
import retrofit2.Response
import retrofit2.http.GET

interface RegionsApi {

    /**
     * getter api call to retrieve the regions list
     */
    @GET("32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getRegionList(): Response<List<RegionListDataItem>>
}
