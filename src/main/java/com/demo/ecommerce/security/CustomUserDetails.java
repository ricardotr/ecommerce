package com.demo.ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails{
    String getEmail();
}
