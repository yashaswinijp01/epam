package com.example.pmt_tool.services;

import com.example.pmt_tool.dto.UserDto;

public interface UserService {
    UserDto findByUserName(String userName);

    void save(UserDto user);
}
