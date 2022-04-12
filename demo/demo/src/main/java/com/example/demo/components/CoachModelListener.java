package com.example.demo.components;

import com.example.demo.model.Coach;
import com.example.demo.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CoachModelListener extends AbstractMongoEventListener<Coach> {

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public CoachModelListener(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Coach> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId((int) sequenceGeneratorService.generateSequence(Coach.SEQUENCE_NAME));
        }
    }
}
