package com.shazdroid.shaz.model


import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("meta")
    var meta: Meta,
    @SerializedName("data")
    var `data`: List<Data>
) {
    data class Meta(
        @SerializedName("pagination")
        var pagination: Pagination
    ) {
        data class Pagination(
            @SerializedName("total")
            var total: Int,
            @SerializedName("pages")
            var pages: Int,
            @SerializedName("page")
            var page: Int,
            @SerializedName("limit")
            var limit: Int
        )
    }

    data class Data(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("gender")
        var gender: String,
        @SerializedName("status")
        var status: String,
        @SerializedName("created_at")
        var createdAt: String,
        @SerializedName("updated_at")
        var updatedAt: String
    )
}