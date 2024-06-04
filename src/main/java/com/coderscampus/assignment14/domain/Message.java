package com.coderscampus.assignment14.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Message implements Serializable {
    private Long id;
    private String text;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("user")
    private User user;

    @JsonProperty("channel")
    private Channel channel;


}
