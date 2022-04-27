package com.cf.symmetry;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cf.symmetry.controller.SymmetryController;
import com.cf.symmetry.dto.EvalRequest;
import com.cf.symmetry.factory.MethodEvaluation;
import com.cf.symmetry.service.evaluation.EvalResponse;
import com.cf.symmetry.service.evaluation.EvaluatorService;
import com.cf.symmetry.service.evaluation.SymmetryFactory;
import com.cf.symmetry.service.requirements.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SymmetryController.class)
@Import({EvaluatorService.class, SymmetryFactory.class, EvalRequest.class, Reader.class})
class SymmetryApplicationTests {

  @Autowired
  private MockMvc mockMvc;


  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void shouldReturnSymmetric_WhenInputAndEvaluationMethodAreInserted() throws Exception {

    mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                new EvalRequest("([{}])", MethodEvaluation.valueOf("WHILE")))))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"message\":\"String ([{}]) is symmetric. Method used: WHILE\"}"));
  }

  @Test
  void shouldReturnNotSymmetric_WhenInputAndEvaluationMethodAreInserted() throws Exception {

    mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                new EvalRequest("([{)])", MethodEvaluation.valueOf("REGEX")))))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"message\":\"String ([{)]) is not symmetric. Method used: REGEX\"}"));
  }

  @Test
  void shouldReturnSymmetric_WhenEvaluationMethodIsMissing() throws Exception {

    EvalResponse evalResponse = new EvalResponse("String ([{}]) is symmetric. Method used: FOR");
    String response = objectMapper.writeValueAsString(evalResponse);

    mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
            .content("{ \"str\": \"([{}])\" }")).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(response));
  }

  @Test
  void shouldReturnNotSymmetric_WhenEvaluationMethodIsMissing() throws Exception {

    EvalResponse evalResponse = new EvalResponse(
        "String ([({}]]) is not symmetric. Method used: FOR");
    String response = objectMapper.writeValueAsString(evalResponse);

    mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
            .content("{ \"str\": \"([({}]])\" }")).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(response));
  }

  @Test
  void shouldReturnError_WhenInputToEvaluateIsMissing() throws Exception {

    mockMvc.perform(post("/api/symmetry-status").contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                new EvalRequest(null, MethodEvaluation.valueOf("REGEX")))))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string(
            "{\"status\":400,\"errors\":[\"Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX\"]}"));
  }

  @Test
  void shouldReturnError_WhenEvaluationMethodIsNotCorrect() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/api/symmetry-status")
            .content("{\"str\" : \"[([{()}])]\", \"method\" : \"test\"}")
            .contentType(APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content()
            .string("Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX"));

  }

  @Test
  void shouldReturnError_WhenInputAndEvaluationMethodAreMissing() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/api/symmetry-status")
            .content("{}")
            .contentType(APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string(
            "{\"status\":400,\"errors\":[\"Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX\"]}"));

  }


}
