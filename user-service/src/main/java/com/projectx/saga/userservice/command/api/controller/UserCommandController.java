package com.projectx.saga.userservice.command.api.controller;

import com.projectx.saga.commonservice.dto.ResponseDto;
import com.projectx.saga.userservice.command.api.dto.UserDto;
import com.projectx.saga.userservice.command.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDetails")
public class UserCommandController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<ResponseDto<String>> createUser(@RequestBody UserDto userDto) {
        String result = userService.createUser(userDto);
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<String>(result,null), HttpStatus.CREATED);
    }
}
