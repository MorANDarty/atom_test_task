package com.lilmir.atomtesttask.feature.chargerslist.data

import androidx.compose.ui.graphics.Color
import com.lilmir.atomtesttask.feature.chargerslist.domain.Charger
import com.lilmir.atomtesttask.feature.chargerslist.ui.ChargerUiItem
import com.lilmir.atomtesttask.ui.theme.BusyCharging
import com.lilmir.atomtesttask.ui.theme.FreeCharging

class ChargerMapper {

    fun mapToUi(charger: Charger): ChargerUiItem = ChargerUiItem(
        name = charger.name,
        address = charger.address,
        busyColor = getColorByBusy(charger.isBusy)
    )

    private fun getColorByBusy(isBusy: Boolean): Color {
        return if (isBusy) BusyCharging else FreeCharging
    }
}
