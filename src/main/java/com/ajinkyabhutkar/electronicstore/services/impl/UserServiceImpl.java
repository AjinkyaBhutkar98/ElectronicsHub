package com.ajinkyabhutkar.electronicstore.services.impl;

import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.dtos.UserDto;
import com.ajinkyabhutkar.electronicstore.entities.User;
import com.ajinkyabhutkar.electronicstore.exceptions.ResourceNotFoundException;
import com.ajinkyabhutkar.electronicstore.repositories.UserRepo;
import com.ajinkyabhutkar.electronicstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        //dto to entity
        User user=dtoToEntity(userDto);
        User savedUser=userRepo.save(user);
        //entity to dto
        UserDto userDto1=entityToDto(savedUser);
        return userDto1;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {

        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id!!"+id));

        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setImage(userDto.getImage());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updatedUser=userRepo.save(user);

        return entityToDto(updatedUser);
    }

    @Override
    public void deleteuser(Long id) {

        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id!!"+id));
        //delete user
        userRepo.delete(user);

    }

    @Override
    public PageableResponse<UserDto> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

       Page<User> allUsers=userRepo.findAll(pageable);

       Page<UserDto> userDtos=allUsers.map(user->modelMapper.map(user,UserDto.class));
        return  PageableResponse.fromPage(userDtos);
    }

    @Override
    public UserDto getUserById(Long id) {

        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id!!"+id));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user=userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("email not found"));

        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        List<User> users=userRepo.findByNameContaining(keyword).orElseThrow(()->new RuntimeException("User not found!!"));

        return users.stream().map(user->entityToDto(user)).collect(Collectors.toList());
    }

    private User dtoToEntity(UserDto userDto){

//        User user=new User();
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setGender(userDto.getGender());
//        user.setPassword(user.getPassword());
//        user.setImage(userDto.getImage());

        return modelMapper.map(userDto,User.class);

    }

    private UserDto entityToDto(User user){

//        UserDto userDto=new UserDto();
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setGender(user.getGender());
//        userDto.setPassword(user.getPassword());
//        userDto.setImage(user.getImage());

        return modelMapper.map(user,UserDto.class);
    }
}
