package com.web.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.web.employee.model.User;

public interface UserService {
    User createUser(User user) throws Exception;

    List<User> getUsers(Optional<String> mobNum, Optional<UUID> userId, Optional<UUID> managerId);

    void deleteUser(UUID userId);

    void updateUser(UUID userId, User user);
}
