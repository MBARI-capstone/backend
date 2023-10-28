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
    private String emailAddress;
    private Integer roleId;
    private String username;

    public static UserDto userEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmailAddress(userEntity.getEmailAddress());
        userDto.setUsername(userEntity.getUsername());
        if (userEntity.getRole() != null) {
            userDto.setRoleId(userEntity.getRole().getRoleId()); // roleIdをセット
        }
        return userDto;
    }

}
