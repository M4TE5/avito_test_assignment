package com.example.avitotestassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.util.*

const val DELAY_TIME = 5000L
const val ITEMS_START_NUMBER = 15
const val MIN_ITEM_VALUE = 1

object ItemListRepository {

    private val itemListLD = MutableLiveData<List<Item>>()
    private val itemList = mutableListOf<Item>()
    private var autoIncrementValue = MIN_ITEM_VALUE
    private var pull = Stack<Int>()
    private var addingIsStarted = false

    init {
        repeat(ITEMS_START_NUMBER){
            itemList.add(Item(autoIncrementValue++))
        }
        updateList()
    }

    private fun addItem(){
        val randomPosition = (0 .. itemList.size).random()
        if (pull.isEmpty()) {
            itemList.add(randomPosition, Item(autoIncrementValue++))
        } else {
            itemList.add(randomPosition, Item(pull.pop()))
        }
        updateList()
    }

    fun deleteItem(item: Item){
        pull.push(item.value)
        val valueToDelete = itemList.indexOfFirst { it.value == item.value }
        itemList.removeAt(valueToDelete)
        updateList()
    }

    fun getItemList(): LiveData<List<Item>> = itemListLD

    private fun updateList(){
        itemListLD.value = itemList.toList()
    }

    suspend fun startAsyncAdding(){
        if(!addingIsStarted){
            addingIsStarted = true
            coroutineScope {
                while (true){
                    delay(DELAY_TIME)
                    addItem()
                }
            }
        }
    }

}
