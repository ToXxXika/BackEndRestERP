package com.example.backerp.Repositories;

import com.example.backerp.Models.Associations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationsRepository extends CrudRepository<Associations,String> {
}
