package com.external.api.consumeapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

public record ProductResponseDTO(@SerializedName("id") @JsonIgnore String id,
                                 @SerializedName("title") String title,
                                 @SerializedName("price") String price,
                                 @SerializedName("category") String category,
                                 @SerializedName("description") String description,
                                 @SerializedName("image") String image) {
}
