package com.cf.symmetry.controller;

import com.cf.symmetry.service.EvalRequest;
import com.cf.symmetry.service.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class SymmetryController {

    @Autowired
    private EvaluatorService evaluatorService;

    @PostMapping("/symmetry-status")
    public ResponseEntity<String> status(@RequestBody EvalRequest evalRequest) {
        String insertedString = evalRequest.getStr();
        String method = evalRequest.getMethod();
        String response = String.format("String %s is not symmetric. Used method: %s", insertedString, method);
        boolean isInsertedStringSymmetric = evaluatorService.checkStrSymmetry(insertedString, method);

        if (isInsertedStringSymmetric) {
            response = response.replace("is not symmetric", "is symmetric");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


