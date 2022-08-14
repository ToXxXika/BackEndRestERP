package com.example.backerp.Services;

import com.example.backerp.Models.Utilisateur;
import com.example.backerp.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository UR;
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = UR.findByMail(mail);
        if (utilisateur.get() == null) {
            throw new UsernameNotFoundException("User not found with mail: " + mail);
        }
        return (UserDetails) utilisateur.get();
    }
}
