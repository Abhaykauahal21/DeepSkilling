package com.cognizant.Exercise2_Implementing_Dependency_Injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final GreetingService greetingService;

    @Autowired
    public UserService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void displayMessage() {
        System.out.println(greetingService.greet());
    }
}