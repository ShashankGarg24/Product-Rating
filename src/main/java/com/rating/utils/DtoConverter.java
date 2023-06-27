package com.rating.utils;

import com.rating.dtos.UserResponse;
import com.rating.models.Userr;
import org.springframework.stereotype.Service;


@Service
public class DtoConverter {

    public UserResponse userEntityToDto(Userr userr){
        UserResponse userDto = new UserResponse();
        userDto.setUserId(userr.getUserId());
        userDto.setUsername(userr.getUsername());
        userDto.setPhoneNumber(userr.getPhoneNumber());

        return userDto;
    }

    public Userr userDtoToEntity(UserResponse userDto){
        Userr userr = new Userr();
        userr.setUserId(userDto.getUserId());
        userr.setUsername(userDto.getUsername());
        userr.setPhoneNumber(userDto.getPhoneNumber());

        return userr;
    }

//    public ProductResponse productEntityToDto(Product product){
//        ProductResponse productDto = new ProductResponse();
//        productDto.setUserId(user.getUserId());
//        productDto.setUsername(user.getUsername());
//        productDto.setPhoneNumber(user.getPhoneNumber());
//
//        return userDto;
//    }
//
//    public User userDtoToEntity(UserResponse userDto){
//        User user = new User();
//        user.setUserId(userDto.getUserId());
//        user.setUsername(userDto.getUsername());
//        user.setPhoneNumber(userDto.getPhoneNumber());
//
//        return user;
//    }
}
