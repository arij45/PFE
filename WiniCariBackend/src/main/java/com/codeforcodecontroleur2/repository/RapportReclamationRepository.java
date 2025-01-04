package com.codeforcodecontroleur2.repository;

import com.codeforcodecontroleur2.model.RapportReclamtion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RapportReclamationRepository extends MongoRepository<RapportReclamtion,String> {

    @Query("{ '$or':[{ matriculecontroleur: ?0 },{ matriculebinome: ?0},{ matricule: ?0 }]}")
    List<RapportReclamtion> findBymatriculecontroleur(String matriculecontroleur);
}
