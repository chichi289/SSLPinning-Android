package com.chichi289.sslpining.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chichi289.sslpining.network.GithubApi
import com.chichi289.sslpining.network.models.GithubUser
import com.chichi289.sslpining.network.JsonPlaceHolderApi
import com.chichi289.sslpining.network.models.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var _userData = MutableLiveData<GithubUser>()
    val userData: LiveData<GithubUser>
        get() = _userData

    private var _todoData = MutableLiveData<Todo>()
    val todoData: LiveData<Todo>
        get() = _todoData

    private var _errorData = MutableLiveData<String>()
    val errorData: LiveData<String>
        get() = _errorData

    init {
        _userData.value = null
        _errorData.value = null
    }

    fun getUserData(profile: String) {
        val api = GithubApi.githubApiService.getUserData(profile)
        api.enqueue(object : Callback<GithubUser> {
            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                Log.e("TAG", "Failed :" + t.message)
                _errorData.value = t.message
            }

            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                _userData.value = response.body()
            }
        })
    }

    fun getTodo(id: Int) {
        val api = JsonPlaceHolderApi.jsonPlaceHolderApi.getTodo(id)
        api.enqueue(object : Callback<Todo> {
            override fun onFailure(call: Call<Todo>, t: Throwable) {
                Log.e("TAG", "Failed :" + t.message)
                _errorData.value = t.message
            }

            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                _todoData.value = response.body()
            }
        })
    }

}