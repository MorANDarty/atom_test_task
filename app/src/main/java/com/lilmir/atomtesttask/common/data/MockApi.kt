package com.lilmir.atomtesttask.common.data

class MockApi {

    //@GET("/chargers")
    suspend fun getChargers(): MockData = parseMockData(MOCK_DATA)
}