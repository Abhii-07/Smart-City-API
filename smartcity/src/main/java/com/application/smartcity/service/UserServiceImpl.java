package com.application.smartcity.service;

import com.application.smartcity.exception.UserException;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.model.User;
import com.application.smartcity.model.Wallet;
import com.application.smartcity.repository.UserRepository;
import com.application.smartcity.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;


    @Override
    public User createUser(User user) throws UserException {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new UserException("Username and password cannot be empty!");
        }
        // Additional validations for uniqueness or any other business rules

        User createdUser = userRepository.save(user);

        // Create a wallet associated with the created user
        Wallet wallet = new Wallet();
        wallet.setUser(createdUser);
        wallet.setBalance(0.0); // Set initial balance as needed
        walletRepository.save(wallet);

        return createdUser;
    }

    @Override
    public User getUserById(Integer userId) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with ID " + userId + " not found!"));
        return user;
    }

    @Override
    public List<User> getAllUsers() throws UserException {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer userId, User updatedUser) throws UserException {
        User existingUser = getUserById(userId);

        // Update user properties
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setAssociatedServices(updatedUser.getAssociatedServices());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) throws UserException {
        if (!userRepository.existsById(userId)) {
            throw new UserException("User with ID " + userId + " not found!");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsersByService(ServiceType serviceType) throws UserException {
        List<User> allUsers = getAllUsers();
        return allUsers.stream()
                .filter(user -> user.getAssociatedServices().contains(serviceType))
                .collect(Collectors.toList());
    }
}
