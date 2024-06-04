package com.coderscampus.assignment14.repository;

import com.coderscampus.assignment14.domain.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ChannelRepository {
    private final List<Channel> channels = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public Channel save(Channel channel) {
        channel.setChannelId(counter.incrementAndGet());
        channels.add(channel);
        return channel;
    }

    public List<Channel> findAll() {
        return channels;
    }

    public Channel findById(Long channelId) {
        return channels.stream()
                .filter(channel -> channel.getChannelId().equals(channelId))
                .findFirst()
                .orElse(null);
    }
}
