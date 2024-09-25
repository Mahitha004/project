package com.web.employee.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.employee.model.Manager;
import com.web.employee.model.User;
import com.web.employee.repository.ManagerRepository;
import com.web.employee.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public User createUser(User user) throws Exception {
        user.setPanNum(user.getPanNum().toUpperCase());
        user.setMobNum(formatMobileNumber(user.getMobNum()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(null); // No update yet

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers(Optional<String> mobNum, Optional<UUID> userId, Optional<UUID> managerId) {
        if (mobNum.isPresent()) {
            return List.of(userRepository.findByMobNum(mobNum.get()).orElseThrow(() -> new RuntimeException("User not found")));
        } else if (userId.isPresent()) {
            return List.of(userRepository.findById(userId.get()).orElseThrow(() -> new RuntimeException("User not found")));
        } else if (managerId.isPresent()) {
            Manager manager = managerRepository.findById(managerId.get()).orElseThrow(() -> new RuntimeException("Manager not found"));
            return userRepository.findByManager(manager);
        }
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public void updateUser(UUID userId, User updateData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullName(updateData.getFullName());
        user.setMobNum(updateData.getMobNum());
        user.setPanNum(updateData.getPanNum().toUpperCase());
        user.setManager(updateData.getManager());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    private String formatMobileNumber(String mobNum) {
        return mobNum; // Placeholder for number formatting logic
    }
}
