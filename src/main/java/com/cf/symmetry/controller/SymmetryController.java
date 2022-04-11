package com.cf.symmetry.controller;

import com.cf.symmetry.EvalResponse;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SymmetryController {

    @Autowired
    private EvaluatorService evaluatorService;

    @PostMapping("/symmetry-status")
    public ResponseEntity<EvalResponse> status(@RequestBody EvalRequestDto evalRequestDto) {
        EvalResponse response = evaluatorService.checkStrSymmetry(evalRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


