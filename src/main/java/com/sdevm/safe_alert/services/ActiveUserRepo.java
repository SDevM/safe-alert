package com.sdevm.safe_alert.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sdevm.safe_alert.models.User;

import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class ActiveUserRepo {
    private Map<String, User> activeUsers = new HashMap<String, User>();

    public void addUser(String sessionId, User user) {
        activeUsers.put(sessionId, user);
    }

    public User getUser(String sessionId) {
        return activeUsers.get(sessionId);
    }

    public void removeUser(String sessionId) {
        activeUsers.remove(sessionId);
    }
}