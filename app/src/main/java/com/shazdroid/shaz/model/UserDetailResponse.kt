package com.shazdroid.shaz.model


import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
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
        @SerializedName("user_id")
        var userId: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("body")
        var body: String,
        @SerializedName("created_at")
        var createdAt: String,
        @SerializedName("updated_at")
        var updatedAt: String
    )
}