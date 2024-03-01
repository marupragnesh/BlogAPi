package com.example.BlogAPi.ServiceImpl;

import com.example.BlogAPi.Dto.UserDto;
import com.example.BlogAPi.Entity.User;
import com.example.BlogAPi.Exception.CustomException;
import com.example.BlogAPi.Service.Repository.UserRepository;
import com.example.BlogAPi.Service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto getUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("user", "User id is not Found :", id));
        return returnEntityDto(user);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = returnEntity(userDto);
        userRepository.save(user);
        return returnEntityDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("user", "User id is not Found :", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setAbout(userDto.getAbout());
        return returnEntityDto(user);


    }

    @Override
    public Boolean deleteUser(Long id) throws CustomException {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("userId", "id", id));
        userRepository.delete(user);
        return true;
    }


    public User returnEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto returnEntityDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }


}
