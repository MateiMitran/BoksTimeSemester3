package com.example.demo.service;

import com.example.demo.model.Request;

import java.util.List;

public interface RequestService {

    List<Request> getRequests();
    List<Request> getRequestsByFighter(int id);
    List<Request> getRequestsByCoach(int id);
    Request saveRequest(Request request);
    boolean deleteRequest(int id);
    Request getRequest(int id);
}
