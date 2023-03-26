package com.example.avitotestassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemListViewModel(private val repository: ItemListRepository): ViewModel() {

    val itemList = repository.getItemList()

    fun startAsyncAdding(){
        viewModelScope.launch {
            repository.startAsyncAdding()
        }
    }

    fun deleteItem(item: Item){
        repository.deleteItem(item)
    }

}