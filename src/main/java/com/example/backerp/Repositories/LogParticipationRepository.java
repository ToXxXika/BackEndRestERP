package com.example.backerp.Repositories;

import com.example.backerp.Models.Logparticipation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogParticipationRepository extends CrudRepository<Logparticipation,Integer> {
}
