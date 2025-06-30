package com.github.paohaijiao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.paohaijiao.anno.JSONField;
import com.github.paohaijiao.anno.JSONIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class JProduct1 {
    @JsonProperty("product")
    private ProductDTO product;

    @NoArgsConstructor
    @Data
    public static class ProductDTO {
        @JsonProperty("id")
        private Integer id;
        @JSONIgnore
        @JsonProperty("serialNumber")
        private Long serialNumber;
        @JsonProperty("isAvailable")
        private Boolean isAvailable;
        @JsonProperty("productionDate")
        @JSONField(name = "haha", format = "yyyy-MM-dd HH:mm:ss")
        private Date productionDate;
        @JsonProperty("price")
        private Double price;
        @JsonProperty("discountRate")
        private Double discountRate;
        @JsonProperty("specifications")
        private SpecificationsDTO specifications;
        @JsonProperty("tags")
        private List<String> tags;
        @JsonProperty("inventory")
        private InventoryDTO inventory;

        @NoArgsConstructor
        @Data
        public static class SpecificationsDTO {
            @JsonProperty("weight")
            private Double weight;
            @JsonProperty("dimensions")
            private DimensionsDTO dimensions;

            @NoArgsConstructor
            @Data
            public static class DimensionsDTO {
                @JsonProperty("length")
                private Integer length;
                @JsonProperty("width")
                private Integer width;
                @JsonProperty("height")
                private Integer height;
            }
        }

        @NoArgsConstructor
        @Data
        public static class InventoryDTO {
            @JsonProperty("warehouse1")
            private Integer warehouse1;
            @JsonProperty("warehouse2")
            private Integer warehouse2;
            @JsonProperty("total")
            private Integer total;
        }
    }
}
