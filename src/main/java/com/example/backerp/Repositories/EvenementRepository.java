package com.example.backerp.Repositories;

import com.example.backerp.Models.Evenement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvenementRepository extends CrudRepository<Evenement,Integer> {
    Optional<Evenement> deleteEvenementByCodevenement(String codeevenment);
}
