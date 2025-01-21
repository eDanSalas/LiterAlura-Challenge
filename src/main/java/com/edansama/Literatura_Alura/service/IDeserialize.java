package com.edansama.Literatura_Alura.service;

public interface IDeserialize {
    <T> T getInformation(String json, Class<T> tClass);
}
