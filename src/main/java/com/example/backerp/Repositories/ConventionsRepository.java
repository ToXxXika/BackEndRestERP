package com.example.backerp.Repositories;

import com.example.backerp.Models.Conventions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionsRepository extends CrudRepository<Conventions,Integer> {
}
