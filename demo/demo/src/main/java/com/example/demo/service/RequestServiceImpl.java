package com.example.demo.service;

import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;
import com.example.demo.model.Request;
import com.example.demo.repo.CoachRepository;
import com.example.demo.repo.FighterRepository;
import com.example.demo.repo.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepo;

    private final CoachRepository coachRepo;

    private final FighterRepository fighterRepository;

    @Override
    public List<Request> getRequests() {
        return requestRepo.findAll();
    }

    @Override
    public List<Request> getRequestsByFighter(int id) {
        List<Request> temp = requestRepo.findAll();
        List<Request> requests = new ArrayList<>();
        for (Request r : temp) {
            if (r.getFighterId() == id) {
                requests.add(r);
            }
        }
        return requests;
    }

    @Override
    public List<Request> getRequestsByCoach(int id) {
        List<Request> temp = requestRepo.findAll();
        List<Request> requests = new ArrayList<>();
        for (Request r : temp) {
            if (r.getCoachId() == id) {
                requests.add(r);
            }
        }
        return requests;
    }

    @Override
    public Request saveRequest(Request request) {
        return requestRepo.save(request);
    }

    @Override
    public boolean deleteRequest(int id) {
        Request temp = requestRepo.findById(id);

        if (temp != null) {
            Coach coach = coachRepo.findById(temp.getCoachId());
            Fighter fighter = fighterRepository.findById(temp.getFighterId());
            coach.addFighter(fighter);
            fighter.setHasCoach(true);
            fighterRepository.save(fighter);
            coachRepo.save(coach);
            requestRepo.delete(temp);
            return true;
        }
        return false;
    }

    @Override
    public Request getRequest(int id) {
        return requestRepo.findById(id);
    }






}
