package com.cf.symmetry.controller;

import com.cf.symmetry.service.EvalRequest;
import com.cf.symmetry.service.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private EvaluatorService evaluatorService;


    @PostMapping("/string")
    public ResponseEntity<String> postResponseSymmetry(@RequestBody EvalRequest evalRequest){

        boolean result = evaluatorService.checkEntries(evalRequest.getStr(), evalRequest.getMethod());

        if(result){
            if (evalRequest.getMethod().isEmpty()){
              return new ResponseEntity<>("String " + evalRequest.getStr() + " is symmetric. Used method: FOR", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("String " + evalRequest.getStr() + " is symmetric. Used method: " + evalRequest.getMethod(), HttpStatus.OK);
            }
        } else {
            if (evalRequest.getMethod().isEmpty()){
                return new ResponseEntity<>("String " + evalRequest.getStr() + " is not symmetric. Used method: FOR", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("String " + evalRequest.getStr() + " is not symmetric. Used method: " + evalRequest.getMethod(), HttpStatus.OK);
            }
        }

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Illegal argument " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
