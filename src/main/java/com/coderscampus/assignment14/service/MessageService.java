package com.coderscampus.assignment14.service;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesByChannelId(Long channelId) {
        if (channelId == null) {
            throw new IllegalArgumentException("Channel ID cannot be null");
        }
        return messageRepository.findByChannelId(channelId);
    }

    public Message saveMessage(Message message) {
        if (message == null || message.getUser() == null || message.getChannel() == null) {
            throw new IllegalArgumentException("Message, User, and Channel cannot be null");
        }
        return messageRepository.save(message);
    }
}
