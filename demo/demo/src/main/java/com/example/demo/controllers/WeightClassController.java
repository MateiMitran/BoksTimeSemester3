package com.example.demo.controllers;

import com.example.demo.model.Fighter;
import com.example.demo.model.Weight_Class;
import com.example.demo.service.FighterService;
import com.example.demo.service.WeightClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class WeightClassController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final WeightClassService weightClassService;

    private final FighterService fighterService;

    public WeightClassController(WeightClassService weightClassService, FighterService fighterService) {
        this.weightClassService = weightClassService;
        this.fighterService = fighterService;
    }

    @GetMapping("/weight_classes")
    public ResponseEntity<List<Weight_Class>> getAllWeightClasses() {
        LOG.info("Getting all weight classes.");
        return ResponseEntity.ok().body(weightClassService.getWeightClasses());
    }
    @GetMapping("/weight_class/{weightClassId}")
    public Weight_Class getWeightClass(@PathVariable int weightClassId) {
        LOG.info("Getting weight class with ID: {}.", weightClassId);
        return weightClassService.getWeightClassById(weightClassId);
    }
    @PostMapping("/weight_class/create")
    public ResponseEntity<Weight_Class> addNewWeightClasses(@RequestBody Weight_Class weightClass) {
        LOG.info("Saving weight class");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/weight_class/create").toUriString());
        return ResponseEntity.created(uri).body(weightClassService.saveWeightClass(weightClass));
    }
    @GetMapping("/weight_class/{name}/fighters")
    public ResponseEntity<List<Fighter>> getFightersFromWeightClass(@PathVariable String name) {
        LOG.info("Getting all fighters from weight class : {}", name);
        List<Fighter> temp = new ArrayList<>();
        List<Fighter> weightClassList = weightClassService.getFighters(name);
        for (Fighter x : weightClassList) {
            LOG.info ("Name from heavyweight is : {}", x.getName());
        }
        List<Fighter> fighterList = fighterService.getFighters();
        for (Fighter x : weightClassList) {
            for (Fighter y : fighterList) {
                LOG.info("First fighter is {} and second is {}", x.getName(), y.getName());
                if (x.getName().equals(y.getName())) {
                    temp.add(y);
                }
            }
        }
        return ResponseEntity.ok().body(temp);
    }
    @PostMapping("/weight_class/{name}/add/{fighterId}")
    public void addFighterToWeightClass(@PathVariable String name, @PathVariable int fighterId) {
        LOG.info("Adding fighter with id {} to weight class {}",fighterId, name);
        weightClassService.addFighterToWeightClass(name,fighterService.getFighterById(fighterId));
    }



}
