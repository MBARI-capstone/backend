package com.MBARI.service;

import com.MBARI.dto.UserDto;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllRegisteredUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        // only take Registered User
        for (int i = 0; i < userEntityList.size(); i++) {
            if (userEntityList.get(i).getRole().getRoleName().equals("Registered User")) {
                userDtoList.add(UserDto.userEntityToUserDto(userEntityList.get(i)));
            }
        }
        return userDtoList;
    }
}
