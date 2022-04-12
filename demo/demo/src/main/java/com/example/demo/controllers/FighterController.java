package com.example.demo.controllers;

import com.example.demo.model.Fighter;
import com.example.demo.service.FighterService;
import com.example.demo.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class FighterController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final FighterService fighterService;

    private final SequenceGeneratorService sequenceGeneratorService;

    public FighterController(FighterService fighterService, SequenceGeneratorService sequenceGeneratorService) {
        this.fighterService = fighterService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @GetMapping("/fighters")
    public ResponseEntity<List<Fighter>> getFighters() {
        LOG.info("Getting all fighters");
        return ResponseEntity.ok().body(fighterService.getFighters());
    }

    @PostMapping("/fighter/create")
    public ResponseEntity<Fighter> addFighter(@RequestBody Fighter fighter) {
        LOG.info("Saving fighter");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/fighter/create").toUriString());
        fighter.setId((int)sequenceGeneratorService.generateSequence(Fighter.SEQUENCE_NAME));
        return ResponseEntity.created(uri).body(fighterService.saveFighter(fighter));
    }

    @PostMapping("/fighter/{id}/update")
    public ResponseEntity<Fighter> updateFighter(@PathVariable int id, @PathParam("age")int age, @PathParam("height")String height, @PathParam("reach")String reach, @PathParam("bouts")int bouts, @PathParam("record")String record) {
        LOG.info("Updating fighter with id: {}", id);
        return ResponseEntity.ok().body(fighterService.updateFighter(id,age,height,reach,bouts,record));
    }

    @GetMapping("/fighter/{id}")
    public ResponseEntity<Fighter> getFighter(@PathVariable int id) {
        LOG.info("Getting fighter with ID: {}", id);
        return ResponseEntity.ok().body(fighterService.getFighterById(id));
    }

    @PostMapping(value = "/fighter/{fighterId}/setImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Fighter> setFighterPicture(@PathVariable("fighterId") int fighterId, @RequestParam MultipartFile file) throws IOException {
        LOG.info("saving image for fighter with id :{}", fighterId);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/fighter/{fighterId}/setImage").toUriString());
        fighterService.setPicture(fighterId,file);
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/fighter/{id}/has-coach")
    public ResponseEntity<Boolean> checkHasCoach(@PathVariable("id")int id) {
        LOG.info("Checking if fighter with id : {} has coach");
        Fighter fighter = fighterService.getFighterById(id);
        return ResponseEntity.ok().body(fighter.isHasCoach());
    }

    @DeleteMapping("/fighter/{id}")
    public ResponseEntity<Boolean> deleteFighter(@PathVariable("id")int id) {
        LOG.info("Deleting fighter with ID : {}",id);
        return ResponseEntity.ok().body(fighterService.deleteFighter(id));
    }
    @GetMapping("/fighter/{fighterId}/image")
    public ResponseEntity<?> getFighterPicture(@PathVariable("fighterId") int fighterId) {
        LOG.info("getting image for fighter with id: {}", fighterId);
        byte[] image = fighterService.getPicture(fighterId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
                .body(image);
    }

    @GetMapping("/get-fighter/{name}")
    public ResponseEntity<Fighter> getFighter(@PathVariable("name")String name) {
        LOG.info("Getting fighter with name: {}", name);
        return ResponseEntity.ok().body(fighterService.getFighterByName(name));
    }

}
