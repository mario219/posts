package com.example.data.model

import com.google.gson.annotations.SerializedName

internal data class UserRemote(
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("email")
	val email: String? = null,
	@field:SerializedName("phone")
	val phone: String? = null,
	@field:SerializedName("website")
	val website: String? = null
)