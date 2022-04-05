package com.cf.symmetry.controller;

import com.cf.symmetry.service.Entries;
import com.cf.symmetry.service.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SymmetryController {

    @Autowired
    private EvaluatorService evaluatorService;


    @PostMapping("/checkSymmetry")
    public ResponseEntity<String> evaluateSymmetry(@RequestBody Entries entries) {
        evaluatorService.setEntries(entries);
        String response = evaluatorService.getSymmetry();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
