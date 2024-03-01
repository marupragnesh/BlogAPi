package com.example.BlogAPi.Service;

import com.example.BlogAPi.Dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUser();


    UserDto updateUser(UserDto userDto, Long id);

    Boolean deleteUser(Long id);


    UserDto getUser(Long id);
}
