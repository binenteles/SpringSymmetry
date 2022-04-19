package com.cf.symmetry;

import com.cf.symmetry.controller.SymmetryController;
import com.cf.symmetry.entity.Request;
import com.cf.symmetry.factory.MethodEvaluation;
import com.cf.symmetry.service.evaluation.SymmetryFactory;
import com.cf.symmetry.service.evaluation.EvalResponse;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SymmetryController.class)
@Import({EvaluatorService.class, SymmetryFactory.class, Request.class})
class SymmetryApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldAcceptCorrectEvaluation_WhenProvideAValidStringAndMethod() throws Exception {

        mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Request("([{}])", MethodEvaluation.WHILE)))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"String ([{}]) is symmetric. Method used: WHILE\"}"));
    }

    @Test
    void shouldAcceptCorrectEvaluation_WhenMethodIsNull() throws Exception {


        EvalResponse evalResponse = new EvalResponse("String ([{}]) is symmetric. Method used: FOR");
        String response = objectMapper.writeValueAsString(evalResponse);

        mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Request("([{}])",null)))).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }

    @Test
    void shouldAcceptBadRequest_WhenStringIsNull() throws Exception {

        mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Request(null, MethodEvaluation.REGEX)))).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":400,\"errors\":[\"Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX\"]}"));
    }



}
