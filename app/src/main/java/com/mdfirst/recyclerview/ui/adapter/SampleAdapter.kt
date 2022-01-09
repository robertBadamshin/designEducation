package com.mdfirst.recyclerview.ui.adapter

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mdfirst.R
import com.mdfirst.recyclerview.ui.model.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

private const val viewTypePlanet = 0
private const val viewTypeAdvertising = 1

class SampleAdapter(
    private var onPlanetClickListener: ((item: PlanetUiModel) -> Unit),
    private var onAdvertisingClickListener: ((item: AdvertisingUiModel) -> Unit),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<SampleListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            viewTypePlanet -> {
                PlanetViewHolder(
                    view = inflater.inflate(R.layout.item_planet_view, parent, false) as View,
                    onPlanetClickListener = onPlanetClickListener,
                )
            }
            viewTypeAdvertising -> {
                AdvertisingViewHolder(
                    view = inflater.inflate(R.layout.item_advertising_view, parent, false) as View,
                    onAdvertisingClickListener = onAdvertisingClickListener,
                )
            }
            else -> {
                throw IllegalArgumentException("Unknown type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypePlanet) {
            holder as PlanetViewHolder
            val planetUiModel = items[position] as PlanetUiModel
            holder.bind(planetUiModel)
        } else {
            holder as AdvertisingViewHolder
            val advertisingUiModel = items[position] as AdvertisingUiModel
            holder.bind(advertisingUiModel)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is PlanetUiModel -> viewTypePlanet
            is AdvertisingUiModel -> viewTypeAdvertising
            else -> throw IllegalStateException("unknown view type")
        }
    }

    // лучше вынести в отдельный файл, здесь для наглядности
    class PlanetViewHolder(
        view: View,
        private val onPlanetClickListener: (item: PlanetUiModel) -> Unit,
    ) : RecyclerView.ViewHolder(view) {

        private var planetImageView: ImageView = view.findViewById(R.id.image_view_planet_image)
        private var planetNameTextView: TextView = view.findViewById(R.id.text_view_planet_name)

        fun bind(planetUiModel: PlanetUiModel) {
            // ТУТ НЕ ДОЛЖНО БЫТЬ ЛОГИКИ , тут должно быть мало кода. Если будет много - список будет тормозить.
            // В помощь вам придут UiModel, в них должны быть уже готовые данные - отформатированы строки, например
            Glide.with(planetImageView.context).load(planetUiModel.pictureUrl).into(planetImageView)

            planetNameTextView.text = planetUiModel.name

            itemView.setOnClickListener { onPlanetClickListener(planetUiModel) }
        }
    }

    class AdvertisingViewHolder(
        view: View,
        private val onAdvertisingClickListener: (item: AdvertisingUiModel) -> Unit,
    ) : RecyclerView.ViewHolder(view) {

        private var advertisingTitleTextView: TextView = view.findViewById(R.id.text_view_advertising_title)
        private var advertisingDescriptionTextView: TextView = view.findViewById(
            R.id.text_view_advertising_description,
        )

        fun bind(advertisingUiModel: AdvertisingUiModel) {
            // ТУТ НЕ ДОЛЖНО БЫТЬ ЛОГИКИ
            advertisingTitleTextView.text = advertisingUiModel.title
            advertisingDescriptionTextView.text = advertisingUiModel.description

            itemView.setOnClickListener { onAdvertisingClickListener(advertisingUiModel) }
        }
    }

}