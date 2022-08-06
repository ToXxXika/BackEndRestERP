package com.example.backerp.Repositories;

import com.example.backerp.Models.Paie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaieRepository extends CrudRepository<Paie,Integer> {
    @Query("select p from Paie p where p.refconv= (select e.refconv from Evenement e where e.idevent=?1)")
    Iterable<Paie> findByRefconv(Integer idevent);
}
