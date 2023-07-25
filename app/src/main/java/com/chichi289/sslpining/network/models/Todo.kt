package com.chichi289.sslpining.network.models


import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("completed")
    val completed: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
){
    override fun toString(): String {
        return "\"userId\": ${userId},\n" +
                "\t\"id\": ${id},\n" +
                "\t\"title\": \"${title}\",\n" +
                "\t\"completed\": $completed"
    }
}