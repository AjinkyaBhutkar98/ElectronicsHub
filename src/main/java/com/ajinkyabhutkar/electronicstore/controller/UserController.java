package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.dtos.FileResponse;
import com.ajinkyabhutkar.electronicstore.dtos.UserDto;
import com.ajinkyabhutkar.electronicstore.services.FileUploadService;
import com.ajinkyabhutkar.electronicstore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;
    private FileUploadService fileUploadService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @Autowired
    public UserController(UserService userService, FileUploadService fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

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
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "size",defaultValue = "3") int size,
            @RequestParam(name= "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc") String sortDir

    ){
        PageableResponse<UserDto> allUsers=userService.getAllUsers(pageNo,size,sortBy,sortDir);
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

    //file upload
    @PostMapping("/images/{id}")
    public ResponseEntity<FileResponse> uploadFile(
            @RequestParam(name = "userImage") MultipartFile file,
            @PathVariable Long id
    ) throws IOException {

        UserDto user=userService.getUserById(id);
        user.setImage(file.getName());
        UserDto userDto=userService.updateUser(user,id);

        String imageName=fileUploadService.uploadFile(file,imageUploadPath);

        FileResponse fileResponse=new FileResponse();
        fileResponse.setFileName(imageName);
        fileResponse.setSuccess(true);
        fileResponse.setMessege("Image Uploaded Successfully");
        fileResponse.setHttpStatus(HttpStatus.CREATED);

        return new ResponseEntity<>(fileResponse,HttpStatus.CREATED);
    }
}
