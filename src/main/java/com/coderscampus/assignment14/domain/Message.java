package com.coderscampus.assignment14.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Message implements Serializable {
    private Long id;
    private String text;
    private LocalDateTime timestamp;
    private User user;
    private Channel channel;


}
