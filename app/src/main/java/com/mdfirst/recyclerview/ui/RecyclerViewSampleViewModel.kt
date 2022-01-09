package com.mdfirst.recyclerview.ui

import androidx.lifecycle.*
import com.mdfirst.recyclerview.ui.model.*
import java.util.*

private const val marsPictureUrl = "https://findicons.com/files/icons/475/solar_system/256/mars.png"
private const val earthPictureUrl = "https://findicons.com/files/icons/144/web/256/earth.png"

class RecyclerViewSampleViewModel: ViewModel() {

    private val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())

    private val messagesLiveData = MutableLiveData<String>()

    fun getItems(): LiveData<List<SampleListItem>> {
        return itemsLiveData
    }

    // нужно позаботиться о том, чтобы сообщение показалось только один раз, потому что при пересоздании фрагмента,
    // фрагмент может подписаться уже на существующую ViewModel, и ему придет старое сообщение и покажется еще раз.
    // Такое может произойти при перевороте экрана, при открытии / закрытии экрана у Falaxy Fold.
    // пример https://github.com/ArtNikita/Material_Design/pull/1/files , класс Event.kt
    fun getMessages(): LiveData<String> {
        return messagesLiveData
    }

    fun loadData() {
        val planet1 = PlanetUiModel(
            id = UUID.randomUUID().toString(),
            pictureUrl = earthPictureUrl,
            name = "Earth",
        )

        val planet2 = planet1.copy(id = UUID.randomUUID().toString())
        val planet3 = planet1.copy(id = UUID.randomUUID().toString())
        val planet4 = PlanetUiModel(
            id = UUID.randomUUID().toString(),
            pictureUrl = marsPictureUrl,
            name = "Mars",
        )
        val planet5 = planet4.copy(id = UUID.randomUUID().toString())
        val planet6 = planet4.copy(id = UUID.randomUUID().toString())
        val planet7 = planet1.copy(id = UUID.randomUUID().toString())
        val planet8 = planet1.copy(id = UUID.randomUUID().toString())
        val planet9 = planet1.copy(id = UUID.randomUUID().toString())
        val planet10 = planet1.copy(id = UUID.randomUUID().toString())

        val advertisingUiModel1 = AdvertisingUiModel(
            id = UUID.randomUUID().toString(),
            title = "Реклама ботинок",
            description = "Покупайте ботинки, удобные"
        )

        val advertisingUiModel2 = AdvertisingUiModel(
            id = UUID.randomUUID().toString(),
            title = "Реклама жвачки",
            description = "Жуйте ботинки, вкусные"
        )

        val items = listOf(
            planet1,
            planet2,
            planet3,
            advertisingUiModel1,
            planet4,
            planet5,
            planet6,
            planet7,
            advertisingUiModel2,
            planet8,
            planet9,
            planet10,
        )

        itemsLiveData.value = items
    }

    fun onPlanetClick(uiModel: PlanetUiModel) {
        // open planet detail uiModel
        // router.openPlanet

        // simple show name
        messagesLiveData.value = uiModel.name

        val oldList = requireCurrentList()
        val newList = oldList - uiModel
        itemsLiveData.value = newList
    }

    fun onAdvertisingClick(uiModel: AdvertisingUiModel) {
        // open advertising
        // router.openAdvertising

        // simple show description
        messagesLiveData.value = uiModel.description
    }

    fun onItemMoved(from: Int, to: Int) {
        val newMutableList = requireCurrentList().toMutableList()
        Collections.swap(newMutableList, from, to)

        itemsLiveData.value = newMutableList
    }

    fun onItemRemoved(position: Int) {
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.removeAt(position)

        itemsLiveData.value = newMutableList
    }

    private fun requireCurrentList(): List<SampleListItem> {
        return itemsLiveData.value ?: throw IllegalStateException("items list is null")
    }
}