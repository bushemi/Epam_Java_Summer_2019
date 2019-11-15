package com.epam.rd.userapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    @JsonProperty("id")
    private Long userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String userName;

    @JsonProperty
    private String email;

    @JsonProperty
    private Address address;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        @JsonProperty
        private String street;

        @JsonProperty
        private String suite;

        @JsonProperty
        private String city;

        @JsonProperty
        private String zipCode;

        @JsonProperty("geo")
        private Geo location;
    }

    @Data
    public static class Geo {
        @JsonProperty("lat")
        private String latitude;

        @JsonProperty("lng")
        private String longitude;
    }
}
