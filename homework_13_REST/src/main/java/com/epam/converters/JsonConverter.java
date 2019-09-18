package com.epam.converters;

public interface JsonConverter<T> {

    String toJson(T entity);

    T fromJson(String json);

}
