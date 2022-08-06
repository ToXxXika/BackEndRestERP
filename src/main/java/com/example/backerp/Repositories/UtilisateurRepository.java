package com.example.backerp.Repositories;

import com.example.backerp.Models.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByCin(String cin);
}
