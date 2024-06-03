package com.coderscampus.assignment14.repository;

import com.coderscampus.assignment14.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MessageRepository {
    private final List<Message> messages = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public Message save(Message message) {
        message.setId(counter.incrementAndGet());
        messages.add(message);
        return message;
    }
    public List<Message> findByChannelId(Long channelId) {
        return messages.stream()
                .filter(message -> message.getChannel().getChannelId().equals(channelId))
                .toList();
    }
}
