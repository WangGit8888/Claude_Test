package com.example.dubbo.consumer;

import com.example.dubbo.api.UserProfile;
import com.example.dubbo.api.UserRequest;
import com.example.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    public UserProfile getUser(String userId) {
        UserRequest request = new UserRequest();
        request.setUserId(userId);
        return userService.getUser(request);
    }
}
