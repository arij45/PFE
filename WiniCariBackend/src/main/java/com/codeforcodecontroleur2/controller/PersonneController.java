package com.codeforcodecontroleur2.controller;


import com.codeforcodecontroleur2.model.Personne;
import com.codeforcodecontroleur2.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/controleur")

    public class PersonneController {

    @Autowired
    private PersonneRepository personneRepository;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String saveControleur(@RequestBody Personne e) {


        personneRepository.insert(e);

        return "Added Controleur id " + e.getId();
    }


    @RequestMapping(value = "all", method = RequestMethod.GET)

    public List<Personne> getAllControleurs() {


        return personneRepository.findAll();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)

    public String deleteControleur(@PathVariable String id) {

        personneRepository.delete(id);

        return "Delete cont for id " + id;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)

    public  String updateControleur(@RequestBody Personne e) {

        personneRepository.save(e);

        return "Updated Controleur for id " + e.getId();
    }

    @RequestMapping(value = "findBycodesecret", method = RequestMethod.GET)

    public Personne getcontroleurBycodesecret(String code_secret) {

        return personneRepository.findBycodesecret(code_secret);
    }



}
