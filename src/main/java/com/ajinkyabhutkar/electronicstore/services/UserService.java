package com.ajinkyabhutkar.electronicstore.services;


import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.dtos.UserDto;

import java.util.List;


public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,Long id);

    //delete
    void deleteuser(Long id);

    //get all
    PageableResponse<UserDto> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);

    //get single user by id
    UserDto getUserById(Long id);

    //get by email
    UserDto getUserByEmail(String email);

    //search
    List<UserDto> searchUser(String keyword);

}
