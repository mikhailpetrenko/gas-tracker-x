package com.devmountain.gasTrackerX.services;

import com.devmountain.gasTrackerX.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(UserDto userDto);
}
