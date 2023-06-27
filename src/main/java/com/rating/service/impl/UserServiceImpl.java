package com.rating.service.impl;

import com.rating.dtos.UserResponse;
import com.rating.models.Product;
import com.rating.models.Userr;
import com.rating.repositories.UserRepo;
import com.rating.service.UserService;
import com.rating.utils.DtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private DtoConverter dtoConverter;

    UserServiceImpl(UserRepo userRepo, DtoConverter dtoConverter){
        this.userRepo = userRepo;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public ResponseEntity<?> addUser(UserResponse userData) {
        Userr userr = dtoConverter.userDtoToEntity(userData);
        userRepo.save(userr);

        return new ResponseEntity<>(userData, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getUser(String userId) {

        Userr user = checkAndFetchUserAvailability(userId);
        if(user == null){
            return new ResponseEntity<>("User not available!", HttpStatus.OK);
        }
        UserResponse userDto = dtoConverter.userEntityToDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<Userr> userrs = userRepo.findAll();
        if(userrs.isEmpty()){
            return new ResponseEntity<>("Users not available!", HttpStatus.OK);
        }
        List<UserResponse> userDtos= new ArrayList<>();

        for(Userr userr : userrs){
            userDtos.add(dtoConverter.userEntityToDto(userr));
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    public Userr checkAndFetchUserAvailability(String userId){
        Optional<Userr> user = userRepo.findById(Long.valueOf(userId));
        return user.orElse(null);
    }
}
