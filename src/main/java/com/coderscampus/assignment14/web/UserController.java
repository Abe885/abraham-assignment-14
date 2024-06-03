package com.coderscampus.assignment14.web;

import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;
    private final ChannelService channelService;


    public UserController(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @GetMapping("/welcome")
    public String getWelcome(ModelMap model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            model.put("channels", channelService.getAllChannels());
            return "channels";
        }
        model.put("user", new User());
        return "welcome";
    }


    @PostMapping("/register")
    public String registerUser(User user, HttpSession session, ModelMap model) {
        try {
            userService.saveUser(user);
            session.setAttribute("username", user.getUsername());
            return "redirect:/channels";
        } catch (IllegalArgumentException e) {
            model.put("error", "Username already exists. Please choose a different username.");
            model.put("user", user);
            return "welcome";
        }
    }

    @GetMapping("/")
    public String redirectToWelcome(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/channels";
        }
        return "redirect:/welcome";
    }


}
