package com.trunghieu.consumer.payload;

import lombok.*;

@Getter
@Setter
@ToString
public class Student {
    private Integer id;

    private String name;

    private String address;

    private String phone;
}
