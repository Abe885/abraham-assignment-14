package com.coderscampus.assignment14.web;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.service.ChannelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }



    @GetMapping("/channels")
    public String getChannels(ModelMap model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/welcome";
        }
        List<Channel> channels = channelService.getAllChannels();
        model.put("channels", channels);
        model.put("channel", new Channel());
        return "channels";
    }

    @PostMapping("/createChannel")
    public String createChannel(Channel channel) {
        channelService.saveChannel(channel);
        return "redirect:/channels";
    }

    @GetMapping("/channels/{channelId}")
    public String getChannel(@PathVariable Long channelId, ModelMap model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/welcome";
        }
        Channel channel = channelService.findById(channelId);
        model.put("channel", channel);
        return "channel";
    }
}
