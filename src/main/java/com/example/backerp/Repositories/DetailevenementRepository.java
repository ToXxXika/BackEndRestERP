package com.example.backerp.Repositories;

import com.example.backerp.Models.Detailevenement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailevenementRepository extends CrudRepository<Detailevenement,Integer> {
}
