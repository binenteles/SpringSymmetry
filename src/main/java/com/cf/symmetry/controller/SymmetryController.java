package com.cf.symmetry.controller;

import com.cf.symmetry.service.EvalRequest;
import com.cf.symmetry.service.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SymmetryController {

    @Autowired
    private EvaluatorService evaluatorService;


    @PostMapping("/string")
    public ResponseEntity<String> postResponseSymmetry(@RequestBody EvalRequest evalRequest){

        boolean result = evaluatorService.checkEntries(evalRequest.getStr(), evalRequest.getMethod());
        String response = String.format("String %s is not symmetric. Used method: FOR", evalRequest.getStr());
        if (result){
            response = response.replace("is not symmetric", "is symmetric");
        }
        if (!evalRequest.getMethod().isEmpty()) {
            response = response.replace("FOR", evalRequest.getMethod());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
