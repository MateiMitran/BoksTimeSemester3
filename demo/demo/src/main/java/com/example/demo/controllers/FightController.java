package com.example.demo.controllers;

import com.example.demo.model.Fight;
import com.example.demo.model.Fighter;
import com.example.demo.service.FightService;
import com.example.demo.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="/api")

public class FightController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final FightService fightService;

    private final SequenceGeneratorService sequenceGeneratorService;

    public FightController(FightService fightService, SequenceGeneratorService sequenceGeneratorService) {
        this.fightService = fightService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @GetMapping("/fights")
    public ResponseEntity<List<Fight>> getAllFights() {
        LOG.info("Getting all fights.");
        return ResponseEntity.ok().body(fightService.getFights());
    }

    @GetMapping("/fight/id/{fightId}")
    public ResponseEntity<Fight> getFight(@PathVariable int fightId) {
        LOG.info("Getting fight with ID: {}.", fightId);
        return ResponseEntity.ok().body(fightService.getFightById(fightId));
    }
    @GetMapping("/fight/{title}")
    public ResponseEntity<List<Fight>> getFightsThatContain(@PathVariable String title) {
        LOG.info("Getting all fights with: {} in title.",title);
        return ResponseEntity.ok().body(fightService.getFightsByTitle(title));
    }
    @GetMapping("/fightId/{title}")
    public int getFightId(@PathVariable String title) {
        return fightService.getFightIdWithTitle(title);
    }
    @GetMapping("/fight-with-name/{name}/get-fight")
    public ResponseEntity<Fight> getFightWithName(@PathVariable String name) {
        LOG.info("Getting fight with name : {}", name);
        return ResponseEntity.ok().body(fightService.getFight(name));
    }

    @PostMapping("/fight/create")
    public ResponseEntity<Fight> addNewFights(@RequestBody Fight fight){
        LOG.info("Saving fight.");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/fight/create").toUriString());
        fight.setId((int) sequenceGeneratorService.generateSequence(Fight.SEQUENCE_NAME));
        return ResponseEntity.created(uri).body(fightService.saveFight(fight));
    }

    @PostMapping(value = "/fight/{fightId}/setImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Fight> setFightPicture(@PathVariable("fightId") int fightId, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.info("saving image for fight with id :{}", fightId);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/fight/{fightId}/setImage").toUriString());
        fightService.setPicture(fightId,file);
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/fight/{fightId}/image")
    public ResponseEntity<?> getFightPicture(@PathVariable("fightId") int fightId) {
        LOG.info("getting image for fight with id: {}", fightId);
        byte[] image = fightService.getPicture(fightId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
                .body(image);
    }

    @PostMapping("/fight/{fightId}/add-fighters/{fighter1Id}/{fighter2Id}")
    public ResponseEntity<Fight> addFighters(@PathVariable("fightId")int fightId, @PathVariable("fighter1Id")int fighter1Id, @PathVariable("fighter2Id")int fighter2Id) {
        LOG.info("Saving  fighter with id {} and fighter with id {} to fight with ID {}", fighter1Id,fighter2Id, fightId);
        return ResponseEntity.ok().body(fightService.addFighters(fightId,fighter1Id,fighter2Id));
    }
    @GetMapping("/fight/{fightId}/first-fighter")
    public ResponseEntity<Fighter> getFirstFighter(@PathVariable("fightId")int fightId) {
        LOG.info("Getting first fighter from fight with ID {}", fightId);
        return ResponseEntity.ok().body(fightService.getFirstFighter(fightId));
    }
    @GetMapping("/fight/{fightId}/second-fighter")
    public ResponseEntity<Fighter> getSecondFighter(@PathVariable("fightId")int fightId) {
        LOG.info("Getting second fighter from fight with ID {}", fightId);
        return ResponseEntity.ok().body(fightService.getSecondFighter(fightId));
    }
    @PostMapping("/fight/{fightId}/increment-views")
    public ResponseEntity<Fight> incrementViews(@PathVariable("fightId")int fightId) {
        LOG.info("Adding 1 view to fight with ID : {}", fightId);
        return ResponseEntity.ok().body(fightService.incrementViews(fightId));
    }

    @GetMapping("/fights/{name}")
    public ResponseEntity<List<Fight>> getFightsFromFighter(@PathVariable("name")String name) {
        LOG.info("Getting all fights from fighter : {}", name);
        return ResponseEntity.ok().body(fightService.getFightsFromFighter(name));
    }
}
