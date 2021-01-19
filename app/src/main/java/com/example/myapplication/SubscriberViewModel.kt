package com.example.myapplication

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.Subscriber
import com.example.myapplication.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscriber = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    //for text on button
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    //initial block to display name to those buttons
    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    //function to udpate
    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subscriber(0, name, email))  // id is 0 because ID is auto incremental
        // clear input
        inputEmail.value = null
        inputName.value = null
    }

    //function to clear or delete
    fun clearAllOrDelete() {
        clearAll()
    }

//    fun insert(subscriber: Subscriber) {
//        viewModelScope.launch {
//            repository.insert((subscriber))
//        }
//    }

    fun insert(subscriber: Subscriber) =
        viewModelScope.launch {
            repository.insert((subscriber))
        }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        repository.update(subscriber)
    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        repository.delete(subscriber)
    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}