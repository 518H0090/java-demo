package com.trunghieu.consumer.converter;

import com.google.gson.Gson;
import com.trunghieu.consumer.payload.Student;

public class JsonConverter {

    private static final Gson gson = new Gson();

    public static Student convertStringToStudent(String jsonString) {
        return gson.fromJson(jsonString, Student.class);
    }
}
