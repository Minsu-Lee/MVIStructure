package com.jackson.mvistructuresample.data.repository

import com.jackson.mvistructuresample.data.model.User

class MainRepository {

    suspend fun getUserOrNull(): User? = null

    suspend fun getUsers() = listOf(
        User(
            id = 0,
            name = "수민이",
            email = "snm4030@gamil.com",
            avatar = "",
        ),
        User(
            id = 1,
            name = "이민수",
            email = "mns0304@gmail.com",
            avatar = "",
        )
    )

}