package com.mdfirst.dailyimage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mdfirst.R

class ChipFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_chip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Chip>(R.id.chip_entry).setOnCloseIconClickListener { chipView ->
            Toast.makeText(context, "Close is Clicked", Toast.LENGTH_SHORT).show()
            chipView.isVisible = false
        }

        val chipGroup = view.findViewById<ChipGroup>(R.id.chip_group_single_selection)
        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val chip = chipGroup.findViewById<Chip>(checkedId)
            val toastText = "Выбран ${chip.text}"
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        }
    }
}