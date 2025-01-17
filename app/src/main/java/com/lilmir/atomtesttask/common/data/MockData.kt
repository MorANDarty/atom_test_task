package com.lilmir.atomtesttask.common.data

import org.json.JSONArray

val MOCK_DATA = """
    [
        {"city":"Moscow","charger":{"busy":true,"name":"Энергия Москвы","address":"Измайловское ш., 4А"}},
        {"city":"Moscow","charger":{"busy":false,"name":"Lipgart","address":"2-я Бауманская ул., 5, стр. 5"}},
        {"city":"Saint Petersburg","charger":{"busy":true,"name":"Станция зарядки электромобилей","address":"Большой просп. Васильевского острова, 68"}},
        {"city":"Moscow","charger":{"busy":false,"name":"Zevs","address":"Мясницкая ул., 13, стр. 10"}},
        {"city":"Saint Petersburg","charger":{"busy":false,"name":"Punkt E","address":"Малая Монетная ул., 2Г"}}
    ]
""".trimIndent()

data class MockData(
    val items: List<MockDataItem>
) {

    data class MockDataItem(
        val city: String,
        val charger: ChargerMockItem
    ) {

        data class ChargerMockItem(
            val busy: Boolean,
            val name: String,
            val address: String
        )
    }
}

fun parseMockData(jsonString: String): MockData {
    try {
        val jsonArray = JSONArray(jsonString)
        val items = buildList {
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val city = jsonObject.getString("city")
                val chargerObject = jsonObject.getJSONObject("charger")

                val charger = MockData.MockDataItem.ChargerMockItem(
                    busy = chargerObject.getBoolean("busy"),
                    name = chargerObject.getString("name"),
                    address = chargerObject.getString("address")
                )
                val item = MockData.MockDataItem(city = city, charger = charger)
                add(item)
            }
        }

        return MockData(items = items)
    } catch (e: Exception) {
        e.printStackTrace()
        throw e
    }
}
