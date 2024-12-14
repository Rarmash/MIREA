package com.rarmash.prac5

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val carts: LiveData<List<Cart>> = repository.getAllCarts()

    fun fetchCarts() {
        viewModelScope.launch {
            repository.fetchAndSaveCarts()
        }
    }
}