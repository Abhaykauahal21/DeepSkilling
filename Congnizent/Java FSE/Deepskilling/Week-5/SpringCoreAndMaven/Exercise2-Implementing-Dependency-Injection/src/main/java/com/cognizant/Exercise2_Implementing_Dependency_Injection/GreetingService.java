package com.cognizant.Exercise2_Implementing_Dependency_Injection;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet() {
        return "Welcome to Dependency Injection!";
    }
}