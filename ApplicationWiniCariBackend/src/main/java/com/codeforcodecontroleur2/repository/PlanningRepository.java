package com.codeforcodecontroleur2.repository;

import com.codeforcodecontroleur2.model.Planning;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PlanningRepository extends MongoRepository<Planning,String> {


    //public List<Planning> findBymatriculecontroleur(String matriculecontroleur , String matriculebinome);
    @Query("{ '$or':[{ matriculecontroleur: ?0 },{ matriculebinome: ?0}]}")
    List<Planning> findBymatriculecontroleur(String matriculecontroleur);
}
