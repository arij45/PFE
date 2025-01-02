package com.codeforcodecontroleur2.repository;

import com.codeforcodecontroleur2.model.Personne;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonneRepository extends MongoRepository<Personne,String> {

     Personne findBycodesecret(String code_secret);
     @Query("{ '$or':[{ matriculecontroleur: ?0 },{ matriculebinome: ?0}]}")

     List<Personne> findByMatricule(String matriculecontroleur);

}



