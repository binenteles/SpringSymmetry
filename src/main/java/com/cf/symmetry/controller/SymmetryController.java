package com.cf.symmetry.controller;

import com.cf.symmetry.service.evaluation.EvalResponse;
import com.cf.symmetry.entity.EvalRequest;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class SymmetryController {


    private EvaluatorService evaluatorService;

    @Autowired
    public SymmetryController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @PostMapping("/symmetry-status")
    public ResponseEntity<EvalResponse> status(@Valid @RequestBody EvalRequest evalRequest) {
        EvalResponse response = evaluatorService.provideResponse(evalRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


