package com.example.demo.components;

import com.example.demo.model.Fight;
import com.example.demo.model.Fighter;
import com.example.demo.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class FighterModelListener extends AbstractMongoEventListener<Fighter> {

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public FighterModelListener(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Fighter> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId((int) sequenceGeneratorService.generateSequence(Fighter.SEQUENCE_NAME));
        }
    }
}
