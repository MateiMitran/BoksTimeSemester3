package com.example.demo.components;

import com.example.demo.model.Fight;
import com.example.demo.model.User;
import com.example.demo.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class FightModelListener extends AbstractMongoEventListener<Fight> {

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public FightModelListener(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Fight> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId((int) sequenceGeneratorService.generateSequence(Fight.SEQUENCE_NAME));
        }
    }
}
