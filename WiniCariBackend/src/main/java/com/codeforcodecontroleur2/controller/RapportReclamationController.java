package com.codeforcodecontroleur2.controller;

import com.codeforcodecontroleur2.model.RapportReclamtion;
import com.codeforcodecontroleur2.repository.RapportReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rapportreclamation")
public class RapportReclamationController {


        @Autowired
        private RapportReclamationRepository rapportreclamationRepository;

        @RequestMapping(value = "add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        public String saveRapportReclamation(@RequestBody RapportReclamtion e) {

            rapportreclamationRepository.insert(e);

            return "Added RapportReclamation id " +e.getId();
        }


        @RequestMapping(value = "all", method = RequestMethod.GET)

        public List<RapportReclamtion> getAllRapportReclamations() {

            return rapportreclamationRepository.findAll();
        }

       @RequestMapping(value = "{id}", method = RequestMethod.DELETE)

       public String deleteRapportreclamation(@PathVariable String id) {

         rapportreclamationRepository.delete(id);

         return "Delete rapportreclamation for id " + id;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)

    public String updaterapportreclamation(@RequestBody RapportReclamtion e) {

        rapportreclamationRepository.save(e);

        return "Updated rapportreclamation for id " + e.getId();
    }


        @RequestMapping(value = "findBymatricule", method = RequestMethod.GET)

        public List<RapportReclamtion> getrapportreclamationBymatricule(String matriculecontroleur) {

            return rapportreclamationRepository.findBymatriculecontroleur(matriculecontroleur);
        }



    }
