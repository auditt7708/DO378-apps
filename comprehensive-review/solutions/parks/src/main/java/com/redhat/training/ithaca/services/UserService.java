package com.redhat.training.ithaca.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    public boolean authenticate(String username, String password) {
        // Stub implementation
        return username.equals("admin") && password.equals("redhat");
    }

}