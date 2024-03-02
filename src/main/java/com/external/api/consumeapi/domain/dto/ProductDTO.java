package com.external.api.consumeapi.domain.dto;

import com.google.gson.annotations.SerializedName;

public record ProductDTO(@SerializedName("id") String id,
                         @SerializedName("title") String title,
                         @SerializedName("price") String price,
                         @SerializedName("category") String category,
                         @SerializedName("description") String description,
                         @SerializedName("image") String image) {
}
