package com.example.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(UserRequest request, StreamObserver<UserProfile> responseObserver) {
        UserProfile profile = UserProfile.newBuilder()
                .setUserId(request.getUserId())
                .setUsername("test-user")
                .setAge(18)
                .setStatus(UserStatus.USER_STATUS_ACTIVE)
                .setEmail("test@example.com")
                .build();

        responseObserver.onNext(profile);
        responseObserver.onCompleted();
    }

    @Override
    public void listUsers(UserRequest request, StreamObserver<UserProfile> responseObserver) {
        UserProfile profile = UserProfile.newBuilder()
                .setUserId(request.getUserId())
                .setUsername("test-user")
                .setAge(18)
                .setStatus(UserStatus.USER_STATUS_ACTIVE)
                .setEmail("test@example.com")
                .build();

        responseObserver.onNext(profile);
        responseObserver.onCompleted();
    }
}
