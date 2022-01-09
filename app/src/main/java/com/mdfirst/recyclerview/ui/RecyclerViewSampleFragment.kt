package com.mdfirst.recyclerview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import com.mdfirst.R
import com.mdfirst.recyclerview.ui.adapter.SampleAdapter

class RecyclerViewSampleFragment : Fragment() {

    private val viewModel by viewModels<RecyclerViewSampleViewModel>()

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        SampleAdapter(
            onPlanetClickListener = { planet -> viewModel.onPlanetClick(planet) },
            onAdvertisingClickListener = { advertising -> viewModel.onAdvertisingClick(advertising) },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_sample)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        val callback = ItemDragTouchHelperCallback(
//            onItemMove = { from, to ->
//                viewModel.onItemMoved(from, to)
//            },
//            onItemSwiped = { position -> viewModel.onItemRemoved(position) },
//        )
//        val touchHelper = ItemTouchHelper(callback)
//        touchHelper.attachToRecyclerView(recyclerView)

        observeViewModel()

        viewModel.loadData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.getItems().observe(viewLifecycleOwner) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()

//            val sampleDiffUtil = SampleDiffUtil(
//                oldList = adapter.items,
//                newList = items,
//            )
//            val sampleDiffResult = DiffUtil.calculateDiff(sampleDiffUtil)
//            adapter.items = items
//            sampleDiffResult.dispatchUpdatesTo(adapter)
        }

        viewModel.getMessages().observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}