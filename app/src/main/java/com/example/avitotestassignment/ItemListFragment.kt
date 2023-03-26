package com.example.avitotestassignment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.avitotestassignment.databinding.FragmentItemListBinding


class ItemListFragment: Fragment() {

    private lateinit var binding: FragmentItemListBinding
    private lateinit var adapter: ItemListAdapter
    private val viewModel by lazy { ItemListViewModel(ItemListRepository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemListBinding.bind(view)

        setUpRecyclerView()
        setUpClickListeners()

        viewModel.itemList.observe(viewLifecycleOwner){list ->
            adapter.submitList(list)
        }

        viewModel.startAsyncAdding()
    }

    private fun setUpClickListeners(){
        adapter.onButtonDelClickListener = {
            viewModel.deleteItem(it)
        }
    }

    private fun setUpRecyclerView(){
        adapter = ItemListAdapter()
        binding.rcList.adapter = adapter
    }

}