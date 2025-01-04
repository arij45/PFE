package com.codeforcodecontroleur2.controller;

import com.codeforcodecontroleur2.model.Personne;
import com.codeforcodecontroleur2.model.Planning;
import com.codeforcodecontroleur2.model.Planning_num;
import com.codeforcodecontroleur2.repository.PersonneRepository;
import com.codeforcodecontroleur2.repository.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planning")

public class PlanningController {

    @Autowired
    private PlanningRepository planningRepository;

    @Autowired
    private PersonneRepository ControlleurRepository;


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String savePlanning(@RequestBody Planning e) {


        planningRepository.insert(e);

        return "Added Planning id " + e.getId();
    }


    @RequestMapping(value = "all", method = RequestMethod.GET)

    public List<Planning> getAllPlannings() {

        List<Planning> allPlannings = planningRepository.findAll();

        return allPlannings;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)

    public String deletePlanning(@PathVariable String id) {

        planningRepository.delete(id);

        return "Delete planning for id " + id;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)

    public String updatePlanning(@RequestBody Planning e) {

        planningRepository.save(e);

        return "Updated Planning for id " + e.getId();
    }


    @RequestMapping(value = "findBymatricule", method = RequestMethod.GET)
    public List<Planning_num> getPlanningByMatricule(String matriculecontroleur) {

       List<Personne>  controleurs = new ArrayList<Personne>();
        List<Planning_num> newList = new ArrayList<Planning_num>();
        List<Planning> planningList = planningRepository.findBymatriculecontroleur(matriculecontroleur);
        for (int i = 0; i < planningList.size(); i++) {

            if (planningList.get(i).getMatriculecontroleur().equals(matriculecontroleur))
            {

                controleurs = ControlleurRepository.findByMatricule(planningList.get(i).getMatriculebinome());
                for (int j = 0; j < controleurs.size(); j++)
                {
                    Planning_num planning_num = new Planning_num();
                    planning_num.setBus(planningList.get(i).getBus());
                    planning_num.setDate(planningList.get(i).getDate());
                    planning_num.setMatriculebinome(planningList.get(i).getMatriculebinome());
                    planning_num.setMatriculecontroleur(planningList.get(i).getMatriculecontroleur());
                    planning_num.setHeure(planningList.get(i).getHeure());
                    planning_num.setNomcontroleur(planningList.get(i).getNomcontroleur());
                    planning_num.setNombinome(planningList.get(i).getNombinome());
                    planning_num.setParametre(planningList.get(i).getParametre());
                    planning_num.setLigne(planningList.get(i).getLigne());
                    planning_num.setNumero(controleurs.get(j).getNumero());
                    planning_num.setStation(planningList.get(i).getStation());
                    newList.add(planning_num);
                }

            }
            else {
                    controleurs = ControlleurRepository.findByMatricule(planningList.get(i).getMatriculecontroleur());
                    for (int j = 0; j < controleurs.size(); j++)
                    {
                        Planning_num planning_num = new Planning_num();
                        planning_num.setBus(planningList.get(i).getId());
                        planning_num.setBus(planningList.get(i).getBus());
                        planning_num.setDate(planningList.get(i).getDate());
                        planning_num.setMatriculebinome(planningList.get(i).getMatriculebinome());
                        planning_num.setMatriculecontroleur(planningList.get(i).getMatriculecontroleur());
                        planning_num.setHeure(planningList.get(i).getHeure());
                        planning_num.setNomcontroleur(planningList.get(i).getNomcontroleur());
                        planning_num.setNombinome(planningList.get(i).getNombinome());
                        planning_num.setParametre(planningList.get(i).getParametre());
                        planning_num.setLigne(planningList.get(i).getLigne());
                        planning_num.setNumero(controleurs.get(j).getNumero());
                        planning_num.setStation(planningList.get(i).getStation());
                        newList.add(planning_num);
                    }



            }







        }
        return newList;
    }

}
