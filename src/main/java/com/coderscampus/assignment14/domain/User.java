package com.coderscampus.assignment14.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private Long userId;
    private String username;
    private String password;

}
