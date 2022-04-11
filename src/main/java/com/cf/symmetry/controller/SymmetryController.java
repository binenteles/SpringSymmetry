package com.cf.symmetry.controller;

import com.cf.symmetry.EvalResponse;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.exceptions.ApiRequestException;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<EvalResponse> status(@RequestBody EvalRequestDto evalRequestDto) {

        EvalResponse response = evaluatorService.provideResponse(evalRequestDto);

        if (evalRequestDto.getStr().isEmpty() || evalRequestDto.getStr() == null){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


