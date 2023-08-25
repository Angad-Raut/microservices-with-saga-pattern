package com.projectx.saga.userservice.command.api.service;

import com.projectx.saga.userservice.command.api.dto.UserDto;

public interface UserService {
    String createUser(UserDto userDto);
}
