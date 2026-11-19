package com.example.dubbo.controller;

import com.example.dubbo.api.UserProfile;
import com.example.dubbo.consumer.UserConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
@RequiredArgsConstructor
public class DubboTestController {

    private final UserConsumer userConsumer;

    @GetMapping("/user")
    public UserProfile getUser(@RequestParam String userId) {
        return userConsumer.getUser(userId);
    }
}
