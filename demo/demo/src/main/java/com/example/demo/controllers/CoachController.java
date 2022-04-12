package com.example.demo.controllers;

import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;
import com.example.demo.service.CoachService;
import com.example.demo.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class CoachController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final CoachService coachService;


    private final SequenceGeneratorService sequenceGeneratorService;

    public CoachController(CoachService coachService, SequenceGeneratorService sequenceGeneratorService) {
        this.coachService = coachService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @PostMapping("/coach/create")
    public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
        LOG.info("saving coach");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/coach/create").toUriString());
        coach.setId(sequenceGeneratorService.generateSequence(Coach.SEQUENCE_NAME));
        return ResponseEntity.created(uri).body(coachService.saveCoach(coach));
    }

    @PostMapping("/coach/{coachId}/add-fighter/{fighterId}")
    public ResponseEntity<Coach> addFighter(@PathVariable("coachId")int coachId, @PathVariable("fighterId")int fighterId) {
        LOG.info("Saving fighter with ID : {} to coach with ID : {}", fighterId, coachId);
        return ResponseEntity.ok().body(coachService.addFighter(coachId,fighterId));
    }

    @PostMapping("/coach/{coachId}/remove-fighter/{fighterId}")
    public ResponseEntity<Coach> removeFighter(@PathVariable("coachId")int coachId, @PathVariable("fighterId")int fighterId) {
        LOG.info("Removing fighter with ID : {} to coach with ID : {}", fighterId, coachId);
        return ResponseEntity.ok().body(coachService.removeFighter(coachId,fighterId));
    }

    @GetMapping("/coach/{id}/fighters")
    public ResponseEntity<List<Fighter>> getFightersFromCoach(@PathVariable("id")int id) {
        LOG.info("Getting fighters from coach with ID : {}", id);
        return ResponseEntity.ok().body(coachService.getFightersFromCoach(id));
    }

    @GetMapping("/coach/{name}")
    public int getIdFromName(@PathVariable("name")String name) {
        LOG.info("Getting id from coach with name : {}", name);
        return coachService.getIdFromName(name);
    }


}
