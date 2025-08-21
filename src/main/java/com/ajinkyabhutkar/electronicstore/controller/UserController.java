package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import com.ajinkyabhutkar.electronicstore.dtos.UserDto;
import com.ajinkyabhutkar.electronicstore.entities.User;
import com.ajinkyabhutkar.electronicstore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1=userService.createUser(userDto);
        System.out.println("User saved Successfully");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Long id){

        UserDto updatedUserDto=userService.updateUser(userDto,id);
        System.out.println("User Updated Successfully");
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto getUserDto=userService.getUserById(id);
        return new ResponseEntity<>(getUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){

        userService.deleteuser(id);

        //use this way to send back response
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessege("User deleted Successfully");
        apiResponse.setSuccess(true);
        apiResponse.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllusers(){
        List<UserDto> allUsers=userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserDto>> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.searchUser(name),HttpStatus.OK);
    }
}
