package com.example.backerp.Repositories;

import com.example.backerp.Models.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository

public interface UtilisateurRepository extends CrudRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByCin(String cin);
    //find user by mail
    Optional<Utilisateur> findByMail(String mail);
    @Transactional
    Optional<Utilisateur> deleteUtilisateurByCin(String cin);
}
