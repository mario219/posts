package com.example.data.mapper

import com.example.data.model.UserRemote
import com.example.domain.model.User
import javax.inject.Inject

internal class UserRemoteMapper @Inject constructor() : BaseRemoteMapper<UserRemote, User> {

    override fun transform(input: UserRemote): User {
        return User(
            id = input.id,
            name = input.name,
            email = input.email,
            phone = input.phone,
            website = input.website
        )
    }
}