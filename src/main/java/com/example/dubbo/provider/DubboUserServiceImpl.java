package com.example.dubbo.provider;

import com.example.dubbo.api.UserProfile;
import com.example.dubbo.api.UserRequest;
import com.example.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class DubboUserServiceImpl implements UserService {

    @Override
    public UserProfile getUser(UserRequest request) {
        UserProfile profile = new UserProfile();
        profile.setUserId(request.getUserId());
        profile.setUsername("dubbo-test-user");
        profile.setAge(18);
        profile.setEmail("dubbo@example.com");
        return profile;
    }
}
