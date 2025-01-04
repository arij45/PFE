package com.codeforcodecontroleur2.repository;

import com.codeforcodecontroleur2.model.Planning_num;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface Planning_numRepository extends MongoRepository<Planning_num,String> {

    @Query("{ '$or':[{ matriculecontroleur: ?0 },{ matriculebinome: ?0}]}")
    List<Planning_num> findBymatriculecontroleur(String matriculecontroleur);
}
