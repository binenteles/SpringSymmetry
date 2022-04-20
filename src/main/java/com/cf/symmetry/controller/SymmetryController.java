package com.cf.symmetry.controller;

import com.cf.symmetry.service.RequestService;
import com.cf.symmetry.service.evaluation.EvalResponse;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SymmetryController {

    private EvaluatorService evaluatorService;

    @Autowired
    public SymmetryController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @PostMapping("/symmetry-status")
    public EvalResponse status(@Valid @RequestBody RequestService request) {
        return evaluatorService.evaluate(request);
    }

}


