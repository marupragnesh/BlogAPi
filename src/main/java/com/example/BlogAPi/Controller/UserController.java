package com.example.BlogAPi.Controller;

import com.example.BlogAPi.Dto.ApiResponse;
import com.example.BlogAPi.Dto.UserDto;
import com.example.BlogAPi.Exception.CustomException;
import com.example.BlogAPi.Service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long uid) {
        UserDto user = userService.getUser(uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);

    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        UserDto user = userService.updateUser(userDto, id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/user/{userid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Long id) throws CustomException {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("Successfull", true), HttpStatus.OK);
        // Above Line Understand
    }
}
