package com.example.backerp.Services;

import com.example.backerp.Models.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Utilisateur findByMail(String Mail);
}
