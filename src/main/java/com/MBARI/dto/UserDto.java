package com.MBARI.dto;

import com.MBARI.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;

    public static UserDto userEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        return userDto;
    }

}
