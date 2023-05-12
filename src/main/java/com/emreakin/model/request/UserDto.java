package com.emreakin.model.request;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    private int age;
    private boolean active;
}
