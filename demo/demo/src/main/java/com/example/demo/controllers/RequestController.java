package com.example.demo.controllers;


import com.example.demo.model.Request;
import com.example.demo.service.RequestService;
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
public class RequestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final SequenceGeneratorService sequenceGeneratorService;

    private final RequestService requestService;


    private RequestController(SequenceGeneratorService sequenceGeneratorService, RequestService requestService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.requestService = requestService;
    }


    @PostMapping("/request/create")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        LOG.info("saving request");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/request/create").toUriString());
        request.setId((int) sequenceGeneratorService.generateSequence(Request.SEQUENCE_NAME));
        return ResponseEntity.created(uri).body(requestService.saveRequest(request));
    }
    @PostMapping("/request/{id}/delete")
    public ResponseEntity<Boolean> deleteRequest(@PathVariable("id")int id) {
        LOG.info("deleting request with id : {}", id);
        return ResponseEntity.ok().body(requestService.deleteRequest(id));
    }
    @GetMapping("/requests")
    public ResponseEntity<List<Request>> getRequests() {
        LOG.info("Getting all requests");
        return ResponseEntity.ok().body(requestService.getRequests());
    }

    @GetMapping("/requests/from-fighter/{id}")
    public ResponseEntity<List<Request>> getRequestsFromFighter(@PathVariable("id")int id) {
        LOG.info("Getting all requests from fighter with id : {}", id);
        return ResponseEntity.ok().body(requestService.getRequestsByFighter(id));
    }
    @GetMapping("/requests/from-coach/{id}")
    public ResponseEntity<List<Request>> getRequestsFromCoach(@PathVariable("id")int id) {
        LOG.info("Getting all requests from coach with id : {}", id);
        return ResponseEntity.ok().body(requestService.getRequestsByCoach(id));
    }
    @GetMapping("/request/{id}")
    public ResponseEntity<Request> getRequest(@PathVariable("id")int id) {
        LOG.info("Getting request with id : {}", id);
        return ResponseEntity.ok().body(requestService.getRequest(id));
    }



}
