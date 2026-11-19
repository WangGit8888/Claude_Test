package com.example.dubbo.api;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserProfile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userId;
    private String username;
    private Integer age;
    private String email;
}
