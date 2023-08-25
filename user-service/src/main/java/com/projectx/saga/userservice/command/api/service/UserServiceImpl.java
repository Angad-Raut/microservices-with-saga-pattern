package com.projectx.saga.userservice.command.api.service;

import com.projectx.saga.commonservice.utils.CommonUtils;
import com.projectx.saga.userservice.command.api.command.UserCreateCommand;
import com.projectx.saga.userservice.command.api.dto.UserDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private transient CommandGateway commandGateway;

    @Override
    public String createUser(UserDto userDto) {
        UserCreateCommand userCreateCommand = UserCreateCommand.builder()
                .userId(UUID.randomUUID().toString())
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .email(userDto.getEmail())
                .mobileNumber(userDto.getMobileNumber())
                .address(userDto.getAddress())
                .cardDetails(userDto.getCardDetails())
                .build();
        commandGateway.sendAndWait(userCreateCommand);
        return CommonUtils.ADD_USER;
    }
}
