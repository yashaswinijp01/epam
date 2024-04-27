package com.example.pmt_tool.services.impl;
import com.example.pmt_tool.dto.UserDto;
import com.example.pmt_tool.entities.User;
import com.example.pmt_tool.repositories.UserRepo;
import com.example.pmt_tool.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto findByUserName(String userName) {
        User user= userRepo.findByUserName(userName);
        if(user==null){
            return null;
        }else{
            return modelMapper.map(user,UserDto.class);
        }
    }

    @Override
    public void save(UserDto userDto) {
        User user=modelMapper.map(userDto,User.class);
        userRepo.save(user);
    }

}
