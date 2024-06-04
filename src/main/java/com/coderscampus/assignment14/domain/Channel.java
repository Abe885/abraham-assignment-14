package com.coderscampus.assignment14.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter@NoArgsConstructor
public class Channel implements Serializable {
    private Long channelId;
    private String channelName;
    private List<Message> messages = new ArrayList<>();

}

