package com.application.smartcity.service;

import com.application.smartcity.exception.UserException;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UserException;
    User getUserById(Integer userId) throws UserException;
    List<User> getAllUsers() throws UserException;
    User updateUser(Integer userId, User updatedUser) throws UserException;
    void deleteUser(Integer userId) throws UserException;

    List<User> getUsersByService( ServiceType serviceType) throws UserException;
}
