package com.geoview.service;

import com.geoview.model.FavoriteCountry;
import com.geoview.model.User;
import com.geoview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get user profile
     */
    public Optional<User> getUserById(String userId) {
        System.out.println("Fetching user from database for userId: " + userId);
        return userRepository.findById(userId);
    }

    /**
     * Get user by username
     */
    public Optional<User> getUserByUsername(String username) {
        System.out.println("Fetching user from database for username: " + username);
        return userRepository.findByUsername(username);
    }

    /**
     * Save or update user
     */
    public User saveUser(User user) {
        System.out.println("Saving user for userId: " + user.getId());
        return userRepository.save(user);
    }

    /**
     * Add favorite country
     */
    public User addFavoriteCountry(String userId, FavoriteCountry favoriteCountry) {
        System.out.println("Adding favorite country for userId: " + userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getFavoriteCountries().add(favoriteCountry);
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Remove favorite country
     */
    public User removeFavoriteCountry(String userId, String countryCode) {
        System.out.println("Removing favorite country for userId: " + userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getFavoriteCountries().removeIf(
                country -> country.getCountryCode().equals(countryCode)
            );
            return userRepository.save(user);
        }
        return null;
    }
}
