package com.coderscampus.assignment14.web;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.MessageService;
import com.coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService ;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/{channelId}")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long channelId) {
        List<Message> messages = messageService.getMessagesByChannelId(channelId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Message> createMessage(@RequestBody Message message, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}
